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
import Connector.Dispatcher;
import java.io.ObjectInputStream;
import java.net.Socket;


/**
 * La classe dispatcher si occupa della ricezione dei messaggi e nell'invocare
 * l'opportuna procedura per la gestione di quel messaggio. Ogni qual volta viene
 * instanziato un oggetto di questo tipo è necessario impostare l'oggetto 
 * che gestisce la connessione. Utilizzando un protocollo TCP tale oggetto sarà
 * una socket. Una volte impostata la socket , viene chiamato il metodo start
 * sul riferimento all'oggetto che lancia il thread che rimane in ascolto dei
 * messaggi.
 * Questa classe può essere utilizzata sia lato client che lato server a patto
 * che venga settato il riferimento all'oggetto IUser o IMessagingService che 
 * rappresentano rispettivamente i client e il server.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class DispatcherTCP extends Dispatcher{

    
    private Socket socket;
    private ObjectInputStream ois;
    
    
    /**
     * Costruttore del DispatcherTCP.
     */
    public DispatcherTCP(){}
        
    
    /**
     * Questo metodo fa in modo che questo oggetto possa ricevere continuamente 
     * messaggi. Viene chiamato quando viene fatto avviare il Thread.
     */
    @Override
    public void run() {
        try{
            while (Thread.currentThread().isInterrupted() != true) {
                    Message m=receive();
                    dispatch(m);           
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    
    @Override
    public Message receive() throws Exception{
        return (Message) ois.readObject();        
    }
    
    
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

    
     /**
     * Imposta lo stream di lettura
     * @param objectInputStream stream di lettura associato associato alla socket
     */
    public void setInputStream(Object objectInputStream) {
        if (objectInputStream instanceof ObjectInputStream) {
            ois = (ObjectInputStream) objectInputStream;
        }
    }

    
    //Non so se mi serve..
    //public void setOutputStream(Object o) {
    //}
}
