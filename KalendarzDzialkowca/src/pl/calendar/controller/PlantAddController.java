package pl.calendar.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.calendar.model.User;
import pl.calendar.service.PlantService;

@WebServlet("/AddPlantController")
public class PlantAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getUserPrincipal() != null) {
			request.getRequestDispatcher("WEB-INF/addplant.jsp").forward(request, response);
		} else {
			response.sendError(403);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("inputName");
		String fertilization = request.getParameter("inputFertilization");
		String pruning = request.getParameter("inputPruning");
		String spraying = request.getParameter("inputSpraying");
		User authenticatedUser = (User) request.getSession().getAttribute("user");
		fertilization = fertilization.substring(4);
		pruning = pruning.substring(4);
		spraying = spraying.substring(4);
		if (request.getUserPrincipal() != null) {
			PlantService plantService = new PlantService();
			plantService.addPlant(name, fertilization, pruning, spraying, authenticatedUser);
			response.sendRedirect(request.getContextPath() + "/PLANT");
		} else {
			response.sendError(403);
		}

	}

}
