<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Фотографии на тему ${param.theme}</h1>
<ul>
    <c:forEach var="photo" items="${requestScope.photosByTheme}">
        <li>
                ${photo.photoTheme()}
        </li>
    </c:forEach>
</ul>
</body>
</html>
