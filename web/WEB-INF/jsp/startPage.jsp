<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Start Page</title>
    <link rel="stylesheet" href="/resources/w3.css">
</head>
<body class=w3-blue-grey>
<header class="w3-container w3-green w3-wide w3-right-align">
    <h1>StockPhoto</h1>
</header>
<div class="w3-container w3-center ">
    <h2><p>Сервис для размещения и покупки фотографий</p></h2>
</div>
<%--<img src="/resources/images/car.jpg" alt="Nice car" style="width:20%">--%>


<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-top-24">
        <button class="w3-btn w3-green w3-round-large" onclick="location.href='/photographers'">Фотографы</button>
        <button class="w3-btn w3-green w3-round-large" onclick="location.href='/allPhotos'">Фотографии</button>
        <button class="w3-btn w3-green w3-round-large" onclick="location.href='/themes'">Темы</button>
        <button class="w3-btn w3-green w3-round-large" onclick="location.href='/registration'">Регистрация</button>
        <button class="w3-btn w3-green w3-round-large" onclick="location.href='/upload'">Загрузка фотогрфий</button>
    </div>
</div>
<div class="w3-container w3-center">
    <h2><p>Популярные темы</p></h2>
</div>
<div class="w3-row-padding w3-margin-top">
    <div class="w3-half">
        <div class="w3-card">
            <img src="/resources/images/cat.jpg" alt="cats" style="width:50%">
            <div class="w3-container" onclick="location.href='/photosByTheme?theme=cats'">
                <p class="w3-btn w3-center">Cats</p>
            </div>
        </div>
    </div>

    <div class="w3-half">
        <div class="w3-card">
            <img src="/resources/images/cat.jpg" alt="Cat" style="width:50%">
            <div class="w3-container">
                <p>Cats</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
