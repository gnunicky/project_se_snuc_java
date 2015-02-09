/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnucServer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
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
            CommandParser instance=new CommandParser("/join #Medical");
            int index = 1;        
            String expResult = null;
            String result = instance.getParameter(index);
            assertEquals(expResult, result);
        }
    

}