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

package Common;

/**
 * Interfaccia che si occupa dell'aggiornamento dell'intefaccia grafica
 * utilizzata dagli utenti del servizio
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public interface IUser_Interaction {

    /**
     * Il metodo permette l'aggiornamento della lista delle stanze
     * 
     * @param list lista delle stanze presenti nel server
     */
    public void updateRoomList(String list);

    /**
     * Il metodo permette l'aggiornamento degli utenti presenti nella stanza 
     * selezionata
     * 
     * @param nameRoom nome della stanza
     * @param users utenti registati nella stanza
     */     
    public void updateUsersToRoom(String nameRoom, Object[] users);

    /**
     * Il metodo stampa in un'apposita area relativa alla stanza il log della
     * registrazione alla stanza
     * 
     * @param log log della registrazione
     * @param room nome della stanza
     */
    public void printLogRoom(String log, String room);

    /**
     * Il metodo stampa in un'apposita area il log della connessione al server
     * 
     * @param log log della connessione al server
     */
    public void printLog(String log);
    
}
