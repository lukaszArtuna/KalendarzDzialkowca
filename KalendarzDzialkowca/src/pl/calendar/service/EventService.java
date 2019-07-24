package pl.calendar.service;
 
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import pl.calendar.dao.DAOFactory;
import pl.calendar.dao.EventDAO;
import pl.calendar.model.Event;
import pl.calendar.model.User;
 
public class EventService {
    public void addEvent(String name, String desc, String url, User user, String date_end) {
        Event event = createEventObject(name, desc, url, user, date_end);
        DAOFactory factory = DAOFactory.getDAOFactory();
        EventDAO eventDao = factory.getEventDAO();
        eventDao.create(event);
    }
     
    private Event createEventObject(String name, String desc, String url, User user, String date_end) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(desc);
        event.setUrl(url);
        User userCopy = new User(user);
        event.setUser(userCopy);
        event.setTimestamp(new Timestamp(new Date().getTime()));
        event.setDate_end(date_end);
        return event;
    }
     
    public Event getEventById(long eventId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EventDAO eventDao = factory.getEventDAO();
        Event event = eventDao.read(eventId);
        return event;
    }

    public boolean deleteEvent( long eventId){
        DAOFactory factory = DAOFactory.getDAOFactory();
        EventDAO eventDao = factory.getEventDAO();
        boolean delete = eventDao.delete(eventId);

        return delete;
    }    
    
    public boolean updateEvent(Event event) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EventDAO eventDao = factory.getEventDAO();
        boolean result = eventDao.update(event);
        return result;
    }
     
    public List<Event> getAllEvents() {
        return getAllEvents(null);
    }
     
    public List<Event> getAllEvents(Event event) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EventDAO eventDao = factory.getEventDAO();
        List<Event> events = eventDao.getAll();
        
        return events;
    }
    public List<Event> getMyEvent(String login) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EventDAO eventDao = factory.getEventDAO();
        List<Event> events = eventDao.getAllEventByLogin(login);

        return events;
    }
}