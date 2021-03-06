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

import java.util.GregorianCalendar;

/**
 * L'interfaccia si occupa della ricezione delle notifiche e dei messaggi 
 * pubblici e privati
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public interface IUser {
    
    /**
     * Il metodo permette la ricezione delle notifiche inviate dal server
     * 
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param calendar data di invio della notifica
     * @param sender nickname del mittente
     */
    public abstract void receiveNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender
    );
    
    /**
     * Il metodo permette la ricezione delle notifiche pubbliche inviate dal 
     * server
     * 
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param calendar data di invio della notifica
     * @param sender nickname del mittente
     * @param roomName nome della stanza in cui sarà inviata la notifica pubblica
     */
    public abstract void receivePublicNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender,
            String              roomName
    );
    
    /**
     * Il metodo permette la ricezione dei messaggi pubblici
     * 
     * @param room nome della stanza
     * @param content contenuto del messaggio pubblico
     * @param date data di invio del messaggio pubblico
     * @param sender nickname del mittente
     */
    public abstract void receivePublicMessage(
            String              room,
            String              content,
            GregorianCalendar   date,
            String              sender
    );

    
    /**
     * Il metodo permette la ricezione dei messaggi privati
     * 
     * @param receiver nickname del destinatario del messaggio privato
     * @param content contenuto del messaggio privato
     * @param date data di invio del messaggio privato
     * @param sender nickname del mittente del messaggio privato
     */
    public abstract void receivePrivateMessage(
            String              receiver,
            String              content,
            GregorianCalendar   date,
            String              sender
    );
}
