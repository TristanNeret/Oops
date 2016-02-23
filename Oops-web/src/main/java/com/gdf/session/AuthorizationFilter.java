/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.session;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Tenderer;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AuthorizationFilter
 *
 * @author Tristan
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest reqt = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession ses = reqt.getSession(false);

        String reqURI = reqt.getRequestURI();
        if (reqURI.contains("/adminManager.xhtml")) {
            if (ses.getAttribute("userCategory") == null || !ses.getAttribute("userCategory").equals(Moderator.userCategory)) {
                // Moderator only
                resp.sendRedirect(reqt.getContextPath() + "/redirect/notAuthorized.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else if (reqURI.contains("/tendererManagement.xhtml")) {
            if (ses.getAttribute("userCategory") == null || !ses.getAttribute("userCategory").equals(Tenderer.userCategory)) {
                // Tenderer only
                resp.sendRedirect(reqt.getContextPath() + "/redirect/notAuthorized.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else if (reqURI.contains("/contractorManagement.xhtml")) {
            if (ses.getAttribute("userCategory") == null || !ses.getAttribute("userCategory").equals(Contractor.userCategory)) {
                // Contractor only
                resp.sendRedirect(reqt.getContextPath() + "/redirect/notAuthorized.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }

}
