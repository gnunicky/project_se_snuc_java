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

import SnucServer.MessagingService;
import Connector.TCP.ProxyUserTCP;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe Messaging Service Test
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class MessagingServiceTest {
    
    MessagingService instance;
    
    public MessagingServiceTest(){
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        System.out.println("------ Test MessagingService ------");
    }
    
    @AfterClass
    public static void tearDownClass(){
        
    }
    
    @Before
    public void setUp() throws Exception{
        instance = new MessagingService("config/Room.txt");
    }
    
    @After
    public void tearDown() {
        instance.getOnlineUsers().getOnlineUsers().clear();
    }

    /**
     * Test of run method, of class MessagingService.
     */
    /*@Test
    public void testRun() throws Exception{
        System.out.println("run");
        Socket socket = new Socket("localhost", 7777);

        //Creo gli stream di imput e di output**********************************
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeUTF("Leo");
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String reply = (String) ois.readUTF();
        System.out.println(reply);
        //**********************************************************************
        
        UserConnectionHandler result = (UserConnectionHandler)instance.getOnlineUsers().get("Leo");
        assertNotNull(result);
    }*/

    /**
     * Test of addUser method, of class MessagingService.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String user = "Leandro";
        String roomName = "#Medical";
        instance.getOnlineUsers().put(user,new ProxyUserTCP());
        boolean expResult = true;
        boolean result = instance.addUser(user,roomName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddUser1() {
        System.out.println("addUser (nick già inserito)");
        String user = "Leandro";
        String roomName = "#Medical";
        instance.getOnlineUsers().put(user,new ProxyUserTCP());
        boolean expResult = false;
        instance.addUser(user, roomName);
        boolean result = instance.addUser(user, roomName);
        assertEquals(expResult, result);
    }




    /**
     * Test of commandHandler method, of class MessagingService.
     */
    @Test
    public void testCommandHandler(){
        System.out.println("commandHandler");
        boolean expResult=true;
        boolean result;
        instance.getOnlineUsers().put("Leo",new ProxyUserTCP());
        result=instance.commandHandler("/listRooms","Leo");
        assertEquals(expResult,result);
         
    }
     /**
     * Test of commandHandler1 method, of class MessagingService.
     */
    @Test
    public void testCommandHandler1(){
        System.out.println("commandHandler (comando non riconosciuto)");
        boolean expResult=false;
        boolean result;
        instance.getOnlineUsers().put("Leo",new ProxyUserTCP());
        result=instance.commandHandler("/NotCommand","Leo");
        assertEquals(expResult,result);
    }

    /**
     * Test of getRoomsName method, of class MessagingService.
     */
    @Test
    public void testGetRoomsName() {
        System.out.println("getRoomsName");
        String expResult = "#Mathematics\n" +
                            "#Pharmacy\n" +
                            "#Medical\n" +
                            "#ElettronicEngineering\n" +
                            "#ComputerScience\n";
        String result = instance.getRoomsName();
        assertEquals(expResult, result);
    }



    /**
     * Test of examineNick method, of class MessagingService.
     */
    @Test
    public void testExamineNick() {
        System.out.println("examineNick");
        instance.getOnlineUsers().put("Nicola", new ProxyUserTCP());
        String proposeNick = "Nicola";
        
        String expResult = "_Nicola";
        String result = instance.examineNick(proposeNick);
        assertEquals(expResult, result);
    }
        /**
     * Test of examineNick method, of class MessagingService.
     */
    @Test
    public void testExamineNick1() {
        System.out.println("examineNick1");
        instance.getOnlineUsers().put("Nicola",new ProxyUserTCP());
        instance.getOnlineUsers().put("_Nicola",new ProxyUserTCP());
        String proposeNick = "Nicola";      
        String expResult = "__Nicola";
        String result = instance.examineNick(proposeNick);
        assertEquals(expResult, result);
    }
    
}
