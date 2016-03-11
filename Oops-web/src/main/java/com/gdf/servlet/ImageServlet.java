/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.servlet;

import com.gdf.ejb.SearchBean;
import com.gdf.persistence.PortfolioImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import javax.ejb.EJB;

/**
 * ImageServlet
 * @author Tristan
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @EJB
    SearchBean sb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Gets string that goes after "/images/"
        String imageId = String.valueOf(request.getPathInfo().substring(1)); 
        // Get Image from DB
        PortfolioImage image = this.sb.getPortfolioImageById(Long.valueOf(imageId)); 

        //response.setHeader("Content-Type", getServletContext().getMimeType(image.getName()));
        response.setHeader("Content-Type", "image");
        response.setHeader("Content-Disposition", "inline; filename=\"" + image.getId() + "\"");

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            
            // Creates buffered input stream
            input = new BufferedInputStream(new ByteArrayInputStream(image.getImage())); 
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[image.getImage().length];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
            
        } finally {
            
            if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
            if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
            
        }
        
    }
    
}