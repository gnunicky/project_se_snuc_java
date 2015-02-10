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
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
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
