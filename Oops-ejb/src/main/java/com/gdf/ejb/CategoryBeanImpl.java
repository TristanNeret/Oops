/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 *
 * @author user
 */
@Stateless
public class CategoryBeanImpl implements CategoryBean {

    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
    
    
    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = em.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(long id) {
        Category category =  em.find(Category.class, id);
        return category;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
