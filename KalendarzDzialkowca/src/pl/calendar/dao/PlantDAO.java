package pl.calendar.dao;

import java.util.List;

import pl.calendar.model.Plant;

public interface PlantDAO extends GenericDAO<Plant, Long>{
	List<Plant> getAll();

	List<Plant> getAllPlantByLogin(String login);

}


