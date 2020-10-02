package com.mmalaenko.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mmalaenko.model.User;
import com.mmalaenko.repository.UserRepository;
import com.mmalaenko.repository.impl.UserRepositoryImpl;
import com.mmalaenko.service.UserService;
import com.mmalaenko.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@WebFilter(urlPatterns = {"/shop"})
public class LoginFilter extends HttpFilter {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository=new UserRepositoryImpl();
    }

    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
       HttpSession session= req.getSession();

        String userName;
        String password;
        if (session.getAttribute("userName")==null) {
            userName = req.getParameter("userName");
            password = req.getParameter("password");
            session.setAttribute("userName", userName);
            session.setAttribute("password", password);
        } else{
            userName = (String) session.getAttribute("userName");
            password = (String) session.getAttribute("password");
        }


        final Optional<User> userByLogin=userRepository.getUserByLogin(userName);

        if(!userByLogin.isPresent()){
            userRepository.save(User.builder()
                    .login(userName)
                    .password(password)
                    .build());
            log.info("User save in DB in filter");
            session.setAttribute("userName", userName);
        }

        if ((req.getParameter("check") != null)) {
            session.setAttribute("check","on");
            chain.doFilter(req,res);
        } else {
            if(session.getAttribute("check")!=null) {
               chain.doFilter(req,res);
               log.info("LoginFilter done");
            }
            else {
                log.info("login attempt without confirmation");
                req.getRequestDispatcher("/WEB-INF/welcome-page.jsp").forward(req,res);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
