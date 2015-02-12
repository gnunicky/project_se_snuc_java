package Connector.TCP;



import Common.IUser;
import Common.Message;

import Connector.ProxyMessagingService;

import java.net.Socket;



public class ProxyMessagingServiceTCP extends ProxyMessagingService {



    
    /**
     * Costruttore ProxyMessagingServiceTCP.
     */
    public ProxyMessagingServiceTCP(){super();}
    
    

    @Override
    public void connect(String nickname,IUser user) throws Exception{

    }

    

    
    @Override
    public boolean commandHandler(
            String cmd,
            String sender
    )
    {

        return true;
    }
    
    
    
    
    @Override
    public void send(Message msg){

    }
    
    
     /**
     * Imposta lo stream di scrittura
     * @param objectOutputStream stream di scrittura associato associato alla socket
     */
    public void setOutputStream(Object objectOutputStream){

    }
    
    
    //Non mi serve..
    //public void setInputStream(Object o){}
    
    
     /**
     * Ritorna l'oggetto Socket che permetterà la comunicazione. 
     * @return riferimento alla socket impostata.
     */
    @Override
    public Socket getConnection(){
        return null;
    }
    
    
     /**
     * Viene impostata l'oggetto Socket che permetterà la comunicazione.
     * @param socket socket necessaria per comunicazione di rete.
     */
    @Override 
    public void setConnection(Object socket){

    }
}
