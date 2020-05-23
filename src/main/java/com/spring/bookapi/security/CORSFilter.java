package com.spring.bookapi.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        System.out.println("Filtering on...........................................................");
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers,Access-Control-Max-Age,Access-Control-Allow-Credentials,Access-Control-Allow-Methods,Access-Control-Allow-Origin,X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers,charset=UTF-8");

        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(200);
        }
        chain.doFilter(req, res);


    }


    public void init(FilterConfig filterConfig) {
    }


    public void destroy() {
    }

}
