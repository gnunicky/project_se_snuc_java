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
    public void receiveNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender
    ){};
    
    @Override
    public  void receivePublicNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender,
            String              roomName
    ) {};



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
