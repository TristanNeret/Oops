/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;
import com.gdf.persistence.Tenderer;
import javax.ejb.Remote;

/**
 *
 * @author bettaieb
 */
@Remote
public interface TenderService {
    public void updateTender(Tenderer t);
    public  Tenderer getTenderById(int id);
    public Tenderer getexistingTend(String log, String pwd) ;
    public Tenderer getTendbyEmail(String email);
    public Tenderer gettendByphone(String phone);

    
   //public Tenderer getexistingTend(String log) ;

   // public Tenderer getTendByLog(String log) ;


}
