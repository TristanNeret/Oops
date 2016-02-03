/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;


import com.gdf.ejb.TenderService;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Notification;
import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author bettaieb
 */
@Stateless
public class TenderserviceImpl implements TenderService{
        @PersistenceContext (unitName = "OopsPU")
        
                EntityManager EntMa;

        //(unitName = "com.gdf_Oops-entities-library_jar_1.0-SNAPSHOTPU")

    public  TenderserviceImpl()
    {
        
    }
    @Override
    public void updateTender(Tenderer t) {
            EntMa.merge(t);  
    }

  
        @Override
    public Tenderer getexistingTend(String log, String pwd) {
		try {
//				Query q1 = EntMa.createQuery("SELECT a FROM Tenderer a where a.login =:log and a.password =:pwd");
//				q1.setParameter("login", log);
//				q1.setParameter("password", pwd);
//				return (Tenderer) q1.getSingleResult();
//
//		     } catch (Exception e) 
//		     {
//			    return null;
//		     }
//	}
         Query query = EntMa.createNamedQuery("Tenderer.findBylogpass");
        query.setParameter(1, log);
        query.setParameter(2, pwd);
        return (Tenderer) query.getSingleResult();
    } catch (Exception e) 
		     {
			    return null;
		     }
	}
    
   
    
    @Override
    public Tenderer getTenderById(int id) {
		return EntMa.find(Tenderer.class,id);
    }
//    public Tenderer getTenderer(Tenderer t1)
//    {
//        return EntMa.find(Tenderer.class,t);
//    }

//   
//    public Tenderer getexistingTend1(String log, String pwd) {
////  Query query5 = EntMa.createNamedQuery("Tenderer.findBylogpass");
////        query5.setParameter(1, log);
////        query5.setParameter(2, pwd);
////        if (query5.getResultList().isEmpty()) {
////            
////            return false;
////            
////        } else {
////            
////            return true;
////            
////        }    }
//
//                Tenderer found = null;
////		String jpql = "select u from User u where u.mail=:email and u.password=:password";
////		Query query = em.createQuery(jpql);
////		query.setParameter("email", email);
////		query.setParameter("password", password);
//Query query5 = EntMa.createNamedQuery("Tenderer.findBylogpass");
//       query5.setParameter(1, log);
//        query5.setParameter(2, pwd);
////		try{
////			found = (User) query.getSingleResult();
////		}catch(Exception ex){
////		}
////		return found;
//                
//               try{
//			found = (Tenderer) query5.getSingleResult();
//		}catch(Exception ex){
//		}
//		return found;}}
//            
//            return false;
//            
//        } else {
//            
//            return true;
//            
//        }    }

    @Override
    public Tenderer getTendbyEmail(String email) {
        Query query = EntMa.createNamedQuery("Tenderer.findByEmail");
        query.setParameter(1, email);
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Tenderer) query.getSingleResult();
            
        }
    }

    @Override
    public Tenderer gettendByphone(String phone) {
  Query query = EntMa.createNamedQuery("Tenderer.findByphone");
        query.setParameter(1, phone);
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Tenderer) query.getSingleResult();
            
        }    }
    
        
  
    }

    
   
    
        
    
       

