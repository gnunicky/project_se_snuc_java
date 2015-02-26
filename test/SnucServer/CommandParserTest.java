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

import SnucServer.CommandParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe Command Parser Test
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class CommandParserTest {
     
    public CommandParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("------ Test CommandParser ------");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp(){
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of getCommand method, of class CommandParser.
    */
    @Test
    public void testGetCommand() {
        System.out.println("getCommand");
        CommandParser instance=new CommandParser("/join '#Medical'");        
        String expResult = "/join";
        String result = instance.getCommand();
        assertEquals(expResult, result);   
    }
    
    /*
    * Test of getCommand method for public message command with 2 parameters
    */
     @Test
    public void testGetCommand1() {
        System.out.println("getCommand1");
        CommandParser instance=new CommandParser("join '#Medical'"); 
        String result = instance.getCommand();
        assertNull(result);
    }
    
    /**
     * Test of testGetCommand2 method, of class CommandParser.
     */
    @Test
    public void testGetCommand2() {
        System.out.println("getCommand2");        
        CommandParser instance=new CommandParser("/listRooms");
        String expResult = "/listRooms";
        String result = instance.getCommand();
        assertEquals(expResult, result);
    }
        /**
     * Test of testGetCommand2 method, of class CommandParser.
     */
    @Test
    public void testGetCommand3() {
        System.out.println("getCommand3");        
        CommandParser instance=new CommandParser("/join #Medical");
        String result = instance.getCommand();
        assertNull(result);
    }
     /**
     * Test of testGetComman4 method, of class CommandParser.
     */
    @Test
    public void testGetCommand4() {
        System.out.println("getCommand4");        
        CommandParser instance=new CommandParser("/join #Medical'");
        String result = instance.getCommand();
        assertNull(result);
    }
    
     /**
     * Test of testGetComman4 method, of class CommandParser.
     */
    @Test
    public void testGetCommand5() {
        System.out.println("getCommand5");        
        CommandParser instance=new CommandParser("/join '#Medical");
        String result = instance.getCommand();
        assertNull(result);
    }

    
    /**
     * Test of getParameter method, of class CommandParser.
     */
    @Test
    public void testGetParameter() {
        System.out.println("getParameter");
        CommandParser instance=new CommandParser("/join '#Medical'");
        int index = 1;        
        String expResult = "#Medical";
        String result = instance.getParameter(index);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetParameter2() {
        System.out.println("getParameter2");
        CommandParser instance=new CommandParser("/join '#Medical'");
        int index = 2;        
        String expResult = null;
        String result = instance.getParameter(index);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetParameter3() {
        System.out.println("getParameter3");
        CommandParser instance = new CommandParser("/join #Medical");
        int index = 1;
        String expResult = null;
        String result = instance.getParameter(index);
        assertEquals(expResult, result);
    }

    /*
     * Test of getParameter method for public message command with 2 parameters
     */
    @Test
    public void testGetParameter4() {
        System.out.println("getParameter4");
        CommandParser instance = new CommandParser("/msg '#Medical' 'Example public message text'");
        int index_1 = 1;
        int index_2 = 2;
        String expResult_1 = "#Medical";
        String expResult_2 = "Example public message text";
        String result_1 = instance.getParameter(index_1);
        String result_2 = instance.getParameter(index_2);
        assertEquals(expResult_1, result_1);
        assertEquals(expResult_2, result_2);
    }


}
