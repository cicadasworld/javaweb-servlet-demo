<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hujin
  Date: 2021/11/7
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改账号信息</title>
</head>
<body>
<h1>修改账号信息</h1>
<div style="color: red">${errorMessage}</div>
<form action="${pageContext.request.contextPath}/account" method="post">
    <input type="hidden" name="method" value="updateAccount">
    <input type="hidden" name="id" value="${account.id}">
    账号名: <input type="text" name="name" value="${account.name}" autocomplete="off"><br>
    账号余额: <input type="text" name="balance" value="${account.balance}" autocomplete="off"><br>
    账号状态:
    <c:if test="${account.status==1}">
        <input type="radio" name="status" value="1" checked>启用
        <input type="radio" name="status" value="0">禁用<br>
    </c:if>
    <c:if test="${account.status==0}">
        <input type="radio" name="status" value="1">启用
        <input type="radio" name="status" value="0" checked>禁用<br>
    </c:if>
    <input type="submit" value="提交">
</form>
</body>
</html>
