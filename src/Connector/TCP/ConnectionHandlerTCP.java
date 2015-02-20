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

package Connector.TCP;


import Connector.ConnectionFactory;
import Connector.ConnectionHandler;
import Connector.Dispatcher;
import Connector.ProxyUser;
import SnucServer.MessagingService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * La classe opera lato server e ha come obittivo quello di far connettere 
 * mediante gli protocollo TCP User con il sistema MessagingService. Utilizzando
 * il protocollo TCP , ad ogni User che viene connesso viene associato un canale
 * di comunicazione che permette lo scambio di messaggi.
 * Ad ogni connessione questa classe interagisce con il servizio di messagistica
 * facendo in modo che l'utente venga inserito nell'elenco degli utenti online.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class ConnectionHandlerTCP extends ConnectionHandler {

    
    private ServerSocket ss;

    
    /**
     * Costruttore del ConnectionHandlerTCP.
     */
    public ConnectionHandlerTCP(){}
    
    
    /**
     * Chiama ciclicamente il metodo listeningConnection. Viene chiamato quando 
     * viene lanciato il Thread.
     */
    @Override
    public void run() {
        try{
            ss = new ServerSocket(getPort());
        }
        catch(Exception e){e.printStackTrace();}
        while (Thread.currentThread().isInterrupted() != true)
            listeningConnection();
    }

    
    @Override
    public void listeningConnection() {
        try {
            System.out.println("Waiting connection...");
            Socket cs = ss.accept();          
            System.out.println("Connected: " + cs);
            
            //Creo gli stream di imput e di output********************************
            ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
            String nickName = (String) ois.readUTF();

            ObjectOutputStream oos = new ObjectOutputStream(cs.getOutputStream());
            String msg_server = "...Checking...";
            oos.writeUTF(msg_server);
            //********************************************************************

            MessagingService service = (MessagingService) super.getServer();

            nickName=service.examineNick(nickName);

            Dispatcher dispatcher
                    = ConnectionFactory.getConnectionFactory().createDispathcer();
            dispatcher.setConnection(cs);
            dispatcher.setMessagingService(service);
            ((DispatcherTCP)dispatcher).setInputStream(ois);

            ProxyUser user = ConnectionFactory.getConnectionFactory().createProxyUser();
            //user.setConnection(cs);
            ((ProxyUserTCP)user).setOutputStream(oos);

            service.getOnlineUsers().put(nickName, user);
            new Thread(dispatcher).start();
        } 
        catch (IOException e){e.printStackTrace();}
    }
    
    
}
