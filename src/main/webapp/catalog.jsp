
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
   <head>


       <title>Каталог цветов</title>
   </head>

   <body>
             <h4>Вы вошли как: ${firstName} ${lastName} </h4>

        <ul>
         <li>У вас на счете: ${account} </li>
         <li>Телефон: ${tel} </li>
         <li>Адрес: ${address} </li>
          <li>email: ${email} </li>
        </ul>
             <form class="form-inline" method="post" action="order">
                 <input type="hidden" value="exit" name="clickExit"/>
                 <input type="submit" value="Выход"><br/>
             </form>

       <center>
<table border="0" width="80%" cellpadding="5">
<th>
                   <center><h2>Корзина</h2><center>
                   <table border="2" width="40%" cellpadding="5">
                        <tr>
                            <th>Наименование</th>
                            <th>Количество (руб.)</th>
                            <th>Сумма</th>

                            <c:forEach items="${cart}" var="cartItem">
                                <tr>
                                    <td>${cartItem.nameFlower}</td>
                                    <td>${cartItem.countFlower}</td>
                                    <td>${cartItem.sumItem}</td>
                                </tr>
                            </c:forEach>
                    </table><br/>
                    <p style="color:#ff0000">${odredMessage}</p>

                    <table border="0" width="60%" cellpadding="5">
                         <th>
                             <h4 style="color:#0000ff">Cумма: ${summa}</h4>
                         </th>
                         <th>
                             <form class="form-inline" method="post" action="order">
                                <input type="hidden" value="orderClick" name="orderBuy"/>
                                <center><input type="submit" value="Заказать"><center>
                             </form>
                         </th>
                         <th>
                             <form class="form-inline" method="post" action="order">
                                <input type="hidden" value="cleanCart" name="btnCleanCart"/>
                                <center><input type="submit" value="Очистить"><center>
                             </form>
                         </th>
                    </table>
</th>

<th>
            <h2>Каталог цветов</h2>
            <table border="5" width="100%" cellpadding="9">
                <tr>
                    <th>Цветок</th>
                    <th>Цена (руб.)</th>
                    <th>В наличии (шт.)</th>
                    <th>Покупка</th>

                    <c:forEach items="${flowers}" var="flower">
                        <tr>
                            <td>${flower.name}</td>
                            <td>${flower.price}</td>
                            <td>${flower.count}</td>

                            <td>
                                <form class="form-inline" method="post" action="order">
                                    <input type="text" name="count" placeholder="шт.">
                                    <input type="hidden" value="buy" name="cast"/>
                                    <input type="hidden" value="${flower.name}" name="flower"/>
                                    <input type="submit" value="В корзину"><br/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tr>
            </table><br/>
            <p style="color:#ff0000">${message}</p>
</th>
</table>

        </center>
    </body>
</html>


