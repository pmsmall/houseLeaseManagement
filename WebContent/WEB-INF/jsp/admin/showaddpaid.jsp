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
		<script type="text/javascript">
			var error = "${param.error}";
			if(error == "inserttopaid") {

				alert("租金信息添加成功！");
			}
		</script>
	</head>

	<body>
		<div>
			<div class="result-title">
				<h1>我要收租</h1>
			</div>
			<form id="houseForm" name="houseForm" action="/paid/showaddpaid.action" method=post>
				<div class="result-title">
					<div class="result-list">

					</div>
				</div>

				<div class="result-content">
					<table id=grid class="result-tab" style="width: 100%;">
						<tbody>
							<tr class="result_tab_head">
								<td>房屋id</td>
								<td>地址</td>
								<td>租金</td>
								<td>租赁人</td>
								<td>租赁人身份证号</td>
								<td>租赁人联系电话</td>
								<td>操作</td>

							</tr>
							<c:forEach items="${zulist}" var="zulist">

								<tr class="result_tab_body">
									<td>${zulist.house_id }</td>

									<td>${zulist.address}</td>

									<td>${zulist.price}</td>
									<td>${zulist.userlist.name}</td>
									<td>${zulist.userlist.idcard}</td>
									<td>${zulist.userlist.phone}</td>
									<td>
										<a class="link-update" href="/houseLeaseManagement/paid/addpaid.action?id=${zulist.zid }">收租</a>
										&nbsp;&nbsp;
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id=pagelink>
					<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">
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