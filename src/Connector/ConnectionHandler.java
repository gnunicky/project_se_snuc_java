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


import Common.IMessagingService;
import SnucServer.MessagingService;


/**
 * La classe opera lato server e ha come obittivo quello di far connettere gli
 * User con il sistema MessagingService. Una volta connessi il sistema
 * interagisce con il servizio di messagistica e si occupera di inserire gli
 * User tra gli utenti Online.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public abstract class ConnectionHandler implements Runnable{
    
    
    private int port;
    private IMessagingService server;

    
    /**
     * Costruttore del ConnectionHandler.
     */
    public ConnectionHandler(){}
    
    
    /**
     * Imposta la porta di ascolto del ConnectionHandler. 
     * @param port porta di ascolto
     */    
    public void setPort(int port) {
        this.port = port;
    }
    
    
    /**
     * Ritorna la porta di ascolto del ConnectionHandler. 
     * @return la porta di ascolto
     */
    public int getPort() {
        return port;
    }
    
    
    /**
     * Imposta il riferimento al servizio di messagistica con cui dovrà interagire. 
     * @param server riferimento al servizio di messagistica
     */
    public void setService(MessagingService server){
        this.server=server;
    }
    
    
    /**
     * Ritorna il riferimento al servizio di messagistica con cui dovrà interagire. 
     * @return riferimento il servizio di messagistica 
     */
    public IMessagingService getServer() {
        return server;
    }
    
    
    /**
     * Questo metodo si occupa di ascoltare le rischieste di connessioni degli
     * User.
     */
    public abstract void listeningConnection();
}
