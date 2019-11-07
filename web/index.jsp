<%--
  Created by IntelliJ IDEA.
  User: eva4
  Date: 2019/11/6
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>数据发送测试-index.jsp</title>
</head>
<body>
<form action="MyServlet" method="GET">
  姓名：<input type="text" name="name" value="张三"><br>
  <input type="submit" value="提交" />
</form>
<a href="HelloServlet">HelloServlet</a>
<a href="MyServlet">MyServlet</a>
<a href="ErrorHandler">ErrorHandler</a>


</body>
</html>
