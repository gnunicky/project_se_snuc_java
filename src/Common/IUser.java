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
 * L'interfaccia si occupa della ricezione delle notifiche
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public interface IUser {
    
    /**
     * Il metodo permette la ricezione delle notifiche inviate dal server
     * 
     * @param notify notifica inviata dal server
     */
    public abstract void receiveNotify(Notify notify);
    
    /**
     * Il metodo permette la ricezione delle notifiche pubbliche inviate dal 
     * server
     * 
     * @param notify notifica pubblica inviata dal server
     */    
    public abstract void receivePublicNotify(PublicNotify notify);
    
    
}
