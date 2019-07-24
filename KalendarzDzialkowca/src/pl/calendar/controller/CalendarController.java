package pl.calendar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.calendar.model.Event;
import pl.calendar.service.EventService;


@WebServlet("/CALEN")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveEventsInRequest(request);
        request.getRequestDispatcher("WEB-INF/calendar.jsp").forward(request, response);
    }
 
    private void saveEventsInRequest(HttpServletRequest request) {
    	EventService eventService = new EventService();
		String login = request.getUserPrincipal().getName();
		List<Event> allEvents = eventService.getMyEvent(login);  //List<Event> allEvents = eventService.getMyEvent(login);  List<Event> allEvents = eventService.getAllEvents();
        request.setAttribute("events", allEvents);
    }
}