<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="utf-8">
  <title>数据发送测试-index.jsp</title>
  <style type="text/css">
    a{
      color: #32A9FF;
      text-decoration: none;
      -webkit-transition: all 0.5s;
      -moz-transition: all 0.5s;
      -ms-transition: all 0.5s;
      -o-transition: all 0.5s;
      transition: all 0.5s;
    }
    a:hover{
      color: #F25353;
      text-decoration: underline;
    }
  </style>
</head>
<body>
提交到MyServlet，会保存name到cookie
<form action="MyServlet" method="GET">
  姓名：<input type="text" name="name" value="张三"><input type="submit" value="提交" /><br>
</form>
<br><a href="HelloServlet">HelloServlet</a>：基础测试
<br><a href="MyServlet" >MyServlet</a>：定时刷新时间，接收表单信息,判断是否抛出异常,保存表单信息到cookie，显示请求头
<br><a href="ErrorHandler" title="">ErrorHandler</a>：error servlet
<br><a href="ReadCookies" >ReadCookies</a>：查看cookies，会删掉cookies里的name
<br><a href="SessionTest" >SessionTest</a>：session信息
<br><a href="DataBaseTest" >DataBaseTest</a>：数据库查询

<br><hr>
文件上传
<form method="post" action="UploadServlet" enctype="multipart/form-data">
  选择一个文件:<br/>
  <input type="file" name="uploadFile" />
  <br/>
  <br/>
  <input type="submit" value="上传" />
  <br/>
  <h2>${message}</h2>
</form>
<br><hr>

文件下载
<a href="DownloadServlet?fileName=test.txt" rel="nofollow">下载文件 test.txt </a>

</body>
</html>
