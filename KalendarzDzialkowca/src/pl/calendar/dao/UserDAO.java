package pl.calendar.dao;
 
import java.util.List;

import pl.calendar.model.User;
 
 
public interface UserDAO extends GenericDAO<User, Long> {
 
    List<User> getAll();
    User getUserByUsername(String username);
     
}