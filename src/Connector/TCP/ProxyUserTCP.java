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


import Common.Message;
import Common.Notify;
import Common.PublicNotify;
import Common.TypeNotify;
import Connector.ProxyUser;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.GregorianCalendar;


/**
 * Rappresenta localmente l'User che si trova in un host remoto.
 * Ha la stessa interfaccia all'oggetto IUser e inoltre gestisce la comunicazione
 * di rete in modo da far corrispondere la chiamata in locale dei suoi metodi con
 * una chiamata in remoto dei metodi dell'utente.
 * Utilizzando un tipo di comunicazione TCP ha bisogno un riferimento alla socket
 * che permette la comunicazione con l'User corrispettivo in remoto. E' possibile
 * inoltre impostare direttamente lo stream di rete senza considerare la socket
 * a patto che lo stream sia stato ricavato sempre facendo riferimento alla socket.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class ProxyUserTCP extends ProxyUser {

    
    private Socket socket;
    private ObjectOutputStream oos;

    
    /**
     * Costruttore del ProxyUserTCP.
     */
    public ProxyUserTCP(){}
    
    
    @Override
    public  void receiveNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender)
    {
        Notify notify=new Notify(type,content,calendar,sender,null);
        send(notify);
    }
    
    
    @Override
    public  void receivePublicNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender,
            String              roomName)
    {
        PublicNotify notify=new PublicNotify(type,content,calendar,sender,null,roomName);
        send(notify);
    }
    
    
    
    
    
    
    @Override
    public void send(Message msg){
        try{
            oos.writeObject(msg);
            oos.flush();
        }
        catch(Exception e){
        }
    }
    
    
    /**
     * Imposta lo stream di scrittura.
     * @param objectOutputStream stream di scrittura associato associato alla socket
     */
    public void setOutputStream(Object objectOutputStream){
        if(objectOutputStream instanceof ObjectOutputStream) oos=(ObjectOutputStream)objectOutputStream;
    }
    
    
    //non mi serve..
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
     * @param socket socket necessaria per la comunicazione di rete.
     */
    @Override 
    public void setConnection(Object socket){
        if(socket instanceof Socket) this.socket=(Socket)socket;
    }

    @Override
    public void receivePublicMessage(String room, String content, GregorianCalendar date, String sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
