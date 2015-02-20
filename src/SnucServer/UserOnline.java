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

import Common.IUser;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * La classe rappresente gli utenti collegati al servizio di messaggistica
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class UserOnline extends Observable{
    
    private Map<String,IUser> usersOnline;
        
    /**
     * Costruttore della classe UserOnline
     */
    public UserOnline() {
        usersOnline = new HashMap();        
    }
    
    /**
     * Il metodo inserisce un utente nella lista degli utenti collegati al server
     * 
     * @param nickname nickname dell'utente
     * @param user riferimento all'utente remoto
     */
    public synchronized void put(String nickname,IUser user){
        usersOnline.put(nickname,user);
        setChanged();
        notifyObservers(nickname);
    }
    
    /**
     * Il metodo rimuove un utente dalla lista degli utenti collegati al server
     * 
     * @param nickname nickname dell'utente
     * 
     */
    public synchronized void remove(String nickname){
        usersOnline.remove(nickname);
        setChanged();
        notifyObservers(nickname);
    }
    
    /**
     * Il metodo verifica sè nella lista degli utenti collegati al server è 
     * presente il nickname passato come argomento
     * 
     * @param nick nickname da verificare
     * @return <code>true</code> se il nickname è già presente             
     *         <code>false</code> se il nickname non è presente             
     */   
    public synchronized boolean containsKey(String nick){
        return usersOnline.containsKey(nick);        
    }
    
    /**
     * Il metodo ritorna il riferimento controller relativo all'utente che
     * implementa l'interfaccia IUser
     * 
     * @param nick nickname dell'utente
     * @return user controller relativo all'utente che implementa l'interfaccia
     * IUser
     */
    public synchronized IUser get(String nick){
        return usersOnline.get(nick);
    }
    
    /**
     * Il metodo ritorna la mappa contenente la lista degli utenti connessi al
     * server
     * 
     * @return mappa di utenti
     */
    synchronized Map getOnlineUsers(){return usersOnline;}
    
    
}
