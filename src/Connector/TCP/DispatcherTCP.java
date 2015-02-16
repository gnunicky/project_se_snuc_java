package Connector.TCP;


import Common.Message;
import Connector.Dispatcher;
import java.io.ObjectInputStream;
import java.net.Socket;


/**
 * La classe dispatcher si occupa della ricezione dei messaggi e nell'invocare
 * l'opportuna procedura per la gestione di quel messaggio. Ogni qual volta viene
 * instanziato un oggetto di questo tipo è necessario impostare l'oggetto 
 * che gestisce la connessione. Utilizzando un protocollo TCP tale oggetto sarà
 * una socket. Una volte impostata la socket , viene chiamato il metodo start
 * sul riferimento all'oggetto che lancia il thread che rimane in ascolto dei
 * messaggi.
 * Questa classe può essere utilizzata sia lato client che lato server a patto
 * che venga settato il riferimento all'oggetto IUser o IMessagingService che 
 * rappresentano rispettivamente i client e il server.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class DispatcherTCP extends Dispatcher{

    
    private Socket socket;
    private ObjectInputStream ois;
    
    
    /**
     * Costruttore del DispatcherTCP.
     */
    public DispatcherTCP(){}
        
    
    /**
     * Questo metodo fa in modo che questo oggetto possa ricevere continuamente 
     * messaggi. Viene chiamato quando viene fatto avviare il Thread.
     */
    @Override
    public void run() {
        try{
            while (Thread.currentThread().isInterrupted() != true) {
                    Message m=receive();
                    dispatch(m);           
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    
    @Override
    public Message receive() throws Exception{
        return (Message) ois.readObject();        
    }
    
    
    /**
     * Ritorna l'oggetto Socket che permetterà la comunicazione. 
     * @return riferimento alla socket impostata.
     */
    @Override
    public Socket getConnection(){
        return socket;
    }
    
    
    /**
     * Viene impostata l'oggetto Socket che permetterà la comunicazione.
     * @param socket socket necessaria per comunicazione di rete.
     */
    @Override 
    public void setConnection(Object socket){
        if(socket instanceof Socket) this.socket=(Socket)socket;
    }

    
     /**
     * Imposta lo stream di lettura
     * @param objectInputStream stream di lettura associato associato alla socket
     */
    public void setInputStream(Object objectInputStream) {
        if (objectInputStream instanceof ObjectInputStream) {
            ois = (ObjectInputStream) objectInputStream;
        }
    }

    
    //Non so se mi serve..
    //public void setOutputStream(Object o) {
    //}
}
