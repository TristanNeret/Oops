/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.session;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * SessionBean
 *
 * @author Tristan
 */
public class SessionBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     * Return the id of the User connected
     * @return Uset id if connected, or null
     */
    public static Long getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (Long) session.getAttribute("userID");
        } else {
            return null;
        }
    }
    
    /**
     * Return the category of the User connected
     * @return Moderator, Tenderer or Contractor type if connected, or null
     */
    public static String getUserCategory() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userCategory");
        } else {
            return null;
        }
    }

    /**
     * Return the login/socialReason of the User connected
     * @return login or the social reason of the Tenderer/Contractor connected
     */
    public static String getUserName() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userName");
        } else {
            return null;
        }
    }

    /**
     * Return the avatar link of the User connected
     * @return the avatar link of the User connected
     */
    public static String getUserAvatar() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userAvatar");
        } else {
            return null;
        }
    }

}
