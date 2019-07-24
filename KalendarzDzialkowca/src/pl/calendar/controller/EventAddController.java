package pl.calendar.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.calendar.model.User;
import pl.calendar.service.EventService;

@WebServlet("/ADD")
public class EventAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        if(request.getUserPrincipal() != null) {
            request.getRequestDispatcher("WEB-INF/new.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    } 
    
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    		request.setCharacterEncoding("UTF-8");
    	    String name = request.getParameter("inputName");
    	    String description = request.getParameter("inputDescription");
    	    String url = request.getParameter("inputUrl");
    	    String date_end = request.getParameter("inputDate");
    	    String vdate_end = request.getParameter("inputDate2");    	
    	    if (date_end == null) {
    	    	String year = new SimpleDateFormat("yyyy").format(System.currentTimeMillis());
    	    	date_end = year + vdate_end;
    	    } 
    	    User authenticatedUser = (User) request.getSession().getAttribute("user");
    	    if(request.getUserPrincipal() != null) {
    	        EventService eventService = new EventService();
    	        eventService.addEvent(name, description, url, authenticatedUser, date_end);
    	        response.sendRedirect(request.getContextPath() + "/CALEN");
    	    } else {
    	        response.sendError(403);
    	    }
    	}

    
}