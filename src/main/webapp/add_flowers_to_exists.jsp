<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="controller?action=add_new_flower_to_exists">
    <INPUT TYPE="button" VALUE="Back" onClick="history.go(-1);">
    <p><input type="hidden" name="user" value="${sessionScope.user.admin}" /></p>
    <p><input type="text" name="name" size="10"/> Name</p>
    <p><input type="text" name="price" size="10"/> Price</p>
    <p><input type="text" name="lengthSteack" size="10"/> LengthSteack</p>
    <p><input type="text" name="iceLevel" size="10"/> IceLevel</p>
    <p><input type="submit" value="Add Flower To Exists">
</form>


</body>
</html>