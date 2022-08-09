<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<h1>Все фотографии фотографа ${requestScope.photographers.get(param.photographerId - 1).username()}</h1>
<ul>
    <c:forEach var="photo" items="${requestScope.photosById}">
        <li>
                ${photo.photoTheme()}
        </li>
    </c:forEach>
</ul>
</body>
</html>

