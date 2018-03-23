<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Flowershop</title>
    </head>

    <body>
    <h2>Регистрация</h2><br/>

        <form name="loginForm" method="POST"  action="registration">

            <h3>Заполните поля:</h3>

            Имя:<br/>
            <input type="text" name="firstName" value=""><br/>

            Фамилия:<br/>
            <input type="text" name="lastName" value=""><br/>

            Адрес:<br/>
            <input type="text" name="address" value=""><br/>

        <!--    БАланс:<br/>
            <input type="text" name="account" value=""><br/> -->

            Телефон:<br/>
            <input type="text" name="tel" value=""><br/>

            Скидка:<br/>
            <input type="text" name="discount" value=""><br/>

            Логин:<br/>
            <input type="text" name="username" value=""><br/>

            Пароль:<br/>
            <input type="password" name="password" value=""> <br/>

            Email:<br/>
            <input type="text" name="email" value=""> <br/>

            <c:if test = '${not empty param.err}' >
                     <font color="red">Регистрация не удалась</font>
            </c:if>

            <br/>
            <input type="submit" value="Зарегестрироваться"><br/>

        </form><hr/>

     </body>
</html>

