/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snuc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leandro
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of updateListRoom method, of class User.
     */
    @Test
    public void testUpdateListRoom() {
        System.out.println("updateListRoom");
        String roomName = "#Medical";
        String[] list = {"Daniele","Leandro","Nicola"};
        User instance = new User();
        instance.updateListRoom(roomName,list);
        
        Object[] result =instance.getUserList(roomName).toArray();
        assertArrayEquals(result,list);
    }
    
}