<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="static/css/style.css" rel="stylesheet" type="text/css" />
<title>Barcode Generator</title>
</head>
<body>
	<div class="container-fluid">
		<div class="well">
			<h2 class="text-primary text-center">EAN13 Barcode Generator</h2>
		</div>
		<div class="row text-center">
			<h3 class="text-success text-center bg-info">Generated Bar code</h3>
			<br />
			<div class="col-md-4" id="easyPaginate">
				<c:forEach var="item" items="${imageSource}">
					<img alt="barcode" src="<c:out value='${item.value}'></c:out>"
						class="img img-responsive img-thumbnail"/>
					<br />
				</c:forEach>
			</div>
			<br />
		</div>
		<c:if test="${isImageMirrored =='yes' }">
		<hr />
			<div class="row text-center">
				<h3 class="text-danger text-center bg-info">Mirrored Bar code</h3>
				<br />
				<div class="col-md-4" id="easyPaginateRepeat">
					<c:forEach var="item" items="${imageSource}">

						<img alt="barcode" src="<c:out value='${item.value}'></c:out>"
						class="img img-responsive img-thumbnail" />
					<br />
					</c:forEach>
				</div>
				<br />
			</div>
		</c:if> 
	</div>

	<script src="static/js/jquery-2.0.0.js" type="text/javascript"></script>
	<script src="static/js/bootstrap.js" type="text/javascript"></script>
	<script src="static/js/jquery.easyPaginate.js" type="text/javascript"></script>
	<script type="text/javascript">
		 var number = ${barcodePerPage}; 
		$('#easyPaginate').easyPaginate({
			paginateElement : 'img',
			elementsPerPage : number,
			effect : 'climb'
		});
		$('#easyPaginateRepeat').easyPaginate({
			paginateElement : 'img',
			elementsPerPage : number,
			effect : 'climb'
		});
	</script>

</body>
</html>