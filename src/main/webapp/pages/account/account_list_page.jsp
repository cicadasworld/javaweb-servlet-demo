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
    <title>查看账户列表(分页显示)</title>
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
<c:forEach items="${pageBean.dataList}" var="account" varStatus="accountStatus">
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
    <tr>
        <td>总条数${pageBean.totalCount}, 每页条数${pageBean.pageSize}</td>
    </tr>
    <tr>
        <%--   显示上一页:如果当前页不等于1，那么显示上一页   --%>
        <td>
            <c:if test="${pageBean.pageNo!=1}">
                <a href="account?method=findAccountByPage&pageNo=${pageBean.pageNo-1}&pageSize=${pageBean.pageSize}">上一页</a>
            </c:if>
        </td>
            <%--   显示页码   --%>
        <td>
            <c:forEach begin="1" end="${pageBean.totalPage}" var="pageNo">
                <%--  如果遍历的页码是当前页码，就高亮显示，不设置链接--%>
                <c:if test="${pageBean.pageNo==pageNo}">
                    <a href="#" style="color: red">${pageNo}</a>
                </c:if>
                <%--  如果遍历的页码不是当前页码，设置链接--%>
                <c:if test="${pageBean.pageNo!=pageNo}">
                    <a href="account?method=findAccountByPage&pageNo=${pageNo}&pageSize=${pageBean.pageSize}">${pageNo}</a>
                </c:if>
            </c:forEach>
        </td>
            <%--   显示下一页:如果当前页不等于总页数，那么显示下一页   --%>
        <td>
            <c:if test="${pageBean.pageNo!=pageBean.totalPage}">
                <a href="account?method=findAccountByPage&pageNo=${pageBean.pageNo+1}&pageSize=${pageBean.pageSize}">下一页</a>
            </c:if>
        </td>
    </tr>
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
