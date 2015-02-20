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
 * La classe rappresenta le notifiche pubbliche che il Server invia agli utenti 
 * per comunicare un determinato evento
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class PublicNotify extends Notify {

    private String room;
    
    /**
     * Costruttore della classe PublicNotify
     * 
     * @param notify tipo di notifica
     * @param content contenuto della notifica
     * @param date data di invio della notifica
     * @param sender nickname del mittente
     * @param type tipo di messaggio
     * @param roomName nome della stanza
     */ 
    public PublicNotify(
            TypeNotify          notify,
            String              content,
            GregorianCalendar   date,
            String              sender,
            Enum                type,
            String              roomName
            )
    {
        super(notify,content,date,sender,type);        
        this.room = roomName;
    }

    /**
     * Il metodo ritorna il nome della stanza relativa alla notifica pubblica
     * 
     * @return nome della stanza
     */
    public String getRoom() {
        return room;
    }

    /**
     * Il metodo imposta il nome della stanza relativa alla notifica pubblica
     * 
     * @param room nome della stanza
     */
    public void setRoom(String room){
        this.room = room;
    }
    
    /**
     * Il metodo imposta il formato del testo della notifica pubblica
     * 
     * @param type tipo della notifica
     * @param content contenuto del messaggio 
     * @param calendar data del messaggio
     * @param sender nickname del mittente
     * @param roomName nome della stanza
     * @return notifica pubblica formattata
     */ 
    public static String textFormat(
            TypeNotify type,
            String content,
            GregorianCalendar calendar,
            String sender,
            String roomName)
    {
        switch(type){
            case ADD_USER_TO_ROOM:
                return  calendar.getTime().toString() + " "
                        + sender + " "
                        + "has joined in "
                        + roomName;
        }
        return "";
    }

}
