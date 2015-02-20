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
import Connector.Dispatcher;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * La classe dispatcher si occupa della ricezione dei messaggi e nell'invocare
 * l'opportuna procedura per la gestione di quel messaggio. Ogni qual volta viene
 * instanziato un oggetto di questo tipo è necessario impostare l'oggetto 
 * che gestisce la connessione. Utilizzando un protocollo UDP tale oggetto sarà
 * una DatagramSocket. Una volta impostata la socket udp,viene chiamato il metodo 
 * start sul riferimento all'oggetto che lancia il thread che rimane il ascolto
 * dei messaggi.
 * Questa classe può essere utilizzato sia lato client che lato server a patto
 * che venga settato il riferimento all'oggetto IUser o IMessagingService che 
 * rappresentano rispettivamente il client e il server.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class DispatcherUDP extends Dispatcher{
    
    
    private DatagramSocket cs;
    int MAX_LENGTH_MESSAGE=4048;
    
    
    /**
     * Costruttore del DispatcherUDP.
     */
    public DispatcherUDP(){}

    
    @Override
    public Message receive() throws Exception {
        
        byte [] incomingMessage= new byte[MAX_LENGTH_MESSAGE];
        DatagramPacket incomingPacket = new DatagramPacket(incomingMessage,incomingMessage.length);
        cs.receive(incomingPacket);        
        /*System.out.println(
                Thread.currentThread()+
                " packet received by " +
                incomingPacket.getAddress()+
                " porta: "+
                incomingPacket.getPort()
        );*/
        return (Message)BytesUtil.toObject(incomingPacket.getData());
    }
    
    
     /**
     * Questo metodo fa in modo che questo oggetto possa ricevere continuamente 
     * messaggi. Viene chiamato quando viene fatto avviare il Thread.
     */
    @Override
    public void run(){
        try{            
            //System.out.println("Il Dispatcher e' in ascolto a: " +cs+" "+port);
            while(Thread.currentThread().isInterrupted()!=true){
                Message m=receive();
                dispatch(m); 
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    
    /**
     * Viene impostata l'oggetto DatagramSocket che permetterà la comunicazione.
     * @param datagramSocket datagramSocket necessaria per comunicazione di rete.
     */
    @Override
    public void setConnection(Object datagramSocket) {
        if(datagramSocket instanceof DatagramSocket) cs=(DatagramSocket)datagramSocket;
    }
    
    
    /**
     * Ritorna l'oggetto DatagramSocket che permetterà la comunicazione. 
     * @return riferimento alla datagramSocket impostata.
     */
    @Override
    public DatagramSocket getConnection() {
        return cs;
    }
    
}
