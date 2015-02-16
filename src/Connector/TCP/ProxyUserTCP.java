package Connector.TCP;


import Common.Message;
import Common.Notify;
import Common.PublicNotify;
import Common.TypeNotify;
import Connector.ProxyUser;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.GregorianCalendar;


/**
 * Rappresenta localmente l'User che si trova in un host remoto.
 * Ha la stessa interfaccia all'oggetto IUser e inoltre gestisce la comunicazione
 * di rete in modo da far corrispondere la chiamata in locale dei suoi metodi con
 * una chiamata in remoto dei metodi dell'utente.
 * Utilizzando un tipo di comunicazione TCP ha bisogno un riferimento alla socket
 * che permette la comunicazione con l'User corrispettivo in remoto. E' possibile
 * inoltre impostare direttamente lo stream di rete senza considerare la socket
 * a patto che lo stream sia stato ricavato sempre facendo riferimento alla socket.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class ProxyUserTCP extends ProxyUser {

    
    private Socket socket;
    private ObjectOutputStream oos;

    
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
        Notify notify=new Notify(type,content,calendar,sender,null);
        send(notify);
    }
    
    
    @Override
    public  void receivePublicNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender,
            String              roomName)
    {
        PublicNotify notify=new PublicNotify(type,content,calendar,sender,null,roomName);
        send(notify);
    }
    
    
    
    
    
    
    @Override
    public void send(Message msg){
        try{
            oos.writeObject(msg);
            oos.flush();
        }
        catch(Exception e){
        }
    }
    
    
    /**
     * Imposta lo stream di scrittura.
     * @param objectOutputStream stream di scrittura associato associato alla socket
     */
    public void setOutputStream(Object objectOutputStream){
        if(objectOutputStream instanceof ObjectOutputStream) oos=(ObjectOutputStream)objectOutputStream;
    }
    
    
    //non mi serve..
    //public void setInputStream(Object o){}
    
    
    
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
     * @param socket socket necessaria per la comunicazione di rete.
     */
    @Override 
    public void setConnection(Object socket){
        if(socket instanceof Socket) this.socket=(Socket)socket;
    }
}
