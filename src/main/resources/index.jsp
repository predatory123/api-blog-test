<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
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
    <title>Title</title>
</head>
<body>
<form action="<%=path%>product/addProduct" method="post" enctype="multipart/form-data">
    图片：<input type="file" name="file">
    <input type="submit" value="提交">
</form>
</body>
</html>