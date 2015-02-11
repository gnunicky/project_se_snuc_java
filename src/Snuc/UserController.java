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

import Common.Command;
import Common.IUser;
import Common.IUser_Interaction;
import Common.Notify;
import Common.PublicNotify;
import Common.TypeNotify;
import java.util.GregorianCalendar;

/**
 * La classe rappresenta il controller dell'utente
 *
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class UserController implements IUser {

    private User user;
    private IUser_Interaction view;
    private UserConnection connection;

    public UserController() {
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
     * Il metodo imposta il riferimento all'oggetto Utente a cui si riferisce il
     * controller.
     *
     * @param user utente
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Il metodo si occupa della connessione del client con il server
     *
     * @param nick nickname dell'utente
     * @param address indirizzo del server
     * @param port porta in cui è in ascolto il server
     * @return <code>true</code> se la connesione va a buon fine
     * <code>false</code> se la connessione fallisce
     */
    public boolean connect(String nick, String address, int port) {
        try {
            connection = new UserConnection(address, port, this);
            user.setNick(nick);
            String confirmNick = connection.connect(user.getNick());
            user.setNick(confirmNick);
            return true;
        } catch (Exception e) {
            System.out.println("Connection error!!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Il metodo permette l'invio di un comando al server per richiedere un
     * determinato servizio
     *
     * @param cmd contenuto del comando
     */
    public void executeCommand(String cmd) {
        Command command = new Command(cmd, null, user.getNick(), null);
        connection.senderMessage(command);
    }

//--------------------------Metodi dell'interfaccia IUser -------------------------------------------------    
    @Override
    public void receiveNotify(Notify notify) {
        switch (notify.getNotify()) {
            case UPDATE_LIST_ROOMS:
                view.updateRoomList(notify.getContent());
                break;
            case BAD_COMMAND:
                view.printLog(notify.getContent());
                break;
            case CONNECTION_ACCEPT:
                user.setNick(notify.getSender());
                view.printLog(notify.getContent());
                break;
        }
    }

    @Override
    public void receivePublicNotify(PublicNotify notify) {

        String content = notify.getContent();
        GregorianCalendar date = notify.getDate();
        String sender = notify.getSender();

        switch (notify.getNotify()) {
            case UPDATE_LIST_USERS:
                if (notify instanceof PublicNotify) {
                    String roomName = ((PublicNotify) notify).getRoom();
                    String users[] = content.split("\n");
                    user.updateListRoom(roomName, users);
                    view.updateUsersToRoom(roomName, users);
                }
                break;
            case ADD_USER_TO_ROOM:
                if (notify instanceof PublicNotify) {
                    String roomName = ((PublicNotify) notify).getRoom();
                    String log = PublicNotify.textFormat(TypeNotify.ADD_USER_TO_ROOM, content, date, sender, roomName);
                    view.printLogRoom(log, roomName);
                }
                break;
        }
    }

//---------------------------------------------------------------------------------------------------------    
    /**
     * Il metodo si occupa della gestione dei messaggi di log
     *
     * @param log messaggi di log
     */
    public void logHandler(String log) {
        view.printLog(log);
    }

}
