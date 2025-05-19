package com.islington.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.islington.utils.CookieUtil;
import com.islington.utils.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String ADMIN_DASH = "/admindash";
    private static final String MODIFY_DASH = "/modifydash";
    private static final String MY_PROFILE = "/MyProfile";
    private static final String MISSIONS = "/missions";
    private static final String ABOUT = "/Aboutus";
    private static final String CONTACT = "/Contactus";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional init logic
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
        String role = CookieUtil.getCookie(req, "role") != null ? CookieUtil.getCookie(req, "role").getValue() : null;

        // Allow static resources
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        // Admin user logic
        if ("admin".equals(role)) {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(req.getContextPath() + ADMIN_DASH);
            } else if (uri.endsWith(ADMIN_DASH) || uri.endsWith(MODIFY_DASH)
                    || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + ADMIN_DASH);
            }
        } else {
            // Guest access to allowed paths
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME)
                    || uri.endsWith(ROOT) || uri.endsWith(MY_PROFILE) || uri.endsWith(MISSIONS)
                    || uri.endsWith(ABOUT) || uri.endsWith(CONTACT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + LOGIN);
            }
        }
    }

    @Override
    public void destroy() {
        // Optional cleanup
    }
}
