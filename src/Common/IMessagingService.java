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
 * Interfaccia che permette l'invio di messaggi pubblici o privati e che si 
 * occupa della gestione dei comandi inviati al server
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public interface IMessagingService {
    

     /**
     * Il metetodo permette di inviare messaggi pubblici 
     * 
     * @param msg contenuto del messaggio pubblico
     * @param sender nickname del mittente del messaggio
     * @param roomName nome della stanza in cui Ã¨ stato spedito il messaggio
     */
    public void publicMessage(
            String msg,
            String sender,
            String roomName
    );
    
    /**
     * Il metodo permette di gestire un comando in base al tipo di comando 
     * inviato
     * 
     * @param cmd comando
     * @param sender nickname del mittente
     * @return <code>true</code> se il comando è stato riconosciuto e gestito
     *         <code>false</code> se il comando non è stato riconosciuto
     */
    public boolean commandHandler(
            String cmd,
            String sender
    );
    


}
