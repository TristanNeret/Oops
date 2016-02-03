/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test registration methods
 * @author Tristan
 */
public class RegistrationBeanImplTest {
    
    private static EJBContainer container;
    private static Context context;
    
    private static ContractorManagerBean contractorManagerBean;
    
    private Contractor c1;
    private String c1Login;
    private Tenderer t1;
    private String t1Login;
    
    /**
     * Create an instance of RegistrationBeanImplTest
     */
    public RegistrationBeanImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException {
        
//        System.out.println("Opening the container");
//        container = EJBContainer.createEJBContainer();
//        context = container.getContext();

        
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(EJBContainer.MODULES, new File("target/classes"));
//        container = EJBContainer.createEJBContainer(properties);
//        context = container.getContext();
//        String contractorManagerBeanImplName = ContractorManagerBeanImpl.class.getSimpleName();
//        contractorManagerBeanImplName = "java:global/classes.ext/" + contractorManagerBeanImplName;
//        contractorManagerBean = (ContractorManagerBean) context.lookup(contractorManagerBeanImplName);

    }
    
    @AfterClass
    public static void tearDownClass() {
        
//        if (container != null) {
//            container.close();
//        }
        System.out.println("Closing the container");
        
    }
    
    @Before
    public void setUp() {
        
        this.c1 = new Contractor();
        this.t1 = new Tenderer();
        this.c1Login = "c1TestLogin013020122015";
        this.t1Login = "t1TestLogin013020122015";
        
    }
    
    @After
    public void tearDown() {
        
//        container.close();
        System.out.println("Closing the container");
        
//        try {
//            
//            if(this.c1 != null) {
//            
//                // Remove test Contractor
//                ContractorManagerBean contractorManagerBean = (ContractorManagerBean)ejbContainer.getContext().lookup("java:global/Oops-ejb/ContractorManagerBean");
//                contractorManagerBean.delete(this.c1);
//            
//            }
//            
//            if(this.t1 != null) {
//            
//                // Remove test Tenderer
//                TendererManagerBean tendererManagerBean = (TendererManagerBean)ejbContainer.getContext().lookup("java:global/Oops-ejb/TendererManagerBean");
//                tendererManagerBean.delete(this.t1);
//            
//            }
//            
//            this.c1 = null;
//            this.t1 = null;
//            
//        } catch (NamingException ex) {
//            Logger.getLogger(RegistrationBeanImplTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    /**
     * Test of register method, of class RegistrationBeanImpl.
     * @throws java.lang.Exception
     */
//    @Test
//    public void testRegister_Contractor() throws Exception {
        
//        // Preparation
//        this.c1 = new Contractor();
//        c1.setLogin(this.c1Login);
//        
//        RegistrationBeanImpl registrationBean = (RegistrationBeanImpl)ejbContainer.getContext().lookup("java:global/Oops-ejb/RegistrationBeanImpl");
//        assertNotNull(registrationBean);
//        
//        // Test
//        registrationBean.register(this.c1);
//
//        SearchBeanImpl searchBean = (SearchBeanImpl)ejbContainer.getContext().lookup("java:global/Oops-ejb/SearchBeanImpl");
//        Contractor newContractor = searchBean.searchContractorByLogin(this.c1Login);
//
//        // Verification
//        assertEquals(this.c1, newContractor);
//
//    }

    /**
     * Test of register method, of class RegistrationBeanImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testRegister_Tenderer() throws Exception {
        
        // Preparation
        this.t1.setLogin(this.t1Login);
        
//        RegistrationBeanImpl registrationBean = (RegistrationBeanImpl) context.lookup("java:target/classes/com/gdf/ejb/RegistrationBeanImpl");
//        SearchBeanImpl searchBean = (SearchBeanImpl) context.lookup("java:target/classes/com/gdf/ejb/SearchBeanImpl");
//        
//        // Test
//        registrationBean.register(this.t1);
//
//        // Verification
//        searchBean.searchTendererByLogin(this.t1Login);
//        assertEquals(this.t1, newTenderer);
        
    }
    
}
