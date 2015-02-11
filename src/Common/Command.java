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
 * Rappresenta un comando che l'utente invia al server al fine di ricevere il
 * servizio richiesto quale ad esempio la lista delle stanze.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class Command extends Message {

    /**
     * Costruttore della classe Command
     *
     * @param content contenuto del comando
     * @param date data in cui Ã¨ stato inviato il comando
     * @param sender nickname del mittente
     * @param type tipo di messaggio
     */
    public Command(
            String content,
            GregorianCalendar date,
            String sender,
            Enum type
    ) {
        super(content, date, sender, type);
    }

}
