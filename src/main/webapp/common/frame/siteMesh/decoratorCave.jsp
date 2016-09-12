<%--
  Created by IntelliJ IDEA.
  User: Jeor
  Date: 2016/4/11
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <h4>head</h4>
  <div class="container web-body" style="margin-bottom:50px;">
    <!--动态部分-->
    <sitemesh:write property='body'/>
  </div>
  <h4>food</h4>
</body>
</html>
