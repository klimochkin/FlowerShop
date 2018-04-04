<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Flowershop</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>

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


<style> .error { border-color: red; } </style>
<style> .success { border-color: green; } </style>
            Скидка:<br/>
            <input type="text" name="discount" value="" id="input1"><br/>

<div id="error"></div>

<script>
input1.onblur = function() {
  if (isNaN(this.value)) { // введено не число
    // показать ошибку
    this.className = "error";
    error.innerHTML = 'Вы ввели не число. '
  }
};

input1.onfocus = function() {
  if (this.className == 'error') {
    this.className = "";
    error.innerHTML = "";
    this.value = "";
  }
};
</script>

 <!--======================================================================================================================-->


            Логин:<br/>
            <input type="text" name="username" value="" id="input2"><br/>

            <div id="error"></div>
            <div id="results"></div>
                 <script>
                      input2.onblur = function() {
                         if(input2.value != ""){
                            $.ajax({
                                url: "http://127.0.0.1:8080/rest/checkuser/check/"+this.value,
                                type: "GET",
                                success: function(data) {
                                   if(data == "false"){
                                         input2.className = "success";
                                         $('#results').html('<font color="green">Логин свободен</font>');
                                         button.disabled = "";
                                   }
                                   else{
                                        input2.className = "error";
                                        error.innerHTML = 'ошибка';
                                        $('#results').html('<font color="red">Такой логин уже занят</font>');
                                        button.disabled = "disabled";
                                   }
                                },
                                error: function(data){
                                    $('#results').html('<p>Возникла неизвестная ошибка. Не получен ответ с сервера</p>');
                               }
                            });
                         }
                      };
                      input2.onfocus = function() {
                        input2.className = "";
                         $('#results').html('');

                      };
                 </script>

 <!--======================================================================================================================-->

            Пароль:<br/>
            <input type="password" name="password" value=""> <br/>


 <!--======================================================================================================================-->


            Email:<br/>
            <input type="text" name="email" value=""> <br/>


 <!--======================================================================================================================-->


            <c:if test = '${not empty param.err}' >
                     <font color="red">Регистрация не удалась</font>
            </c:if>

            <br/>
            <input type="submit" value="Зарегестрироваться" id="button"><br/>

        </form><hr/>

     </body>
</html>

