/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnucServer;

import Common.IUser;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * La classe rappresente gli utenti collegati al servizio di messaggistica
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
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
