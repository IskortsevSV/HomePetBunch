<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.02.2018
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jstl:if test="${not empty requestScope.notExists}">
    <p>This user not exists</p>
</jstl:if>

   <form method="post" action="controller?action=login">
       <%--type="text" - вводим определенный текст--%>
    <p><input type="text" name="name" size="10"/></p>
       <%--type="password" - вводим определенный текст--%>
    <p><input type="password" name="password" size="10" /></p>
    <!-- <p><input type="checkbox" name="admin" /></p> -->
       <%-- при нажатии кнопки Login переходим на controller?action=login
       и передаем action=login--%>
    <p><input type="submit" value="Login" /></p>
    <p></p>
  </form>

</body>
</html>
