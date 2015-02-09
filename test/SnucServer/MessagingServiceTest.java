/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnucServer;


import SnucServer.UserConnectionHandler;
import SnucServer.MessagingService;
import Common.Command;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
public class MessagingServiceTest {
    
    static MessagingService instance;
    
    public MessagingServiceTest(){
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        System.out.println("------ Test MessagingService ------");
        instance = new MessagingService(0);
        //new Thread(instance).start();
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() throws Exception{
    }
    
    @After
    public void tearDown() {
        instance.getOnlineUsers().clear();
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
    public void testAddUser() throws Exception{
        System.out.println("addUser");
        MessagingService instance = new MessagingService(0);
        String user = "Leandro";
        String roomName = "#Medical";
        
        boolean expResult = true;
        boolean result = instance.addUser(user, roomName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddUser1() throws Exception{
        System.out.println("addUser (nick giÃ  inserito)");
        MessagingService instance = new MessagingService(0);
        String user = "Leandro";
        String roomName = "#Medical";
        
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
        Command cmd = new Command("/listRooms", null, "Leo",null);
        boolean expResult=true;
        boolean result;
        instance.getOnlineUsers().put("Leo",new UserConnectionHandler(null,instance));
        result=instance.commandHandler(cmd);
        assertEquals(expResult,result);
         
    }
     /**
     * Test of commandHandler1 method, of class MessagingService.
     */
    @Test
    public void testCommandHandler1(){
        System.out.println("commandHandler (comando non riconosciuto)");
        Command cmd = new Command("/NotCommand", null,"Leo",null);
        boolean expResult=false;
        boolean result;
        instance.getOnlineUsers().put("Leo",new UserConnectionHandler(null,instance));
        result=instance.commandHandler(cmd);
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
        instance.getOnlineUsers().put("Nicola", null);
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
        instance.getOnlineUsers().put("Nicola", null);
        instance.getOnlineUsers().put("_Nicola", null);
        String proposeNick = "Nicola";      
        String expResult = "__Nicola";
        String result = instance.examineNick(proposeNick);
        assertEquals(expResult, result);
    }
}
