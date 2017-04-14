<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员信息修改</title>
<style type="text/css">
body {
	width: 500px
}

tr {
	background-color: green
}

td {
	text-align: center;
}
</style>
</head>
<body>
	<form action="MembersInfoSer?tag=edit" method="post">
		<input type="hidden" value="${member.mid}" name="mid">
		<table>
			<tr>
				<th colspan="2">会员信息</th>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="mname" value="${member.mname }"></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><c:choose>
						<c:when test="${member.mgender.equals(\"男\") }">
							<input type="radio" name="mgender" value="男" checked="checked" />男
                        <input type="radio" name="mgender" value="女" />女
                    </c:when>
						<c:otherwise>
							<input type="radio" name="mgender" value="男" />男
                        <input type="radio" name="mgender" value="女"
								checked="checked" />女
                    </c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input type="text" name="mage" value="${member.mage }"></td>
			</tr>
			<tr>
				<td>家庭住址</td>
				<td><input type="text" name="maddress"
					value="${member.maddress }"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="memail" value="${member.memail }"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="修改" /> <input
					type="button" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>