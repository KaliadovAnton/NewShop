package com.mmalaenko.servlet;

import com.mmalaenko.model.Order;
import com.mmalaenko.model.Product;
import com.mmalaenko.model.User;
import com.mmalaenko.service.OrderGoodService;
import com.mmalaenko.service.OrderService;
import com.mmalaenko.service.UserService;
import com.mmalaenko.service.impl.UserServiceImpl;
import com.mmalaenko.utill.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private UserService userService;
    private OrderService orderService;
    private OrderGoodService orderGoodService;

    private static List<Order> listOrderByUser = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext context = SpringContext.getApplicationContext();
        userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        orderService = (OrderService) context.getBean("orderServiceImpl");
        orderGoodService = (OrderGoodService) context.getBean("orderGoodServiceImpl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Enter in CartServlet");
        HttpSession session = req.getSession();
        User user = userService.getUserByLogin((String) session.getAttribute("userName"));

        List<Product> bucket = (List<Product>) session.getAttribute("cart");
        double total = 0;
        if (bucket != null) {
            for (Product product : bucket) {
                total += product.getPrice();
            }
        }
        listOrderByUser = orderService.getListOrderByUser(user.getId());
        List<Integer> numbersOfOrderByUser = listOrderByUser.stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        session.setAttribute("numbersOfOrders", numbersOfOrderByUser);
        if (total != 0) {
            Order order = Order.builder()
                    .userID(user.getId())
                    .totalPrice(total)
                    .build();

            orderService.saveOrder(order);

            for (Product product : bucket) {
                orderGoodService.save(order.getId(), product.getId());
            }
        }




        req.setAttribute("total", total);
        req.setAttribute("bucket", bucket);
        session.setAttribute("cart", null);
        getServletContext().getRequestDispatcher("/WEB-INF/cart-page.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

