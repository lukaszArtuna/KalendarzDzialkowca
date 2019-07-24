package pl.calendar.service;

import java.util.List;

import pl.calendar.dao.DAOFactory;
import pl.calendar.dao.PlantDAO;
import pl.calendar.model.Plant;
import pl.calendar.model.User;

public class PlantService {
	
	
	public void addPlant(String name, String fertilization, String pruning, String spraying, User user) {
        Plant plant = createPlantObject(name, fertilization, pruning, spraying, user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        PlantDAO plantDao = factory.getPlantDAO();
        plantDao.create(plant);
    }
	
	
	private Plant createPlantObject(String name, String fertilization, String pruning, String spraying, User user) {
        Plant plant = new Plant();
        plant.setName(name);
        plant.setFertilization(fertilization);
        plant.setPruning(pruning);
        plant.setSpraying(spraying);
        plant.setUser(user);

        return plant;
    }
     
    public Plant getPlantById(long plantId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PlantDAO plantDao = factory.getPlantDAO();
        Plant plant = plantDao.read(plantId);
        return plant;
    }

    public boolean deletePlant( long plantId){
        DAOFactory factory = DAOFactory.getDAOFactory();
        PlantDAO plantDao = factory.getPlantDAO();
        boolean delete = plantDao.delete(plantId);

        return delete;
    }    
    
    public boolean updatePlant(Plant plant) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PlantDAO plantDao = factory.getPlantDAO();
        boolean result = plantDao.update(plant);
        return result;
    }
     
    public List<Plant> getAllPlants() {
        return getAllPlants(null);
    }
     
    public List<Plant> getAllPlants(Plant plant) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PlantDAO plantDao = factory.getPlantDAO();
        List<Plant> plants = plantDao.getAll();
        
        return plants;
    }

    
    public List<Plant> getMyPlant(String login) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PlantDAO plantDao = factory.getPlantDAO();
        List<Plant> plants = plantDao.getAllPlantByLogin(login);

        return plants;
    }

}
