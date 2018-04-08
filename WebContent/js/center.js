// 设置title
function setUsernameLiContent(username) {
	$("#username_li").html("欢迎您," + username + " !");
}

// 提交分页的查询的表单
function to_page(url1) {
	$("#view").attr("src", "/houseLeaseManagement/centerHome.html");
	//	$.ajax({
	//		url: url1,
	//		type: "post",
	//		async: false,
	//
	//		success: function(data) {
	//			// 你的具体操作
	//			// alert(data);
	//			$("#inside #view").html(data);
	//			alert("成功");
	//
	//		},
	//		error: function() {
	//			alert("失败，请稍后再试！");
	//		}
	//	});
}
var baseUrl = "http://localhost:8080/houseLeaseManagement";

function responseHandle(data, successFuc) {
	if(data['status']) {
		successFuc(data);
	} else {
		// window.location.href = '/houseLeaseManagement/login';
		alert(data['status'] + '，' + data['username']);
	}
}

function mySubmit(sendData, dst, successFuc) {
	$.ajax({
		url: baseUrl + dst,
		type: "post",
		contentType: 'application/json;charset=UTF-8',
		data: sendData,
		dataType: "json",
		success: function(data) {
			responseHandle(data, successFuc);
		},
		error: function() {
			// alert("error");
		}
	});
}
$(document).ready(function() {
	username = $.cookie('username');
	if(username == null || username === 'null') {
		mySubmit(JSON.stringify({
			query: 'username'
		}), '/query', function(data) {
			$.cookie('username', data['username'], {
				expires: 1,
				path: '/'
			});
			setUsernameLiContent(data['username']);
		});
		$("#username_li").html("请稍后!");
	} else
		setUsernameLiContent(username);

	$(".drop > li > a").on("click", function() {
		var url = $(this).attr('href');
		//alert(url);
		$("#view").attr("src", url);
		return false;
	});
});