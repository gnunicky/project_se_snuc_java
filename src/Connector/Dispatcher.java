package Connector;


import Common.Command;
import Common.IMessagingService;
import Common.IUser;
import Common.Message;
import Common.Notify;
import Common.PublicNotify;
import Common.TypeNotify;
import java.util.GregorianCalendar;


/**
 * La classe dispatcher si occupa della ricezione dei messaggi e nell'invocare
 * l'opportuna procedura per la gestione di quel messaggio.
 * Questa classe può essere utilizzata sia lato client che lato server a patto
 * che venga settato il riferimento all'oggetto IUser o IMessagingService che 
 * rappresentano rispettivamente i client e il server.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public abstract class Dispatcher implements Runnable{

    
    private IUser user;
    private IMessagingService messagingService;
    
    
    /**
     * Costruttore del Dispatcher.
     */
    public Dispatcher(){}
    
    
    /**
     * Imposta il riferimento all'IUser quando viene utilizzato lato client.
     * @param user riferimento al client
     */
    public void setUser(IUser user) {
        this.user = user;
    }
    
    
    /**
     * Imposta il riferimento all'IMessagingService quando viene utilizzato lato
     * server.
     * @param messagingService  riferimento al server
     */
    public void setMessagingService(IMessagingService messagingService) {
        this.messagingService = messagingService;
    }
    
    
    /**
     * In base al tipo di messaggio che viene passato come parametro, chiama 
     * l'oppurtuno metodo per la gestione di tale messaggio.
     * @param msg messaggio che verrà smistato nell'opportuno metodo 
     */
    public void dispatch(Message msg) {
               
        String content = msg.getContent();
        GregorianCalendar date = msg.getDate();
        String sender = msg.getSender();
        
        
        if (msg instanceof PublicNotify){
            TypeNotify type = ((Notify) msg).getNotify();
            String room=((PublicNotify)msg).getRoom();
            user.receivePublicNotify(type, content, date, sender,room);
        }
        else if (msg instanceof Notify){
            TypeNotify type = ((Notify) msg).getNotify();
            user.receiveNotify(type, content, date, sender);
        }
        else if (msg instanceof Command) {
            messagingService.commandHandler(content, sender);
        }
    }
    
    
    /**
     * Il metodo rimane in attessa che arrivi un messaggio.
     * @return messaggio ricevuto
     * @throws Exception se quanlosa dovesse andare storto nella ricezione del
     * messaggio
     */
    public abstract Message receive() throws Exception;
    
    
    /**
     * Permette di settare l'oggetto necessario per la comunicazione di rete.
     * @param connection riferimento alla connessione
     */
    public abstract void setConnection(Object connection);
    
    
    /**
     * Ritorna l'oggetto necessario per la comunicazione di rete. 
     * @return riferimento alla connessione
     */
    public abstract Object getConnection();
        
}
