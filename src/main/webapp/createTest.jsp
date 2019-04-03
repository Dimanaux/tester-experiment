<%--
  Created by IntelliJ IDEA.
  User: cosmos
  Date: 2019-03-24
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload spec</title>
</head>
<body>
<form enctype="multipart/form-data" method="POST" action="/specs">
    <label>
        upload spec file:
        <input type="file" name="specFile">
    </label>
    <p>
        <input type="submit" value="upload">
    </p>
</form>
</body>
</html>
