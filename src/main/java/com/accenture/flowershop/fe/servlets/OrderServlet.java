package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.BuyBusinessService;
import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Cart;
import com.accenture.flowershop.be.entity.order.CartItem;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private BuyBusinessService buyBusinessService;

    @Autowired
    private CatalogBusinessService catalogBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cart cart = new Cart();

        String action = request.getParameter("cast");
        String strFlowerName = request.getParameter("flower");
        String strCount = request.getParameter("count");
        String orderBuy = request.getParameter("orderBuy");
        String strExit = request.getParameter("clickExit");
        String strClean = request.getParameter("btnCleanCart");
        String userPage = request.getParameter("btnLS");


        HttpSession session = request.getSession();

        List<CartItem> cartFromSession = (List<CartItem>) session.getAttribute("cart");

         if(cartFromSession != null){

            cart.setCart(cartFromSession);
        }

/*
        Integer statuse = Integer.parseInt(request.getParameter("editStatuse"));
        Integer orderId = Integer.parseInt(request.getParameter("OrderId"));

        // Оплата
        if (statuse == 1) {
            HttpSession session = request.getSession();
            String username = session.getAttribute("Username").toString();
            User user = this.userBusinessService.findUser(username);
            String odredMessage = "";
            if(buyBusinessService.editStatuseOrder(orderId, username)) {
                odredMessage = "На счету недостаточно средств";
                session.setAttribute("odredMessage", odredMessage);
            }
            else {
            }
            session.setAttribute("account", user.getAccount());
            session.setAttribute("summa", 0);
        }

*/
        // На страницу юзера
        if ("ls".equals(userPage)) {
            response.sendRedirect("/user");
            return;
        }
        // Бросить в корзину
        String message = "";
        if ("buy".equals(action)) {
            Integer count = -1;
            try {
                count = Integer.parseInt(strCount);
            } catch (NumberFormatException e) {
                response.sendRedirect("/catalog");
                return;
            }

            Flower flower = catalogBusinessService.findFlower(strFlowerName);

            if(!cart.cast(flower, strFlowerName, count))
                message = "На складе недостатоно цветов!";

            // Получаем сумму со скидкой

            Integer discount = -1;
            try {
                 discount = Integer.parseInt(session.getAttribute("discount").toString());
            } catch (NumberFormatException e) {
                discount = 0;
            }


            // Запоминаем в сессии
            session.setAttribute("cart", cart.getCart());
            session.setAttribute("message", message);
            session.setAttribute("summa", cart.allSum().toString());
            session.setAttribute("summaDiscounted", cart.discountedSum(discount).toString());
        }

        // Создать заказ
        if ("orderClick".equals(orderBuy) && cartFromSession.size()!=0) {
         //   HttpSession session = request.getSession();
            String username = session.getAttribute("Username").toString();

            String odredMessage1 = "";
            if(this.buyBusinessService.saveOrderCustomer(username, cart)){
                odredMessage1 = "Заказ создан";
                session.setAttribute("odredMessage1", odredMessage1);
            }
            //!!!
           cart.cartClear();
            session.setAttribute("summa", 0);
            session.setAttribute("summaDiscounted", 0);
            session.setAttribute("flowers", null);
        }

        // Выход
        if ("exit".equals(strExit)) {
        //    HttpSession session = request.getSession();
            //!!!
            cart.cartClear();
           // this.buyBusinessService.flowerListClear();
            session = request.getSession();
            session.invalidate();
            cart.cartClear();
           // session.removeAttribute("summa");
           // session.removeAttribute("cart");
           // session.setAttribute("summa", 0);
            response.sendRedirect("/login");
            return;
        }

        // Очистить корзину
        if ("cleanCart".equals(strClean)) {
        //    HttpSession session = request.getSession();
            cart.cartClear();
          //  this.buyBusinessService.flowerListClear();
            session.setAttribute("summa", 0);
            session.setAttribute("summaDiscounted", 0);
            session.removeAttribute("cart");
           // session.setAttribute.
        }

        response.sendRedirect("/catalog");
       // response.sendRedirect("/login");
    }

}
