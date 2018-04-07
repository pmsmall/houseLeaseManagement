<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>房屋租赁系统</title>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" href="<%=path%>/css/main_action.css">
		<link rel="stylesheet" href="<%=path%>/css/main_action_style.css">
		<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/libs/modernizr.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/my_action.js"></script>
		<style>
			a:hover {
				cursor: pointer;
			}
		</style>
		<script type="text/javascript">
			// 提交分页的查询的表单
			function to_page(url1) {
				// 我理解的你说的去括号中取值

				$.ajax({
					url: url1,
					type: "post",
					async: false,

					success: function(data) {
						// 你的具体操作
						// alert(data);
						$("#inside").html(data);
						alert("成功");

					},
					error: function() {
						alert("失败，请稍后再试！");
					}
				});
			}
		</script>
	</head>

	<body>
		<div class="topbar-wrap white">
			<div class="topbar-inner clearfix">
				<div class="topbar-logo-wrap clearfix">
					<h1 class="topbar-logo none">
					<a href="index.html" class="navbar-brand">后台管理</a>
				</h1>
					<ul class="navbar-list clearfix">
						<li>
							<a class="on sendRequest" href="javascript::void(0)" onclick="to_page('toindex.action')">首页</a>
						</li>

					</ul>
				</div>
				<div class="top-info-wrap">
					<ul class="top-info-list clearfix">
						<li>欢迎您，${sessionScope.user.username}！</li>

						<li>
							<a href="javascript:if(confirm('确实要退出登录吗?'))location='<%=path%>/logout.action'">退出</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="container clearfix">
			<div id="container">
				<div id="dock outer">
					<ul class="main">
						<li onmouseleave="nodisplayli(event)">
							<ul class="drop menu1 top_menu">
								<li>
									<a href="<%=path%>/ahouselist.action">房源列表</a>
								</li>
								<li>
									<a href="<%=path%>/toaddhouse.action">添加房源</a>
								</li>
							</ul> <span class="top_menu">房源信息</span>
							<a href="#"><img src="<%=path%>/images/house.png" onmouseenter='displayli(event)' />
							</a>
						</li>
						<li onmouseleave="nodisplayli(event)">
							<ul class="drop menu2 top_menu">
								<li>
									<a href="<%=path%>/zulist/findzulist.action">在租列表</a>
								</li>
								<li>
									<a href="<%=path%>/checkout/getallcheckout.action">已退租列表</a>
								</li>
							</ul> <span class="top_menu">租赁信息</span>
							<a href="#"><img src="<%=path%>/images/my_lease.png" onmouseenter='displayli(event)' /> </a>
						</li>
						<li onmouseleave="nodisplayli(event)">
							<ul class="drop menu3 top_menu">
								<li>
									<a href="<%=path%>/findapplylist.action">看房申请</a>
								</li>
								<li>
									<a href="<%=path%>/applyout/findallapplyout.action">退租申请</a>
								</li>
							</ul> <span class="top_menu">申请列表</span>
							<a href="#"><img src="<%=path%>/images/person.png" onmouseenter='displayli(event)' /></a>
						</li>
						<li onmouseleave="nodisplayli(event)">
							<ul class="drop menu4 top_menu">
								<li>
									<a href="<%=path%>/paid/showaddpaid.action">我要收租</a>
								</li>
								<li>
									<a href="<%=path%>/paid/topaidlist.action">租客待缴租金</a>
								</li>
								<li>
									<a href="<%=path%>/paid/selectall.action">租客已缴租金</a>
								</li>
							</ul> <span class="top_menu">租金信息</span>
							<a href="#"><img src="<%=path%>/images/money_info.png" onmouseenter='displayli(event)' /></a>
						</li>
						<li onmouseleave="nodisplayli(event)">
							<ul class="drop menu5 top_menu">
								<li>
									<a href="<%=path%>/wrong/wronglist.action">待处理报障</a>
								</li>
								<li>
									<a href="<%=path%>/wrong/selectall.action">已处理报障</a>
								</li>
							</ul> <span class="top_menu">报故模块</span>
							<a href="#"><img src="<%=path%>/images/errors.png" onmouseenter='displayli(event)' /></a>
						</li>
						<li onmouseleave="nodisplayli(event)">
							<ul class="drop menu6 top_menu">
								<li>
									<a href="<%=path%>/schedule/selectAll.action">查看日程</a>
								</li>
								<li>
									<a href="<%=path%>/schedule/toinsert.action">添加日程</a>
								</li>
							</ul> <span class="top_menu">我的日程</span>
							<a href="#"><img src="<%=path%>/images/schedule.png" onmouseenter='displayli(event)' /></a>
						</li>
						<li onmouseleave="nodisplayli(event)">
							<ul class="drop menu3 top_menu">
								<li>
									<a href="<%=path%>/findalluserlist.action">账户管理</a>
								</li>
							</ul> <span class="top_menu">其他操作</span>
							<a href="#"><img src="<%=path%>/images/others.png" onmouseenter='displayli(event)' /></a>
						</li>
					</ul>
				</div>
			</div>
			<!--/sidebar-->
			<div class="main-wrap">
				<div class="crumb-wrap">
					<div class="crumb-list">
						<i class="icon-font">&#xe06b;</i><span>欢迎使用本系统！</span>
					</div>
				</div>

				<div class="result-wrap" id="inside">
					<jsp:include page="${mainPage==null?'../admin/index.jsp':mainPage}"></jsp:include>
				</div>
			</div>

		</div>
		<iframe class="main_background" src="<%=path%>/main_background.html" seamless></iframe>
		<!--/main-->
	</body>

</html>