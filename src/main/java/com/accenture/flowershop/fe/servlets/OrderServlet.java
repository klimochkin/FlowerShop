package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.BuyBusinessService;
import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.business.UserBusinessService;
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


@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private BuyBusinessService buyBusinessService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("cast");
        String strFlowerName = request.getParameter("flower");
        String strCount = request.getParameter("count");
        String orderBuy = request.getParameter("orderBuy");
        String strExit = request.getParameter("clickExit");
        String strClean = request.getParameter("btnCleanCart");

//<input type="hidden" value="cleanCart" name="btnCleanCart"/>

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

            if(!this.buyBusinessService.cast(strFlowerName, count))
                message = "На складе недостатоно цветов!";

            // Запоминаем в сессии
            HttpSession session = request.getSession();
            session.setAttribute("cart", buyBusinessService.getCart());
            session.setAttribute("message", message);
            session.setAttribute("summa", buyBusinessService.allSum().toString());
        }

        // Создать заказ
        if ("orderClick".equals(orderBuy)) {
            HttpSession session = request.getSession();
            String username = session.getAttribute("Username").toString();

            String odredMessage = "";
            if(!this.buyBusinessService.saveOrderCustomer(username)){
                odredMessage = "На счету недостаточно средств";
                session.setAttribute("odredMessage", odredMessage);
            }
            User user = this.userBusinessService.findUser(username);
            session.setAttribute("account", user.getAccount());
            session.setAttribute("summa", 0);
        }

        // Выход
        if ("exit".equals(strExit)) {
            HttpSession session = request.getSession();
            this.buyBusinessService.cartClear();
            this.buyBusinessService.flowerListClear();
            session = request.getSession();
            session.invalidate();
            response.sendRedirect("/login");
            return;
        }

        // Очистить корзину
        if ("cleanCart".equals(strClean)) {
            HttpSession session = request.getSession();
            this.buyBusinessService.cartClear();
            this.buyBusinessService.flowerListClear();
            session.setAttribute("summa", 0);
        }

        response.sendRedirect("/catalog");
       // response.sendRedirect("/login");
    }

}
