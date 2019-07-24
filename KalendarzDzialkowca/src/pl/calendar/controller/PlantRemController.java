package pl.calendar.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.calendar.service.PlantService;


@WebServlet("/PlantRemController")
public class PlantRemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    PlantService plantService = new PlantService();
	    String temporaryPlant_id = request.getParameter("plant_id");
	    long plant_id = Long.valueOf(temporaryPlant_id);
	    if (plant_id>3) {
	    	plantService.deletePlant(plant_id);
	    }
	    //plantService.deletePlant(Long.valueOf(plant_id));
	    
	
	    
	    response.sendRedirect(request.getContextPath() + "/PLANT");
	  }
	}
