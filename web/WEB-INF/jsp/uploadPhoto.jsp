<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
</body>
<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <p>Выберите тему загружаемой фотографии (поддерживаемые форматы - jpeg, png)</p>
        <c:forEach var="theme" items="${requestScope.themes}">
            <input type="radio" name="theme" value="${theme}" required>${theme}
            <br>
        </c:forEach>
    <p>Выберите изображение для загрузки:</p>
    <label for="photoId">
        <input type="file" name="photo" id="photoId" required>
    </label><br>
    <p>
        <button type="submit">Загрузить</button>
    </p>
</form>
</html>
