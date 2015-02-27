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


import Common.Command;
import Common.IUser;
import Common.Message;
import Common.PublicMessage;
import Connector.ConnectionFactory;
import Connector.Dispatcher;
import Connector.ProxyMessagingService;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * Rappresenta localmente il servizio di messagistica che si trova in un host
 * remoto.
 * Ha la stessa interfaccia dell'oggetto MessagingSevice e inoltre gestisce la
 * comunicazione di rete in modo da far corrispondere la chimata in locale dei
 * suoi metodi con una chiamata in remoto dei metodi del servizio di messagistica.
 * La comunicazione di rete utilizzata è di tipo UDP.Una volta settatti indirizzo
 * e porta del servizio di messagistica in remoto , il protocollo non potendo 
 * instaurare connessioni, fa in modo di crearne una logica. Bisogna chiamare il
 * metodo connect con il quale sarà comunicata la nuova porta all'User a cui
 * dovrà inviare i messaggi da quel momento in poi. Bisogna inoltre avere un 
 * riferimento all'IUser in quanto questo oggetto si occuperà di avviare il
 * Dispatcher per la ricezione dei messaggi.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class ProxyMessagingServiceUDP extends ProxyMessagingService{
    
    
    private DatagramSocket cs;
    final private int MAX_LENTGH_REPLY=24;

    
    /**
     * Costruttore del ProxyMessagingServiceUDP.
     */
    public ProxyMessagingServiceUDP() {super();
    }
    
    
     /**
     * Richiede la connessione al servizio di messagistica proponendogli un nickname
     * scelto dall'User. Inoltre in questa fase se la connesione va a buon fine
     * verrà avviato il Dispatcher.
     * @param nickname nome utilizzato dall'User nel servizio di messagistica
     * @param user riferimento ad un oggetto di tipo IUser necessario per il 
     * Dispatcher che avvia dopo la connessione
     * @throws Exception In caso quancosa vada storto nella connessione
     */
    @Override
    public void connect(String nickname, IUser user) throws Exception {
            
            cs = new DatagramSocket();
            
            //******************************Propongo al server un nickname ******************************* 
            byte[] dataSend=BytesUtil.toByteArray(nickname);      
            DatagramPacket sendPacket = new DatagramPacket(dataSend,dataSend.length,getAddress(),getPort());
            cs.send(sendPacket);
            //********************************************************************************************
            
            
            //******************************Ricevo dal server la porta su cui avverrà comunicazione******
            byte [] availablePort=new byte[MAX_LENTGH_REPLY];
            DatagramPacket incomingPacket = new DatagramPacket(availablePort,availablePort.length);
            cs.setSoTimeout(1500);
            cs.receive(incomingPacket);
            cs.setSoTimeout(6000000);
            String reply=(String)BytesUtil.toObject(incomingPacket.getData());
            setPort(Integer.parseInt(reply));                                      //Setto la nuova porta
            //System.out.println("Response from server:" + reply);
            //*******************************************************************************************
            
            Dispatcher dispatcher=ConnectionFactory.getConnectionFactory().createDispathcer();
            dispatcher.setUser(user);
            dispatcher.setConnection(cs);
            
            new Thread(dispatcher).start();
    }

    
    @Override
    public void send(Message msg) {
        try{                     
            byte[] dataSend = BytesUtil.toByteArray(msg);
            DatagramPacket sendPacket = new DatagramPacket(dataSend,dataSend.length,getAddress(),getPort());
            cs.send(sendPacket);
            //System.out.println("Spedito dal client: "+dataSend + " lungo " + dataSend.length);
        }
        catch(Exception e){
            System.out.println("Proxy messaging service UDP errore send");
            e.printStackTrace();
        }
    }

    
    @Override
    public void publicMessage(
            String msg,
            String sender,
            String roomName
    )
    {
        PublicMessage message = new PublicMessage(roomName, msg, null, sender, null);
        send(message);
    }

    
    @Override
    public boolean commandHandler(
            String cmd,
            String sender
    )
    {
        Command message = new Command(cmd, null, sender, null);
        send(message);
        return true;
    }
    
    


    
     /**
     * Viene impostata l'oggetto DatagramSocket che permetterà la comunicazione.
     * @param datagramSocket datagramSocket necessaria per comunicazione di rete.
     */
    @Override
    public void setConnection(Object datagramSocket){
        if(datagramSocket instanceof DatagramSocket) this.cs=(DatagramSocket)datagramSocket;
    }
    
    
     /**
     * Ritorna l'oggetto DatagramSocket che permetterà la comunicazione. 
     * @return riferimento alla datagramSocket impostata.
     */
    @Override
    public DatagramSocket getConnection(){return cs;}

    @Override
    public void privateMessage(String msg, String sender, String receiver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
