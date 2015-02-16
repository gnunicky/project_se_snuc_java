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
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
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
}
