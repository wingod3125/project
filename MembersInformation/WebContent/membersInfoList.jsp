<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员信息列表</title>
<script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
<style type="text/css">
body {
	width: 500px;
}

h3 {
	background-color: green;
	text-align: center;
}

td {
	text-align: center;
}

p {
	text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("h3").css("background-color", "green").css("text-align", "center")
		$("tr:even").css("background-color", "yellow")
	})
</script>
</head>
<body>
	<h3>俱乐部会员信息</h3>
	<table>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>年龄</th>
			<th>家庭住址</th>
			<th>Email</th>
		</tr>
		<c:forEach items="${pu.list }" var="member">
			<tr>
				<td><a href="MembersInfoSer?tag=edit&mid=${member.mid }">${member.mid
						}</a></td>
				<td>${member.mname }</td>
				<td>${member.mgender }</td>
				<td>${member.mage }</td>
				<td>${member.maddress }</td>
				<td>${member.memail }</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<c:choose>
			<c:when test="${pu.pageIndex > 1 }">
				<a href="MembersInfoSer?pageIndex=${pu.pageIndex-1 }">上一页</a>
			</c:when>
			<c:otherwise>
				<a href="MembersInfoSer?pageIndex=1">上一页</a>
			</c:otherwise>
		</c:choose>
		&nbsp;&nbsp;
		<c:choose>
			<c:when test="${pu.pageIndex < pu.pageCount }">
				<a href="MembersInfoSer?pageIndex=${pu.pageIndex+1 }">下一页</a>
			</c:when>
			<c:otherwise>
				<a href="MembersInfoSer?pageIndex=${pu.pageCount }">下一页</a>
			</c:otherwise>
		</c:choose>
	</p>

</body>
</html>