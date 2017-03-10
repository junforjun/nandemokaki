<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%>
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

$(".tbtitle").hide
    $(document).ready(function(){
        $(".tbtitle").click(function(){
           alert()
        });
    });
</script>
<style>
    .tbtitle {cursor:pointer;}
    .table .hide{display:none;}
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
				<div class="navbar-brand">○ FIT INFORMATION SYSTEM</div>
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
					<li><a href="#">받은편지함(${mailcnt})</a></li>
					<li><a href="#">편지쓰기</a></li>
					<li><a href="#">로그인</a></li>
					<li><a href="#">서명</a></li>
					<li><a href="#">관리자메뉴</a></li>
				</ul>
			</div>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">받은편지함(${mailcnt})</h2>
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th style="width: 5%"><input type="checkbox" /></th>
								<th style="width: 10%">보낸사람</th>
								<th style="width: 50%">제목</th>
								<th style="width: 10%">발송시간</th>
								<th style="width: 10%">수신확인</th>
								<th style="width: 10%">수신확인시간</th>
							</tr>
						</thead>
							<c:forEach var="it" items="${mailList}" varStatus="status">

							<!-- 요기!!!!!!! -->

							<tbody class="cl">
								<tr>
									<td><input type="checkbox" /></td>
									<td><c:out value="${it.from}" /></td>
									<td><c:out value="${it.subject}" /></td>
									<td><c:out value="${it.date}" /></td>
									<td><c:out value="${it.isRead}" /></td>
									<td><c:out value="${it.readTime}" /></td>
								</tr>
								<tr class="hide" >
									<td></td>
									<td colspan="5" style="border-style: outset; border-bottom-style:none;" >CC : </td>
								</tr>
								<tr  class="hide">
									<td></td>
									<td colspan="5" style="border-left-style: outset; border-right-style: outset;">Attched file : </td>
								</tr>
								<tr  class="hide">
									<td></td>
									<td colspan="5" style="border-style: outset; border-top-style:none;">${it.message}</td>

								</tr>
							</tbody>
							</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>