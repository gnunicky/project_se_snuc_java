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


import Common.Command;
import Common.IUser;
import Common.Message;
import Common.PublicMessage;
import Connector.ConnectionFactory;
import Connector.Dispatcher;
import Connector.ProxyMessagingService;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * Rappresenta localmente il servizio di messagistica che si trova in un host
 * remoto.
 * Ha la stessa iterfaccia all'oggetto MessagingSevice e inoltre gestisce la
 * comunicazione di rete in modo da far corrispondere la chimata in locale dei
 * suoi metodi con una chiamata in remoto dei metodi del servizio di messagistica.
 * La comunicazione di rete utilizzata è di tipo TCP. Una volta settatti indirizzo
 * e porta del servizio di messagistica in remoto , si può istaurare la
 * connessione grazie al metodo connect. Bisogna inoltre avere un riferimento
 * all'IUser in quanto questo proxy si occuperà di avviare il dispatcher per la
 * ricezione dei messaggi.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class ProxyMessagingServiceTCP extends ProxyMessagingService {

    
    private Socket socket;
    private ObjectOutputStream oos;

    
    /**
     * Costruttore ProxyMessagingServiceTCP.
     */
    public ProxyMessagingServiceTCP(){super();}
    
    
     /**
     * Richiede la connessione al servizio di messagistica proponendogli un 
     * nickname scelto dall'User. Inoltre in questa fase se la connesione va a 
     * buon fine verrà avviato il Dispatcher.
     * @param nickname nome utilizzato dall'User nel servizio di messagistica
     * @param user riferimento ad un oggetto di tipo IUser necessario per il 
     * Dispatcher che avvia dopo la connessione
     * @throws Exception In caso quancosa vada storto nella connessione
     */
    @Override
    public void connect(String nickname,IUser user) throws Exception{
            socket = new Socket();
            
            socket.connect(new InetSocketAddress(getAddress(),getPort()),3000);
            //Creo gli stream di imput e di output**********************************
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeUTF(nickname);
            oos.flush();
            
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            String reply=(String)ois.readUTF();
            System.out.println(reply);
            //**********************************************************************
            
            setOutputStream(oos);
            
            Dispatcher dispatcher=ConnectionFactory.getConnectionFactory().createDispathcer();
            dispatcher.setUser(user);
            dispatcher.setConnection(socket);
            
            new Thread(dispatcher).start();
            ((DispatcherTCP)dispatcher).setInputStream(ois);
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
    
    
    
    
    @Override
    public void send(Message msg){
        try {
            oos.writeObject(msg);
            oos.flush();
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("ProxyMessagingService: Send error!");
        }
    }
    
    
     /**
     * Imposta lo stream di scrittura
     * @param objectOutputStream stream di scrittura associato associato alla socket
     */
    public void setOutputStream(Object objectOutputStream){
        if(objectOutputStream instanceof ObjectOutputStream) oos=(ObjectOutputStream)objectOutputStream;
    }
    
    
    //Non mi serve..
    //public void setInputStream(Object o){}
    
    
     /**
     * Ritorna l'oggetto Socket che permetterà la comunicazione. 
     * @return riferimento alla socket impostata.
     */
    @Override
    public Socket getConnection(){
        return socket;
    }
    
    
     /**
     * Viene impostata l'oggetto Socket che permetterà la comunicazione.
     * @param socket socket necessaria per comunicazione di rete.
     */
    @Override 
    public void setConnection(Object socket){
        if(socket instanceof Socket) this.socket=(Socket)socket;
    }
}