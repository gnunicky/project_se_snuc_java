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

import java.util.ArrayList;
import java.util.Collection;

/**
 * La classe rappresenta le stanze presenti nel server
 *
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class Room {

    final private String name;

    final private Collection<String> users, banUser;

    /**
     * Costruttore della classe Room
     *
     * @param name nome della stanza
     */
    public Room(String name) {
        this.name = name;
        this.users = new ArrayList();
        this.banUser = new ArrayList();
    }

    /**
     * Il metodo ritorna il nome della stanza
     *
     * @return nome della stanza
     */
    public synchronized String getName() {
        return name;
    }

    /**
     * Il metodo permette l'inserimento di un utente nella lista degli utenti
     * registrati alla stanza
     *
     * @param u nickname dell'utente
     * @return <code>true</code> se l'utente è stato inserito correttamente
     * <code>false</code> se l'utente è già  registrato nella stanza
     */
    public synchronized boolean addUser(String u) {
        if (!users.contains(u)) {
            users.add(u);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Il metodo permette la rimozione dell'utente dalla lista degli utenti
     * registrati alla stanza
     *
     * @param u nickname dell'utente
     */
    public synchronized void delUser(String u) {
        users.remove(u);
    }

    /**
     * Il metodo verifica se la stanza è vuota
     *
     * @return <code>true</code> se la stanza è vuota <code>false</code> se la
     * stanza non è vuota e quindi è presente almeno un utente nella lista degli
     * utenti registrati alla stanza
     */
    public synchronized Boolean emptyRoom() {
        return users.isEmpty();
    }

    /**
     * Il metodo restituisce la lista degli utenti registrati alla stanza
     *
     * @return lista degli utenti registrati alla stanza sottoforma di stringa
     */
    public synchronized String getUsersToString() {
        String list = "";
        for (String u : users) {
            list = list.concat(u + "\n");
        }
        return list;
    }

    /**
     * Il metodo restituisce la lista degli utenti registrati alla stanza
     *
     * @return lista degli utenti registrati alla stanza
     */
    public synchronized Collection<String> getUsers() {
        return users;
    }

}
