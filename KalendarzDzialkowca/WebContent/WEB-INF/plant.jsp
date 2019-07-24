<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Kalendarz działkowca - Dodaj roślinę</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>
 
  <body>
     
    <jsp:include page="fragment/navbar.jspf" />
     
    <div class="container">
    <div class="col col-md-8 col-sm-10">
	<div class="jumbotron">
	
	<div class="col-xs-6 col-sm-3 col-md-4 col-lg-5">
	<a href="${pageContext.request.contextPath}/ADD" class="btn btn-md btn-primary" role="button">Dodaj wydarzenie niestandardowe</a>
	</div>
	<div class="col-xs-6 col-sm-3 col-md-4 col-lg-5">
	<a href="${pageContext.request.contextPath}/AddPlantController" class="btn btn-md btn-primary" role="button">Dodaj własną roślinę</a>
	
	</div>
    </div>
     
    <c:if test="${not empty requestScope.plants}">
        <c:forEach var="plant" items="${requestScope.plants}">
            <div class="container">
              <div class="row bs-callout bs-callout-primary">
                 <div class="col col-md-8 col-sm-10">
                 
                 
                 
                  <h3 class="centered"><c:out value="${plant.name}" /></h3>
	              
	              <!-- <div> ${plant} </div> -->    
	                 
	                 <div class="row">
					  <div class="col-xs-6 col-sm-3 col-md-4 col-lg-3">
							   
			                <form class="form-group" method="post" action="ADD">
			                <input type="hidden" name="inputName" type="text" class="form-control" value="${plant.name}"  />                    
			                <input type="hidden" name="inputDate2" type="date" class="form-control" value="${plant.fertilization}"  />
			                <input type="hidden" name="inputDescription" Type="text" class="form-control" value="Nawożenie" />
			                <input class="btn btn-sm btn-primary" type="submit"  value="Dodaj Nawożenie" >
			            	</form>
					  </div>
					  
					  <div class="col-xs-6 col-sm-3 col-md-4 col-lg-3">
					  		
							<form class="form-group" method="post" action="ADD">
			                <input type="hidden" name="inputName" type="text" class="form-control" value="${plant.name}" autofocus />                    
			                <input type="hidden" name="inputDate2" type="date" class="form-control" value="${plant.pruning}" autofocus />
			                <input type="hidden" name="inputDescription" Type="text" class="form-control" value="Przycinanie" />
			                <input class="btn btn-sm btn-primary" type="submit"  value="Dodaj Przycinanie" >
			            	</form>
	            		</div>
	            	
					  <div class="col-xs-6 col-sm-3 col-md-4 col-lg-3">
					  
				            <form class="form-group" method="post" action="ADD">
			                <input type="hidden" name="inputName" type="text" class="form-control" value="${plant.name}" autofocus />                    
			                <input type="hidden" name="inputDate2" type="date" class="form-control" value="${plant.spraying}" autofocus />
			                <input type="hidden" name="inputDescription" Type="text" class="form-control" value="Oprysk" />
			                <input class="btn btn-sm btn-primary" type="submit"  value="Dodaj Oprysk" >
			            	</form>
					  </div>

		                
		                  
				<a href="${pageContext.request.contextPath}/PlantRemController?plant_id=${plant.id}" class="btn btn-sm "><span class="glyphicon glyphicon-trash"></span>  Usuń </a>	
				</div>
                </div>
              </div>
            </div>
        </c:forEach> 
    </c:if>
    </div>
    </div>
    

     
     
     
     
    <jsp:include page="fragment/footer.jspf" />
     
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>