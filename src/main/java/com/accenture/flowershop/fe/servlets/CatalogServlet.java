package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.business.BuyBusinessService;
import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.CartItem;
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
import java.beans.Customizer;
import java.io.IOException;
import java.util.List;


@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {


    @Autowired
    private BuyBusinessService buyBusinessService;

    @Autowired
    private CatalogBusinessService catalogBusinessService;


    //private List<Flower> listFlowers;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();

        // Если пользователь еще не залогинимся - перенаправим на страницу логина
        if (session.getAttribute("Username") == null){
            response.sendRedirect("/login");
            return;
        }

        request.setAttribute("firstName", session.getAttribute("firstName"));
        request.setAttribute("lastName", session.getAttribute("lastName"));

        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        // Получаем каталог и выводим
        request.setAttribute("flowers", catalogBusinessService.findAllFlower());


        RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request, response);
    }
}
