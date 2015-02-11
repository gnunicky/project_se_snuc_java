/**
 * SNUC  is a program written in Java SE (version 1.8.0_31) during a project of 
 * course Software Engineering in University of Catania academic year 2014-15.
 * SNUC is Smart Network University Communications.
 * 
 * Copyright (C) 2015 onwards Leandro Russo (leandrorusso90@gmail.com)
 * Copyright (C) 2015 onwards Invincibile Daniele (d.invincibile@gmail.com)
 * Copyright (C) 2015 onwards Nicola Didomenico (nicola.didomenico@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public Licens along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
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
 * Classe Room Test
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
