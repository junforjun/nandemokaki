/**
 * index.js
 */
function loginChk() {
	jQuery.ajax({
		type : "POST",
		url : "http://localhost:8080/login/",
		data : {
			"username" : $("#username").val(),
			"password" : $("#password").val()
		},
		success : function(data) {

			if (data == "") {
				alert("Check ID or Password");
			} else {
				redirect("/mail/")
			}
		},
		complete : function(data) {
			// alert("complete");
		},
		error : function(xhr, status, error) {
			alert("server error" + status);
		}
	});

}