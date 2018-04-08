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
		<script type="text/javascript" src="/houseLeaseManagement/js/libs/modernizr.min.js"></script>
		<script type="text/javascript" src="/houseLeaseManagement/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/houseLeaseManagement/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="/houseLeaseManagement/js/jquery.validate.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/houseLeaseManagement/css/jquery-ui.css" />
		<style type="text/css">
			.sum {
				float: right;
			}
		</style>
		<script type="text/javascript">
			$().ready(function() {
				// 在键盘按下并释放及提交后验证提交表单
				$("#fromdate").datepicker();
				$("#todate").datepicker();
			});
		</script>

	</head>

	<body>
		<div>
			<div class="result-title">
				<h1>待缴租金列表</h1>
			</div>
			<form action="/paid/mytopaidlist.action" method="post" name="myform">

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

								<td>应缴租金</td>
								<td>租金应缴日期</td>

								<td>租客姓名</td>

								<td>状态</td>
								<td>操作</td>

							</tr>
							<c:forEach items="${topaid}" var="topaid">
								<tr class="result_tab_body">
									<td>${topaid.house_id }</td>

									<td>${topaid.address}</td>

									<td>${topaid.price}</td>
									<td>${topaid.date}</td>

									<td>${topaid.name}</td>
									<td>${topaid.status}</td>

									<td>
										<a class="link-update" href="/houseLeaseManagement/paid/gotopay.action?id=${topaid.id }" onclick="return window.confirm('确定要支付吗?')">支付租金</a>
										&nbsp;&nbsp; </td>

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
				document.myform.submit();
			}
		</script>
	</body>

</html>