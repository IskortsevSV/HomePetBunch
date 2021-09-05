<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.02.2018
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<jstl:forEach items="${requestScope.flowers}" var="flower">
    <br/>
    <p><img src="${flower.imagePath}" alt="flower" /></p>
    <p>${flower.name}</p>
    <p>${flower.price}</p>
    <p>${flower.lengthSteack}</p>
    <p>${flower.iceLevel}</p>

    <form method="post" action="controller?action=remove_flower">
        <input type="hidden" name="flower_id" value="${flower.id}">
        <input type="hidden" name="bunch_id" value="${requestScope.id}">
        <input type="hidden" name="name" value="${flower.name}" />
        <input type="hidden" name="price" value="${flower.price}" />
        <input type="hidden" name="lengthSteack" value="${flower.lengthSteack}" />
        <input type="hidden" name="iceLevel" value="${flower.iceLevel}" />
        <input type="submit" value="Remove Flower"/>
    </form>
</jstl:forEach>

</body>
</html>
