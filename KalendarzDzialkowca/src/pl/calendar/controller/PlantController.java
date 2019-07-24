package pl.calendar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.calendar.model.Plant;
import pl.calendar.service.PlantService;



@WebServlet("/PLANT")
public class PlantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        savePlantsInRequest(request);
        request.getRequestDispatcher("WEB-INF/plant.jsp").forward(request, response);
    }
 
    private void savePlantsInRequest(HttpServletRequest request) {
    	PlantService plantService = new PlantService();
    	String login = request.getUserPrincipal().getName();
        List<Plant> allPlants = plantService.getMyPlant(login);
        request.setAttribute("plants", allPlants);
    }
    


}


