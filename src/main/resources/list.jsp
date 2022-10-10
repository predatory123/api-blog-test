<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <base href="<%=basePath%>>">
    <meta charset="UTF-8">
    <title>查询商品</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>css/allUser.css">
    <script src="<%=path%>js/allUser.js"></script>
    <style type="text/css">
        #images {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
<div id="login_frame">
    <p id="image_logo"><img src="<%=path%>images/lyancafe.png"></p>
    <table border="1">
        <tbody>
        <tr>
            <th>序号</th>
            <th>图片</th>
        </tr>
        <c:if test="${empty productList}">
            <li>暂无商品</li>
        </c:if>
        <c:if test="${!empty productList}">
            <c:forEach items="${productList}" var="product">
                <tr>
                    <th>${product.id}</th>
                    <th>
                        <c:if test="${product.image !=null }">
                            <img id="images" alt="" src="<%=path%>images/${product.image }">
                        </c:if>
                    </th>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>