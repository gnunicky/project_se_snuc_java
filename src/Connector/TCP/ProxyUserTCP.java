package Connector.TCP;


import Common.Message;
import Common.TypeNotify;
import Connector.ProxyUser;
import java.net.Socket;
import java.util.GregorianCalendar;



public class ProxyUserTCP extends ProxyUser {

    

    
    /**
     * Costruttore del ProxyUserTCP.
     */
    public ProxyUserTCP(){}
    
    
    @Override
    public  void receiveNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender)
    {

    }
    
    
    @Override
    public  void receivePublicNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender,
            String              roomName)
    {

    }
    
    
    
    
    
    
    @Override
    public void send(Message msg){

    }
    
    
    /**
     * Imposta lo stream di scrittura.
     * @param objectOutputStream stream di scrittura associato associato alla socket
     */
    public void setOutputStream(Object objectOutputStream){

    }
    
    
    //non mi serve..
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
     * @param socket socket necessaria per la comunicazione di rete.
     */
    @Override 
    public void setConnection(Object socket){

    }
}
