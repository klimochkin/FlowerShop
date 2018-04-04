
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
   <head>
       <title>Заказы</title>
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



            <h2>Список заказов</h2>
            <table border="6" width="40%" cellpadding="9">
                <tr>
                    <th>Номер заказа</th>
                    <th>Сумма (руб.)</th>
                    <th>Статус заказа</th>
                    <th>Дата заказа</th>
                    <th>Оплатить</th>

                    <c:forEach items="${listOrderCustomer}" var="Orders">
                        <tr>
                            <td>${Orders.orderCustomerId}</td>
                            <td>${Orders.sum}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${Orders.status == 'CREATED'}"><font color="red">Новый</font></c:when>
                                    <c:when test="${Orders.status == 'PAID'}"><font color="green">Оплачен</font></c:when>
                                    <c:when test="${Orders.status == 'CLOSED'}">Закрыт</c:when>
                                    <c:otherwise>undefined</c:otherwise>
                                </c:choose>
                            </td>


                            <td>${Orders.dateOrder}</td>

                             <td>
                                 <form class="form-inline" method="post" action="user">
                                     <input type="hidden" value="1" name="editStatuse"/>
                                     <input type="hidden" value="${Orders.orderCustomerId}" name="OrderId"/>
                                     <input type="submit" value="Оплатить"><br/>
                                 </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tr>
            </table>
            <p style="color:#ff0000">${odredMessage}</p>
    </body>
</html>


