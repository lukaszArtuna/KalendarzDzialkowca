<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Kalendarz Działkowicza - Kalendarz</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>
 
  <body>
     
    <jsp:include page="fragment/navbar.jspf" />
    
    <c:if test="${ empty requestScope.events}">
            <div class="container">
              <div class="jumbotron">
					<h1>Tutaj będą widnieć twoje zaplanowane zadania</h1>
              </div>
            </div>
      
    </c:if>
     
    <c:if test="${not empty requestScope.events}">
        <c:forEach var="event" items="${requestScope.events}">
            <div class="container">
              <div class="row bs-callout bs-callout-primary">
                 <div class="col col-md-8 col-sm-10">
                <!-- <div> ${event} </div> --> 
                  <h3 class="centered"><c:out value="${event.name}" /></h3>
                  <h6><small>Dodane przez: <c:out value="${event.user.username}" />, 
                  Dnia: <fmt:formatDate value="${event.timestamp}"/></small></h6>
                  <h6><small><b> Termin: <c:out value="${event.date_end}" /></b></small></h6>
                  <p><c:out value="${event.description}" /></p>
                  <a href="<c:out value="${event.url}" />" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-globe"></span>  Przejdź do strony </a>
                  <a href="${pageContext.request.contextPath}/EventRemController?event_id=${event.id}" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-trash"></span>  Usuń </a>
                </div>
              </div>
            </div>
        </c:forEach> 
    </c:if>
     
    <jsp:include page="fragment/footer.jspf" />
     
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>