<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Kalendarz działkowca - Dodaj</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>
 
  <body>
     
    <jsp:include page="fragment/navbar.jspf" />
     
	 <div class="container"   >
	      <div class="jumbotron">
	        <div class="col-md-8 col-md-offset-2">
	            <form class="form-signin" method="post" action="ADD">
	                <h2 class="form-signin-heading">Dodaj zadanie</h2>
	                <input name="inputName" type="text" class="form-control" placeholder="Rodzaj czynności"
	                    required autofocus />                    
	                <input name="inputDate" type="date" class="form-control" placeholder="Data w formacie RRRR-MM-DD"  maxlength="10"
	                    required autofocus />
	                <input name="inputUrl" type="url" class="form-control" placeholder="Adres sklepu internetowego http://adres.pl"
	                     autofocus /> 
	                <textarea name="inputDescription" rows="5" name="inputUsername"
	                    class="form-control" placeholder="Opis czynności"  autofocus></textarea>
	                <input class="btn btn-lg btn-primary btn-block" type="submit"
	                    value="Dodaj!" />
	            </form>
        	</div>
	          
	      </div>
    </div>
     
    <jsp:include page="fragment/footer.jspf" />
     
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>