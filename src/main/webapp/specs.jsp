<%--
  Created by IntelliJ IDEA.
  User: cosmos
  Date: 14.04.19
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>specs</title>
</head>
<body>

<p>
    Create new?
    <a href="/specs/new">/specs/new</a>
</p>

<ol>
    <h2>Results:</h2>
    <c:forEach items="${specs}" var="s">
        <li>
            <a href="/specs/${s.id}">${s.title}</a>
        </li>
    </c:forEach>
</ol>

</body>
</html>
