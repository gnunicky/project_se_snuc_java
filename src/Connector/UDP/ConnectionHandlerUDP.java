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

package Connector.UDP;


import Connector.ConnectionFactory;
import Connector.ConnectionHandler;
import Connector.Dispatcher;
import Connector.ProxyUser;
import SnucServer.MessagingService;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * La classe opera lato server e ha come obittivo quello di far connettere 
 * mediante gli protocollo UDP l'User con il sistema MessagingService.
 * Essendo il protocollo UDP non orientato alla connessione tale sistema comunica
 * all'User la porta che verrà dedicata alla ricevezione dei messaggi di quel
 * determinato User , diversa dalla porta con cui vengono richieste le connessioni
 * (logiche). Ad ogni connessione (logica) questa classe interagisce con il 
 * servizio di messagistica facendo in modo che l'utente venga inserito
 * nell'elenco degli utenti online.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class ConnectionHandlerUDP extends ConnectionHandler{
    
    
    private DatagramSocket ss;
    private final int MAX_LENGTH_NICK=64;
    private int NUMBER_CONNECTION=1;

    
    /**
     * Costruttore ConnectionHandlerUDP.
     */
    public ConnectionHandlerUDP(){}
    
    
    /**
     * Chiama ciclicamente il metodo listeningConnection. Viene chiamato quando 
     * viene lanciato il Thread.
     */
    @Override
    public void run() {
        try {
            ss = new DatagramSocket(getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (Thread.currentThread().isInterrupted() != true) {
            listeningConnection();
        }
    }
    
    
    @Override
    public void listeningConnection() {
        
        byte[] incomingData = new byte[MAX_LENGTH_NICK];
        try {
            //********************** Richieste di connessioni *********************************************************
            System.out.println("Waiting request connection...");
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            ss.receive(incomingPacket);
            System.out.println("Request connection received by " + incomingPacket.getAddress() + " " + incomingPacket.getPort());
            String nickName = (String) BytesUtil.toObject(incomingPacket.getData());
            //*********************************************************************************************************

            //***********************Dati utile alla connessione logica**************************
            //int availablePort = getAvailablePort();                 //cerca una porta disponibile
            int availablePort = getAvailablePort();
            
            System.out.println("Il client comunica con questa porta: "+availablePort);
            InetAddress IPAddress = incomingPacket.getAddress();    //indirizzo mittente
            int port = incomingPacket.getPort();                    //porta     mittente
            //***********************************************************************************

            //***********************Invio porta di ascolto del server***************************
            byte[] reply = BytesUtil.toByteArray(availablePort + "");
            DatagramPacket replyPacket = new DatagramPacket(reply, reply.length, IPAddress, port);
            ss.send(replyPacket);
            //***********************************************************************************

            MessagingService service = (MessagingService) super.getServer();

            //Examinate nick
            nickName=service.examineNick(nickName);

            Dispatcher dispatcher
                    = ConnectionFactory.getConnectionFactory().createDispathcer();

            dispatcher.setConnection(new DatagramSocket(availablePort));         //Assegno al dispatcher la nuova porta
            dispatcher.setMessagingService(service);

            ProxyUser user = ConnectionFactory.getConnectionFactory().createProxyUser();
            user.setAddress(IPAddress);
            user.setPort(port);

            service.getOnlineUsers().put(nickName, user);    //inserisco il nuovo utente tra gli utenti on line
            new Thread(dispatcher).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Ha come obbiettivo quello ritornare una porta disponibile che verrà
     * comunicata all'utente in remoto per stabilire una connessione logica.
     * Tutti i messaggi dell'user arriveranno a in questa porta.
     * @return porta disponibile per la connessione logica con l'User.
     */
    private int getAvailablePort(){
        return getPort() + (NUMBER_CONNECTION++);
    }
}
