package com.gdf.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gdf.ejb.TendererManagerBean;
import com.gdf.persistence.Tenderer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author bibo
 */
@RunWith(MockitoJUnitRunner.class)
public class TendererManagementBeanTest {
    
//    @Mock
//    private EntityManager mockedEntityManager;
//    private static TendererBean tendererBean;
//    private static TendererManagerBean tendererManagerBean;   
//    private static EJBContainer container;
//    
//    
//    private static Tenderer t1, t2, t3;
//    private static String login1, login2, login3;
//    private static String mail1, mail2, mail3;
//    private static String pwd1, pwd2, pwd3;
    
    
    public TendererManagementBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException {
        
//        final Properties p = new Properties();
//        p.put("jdbc/Oops", "new://Resource?type=DataSource");
//        p.put("jdbc/Oops.JdbcDriver", "apache_derby_net");
//        p.put("jdbc/Oops.JdbcUrl", "jdbc:derby://localhost:1527/OopsDB");
//        container = EJBContainer.createEJBContainer(p);
//        final Context context = container.getContext();
//        tendererBean = (TendererBean) context.lookup("java:global/classes/TendererManagerBean");
//        
//        
//        login1= "t1";
//        mail1 = "t1@test.fr";
//        pwd1 = "t1";
//        
//        t1 = new Tenderer();
//        t1.setLogin(login1);
//        t1.setEmail(mail1);
//        t1.setPassword(pwd1);
//        
//        tendererBean.register(t1);
//        
//        
//        login2= "t2";
//        mail2 = "t2@test.fr";
//        pwd2 = "t2";
//        
//        t2 = new Tenderer();
//        t2.setLogin(login2);
//        t2.setEmail(mail2);
//        t2.setPassword(pwd2);
//        
//        tendererBean.register(t2);
//        
//        login3 = "t3";
//        mail3 = "t2@test.fr";
//        pwd3 = "t3";
//        
//        
//        
//        t3 = new Tenderer();
//        t3.setLogin(login3);
//        t3.setEmail(mail3);
//        t3.setPassword(pwd3);
//        
//        tendererBean.register(t3);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
//        tendererManagerBean.delete(t1);
//        tendererManagerBean.delete(t2);
//        tendererManagerBean.delete(t3);
//        container.close();
    }
    
    @Before
    public void setUp() {
        
//        tendererBean = new TendererBean();
//        tendererBean.setEntityManager(mockedEntityManager);
    }
    
    @After
    public void tearDown() {
 
    }

    
    
    @Test
    public void testRegister() {
        // Creation du soumissionnaire a ajouter
//        Tenderer t = new Tenderer();
//        // ajouter un soumissionnaire
//        tendererBean.register(t);
//        // On verifie que l'EntityManager a bien ete appele, et qu'il a bien persiste le soumissionnaire
//        verify(mockedEntityManager, times(1)).persist(t);
    }
    
    @Test
    public void testFindAll() {
//        List<Tenderer> tenderers = new ArrayList<>();
//        tenderers.add(t1);
//        tenderers.add(t2);
//        tenderers.add(t3);
//        List<Tenderer> result = tendererManagerBean.findAll();
//        Assert.assertEquals("Erreur dans findAll : on ne trouve pas les bons soumissionnaire", tenderers, result);
    }
    
    /**
     * Test of findByLogin method, of class TendererManagerBean.
     * On teste si on trouve le bon soumissionnaire avec son login
     */
    @Test
    public void testFindByLogin1() {
//        List<Tenderer> tenderers = new ArrayList<>();
//        tenderers.add(t1);
//        List<Tenderer> result = tendererManagerBean.findByLogin(login1);
//        Assert.assertEquals("Erreur dans findByLogin : on ne trouve pas le bon soumissionnaire", tenderers, result);
    }
    
    /**
     * Test of findByLogin method, of class TendererManagerBean.
     * On teste que l'on ne trouve rien quand le tenderers n'existe pas dans la base de donnees
     */
    @Test
    public void testFindByLogin2() {
//        List<Tenderer> tenderers = new ArrayList<>();
//        List<Tenderer> result = tendererManagerBean.findByLogin("nimporte quoi");
//        Assert.assertEquals("Erreur dans findByLogin : on trouve un soumissionnaire alors qu'on ne devrait pas", tenderers, result);
    }
    
    /**
     * Test of findByEmail method, of class TendererManagerBean.
     * On teste si on trouve le bon soumissionnaire avec son mail
     */
    @Test
    public void testFindByMail1() {
//        List<Tenderer> tenderers = new ArrayList<>();
//        tenderers.add(t1);
//        List<Tenderer> result = tendererManagerBean.findByEmail(mail1);
//        Assert.assertEquals("Erreur dans findByEmail : on ne trouve pas le bon soumissionnaire", tenderers, result);
    }
    
    /**
     * Test of findByEmail method, of class TendererManagerBean.
     * On teste que l'on ne trouve rien quand le soumissionnaire n'existe pas dans la base de donnees
     */
    @Test
    public void testFindByMail2() {
//        List<Tenderer> tenderers = new ArrayList<>();
//        List<Tenderer> result = tendererManagerBean.findByEmail("mon mail");
//        Assert.assertEquals("Erreur dans findByEmail : on trouve un soumissionnaire alors qu'on ne devrait pas", tenderers, result);
    }
    
    @Test
    public void testUpdate() {
//        Tenderer t = new Tenderer();
//        tendererManagerBean.update(t);
//        verify(mockedEntityManager, times(1)).merge(t);
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
