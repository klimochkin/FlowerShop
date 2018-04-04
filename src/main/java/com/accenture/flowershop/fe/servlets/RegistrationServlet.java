package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
      //  BigDecimal account  = new BigDecimal(request.getParameter("account"));
        String tel = request.getParameter("tel");
        Integer discount = Integer.parseInt(request.getParameter("discount"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");


    //    User user = new User(firstName, lastName, address, "2000", tel, discount, login, password, email,);

        if (this.userBusinessService.register(firstName, lastName, address, new BigDecimal("2000"), tel, discount, username, password, email)) {
            System.out.println("Регистрация успешна");
            response.sendRedirect("/login");
        } else {
            response.sendRedirect("registration?err=1");
           // response.sendRedirect("/catalog");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
        dispatcher.forward(request, response);
    }
}
