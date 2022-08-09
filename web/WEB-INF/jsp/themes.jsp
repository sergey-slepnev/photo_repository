<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Темы фотографий</h1>
<ul>
    <c:forEach var="theme" items="${requestScope.themes}">
        <li>
            <a href="${pageContext.request.contextPath}/photosByTheme?theme=${theme}">${theme}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
