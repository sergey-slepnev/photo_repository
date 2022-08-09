<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Список фотографов</h1>
<ul>
    <c:forEach var="photographer" items="${requestScope.photographers}">
        <li>
            <a href="${pageContext.request.contextPath}/photosById?photographerId=${photographer.id()}">${photographer.username()}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
