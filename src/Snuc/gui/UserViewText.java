/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snuc.gui;


import Common.IUser_Interaction;
import Snuc.gui.Split;
import Snuc.User;
import Snuc.User;
import Snuc.UserController;
import Snuc.UserController;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import static java.lang.System.in;
import java.util.Scanner;

/**
 *
 * @author nicky
 */
public class UserViewText implements IUser_Interaction{
    User usertext;
    UserController controller;
    
    public UserViewText(UserController controller,User usertext) {
        this.controller=controller;
        this.usertext=usertext;
    }
           
    public void clearnetbeans( ) {
        try {
             Robot pressbot = new Robot();
             pressbot.keyPress(17); // Holds CTRL key.
             pressbot.keyPress(76); // Holds L key.
             pressbot.keyRelease(17); // Releases CTRL key.
             pressbot.keyRelease(76); // Releases L key.
            } catch (AWTException ex) {
                ex.printStackTrace();
                //Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void clearconsole() {
        try {
                final String os = System.getProperty("os.name");
                    if (os.contains("Windows")) {
                     Runtime.getRuntime().exec("cls");
                    }
                    else {
                         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                          String cmd = "clear";
                          Process p = Runtime.getRuntime().exec(cmd);
                          BufferedReader pbr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                          String line = pbr.readLine();
                          while (line != null) {
                            System.out.println(line);
                            line = pbr.readLine(); 
                          } 
                    } 
            } catch (final Exception e) {
               //  Handle any exceptions.
            }
    }
        
    public static void menu() {
        System.out.println("        Smart Intelligent University Communications");
        System.out.println("**************************************************************");
        System.out.println("Connection to Server    : /connect <nickname> <address> <port>");
        System.out.println("Get List Rooms          : /list");
        System.out.println("Join Rooms              : /join <#room>");
        System.out.println("Sent a public message   : /me <message>");
        System.out.println("Start a Private Message : /query <nick>");
        System.out.println("Quit Room               : /quit <#room>");
        System.out.println("Exit                    : /exit");
        System.out.println("Clear the window        : /clear");
        System.out.println("**************************************************************");
    }
    
    public void start () {
        Split strsplit=new Split();
        String str;
        String delemeter=" ";        
        Scanner in = new Scanner(System.in);        
        boolean quit = false;
        menu();
        do {
            System.out.print("Type choice: ");
            str = in.nextLine();
            String [] result=strsplit.getsplit(str, delemeter);
            switch (result[0]) {
                case "/connect":
                   try {
                            System.out.println("Nickname:" +result[1]);
                            System.out.println("Address: "+result[2]);
                            System.out.println("Port: "+Integer.parseInt(result[3]));
                            
                            controller.connect(result[1],result[2],Integer.parseInt(result[3]));
                            
                        } catch (Exception e ) {
                            System.err.print("This program takes 3 parameters: ");
                            System.err.println("Nickname, Address, Port ");
                        }
                    break;
                case "/list":
                            System.out.println("list:" +result[0]);
                    break;
                case "/join":
                    try {
                            System.out.println("name room: " +result[1]);
                        } catch (ArrayIndexOutOfBoundsException e ) {
                            System.err.print("This program takes 1 parameters: ");
                            System.err.println("<#nameroom> ");
                        }
                    break;
                case "/me":
                        try {
                            System.out.println("message: " +result[1]);
                        } catch (ArrayIndexOutOfBoundsException e ) {
                            System.err.print("This program takes 1 parameters: ");
                            System.err.println("<message> ");
                        }
                    break;
                case "/query":
                        try {
                            System.out.println("nickname:" +result[1]);
                        } catch (ArrayIndexOutOfBoundsException e ) {
                            System.err.print("This program takes 1 parameters: ");
                            System.err.println("<nickname> ");
                        }
                    break;
                case "/quit":
                    System.out.println("quit");
                    break; 
                case "/clear":
                     clearnetbeans();
                     menu();
                    break;    
                case "/exit":
                    System.out.println("exit");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!quit);
        System.out.println("Bye-bye!");
    }
    
    
    public void updateRoomList(String list){}

    public void updateUsersToRoom(String nameRoom, Object[] users){}

    public void printLogRoom(String log, String room){}

    public void printLog(String log){}

    public void printPublicMessage(String content, String room){}

    public void printPrivateMessage(String content, String sender){}
}

