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

import Common.Message;
import Common.Notify;
import Common.PublicNotify;
import Common.TypeNotify;
import Connector.ProxyUser;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.GregorianCalendar;


/**
 * Rappresenta localmente l'User che si trova un host remoto.
 * Ha la stessa interfaccia dell'oggetto IUser e inoltre gestisce la comunicazione
 * di rete in modo da far corrispondere la chiamata in locale dei suoi metodi con
 * una chiamata in remoto dei metodi dell'utente.
 * Utilizzando un tipo di comunicazione UDP non orientata alla connessione, si
 * ha bisogno solamente di impostare l'indirizzo ip e la porta dell'User romoto
 * e da quel momento in poi questo oggetto invierà pacchetti esclusvamente a 
 * quella porta e quell'indirizzo.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class ProxyUserUDP extends ProxyUser{
    
    
    private DatagramSocket cs;
    
    
    /**
     * Costruttore del ProxyUserUDP.
     */
    public ProxyUserUDP(){
    }

    
    @Override
    public void send(Message msg) {
        try{
            if(cs==null) cs = new DatagramSocket();                        
            byte[] dataSend = BytesUtil.toByteArray(msg);
            DatagramPacket sendPacket = new DatagramPacket(dataSend,dataSend.length,getAddress(),getPort());
            cs.send(sendPacket);
            //System.out.println("Spedito a "+getAddress()+" "+getPort());
            //System.out.println(msg+ " lungo " + dataSend.length);
        }
        catch(Exception e){e.printStackTrace();}
    }
    

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
    
    
    
    
    
    
     /**
     * Ritorna l'oggetto DatagramSocket che permetterà la comunicazione. 
     * @return riferimento alla datagramSocket impostata.
     */
    @Override
    public DatagramSocket  getConnection() {
        return cs;
    }
    
    
    /**
     * Viene impostata l'oggetto DatagramSocket che permetterà la comunicazione.
     * @param datagramSocket datagramSocket necessaria per comunicazione di
     * rete.
     */
    @Override
    public void setConnection(Object datagramSocket) {
        if (datagramSocket instanceof DatagramSocket)
             cs = ((DatagramSocket) datagramSocket);
    }

    @Override
    public void receivePublicMessage(String room, String content, GregorianCalendar date, String sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
