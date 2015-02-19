/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnucServer;

import SnucServer.Room;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class RoomTest {
    
    private Room instance;
    
    public RoomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("------ Test Room ------");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance=new Room("#Medical"); 
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of addUser method, of class Room.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");    
        boolean expResult = true;
        boolean result = instance.addUser("Daniele");
        assertEquals(expResult, result);             
    }  

        /**
     * Test of addUser method, of class Room.
     */
    @Test
    public void testAddUser1() {
        System.out.println("addUser (same name)");      
        boolean expResult = false;
        instance.addUser("Daniele");
        boolean result = instance.addUser("Daniele");      
        assertEquals(expResult, result);       
    }
    
    

    /**
     * Test of getUsersToString method, of class Room.
     */
    @Test
    public void testGetUsersToString(){
        System.out.println("getUsersToString");
        instance.addUser("Daniele");
        instance.addUser("Leandro");
        instance.addUser("Nicola");
        String expResult =  "Daniele\n"+
                            "Leandro\n"+
                            "Nicola\n";
        String result = instance.getUsersToString();
        assertEquals(expResult, result);
    }
}
