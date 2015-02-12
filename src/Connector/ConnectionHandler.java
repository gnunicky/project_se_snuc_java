package Connector;


import Common.IMessagingService;
import SnucServer.MessagingService;


/**
 * La classe opera lato server e ha come obittivo quello di far connettere gli
 * User con il sistema MessagingService. Una volta connessi il sistema
 * interagisce con il servizio di messagistica e si occupera di inserire gli
 * User tra gli utenti Online.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public abstract class ConnectionHandler implements Runnable{
    
    
    private int port;
    private IMessagingService server;

    
    /**
     * Costruttore del ConnectionHandler.
     */
    public ConnectionHandler(){}
    
    
    /**
     * Imposta la porta di ascolto del ConnectionHandler. 
     * @param port porta di ascolto
     */    
    public void setPort(int port) {
        this.port = port;
    }
    
    
    /**
     * Ritorna la porta di ascolto del ConnectionHandler. 
     * @return la porta di ascolto
     */
    public int getPort() {
        return port;
    }
    
    
    /**
     * Imposta il riferimento al servizio di messagistica con cui dovrà interagire. 
     * @param server riferimento al servizio di messagistica
     */
    public void setService(MessagingService server){
        this.server=server;
    }
    
    
    /**
     * Ritorna il riferimento al servizio di messagistica con cui dovrà interagire. 
     * @return riferimento il servizio di messagistica 
     */
    public IMessagingService getServer() {
        return server;
    }
    
    
    /**
     * Questo metodo si occupa di ascoltare le rischieste di connessioni degli
     * User.
     */
    public abstract void listeningConnection();
}
