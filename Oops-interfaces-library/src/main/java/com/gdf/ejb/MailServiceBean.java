/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import javax.ejb.Remote;

/**
 *
 * @author chris
 */
@Remote
public interface MailServiceBean {
    /**
     * prototype of the function to send an email
     * @param to the email address to send to
     * @param subject subject of the email
     * @param message message of the email
     */
    public void sendEmail(String to, String subject, String message);
}
