package pl.calendar.dao;
 
import java.util.List;

import pl.calendar.model.Event;
 
public interface EventDAO extends GenericDAO<Event, Long>{
 
    List<Event> getAll();
    List<Event> getAllEventByLogin(String login);
     
}