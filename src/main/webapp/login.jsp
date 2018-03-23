<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>

    <body>
    <h2>Вход в систему</h2>

        <form name="loginForm" method="POST"  action="login">

            Login:<br/>
            <input type="text" name="login" value=""><br/>

            Password:<br/>
            <input type="password" name="password" value=""> <br/><br/>

            <c:if test = '${not empty param.err}' >
                     <font color="red">Некорректный логин или пароль</font><br/>
            </c:if>

            <input type="submit" value="Войти"><br/>

            <td align="center" colspan="2"> <a href="registration.jsp"><h3>Регистрация</h3></a></td>

        </form><hr/>
     </body>
</html>