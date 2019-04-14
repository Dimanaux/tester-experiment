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
<p>
    Upload single file - Spec.java. <br>
    We use JUnit 4.12.
</p>

<form enctype="multipart/form-data" method="POST" action="/specs">
    <p>
        <label>
            Upload spec file:
            <input type="file" name="specFile">
        </label>
    </p>
    <p>
        <label>
            Enter test title:
            <input type="text" name="title">
        </label>
    </p>
    <p>
        <label>
            Enter description:
            <input type="text" name="description" placeholder="say something">
        </label>
    </p>
    <p>
        <input type="submit" value="upload">
    </p>
</form>
</body>
</html>
