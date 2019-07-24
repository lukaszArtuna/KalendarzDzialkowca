package pl.calendar.dao;
 
public class MysqlDAOFactory extends DAOFactory {
 
    @Override
    public EventDAO getEventDAO() {
        return new EventDAOImpl();
    }
 
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
    
    @Override
    public PlantDAO getPlantDAO() {
        return new PlantDAOImpl();
    }
 

 
}