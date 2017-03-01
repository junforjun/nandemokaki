<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Css Import -->
<link rel="stylesheet" href="/Css/regMember.css" type="text/css">

<!-- JavaScript Import -->
<script type="text/javascript" src="/Js/regMember.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>


<title>Insert title here</title>
</head>
<body>

	<!-- Header -->
	<div class="main H3" style="height: 50px;">
	Nandemokaki
		<!-- Login -->
		<div class="headerRight" style="left: 59%;bottom: 0px;width:152px;" > ID <input type="text" class="idBox"/></div>
		<div class="headerRight" style="left: 74%;bottom: 0px;width:157px;" > PASS <input type="password" class="idBox"/></div>
		<div class="headerRight" style="left: 90%;bottom: 0px;background-color: #FFF;" ><input type="submit" class="css3button" value="LOGIN"></input></div>
	</div>

	<hr width="1024px"></hr>

	<div class="main" style="">
		<div class="rel30">1</div>
		<div class="rel70">
			<div class="H3">Join Member</div>
				<div class="regbox">
					<div class="formbox">
						<font color="#FF3838">*</font> E-MAIL
					</div>
					<div style="position:absolute; left:32%;width: 300px">
						<input id="email" type="email" class="textbox" onkeyup="chkEmail(email.value)"/>
					</div>
					<div id = "emailChkBox" style="position:absolute; left:70%;width: 200px"> </div>

				</div>
				<br/>

				<div class="regbox">
					<div class="formbox">
						<font color="#FF3838">*</font> Password
					</div>
					<div style="position:absolute; left:32%;width: 300px">
						<input type="password" id = "pass" class="textbox"/>
					</div>
				</div>
				<p/>

				<div class="regbox">
					<div class="formbox">
						<font color="#FF3838">*</font> rePassword
					</div>
					<div style="position:absolute; left:32%;width: 300px">
						<input id="rePass" type="password" class="textbox" onkeyup="chkRePass(pass.value, rePass.value)" />
					</div>
					<div id = "passChkBox" style="position:absolute; left:70%;width:150px;"></div>

				</div>
				<p/>

				<div class="regbox">
					<div class="formbox">
						Sex
					</div>
					<div style="position:absolute; left:32%;width: 300px">
					<select name="sex">
						<option value="3" selected="selected">3rd gender</option>
						<option value="1">Male</option>
						<option value="2">Female</option>
					</select>
				</div>
				</div>
				<p>

				<div class="regbox">
					<div class="formbox">
						Date of birth
					</div>
					<div style="position:absolute; left:32%;width: 300px">
						Y <input type="text" size="4" class="textbox" style="width: 50px;"/>
						M <input type="text" size="2" class="textbox" style="width: 30px;"/>
						D <input type="text" size="2" class="textbox" style="width: 30px;"/>
					</div>
				</div>
				<p>

				<div class="regbox">
					<div class="formbox">
						Location (Free write)
					</div>
					<div style="position:absolute; left:32%;width: 300px">
						<input type="text" class="textbox"/>
					</div>
				</div>
				<p>

				<div class="regbox">
					<div class="formbox">
						thumbnail
					</div>
					<div style="position:absolute; left:32%;width: 300px">
						<input type="button" value="Open"/>
					</div>
				</div>
				<br>

				<div style="position:absolute; left:32%;width: 300px">
					<input type="button" class ="css3button" style="border: 7px solid #900000;" value="Join in" onclick="test()"/>
				</div>
		</div>
	</div>





</body>
</html>