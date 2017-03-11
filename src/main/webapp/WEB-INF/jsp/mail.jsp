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
<script type="text/javascript" src="/Js/common.js"></script>
<script type="text/javascript" src="/Js/mail.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="/Js/tinymce/tinymce.min.js"></script>
<script>tinymce.init({ });

tinyMCE.init({
	 selector:'textarea',
    height : "480"
});

</script>

<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/login.css">

<style>
.title {
	cursor: pointer;
}

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
					<li><a href="#">받은편지함(${mailcnt})</a></li>
					<li><a href="#" data-target="#layerpop" data-toggle="modal">편지쓰기</a></li>
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
							<thead class="cl">
								<tr class="title" onclick="redirectDetail(${it.mailId})">
									<td><input type="checkbox" /></td>
									<td><c:out value="${it.from}" /></td>
									<td><c:out value="${it.subject}" /></td>
									<td><c:out value="${it.date}" /></td>
									<td><c:out value="${it.isRead}" /></td>
									<td><c:out value="${it.readTime}" /></td>
								</tr>
							</thead>

						</c:forEach>
					</table>


	<button class="btn btn-default" data-target="#layerpop" data-toggle="modal">모달출력버튼</button>
					<br />

					<div class="modal fade" id="layerpop">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- header -->
								<div class="modal-header">

									<button type="button" class="close" data-dismiss="modal">×</button>

									<!-- header title -->
									<h4 class="modal-title">To</h4>
									<input type="email" id="to" class="form-control" required autofocus >
									<h4 class="modal-title">CC</h4>
									<input type="email" id="cc" class="form-control" required >
									<h4 class="modal-title">BCC</h4>
									<input type="email" id="bcc" class="form-control" required >

									<h4 class="modal-title">Subject</h4>
									<input type="text" id="subject" class="form-control" required >

								</div>
								<!-- body -->
								<div class="modal-body">

								<textarea></textarea>

								</div>
								<!-- Footer -->
								<div class="modal-footer">

									<button type="button" class="btn btn-default" data-dismiss="modal" onclick="sendEmail()">SEND</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>


								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>








</body>
</html>