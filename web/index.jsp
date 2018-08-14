<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/30
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
HelloWorld <%=request.getContextPath()%>
<a href="/hello">hello</a>
<a href="<%=request.getContextPath()%>/test">baidu</a>
</body>
</html>
