package com.mmalaenko.servlet;

import com.mmalaenko.model.Product;
import com.mmalaenko.model.User;
import com.mmalaenko.repository.impl.ProductRepositoryImpl;
import com.mmalaenko.repository.impl.UserRepositoryImpl;
import com.mmalaenko.service.ProductService;
import com.mmalaenko.service.UserService;
import com.mmalaenko.service.impl.ProductServiceImpl;
import com.mmalaenko.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebServlet("shop")
public class ShopServlet extends HttpServlet {


 private UserService userService;
 private ProductService productService;

    private static List<Product> inCart = new ArrayList<>();
    private static List<Product> tempListProduct;
    private static final int NAME_OF_PRODUCT = 0;



    @Override
    public void init() {
        userService=new UserServiceImpl(new UserRepositoryImpl());
        productService= new ProductServiceImpl(new ProductRepositoryImpl());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("in method doPost ShopServlet");
        HttpSession session = req.getSession();

        checkCurrentSelect(req, session);

        session.setAttribute("productList", new ProductServiceImpl(new ProductRepositoryImpl()).getAll());
        inCart.clear();
        getServletContext().getRequestDispatcher("/WEB-INF/shop-page.jsp").forward(req, resp);

    }

    private void checkCurrentSelect(HttpServletRequest req, HttpSession session) {
        if (req.getParameter("currentSelect") != null) {
             String selectedProducts = req.getParameter("currentSelect");
             String[] arrNamePrice = selectedProducts.split(" ");
             String name = arrNamePrice[NAME_OF_PRODUCT];
             Product product=productService.getProductByName(name);

            if (session.getAttribute("cart")==null) {
                tempListProduct = new ArrayList<>();
                addProductInTemp(session, product);
                log.info("product {} save in cart",name);
            }
            else {
               // inCart = (List<Product>) session.getAttribute("cart");
                tempListProduct= (List<Product>) session.getAttribute("cart");
                addProductInTemp(session, product);
            }
        }
    }

    private void addProductInTemp(HttpSession session, Product product) {
        inCart.add(product);
        tempListProduct.add(new Product(inCart.get(0).getId(), inCart.get(0).getName(), inCart.get(0).getPrice()));
        session.setAttribute("cart", tempListProduct);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
