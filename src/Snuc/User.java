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

package Snuc;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * La classe rappresenta l'utente del sistema
 *
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class User {

    private String nick;
    private Map<String, Collection<String>> roomsMap;

    /**
     * Costruttore della classe User
     */
    public User() {
        roomsMap = new HashMap();
    }

    /**
     * Il metodo imposta il nickname dell'utente
     *
     * @param nick nickname dell'utente
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Il metodo ritorna il nickname dell'utente
     *
     * @return nickname dell'utente
     */
    public String getNick() {
        return nick;
    }

    /**
     * Il metodo ritorna, in base al nome della stanza selezionata in cui è
     * registrato l'utente, la lista aggiornata degli utenti registrati a tale
     * stanza
     *
     * @param room nome della stanza
     * @return lista degli utenti(nickname) registrati alla stanza
     */
    public Collection<String> getUserList(String room) {
        return roomsMap.get(room);
    }

    /**
     * Il metodo permette l'aggiornamento della lista degli utenti registrati
     * nella specifica stanza
     *
     * @param roomName nome della stanza
     * @param list lista degli utenti registrati nella stanza
     */
    public void updateListRoom(String roomName, String[] list) {
        roomsMap.put(roomName, new LinkedList());
        Collection c = roomsMap.get(roomName);
        for (int i = 0; i < list.length; i++) {
            c.add(list[i]);
        }
    }

}
