<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>房屋租赁系统</title>
		<link rel="stylesheet" type="text/css" href="/houseLeaseManagement/css/common.css" />
		<link rel="stylesheet" type="text/css" href="/houseLeaseManagement/css/main.css" />
		<script type="text/javascript" src="/houseLeaseManagement/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/houseLeaseManagement/js/libs/modernizr.min.js"></script>
		<style type="text/css">

		</style>
		<script type="text/javascript">
			var error = "${param.error}";
			if(error == "deletesuccess") {

				alert("删除成功！");
			}
		</script>
	</head>

	<body>
		<div>
			<div class="result-title">
				<h1>用户列表</h1>
			</div>
			<form id="houseForm" name="houseForm" action="/findalluserlist.action" method=post>
				<div class="result-title">
					<div class="result-list"></div>
				</div>

				<div class="result-content">
					<table id=grid class="result-tab" style="width= 100%;">
						<tbody>
							<tr class="result_tab_head">
								<td>租客用户名</td>
								<td>租客姓名</td>
								<td>租客身份证号码</td>
								<td>租客联系电话</td>
								<td>操作</td>
							</tr>
							<c:forEach items="${userlist}" var="userlist">
								<tr class="result_tab_body">
									<td>${userlist.user.username}</td>
									<td>${userlist.name}</td>
									<td>${userlist.idcard}</td>
									<td>${userlist.phone}</td>
									<td>
										<a class="link-update" href="/houseLeaseManagement/deleteuserlist.action?id=${userlist.user.id}" onclick="return window.confirm('确定删除吗？')">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id=pagelink>
					<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top: 10px">
						共[
						<B>${p.total}</B>]条记录，共[
						<B>${p.pages}</B>]页 ,
						<c:if test="${ p.pageNum > 1 }">
							[
							<A href="javascript:to_page(${p.prePage})">前一页</A>]
						</c:if>
						<input type="hidden" name="page" id="page" value="" /> 第
						<B>${p.pageNum}</B>页

						<c:if test="${ p.pageNum < p.pages }">
							[
							<A href="javascript:to_page(${p.nextPage})">后一页</A>]
						</c:if>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript">
			// 提交分页的查询的表单
			function to_page(page) {
				if(page) {
					$("#page").val(page);
				}
				document.houseForm.submit();
			}
		</script>
	</body>

</html>