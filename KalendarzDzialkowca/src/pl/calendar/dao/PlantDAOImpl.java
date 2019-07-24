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

import pl.calendar.model.Plant;
import pl.calendar.model.User;
import pl.calendar.util.ConnectionProvider;

public class PlantDAOImpl implements PlantDAO{
	
	
			private static final String CREATE_PLANT = 
	  	      "INSERT INTO plant_management(name, fertilization, pruning, spraying, user_id) "
	  	      + "VALUES(:name, :fertilization, :pruning, :spraying, :user_id);";
		  	private static final String READ_ALL_PLANTS = 
	  	      "SELECT name, fertilization, pruning, spraying "
	  	      + "FROM plant_management ";
	  	    private static final String READ_PLANT = 
	  	  	  "SELECT user.user_id, username, email, is_active, password, plant_id, name, fertilization, pruning, spraying, "
	  	  	  + "FROM plant_management LEFT JOIN user ON plant.user_id=user.user_id WHERE plant_id=:plant_id;";
	  	    
	  	  private static final String READ_ALL_PLANTS_BY_LOGIN =
	  			"SELECT user.user_id, username, email, is_active, password, plant_id, name, fertilization, pruning, spraying "
	  	      	    	+ "FROM plant_management LEFT JOIN user ON plant_management.user_id=user.user_id WHERE user.username = :login OR user.username='admin';";
	  	    

/*	  	    
	  	    
		  	private static final String READ_ALL_PLANTS_BY_LOGIN =
		  	"SELECT plant_id, name, fertilization, pruning, spraying  "
	  	      + "FROM plant_management LEFT JOIN user ON plant_management.user_id=user.user_id WHERE user.username = :login OR plant_management.user_id='0';";
		  	
		  	SELECT plant_id, name, fertilization, pruning, spraying, user.user_id FROM plant_management LEFT JOIN user ON plant_management.user_id=user.user_id WHERE user.username = 'damian' OR plant_management.user_id='1';
		  	
		  	
		  	"SELECT user.user_id, username, email, is_active, password, plant_id, name, fertilization, pruning, spraying "
      	    	+ "FROM plant_management LEFT JOIN user ON plant_management.user_id=user.user_id WHERE user.username = :login ORDER BY date_end ASC;";
		  	
		  	*/
		  	
	  	    private static final String UPDATE_PLANT = 
	  	  	 "UPDATE event SET name=:name, fertilization=:fertilization, pruning=:pruning, spraying=:spraying, user_id=:user_id, "
	  	  	  + "WHERE plant_id=:plant_id;";
	  	    private static final String DELETE_PLANT = 
	  	  	  "DELETE FROM plant_management"
	  	  	  + " WHERE plant_id=:plant_id;";
		  	
		  	
		  	private NamedParameterJdbcTemplate template;
		     
		    public PlantDAOImpl() {
		        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
		    }
 
		    @Override
		    public Plant create(Plant plant) {
		        Plant resultPlant = new Plant(plant);
		        KeyHolder holder = new GeneratedKeyHolder();
		        Map<String, Object> paramMap = new HashMap<String, Object>();
		        paramMap.put("name", plant.getName());
		        paramMap.put("fertilization", plant.getFertilization());
		        paramMap.put("pruning", plant.getPruning());
		        paramMap.put("spraying", plant.getSpraying());
		        paramMap.put("user_id", plant.getUser().getId());
		        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		        int update = template.update(CREATE_PLANT, paramSource, holder);
		        if(update > 0) {
		            resultPlant.setId((Long)holder.getKey());
		        }
		        return resultPlant;
		    }
		    
		    
		    @Override
		    public boolean update(Plant plant) {
		        boolean result = false;
		        Map<String, Object> paramMap = new HashMap<String, Object>();
		        paramMap.put("name", plant.getName());
		        paramMap.put("fertilization", plant.getFertilization());
		        paramMap.put("pruning", plant.getPruning());
		        paramMap.put("spraying", plant.getSpraying());
		        paramMap.put("user_id", plant.getUser().getId());
		        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		        int update = template.update(UPDATE_PLANT, paramSource);
		        if(update > 0) {
		            result = true;
		        }
		        return result;
		    }
		    
		    
		    
		    @Override
		    public List<Plant> getAll() {
		        List<Plant> plants = template.query(READ_ALL_PLANTS, new PlantRowMapper());
		        return plants;
		    }
		    
		    
		    private class PlantRowMapper implements RowMapper<Plant> {
		        @Override
		        public Plant mapRow(ResultSet resultSet, int row) throws SQLException {
		            Plant plant = new Plant();

		            plant.setName(resultSet.getString("name"));
		            plant.setFertilization(resultSet.getString("fertilization"));
		            plant.setPruning(resultSet.getString("pruning"));
		            plant.setSpraying(resultSet.getString("spraying"));
		            plant.setId(resultSet.getLong("plant_id"));
		            User user = new User();
		            user.setId(resultSet.getLong("user_id"));
		            user.setUsername(resultSet.getString("username"));
		            user.setEmail(resultSet.getString("email"));
		            user.setPassword(resultSet.getString("password"));
		            plant.setUser(user);
		            return plant;
		        	}
		    }

		    @Override
		    public boolean delete(Long plantId) {
		        SqlParameterSource paramSource = new MapSqlParameterSource("plant_id", plantId);
		        int update = template.update(DELETE_PLANT, paramSource);

		        if (update > 0) {
		            return true;
		        } else {
		            return false;
		        }
		    }




		    @Override
		    public Plant read(Long primaryKey) {
		        SqlParameterSource paramSource = new MapSqlParameterSource("plant_id", primaryKey);
		        Plant plant = template.queryForObject(READ_PLANT, paramSource, new PlantRowMapper());
		        return plant;
		    }


			@Override
			public List<Plant> getAllPlantByLogin(String login) {
				SqlParameterSource paramSource = new MapSqlParameterSource("login", login);
		        List<Plant> plants = template.query(READ_ALL_PLANTS_BY_LOGIN, paramSource, new PlantRowMapper());
		        return plants;
			}
}
