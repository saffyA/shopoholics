package com.vapasians.shopoholics.config;

import com.vapasians.shopoholics.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class WebRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        String url = ((HttpServletRequest)request).getRequestURI();
        System.out.println(url);
        if(session.getAttribute("loggedInUser")!=null) {
            System.out.println("We have a logged in user");
            if(url.contains("signUp") || url.contains("login"))
                ((HttpServletResponse)response).sendRedirect("/");
            if(url.contains("admin"))
            {
                if(((User)session.getAttribute("loggedInUser")).getRole()!='A')
                    ((HttpServletResponse)response).sendRedirect("/");
            }
            if(((User)session.getAttribute("loggedInUser")).getRole()=='A')
            {
                if(url.equals("/") || url.contains("cart") || url.contains("checkout") || url.contains("Ox`x`rder"))
                if(((User)session.getAttribute("loggedInUser")).getRole()=='A')
                    ((HttpServletResponse)response).sendRedirect("/admin");
            }
        }
        else
        {
            if(url.contains("/admin") || url.contains("/checkout")) {
                System.out.println("url starts with admin - not allowed as guest");
                    ((HttpServletResponse)response).sendRedirect("/login");
            }
        }
        chain.doFilter(request,response);
    }
}
