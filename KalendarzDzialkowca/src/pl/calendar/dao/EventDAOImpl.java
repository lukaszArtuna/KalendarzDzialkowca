package pl.calendar.dao;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.calendar.model.Event;
import pl.calendar.model.User;
import pl.calendar.util.ConnectionProvider;
 
public class EventDAOImpl implements EventDAO {
 
		private static final String CREATE_EVENT = 
  	      "INSERT INTO event(name, description, url, user_id, date, date_end) "
  	      + "VALUES(:name, :description, :url, :user_id, :date, :date_end);";
  	    private static final String READ_ALL_EVENTS = 
  	      "SELECT user.user_id, username, email, is_active, password, event_id, name, description, url, date, date_end "
  	      + "FROM event LEFT JOIN user ON event.user_id=user.user_id;";
  	    private static final String READ_EVENT = 
  	        "SELECT user.user_id, username, email, is_active, password, event_id, name, description, url, date, date_end "
  	        + "FROM event LEFT JOIN user ON event.user_id=user.user_id WHERE event_id=:event_id;";
  	    private static final String UPDATE_EVENT = 
  	        "UPDATE event SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date, date_end=:date_end "
  	        + "WHERE event_id=:event_id;";
  	    private static final String DELETE_EVENT = 
  	        "DELETE FROM event"
  	        + " WHERE event_id=:event_id;";
  	    private static final String READ_ALL_EVENTS_BY_LOGIN = 
      	    	"SELECT user.user_id, username, email, is_active, password, event_id, name, description, url, date, date_end "
      	    	+ "FROM event LEFT JOIN user ON event.user_id=user.user_id WHERE user.username = :login ORDER BY date_end ASC;";
 
    private NamedParameterJdbcTemplate template;
     
    public EventDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
 
    @Override
    public Event create(Event event) {
        Event resultEvent = new Event(event);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", event.getName());
        paramMap.put("description", event.getDescription());
        paramMap.put("url", event.getUrl());
        paramMap.put("user_id", event.getUser().getId());
        paramMap.put("date", event.getTimestamp());
        paramMap.put("date_end", event.getDate_end());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_EVENT, paramSource, holder);
        if(update > 0) {
            resultEvent.setId((Long)holder.getKey());
        }
        return resultEvent;
    }
 
    @Override
    public Event read(Long primaryKey) {
        SqlParameterSource paramSource = new MapSqlParameterSource("event_id", primaryKey);
        Event event = template.queryForObject(READ_EVENT, paramSource, new EventRowMapper());
        return event;
    }
 
    @Override
    public boolean update(Event event) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("event_id", event.getId());
        paramMap.put("name", event.getName());
        paramMap.put("description", event.getDescription());
        paramMap.put("url", event.getUrl());
        paramMap.put("user_id", event.getUser().getId());
        paramMap.put("date", event.getTimestamp());
        paramMap.put("date_end", event.getDate_end());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_EVENT, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }
 
    @Override
    public boolean delete(Long eventId) {
        SqlParameterSource paramSource = new MapSqlParameterSource("event_id", eventId);
        int update = template.update(DELETE_EVENT, paramSource);

        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }
    
	@Override
	public List<Event> getAllEventByLogin(String login) {
		SqlParameterSource paramSource = new MapSqlParameterSource("login", login);
        List<Event> events = template.query(READ_ALL_EVENTS_BY_LOGIN, paramSource, new EventRowMapper());
        return events;
	}
 
    @Override
    public List<Event> getAll() {
        List<Event> events = template.query(READ_ALL_EVENTS, new EventRowMapper());
        return events;
    }
     
    private class EventRowMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet resultSet, int row) throws SQLException {
            Event event = new Event();
            event.setId(resultSet.getLong("event_id"));
            event.setName(resultSet.getString("name"));
            event.setDescription(resultSet.getString("description"));
            event.setUrl(resultSet.getString("url"));
            event.setTimestamp(resultSet.getTimestamp("date"));
            event.setDate_end(resultSet.getString("date_end"));
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            event.setUser(user);
            return event;
        }
    }
}