<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
    <lable for="username">Имя пользователя:
        <input type="text" name="username" id="username">
    </lable>
    <br>
    <lable for="email">Email:
        <input type="text" name="email" id="email">
    </lable>
    <br>
    <lable for="password">Пароль:
        <input type="password" name="password" id="password">
    </lable>
    <br>
    <lable for="phoneNumber">Номер телефона:
        <input type="tel" name="phoneNumber" id="phoneNumber">
    </lable>
    <br>
    <lable for="socialNetwork">Ссылка на соцсеть:
        <input type="tel" name="socialNetwork" id="socialNetwork">
    </lable>
    <br>
    Роль:
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select><br>
    Пол:<br>
    <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" value="${gender}">${gender}
        <br>
    </c:forEach>
    <br>
    <button type="submit">Зарегистрироваться</button>
    <c:if test="${not empty requestScope.errors }">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
