<%--
  Created by IntelliJ IDEA.
  User: cosmos
  Date: 14.04.19
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>results</title>
</head>
<body>

<a href="/specs">back</a>

<h2>Results:</h2>
<ol>
    <c:forEach items="${solutions}" var="s">
        <li>
                ${s.authorName} =>
        </li>
        <details>
            <summary>${s.getSummary()}</summary>
            <pre>
                    ${s.result}
            </pre>
        </details>
    </c:forEach>
</ol>

</body>
</html>
