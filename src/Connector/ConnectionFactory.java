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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Dichiara l'interfaccia per le ConnectionFactory relative alla tipo
 * di comunicazione.
 * Delega la creazione di oggetti utili alla comunicazione alle sue sottoclassi.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public abstract class ConnectionFactory {
    
    static private ConnectionFactory instance=null;    
    /**
     * Costruttore della ConnectionFactory
     */
    public ConnectionFactory(){}
    
    /**
     * In base ad opportuno file di configurazione Confing.txt si occuperà di
     * istanziare la Factory concreta relativa alla tipologia di comunicazione
     * che si vuole utilizzare.
     * @return istanza di tipo ConnectionFactory relativa al tipo di comunicazione
     * di rete.
     */
    public static ConnectionFactory getConnectionFactory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config/Config.txt"));
            String line = br.readLine();
            if (line.equalsIgnoreCase("TCP")) {
                return (instance==null)?new ConnectionFactoryTCP():instance;
            } else {
                return (instance==null)?new ConnectionFactoryUDP():instance;
            }
        } catch (IOException e) {
            System.out.println("Config File not found!");
            return null;
        }
    }
    
    
    /**
     * Crea oggetti di tipo ProxyMessagingSevice in base alla configurazione che
     * viene data
     * @return istanza di tipo ProxyMessagingSevice
     */
    public abstract ProxyMessagingService createProxyMessagingSevice();
    
    
    /**
     * Crea oggetti di tipo ProxyUser in base alla configurazione che
     * viene data
     * @return istanza di tipo ProxyUser
     */
    public abstract ProxyUser createProxyUser();
    
    
     /**
     * Crea oggetti di tipo Dispathcer in base alla configurazione che
     * viene data
     * @return istanza di tipo Dispathcer
     */
    public abstract Dispatcher createDispathcer();
    
    
     /**
     * Crea oggetti di tipo ConnectionHandler in base alla configurazione che
     * viene data
     * @return istanza di tipo ConnectionHandler
     */
    public abstract ConnectionHandler createConnectionHandler();
}
