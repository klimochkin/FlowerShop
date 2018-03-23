package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

//----------------------------------------------------------------

    @Autowired
    private UserBusinessService userBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();


        User user;

        if(session.getAttribute("Username")== null)
            user = this.userBusinessService.login(login, password);
        else
            user = this.userBusinessService.findUser(session.getAttribute("Username").toString());


        if (user != null) {

               session.setAttribute("firstName", user.getFirstName());
               session.setAttribute("lastName", user.getLastName());
               session.setAttribute("address", user.getAddress());
               session.setAttribute("account", user.getAccount());
               session.setAttribute("tel", user.getTel());
               session.setAttribute("discount", user.getDiscount());
               session.setAttribute("email", user.getEmail());
               session.setAttribute("Username", user.getUsername());
               session.setMaxInactiveInterval(10 * 60);

            //проверка на админа
            if (user.getUserRole().getType().equals("admin"))
                response.sendRedirect("/admin");
             else
                response.sendRedirect("/catalog");

            session.setMaxInactiveInterval(10 * 60);
        }
        else
             response.sendRedirect("login?err=1");
    }


    @Override
    public  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}

