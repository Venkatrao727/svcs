package com.slocamo.auth.security;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoRedirectStrategy implements RedirectStrategy{

    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        // Forget about redirecting, there is no need!
    }
}
