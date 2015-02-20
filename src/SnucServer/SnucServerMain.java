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

package SnucServer;

import Connector.ConnectionFactory;
import Connector.ConnectionHandler;
import java.io.IOException;

/**
 * La classe contiene il main del lato del Server
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class SnucServerMain {

    public static void main(String[] arg) {
        try {
            MessagingService server = new MessagingService("config/Room.txt");
                        
            ConnectionHandler connectionHandler=
                    ConnectionFactory.getConnectionFactory().createConnectionHandler();
            connectionHandler.setPort(7777);
            connectionHandler.setService(server);
            
            new Thread(connectionHandler).start(); //Thread in attesa di connessioni...
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore avvio server");
        }
    }

}
