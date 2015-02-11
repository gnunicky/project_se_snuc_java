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

import Common.Command;
import Common.Message;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * La classe opera lato Server e si occupa della gestione della comunicazione
 * del Server con il Client, della ricezione e dell'invio dei messaggi.
 *
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class UserConnectionHandler implements Runnable {

    final private Socket cs;
    final private MessagingService server;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    /**
     * Costruttore della classe UserConnectionHandler
     *
     * @param cs socket
     * @param server server
     */
    public UserConnectionHandler(Socket cs, MessagingService server) {
        this.cs = cs;
        this.server = server;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted() != true) {
            Message msg = receiveMessage();
            if (msg != null) {
                dispatch(msg);
            }
        }
    }

    /**
     * Il metodo si occupa della ricezione dei messaggi da parte del Server
     *
     * @return messaggio ricevuto
     */
    public Message receiveMessage() {
        try {
            return (Message) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * In base al tipo di messaggio che viene passato come parametro, chiama
     * l'oppurtuno metodo per la gestione di tale messaggio.
     *
     * @param msg messaggio che verrà  smistato nel metodo opportuno
     * @return <code>true</code> se il messaggio è stato gestito correttamente
     * <code>false</code> se il messaggio non è stato gestito correttamente
     */
    private boolean dispatch(Message msg) {
        if (msg instanceof Command) {
            return server.commandHandler((Command) msg);
        }
        return false;
    }

    /**
     * Il metodo si occupa dell'invio dei messaggi da parte del Server
     *
     * @param m messaggio da inviare
     */
    public void sendMessage(Message m) {
        try {
            oos.writeObject(m);
            oos.flush();
        } catch (Exception e) {
        }
    }

    /**
     * Imposta lo stream di lettura
     *
     * @param ois stream di lettura associato associato alla socket
     */
    protected void setInputStream(ObjectInputStream ois) {
        this.ois = ois;
    }

    /**
     * Imposta lo stream di scrittura
     *
     * @param oos stream di scrittura associato alla socket
     */
    protected void setOutputStream(ObjectOutputStream oos) {
        this.oos = oos;
    }

}
