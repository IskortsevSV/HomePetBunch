<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.02.2018
  Time: 14:06
  To change this template use File | Settings | File Templates.

  При использовании области действия page объект доступен только на той странице, где он определен.
  На включаемых (jsp:include) и переадресуемых (jsp:forward) страницах данный объект уже не доступен.

  Таким образом, чтобы бин был доступен всем JSP страницам, необходимо указать область видимости application или session,
  в зависимости от того требуется ли доступ к объекту всем пользователям или только текущему. Для указания требуемой области действия при определении объекта на JSP странице используется атрибут scope тега jsp:useBean:

  <jsp:useBean id="myBean" class="ru.javacore.MyBean" scope="session"/>
  Отметит, что если не указывать атрибут scope, то по умолчанию задается область видимости страницы page


--%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <jstl:choose>
    <jstl:when test="${not empty sessionScope.user}"> <%--ключ user--%>
       <%-- В случае области действия сессии session, объект будет помещен в сеанс пользователя.
        Бин будет доступен на всех JSP страницах и будет существовать пока существует
        сессия пользователя, или он не будет из нее принудительно удален.--%>
        <p>${sessionScope.user.name}</p>
        <p><a href="controller?action=bunches">My bunch</a></p>
        <p><a href="controller?action=logout">Logout</a></p>
    </jstl:when>
     <jstl:otherwise>
         <p><a href="register.jsp">Register</a></p>
         <p><a href="login.jsp">Login</a></p>
     </jstl:otherwise>
   </jstl:choose>



  <%-- Перебераем все свободные цветы--%>
   <jstl:choose>
       <jstl:when test="${empty sessionScope.user}">
         <%--  При использовании области действия запроса request объект будет доступен на текущей JSP странице,
           странице пересылки (при использовании jsp:forward) или на включаемой странице (при использовании jsp:include).
          --%> <jstl:forEach items="${requestScope.flowers}" var="flower">
               <br/>
               <p><img src="${flower.imagePath}" alt="flower" /></p>
               <p>Name: ${flower.name}</p>
               <p>Price: ${flower.price}</p>
               <p>Length Steak: ${flower.lengthSteack}</p>
               <p>Ice level: ${flower.iceLevel}</p>

               <%--Форма для перехода на регистрацию--%>
               <form method="post" action="controller?action=register">
                   <input type="submit" value="Register for add this flower to the bunch" />
               </form>
           </jstl:forEach>
       </jstl:when>
       <jstl:otherwise>

           <jstl:choose>
               <jstl:when test="${sessionScope.user.admin == false}">

                   <form method="post" action="controller?action=create_new_bunch">
                       <input type="submit" value="Create new bunch" />
                   </form>


                   <jstl:forEach items="${requestScope.bunches}" var="bunch">

                       <p>Bunch id: ${bunch.id}</p>

                       <form method="post" action="controller?action=add_to_bunch">

                           <input type="hidden" name="id" value="${bunch.id}" />

                           <input type="submit" value="Add to bunch" />

                       </form>

                       <form method="post" action="controller?action=bunch_flowers">

                           <input type="hidden" name="id" value="${bunch.id}" />

                           <input type="submit" value="Flowers of this bunch" />

                       </form>


                   </jstl:forEach>

               </jstl:when>
               <jstl:otherwise>
                   <a href="add_flowers_to_exists.jsp">Add new flower to exists</a>
                   <br>
                   <a href="controller?action=add_empty_bunch">Add free bunch</a>
               </jstl:otherwise>
           </jstl:choose>

       </jstl:otherwise>
   </jstl:choose>



    

</body>
</html>
