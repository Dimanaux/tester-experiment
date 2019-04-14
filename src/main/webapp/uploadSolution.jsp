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
    <title>Upload solution</title>
</head>
<body>

<div>
    <h2>Solution for spec ${spec.getTitle()}</h2>
    <h3>Description:</h3>
    ${spec.getDescription()}
</div>
<hr>
<div>
    <form enctype="multipart/form-data" method="POST">
        <p>
            <label>
                Upload solution for test #${specId}:
                <input type="file" name="solutionFile">
            </label>
        </p>
        <p>
            <label>
                Your name:
                <input type="text" name="username">
            </label>
        </p>
        <p>
            <input type="submit" value="upload">
        </p>
    </form>
</div>

</body>
</html>
