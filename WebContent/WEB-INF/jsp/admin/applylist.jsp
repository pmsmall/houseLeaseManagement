<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>房屋租赁系统</title>
		<link rel="stylesheet" type="text/css" href="/houseLeaseManagement/css/common.css" />
		<link rel="stylesheet" type="text/css" href="/houseLeaseManagement/css/main.css" />
		<script type="text/javascript" src="/houseLeaseManagement/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/houseLeaseManagement/js/libs/modernizr.min.js"></script>
	</head>

	<body>
		<div class="result-title">
			<h1>申请看房列表</h1>
		</div>

		<div>
			<form id="houseForm" name="houseForm" action="houselist.action" method=post>
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
								<td>面积</td>
								<td>价格</td>
								<td>申请人姓名</td>
								<td>申请人身份证号</td>
								<td>申请人电话号码</td>
								<td>操作</td>

							</tr>
							<c:forEach items="${applylist}" var="apply">
								<tr class="result_tab_body">
									<td>${apply.house_id }</td>

									<td>${apply.address}</td>
									<td>${apply.area}</td>
									<td>${apply.price}</td>
									<td>${apply.userlist.name}</td>
									<td>${apply.userlist.idcard}</td>
									<td>${apply.userlist.phone}</td>
									<td>
										<a class="link-update" href="/houseLeaseManagement/zulist/toaddhetong.action?house_id=${apply.house_id}">同意租赁</a>
										&nbsp;&nbsp;
										<input type="hidden" name="id" value="${houselist.id}" />
										<a class="link-del" href="/houseLeaseManagement/refuseapply.action?house_id=${apply.house_id}" onclick="return window.confirm('确定要拒绝该租客的申请吗？')">拒绝租赁</a>
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