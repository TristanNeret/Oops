/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.PortfolioImage;
import java.io.Serializable;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class managing Contractor
 * @author Tristan
 */
@Stateless
public class ContractorManagerBeanImpl implements ContractorManagerBean, Serializable {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  

    @Override
    public void update(Contractor c) {
        em.merge(c);
    }
    
    @Override
    public void delete(Contractor c) {
        Contractor contractorToRemove =  em.find(Contractor.class, c.getId());
        if(contractorToRemove != null) {
            this.em.remove(contractorToRemove);
        }
        
    }
    
    @Override
    public Contractor undo(Contractor c) {
        Contractor attachedContractor = em.merge(c);
        em.refresh(attachedContractor);
        return attachedContractor;
    }

    @Override
    public void addPortfolioImage(Long id, byte[] image, String name, String description) {
        
        PortfolioImage newImage = new PortfolioImage(id, image, name, description);
        this.em.persist(newImage);
        
        Contractor contractor =  this.em.find(Contractor.class, id);
        if(contractor != null) {
            
            contractor.getImages().add(newImage);
            this.em.merge(contractor);
            
        }
        
    }

    @Override
    public void editPortfolioImage(PortfolioImage image) {
        
        //if (image != null) {
        
            this.em.merge(image);
            
            // Delete image from Contractor list
            /*Contractor contractor =  this.em.find(Contractor.class, image.getId());
            if(contractor != null) {
                
                boolean test = true;
		int i = 0;
		while (i < contractor.getImages().size() && test) {
			
                    if (contractor.getImages().get(i).getId().equals(image.getId())) {
                        test = false;
                    } else {
                        i++;
                    }
                    
		}
                contractor.getImages().set(i, image);
                this.em.merge(contractor);

            }
            
        }*/
        
    }
    
    @Override
    public void deletePortfolioImage(PortfolioImage image) {
        
        PortfolioImage attachedImage = this.em.find(PortfolioImage.class, image.getId());
        /*if (image != null) {
        
            // Delete image from Contractor list
            Contractor contractor =  this.em.find(Contractor.class, image.getId());
            if(contractor != null) {
                
                boolean test = true;
                int i = 0;
		while (i < contractor.getImages().size() && test) {
			
                    if (contractor.getImages().get(i).getId().equals(image.getId())) {
                        test = false;
                    } else {
                        i++;
                    }
                    
		}
                contractor.getImages().remove(i);
                this.em.merge(contractor);

            }*/
            
            // Delete image from database
            this.em.remove(attachedImage);
        
        //}
        
    }

}
