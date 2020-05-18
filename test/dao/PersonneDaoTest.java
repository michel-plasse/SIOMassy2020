/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import java.util.List;
import modele.Personne;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aline
 */
public class PersonneDaoTest {
    
    public PersonneDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    

    /**
     * Test of estValide method, of class PersonneDao.
     */
  

    @Test
  public void testEstValide() throws Exception {
    System.out.println("estValide");
    String mail = "edwarbarrera@gmail.com";
    boolean expResult = true;
    boolean result = PersonneDao.estValide(mail);
    assertEquals(expResult, result);
    if(!expResult == result){
    fail("The test case is a prototype.");
    }
  }

    

    
}
