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

package Connector;


import Common.IUser;
import Common.Message;
import java.net.InetAddress;


/**
 * Rappresenta localmente l'User che si trova in un host remoto.
 * Ha la stessa interfaccia all'oggetto IUser e inoltre gestisce la comunicazione
 * di rete in modo da far corrispondere la chiamata in locale dei suoi metodi con
 * una chiamata in remoto dei metodi dell'utente.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public abstract class ProxyUser implements IUser{
    
    
    private InetAddress address;
    private int port;
    
    
    /**
     * Costruttore del ProxyUser.
     */
    public ProxyUser(){}
    
    
    /**
     * Imposta l'indirizzo dell'host remoto con cui deve comunicare.
     * @param address Indirizzo dell'User
     */
    public void setAddress(InetAddress address) {
        this.address = address;
    }
    
    
    /**
     * Mette a disposizione l'indirizzo dell'host remoto impostato precendentemente.
     * @return Indirizzo dell'User
     */
    public InetAddress getAddress() {
        return address;
    }
    
    
    /**
     * Imposta la porta dell'host remoto con cui deve comunicare.
     * @param port Porta dell'utente
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    
    /**
     * Mette a disposizione la porta dell'host remoto impostato precendentemente.
     * @return Porta dell'utente
     */
    public int getPort() {
        return port;
    }
    
    
    /**
     * Invia il messaggio al utente
     * @param message messaggio da inviare
     */
    public abstract void send(Message message);
    
    
    /**
     * Permette di settare l'oggetto necessario per la comunicazione di rete.
     * @param connection  riferimento alla connessione
     */
    public abstract void setConnection(Object connection);
    
    
    /**
     * Ritorna l'oggetto necessario per la comunicazione di rete. 
     * @return riferimento alla connessione
     */
    public abstract Object getConnection();
    
}
