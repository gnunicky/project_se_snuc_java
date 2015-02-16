/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnucServer;


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
 * @author Leandro
 */
public class MessagingServiceTest {
    
    static MessagingService instance;
    
    public MessagingServiceTest(){
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        System.out.println("------ Test MessagingService ------");
//        instance = new MessagingService(0);
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
   
}
