package Connector.UDP;

import Common.Message;
import Common.Notify;
import Common.PublicNotify;
import Common.TypeNotify;
import Connector.ProxyUser;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.GregorianCalendar;


/**
 * Rappresenta localmente l'User che si trova un host remoto.
 * Ha la stessa interfaccia dell'oggetto IUser e inoltre gestisce la comunicazione
 * di rete in modo da far corrispondere la chiamata in locale dei suoi metodi con
 * una chiamata in remoto dei metodi dell'utente.
 * Utilizzando un tipo di comunicazione UDP non orientata alla connessione, si
 * ha bisogno solamente di impostare l'indirizzo ip e la porta dell'User romoto
 * e da quel momento in poi questo oggetto invierà pacchetti esclusvamente a 
 * quella porta e quell'indirizzo.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class ProxyUserUDP extends ProxyUser{
    
    
    private DatagramSocket cs;
    
    
    /**
     * Costruttore del ProxyUserUDP.
     */
    public ProxyUserUDP(){
    }

    @Override
    public void send(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setConnection(Object connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveNotify(TypeNotify type, String content, GregorianCalendar calendar, String sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receivePublicNotify(TypeNotify type, String content, GregorianCalendar calendar, String sender, String roomName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


}
