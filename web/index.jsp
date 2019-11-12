<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>测试servlet-index.jsp</title>
    <style type="text/css">
        a {
            color: #32A9FF;
            text-decoration: none;
            -webkit-transition: all 0.5s;
            -moz-transition: all 0.5s;
            -ms-transition: all 0.5s;
            -o-transition: all 0.5s;
            transition: all 0.5s;
        }

        a:hover {
            color: #F25353;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h2>提交到MyServlet，会保存name到cookie</h2>
<form action="MyServlet" method="GET">
    姓名：<input type="text" name="name" value="张三"><input type="submit" value="提交"/><br>
</form>
<br><a href="HelloServlet">HelloServlet</a>：基础测试
<br><a href="MyServlet">MyServlet</a>：定时刷新时间，接收表单信息,判断是否抛出异常,保存表单信息到cookie，显示请求头
<br><a href="ErrorHandler" title="">ErrorHandler</a>：error servlet
<br><a href="ReadCookies">ReadCookies</a>：查看cookies，会删掉cookies里的name
<br><a href="SessionTest">SessionTest</a>：session信息
<br><a href="DataBaseTest">DataBaseTest</a>：数据库查询
<br>
<hr>

<h2>文件上传</h2>
<form method="post" action="UploadServlet" enctype="multipart/form-data">
    选择一个文件:<br/>
    <input type="file" name="uploadFile"/>
    <br/>
    <br/>
    <input type="submit" value="上传"/>
    <br/>
    <h2>${message}</h2>
</form>
<br>
<hr>

<h2>文件下载</h2>
<a href="DownloadServlet?fileName=test.txt" rel="nofollow">下载文件 test.txt </a>
<br>
<hr>

<h2>邮件发送，使用qq邮箱发送邮件</h2>
<form action="SendMailServlet" method="GET">
    发件人：<input type="text" name="from" value="@qq.com"><br>
    授权码：<input type="text" name="token" placeholder="授权码登陆邮箱获取">
    <a href="https://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256">了解授权码</a><br>
    收件人：<input type="text" name="to" value="@qq.com"><br>
    标题：<input type="text" name="mailTitle"><br>
    内容：<textarea name="mailMessage" placeholder="邮件内容"></textarea>
    <input type="submit" value="提交"/><br>
</form>
<br>
<hr>

</body>
</html>
