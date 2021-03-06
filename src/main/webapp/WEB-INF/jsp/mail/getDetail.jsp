<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FIS Mail Server</title>
<!-- bootstrap -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/Js/mail.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/login.css">


<script>

</script>
<style>

</style>

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="navbar-brand">○ Funble Studio Mail</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Logout</a></li>
					<li><a href="#"></a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>



	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a>편지함<span class="sr-only">(current)</span></a></li>
					<li><a href="/mail">받은편지함(${mailcnt})</a></li>
					<li><a href="#">편지쓰기</a></li>
					<li><a href="#">서명</a></li>
					<li><a href="#">관리자메뉴</a></li>
				</ul>
			</div>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="table-responsive">
					<table class="table ">
					<tr><td>Subject : <c:out value="${mailDetail.subject}" /></td></tr>
					<tr><td>From : <c:out value="${mailDetail.from}" /></td></tr>
					<tr><td>CC : <c:out value="${mailDetail.cc}" /></td></tr>
					<tr><td>Attched file :</td></tr>
					<tr><td><div>${mailDetail.message}</div></td></tr>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>