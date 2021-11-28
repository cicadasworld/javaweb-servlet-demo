<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hujin
  Date: 2021/11/1
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看账户列表</title>
</head>
<body>
<h1>查看账户列表</h1>
<div style="color: red">${errorMessage}</div>
<table>
    <tr>
        <th>编号</th>
        <th>账号名</th>
        <th>账号余额</th>
        <th>账号状态</th>
        <th>账号创建时间</th>
        <th>账号修改时间</th>
        <th>操作</th>
    </tr>
<c:forEach items="${accounts}" var="account" varStatus="accountStatus">
    <tr>
        <td>${accountStatus.count}</td>
        <td>${account.name}</td>
        <td>${account.balance}</td>
        <td><c:if test="${account.status==1}">启用</c:if><c:if test="${account.status==0}">禁用</c:if></td>
        <td>${account.createDate}</td>
        <td>${account.updateDate}</td>
        <td>
            <a href="#" onclick="deleteAccountById('${account.id}', '${account.name}')">删除</a>
            <a href="#" onclick="toUpdateAccountPage('${account.id}')">修改</a>
        </td>
    </tr>
</c:forEach>
</table>
<div>
    <a href="${pageContext.request.contextPath}/pages/account/account_add.jsp" target="_blank">添加账号</a>
</div>
<script type="text/javascript">
    function deleteAccountById(id, name) {
        let flag = confirm("确认要删除" + name + "吗?")
        if (flag) {
            window.location.href = "${pageContext.request.contextPath}/account?method=deleteAccountById&id=" + id
        }
    }

    function toUpdateAccountPage(id) {
        window.location.href = "${pageContext.request.contextPath}/account?method=toUpdateAccountPage&id=" + id
    }
</script>
</body>
</html>
