/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
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
