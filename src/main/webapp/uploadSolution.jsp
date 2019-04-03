<%--
  Created by IntelliJ IDEA.
  User: cosmos
  Date: 2019-04-03
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload solution</title>
</head>
<body>
<form enctype="multipart/form-data" method="POST">
    <label>
        upload solution for test #${specId}:
        <input type="file" name="solutionFile">
    </label>
    <p>
        <input type="submit" value="upload">
    </p>
</form>
</body>
</html>
