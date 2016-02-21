/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author aziz
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchBeanImplTest {
    
    @InjectMocks
    private SearchBeanImpl searchBeanImpl; // class under test
    
    // mocks
    @Mock
    private EntityManager mockedEntityManager; 
    @Mock
    private Query mockedQuery;
    
    public SearchBeanImplTest() {
    }

    @Before 
    public void setUp() {
        searchBeanImpl.em = mockedEntityManager;
    }

    @Test
    public void testfindTendererBeginBy() {
        String param = "Ber";
        
        List<String> expected = Arrays.asList("Bernard", "Bertrand");
        
        when(mockedQuery.getResultList()).thenReturn(expected);
        when(mockedQuery.setParameter(1, param)).thenReturn(mockedQuery); // 
        when(mockedEntityManager.createNamedQuery("Tenderer.beginBy")).thenReturn(mockedQuery);
        
        List<String> result = searchBeanImpl.findTendererBeginBy(param);
        
        assertEquals(expected, result);
    }  
    
    @Test
    public void testfindTendererBeginByEmptyResult() {
        when(mockedQuery.getResultList()).thenReturn(Collections.EMPTY_LIST);
        when(mockedEntityManager.createNamedQuery("Tenderer.beginBy")).thenReturn(mockedQuery);
        
        List<String> result = searchBeanImpl.findTendererBeginBy("Ber");
        
        assertTrue(result.isEmpty());
    } 
}
