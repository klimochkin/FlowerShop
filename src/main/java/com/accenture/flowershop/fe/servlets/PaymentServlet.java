package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.AdminBusinessService;
import com.accenture.flowershop.be.business.BuyBusinessService;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class PaymentServlet extends HttpServlet{

    @Autowired
    private AdminBusinessService adminBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    BuyBusinessService buyBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    public  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String username = session.getAttribute("Username").toString();
        List<OrderCustomer> listOrderCustomer = adminBusinessService.getOrderList(username);
        request.setAttribute("listOrderCustomer", listOrderCustomer);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/order.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       // Integer statuse = Integer.parseInt(request.getParameter("editStatuse"));
        Integer orderId = Integer.parseInt(request.getParameter("OrderId"));
        String odredMessage = "";
        HttpSession session = request.getSession();
// Оплата
       // this.buyBusinessService.getOrderCustomer(orderId).setStatus();

            String username = session.getAttribute("Username").toString();

            if (!buyBusinessService.editStatuseOrder(orderId, username)) {
                odredMessage = "На счету недостаточно средств";
                session.setAttribute("odredMessage", odredMessage);
            } else {
                User user = this.userBusinessService.findUser(username);
                session.setAttribute("account", user.getAccount());
               // session.setAttribute("summa", 0);
            }

        response.sendRedirect("/user");
    }
}