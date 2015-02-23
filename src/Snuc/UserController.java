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

import Common.IMessagingService;
import Common.IUser;
import Common.IUser_Interaction;
import Common.PublicNotify;
import Common.TypeNotify;
import Connector.ConnectionFactory;
import Connector.ProxyMessagingService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;

/**
 * La classe rappresenta il controller dell'utente
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class UserController implements IUser {

    private User user;
    private IUser_Interaction view;
    private IMessagingService server;
    

    /**
     * Il metodo imposta il riferimento all'oggetto Utente a cui si riferisce 
     * il controller.
     * 
     * @param user utente
     */
    public void setUser(User user){
        this.user=user;
    }
    
    /**
     * Il metodo imposta il riferimento all'interfaccia utente.
     * 
     * @param v vista
     */
    public void setView(IUser_Interaction v) {
        view = v;
    }

    /**
     * Il metodo si occupa della connessione del client con il server
     * 
     * @param nick nickname dell'utente
     * @param address indirizzo del server
     * @param port porta in cui è in ascolto il server
     * @throws UnknownHostException l'eccezione viene lanciata nel caso in cui il
     * nome dell'host non viene riconosciuto
     * @throws Exception l'eccezione viene lanciata nel caso in cui non è stato possibile
     * instaurare la connessione
     */
    public void connect(String nick, String address, int port) throws UnknownHostException,Exception{
            user.setNick(nick);
            server=ConnectionFactory.getConnectionFactory().createProxyMessagingSevice();
            ProxyMessagingService service=(ProxyMessagingService)server;
            service.setAddress(InetAddress.getByName(address));
            service.setPort(port);
            service.connect(nick,this);            
    }        

    /**
     * Il metodo si occupa della gestione dei messaggi di log
     * 
     * @param log messaggi di log
     */
    public void logHandler(String log) {
        view.printLog(log);
    }
    

    
    /**
     * Il metodo permette l'invio di un comando al server per richiedere un
     * determinato servizio
     * 
     * @param cmd contenuto del comando
     */
    public void executeCommand(String cmd) {
        server.commandHandler(cmd, user.getNick());        
    }
    



    
 //----------------Medoti dell'interfaccia IUser -----------------------------------   
    @Override
    public void receiveNotify(
            TypeNotify type,
            String content,
            GregorianCalendar calendar,
            String sender) {
        switch (type){
            case UPDATE_LIST_ROOMS:
                view.updateRoomList(content);
                break;
            case BAD_COMMAND:
                view.printLog(content);
                break;
            case CONNECTION_ACCEPT:
                user.setNick(sender);
                view.printLog(content);
                break;
        }
    }
    
    @Override
    public void receivePublicNotify(
            TypeNotify type,
            String content,
            GregorianCalendar calendar,
            String sender,
            String roomName) {
        switch (type) {
            case UPDATE_LIST_USERS:
                String users[] = content.split("\n");
                user.updateListRoom(roomName, users);
                view.updateUsersToRoom(roomName, users);
                break;
            case ADD_USER_TO_ROOM:
                String log=PublicNotify.textFormat(type,null,calendar, sender,roomName);
                view.printLogRoom(log,roomName);
                break;
        }
    }
    
    

    
   
    
    //----------------------------------------------------------------------------------

    @Override
    public void receivePublicMessage(String room, String content, GregorianCalendar date, String sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
