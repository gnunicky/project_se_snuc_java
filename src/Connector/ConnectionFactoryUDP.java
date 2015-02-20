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

import Connector.UDP.ProxyUserUDP;
import Connector.UDP.ConnectionHandlerUDP;
import Connector.UDP.DispatcherUDP;
import Connector.UDP.ProxyMessagingServiceUDP;

/**
 * Si occuperà di creare oggetti indispensabili per la connessione relativi al
 * protocollo UDP.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class ConnectionFactoryUDP extends ConnectionFactory{
    
    
    /**
     * Costruttore della ConnectionFactoryUDP.
     */
    public ConnectionFactoryUDP(){}
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ProxyMessagingServiceUDP.
     * @return istanza di tipo ProxyMessagingServiceUDP
     */
    @Override
    public  ProxyMessagingService createProxyMessagingSevice(){
        return new ProxyMessagingServiceUDP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ProxyUserUDP.
     * @return istanza di tipo ProxyUserUDP
     */
    @Override
    public  ProxyUser createProxyUser(){
        return new ProxyUserUDP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo DispatcherUDP.
     * @return istanza di tipo DispatcherUDP
     */
    @Override
    public  Dispatcher createDispathcer(){
        return new DispatcherUDP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ConnectionHandlerUDP.
     * @return istanza di tipo ConnectionHandlerUDP
     */
    @Override
    public  ConnectionHandler createConnectionHandler(){
        return new ConnectionHandlerUDP();
    }
}
