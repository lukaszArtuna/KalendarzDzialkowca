package pl.calendar.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.calendar.service.EventService;

@WebServlet("/EventRemController")
public class EventRemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EventService eventService = new EventService();
		String event_id = request.getParameter("event_id");
		eventService.deleteEvent(Long.valueOf(event_id));

		response.sendRedirect(request.getContextPath() + "/CALEN");
	}
}
