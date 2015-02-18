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
    public void send(Message msg) {
        try{
            if(cs==null) cs = new DatagramSocket();                        
            byte[] dataSend = BytesUtil.toByteArray(msg);
            DatagramPacket sendPacket = new DatagramPacket(dataSend,dataSend.length,getAddress(),getPort());
            cs.send(sendPacket);
            //System.out.println("Spedito a "+getAddress()+" "+getPort());
            //System.out.println(msg+ " lungo " + dataSend.length);
        }
        catch(Exception e){e.printStackTrace();}
    }
    

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
    
    
    
    
    
    
     /**
     * Ritorna l'oggetto DatagramSocket che permetterà la comunicazione. 
     * @return riferimento alla datagramSocket impostata.
     */
    @Override
    public DatagramSocket  getConnection() {
        return cs;
    }
    
    
    /**
     * Viene impostata l'oggetto DatagramSocket che permetterà la comunicazione.
     * @param datagramSocket datagramSocket necessaria per comunicazione di
     * rete.
     */
    @Override
    public void setConnection(Object datagramSocket) {
        if (datagramSocket instanceof DatagramSocket)
             cs = ((DatagramSocket) datagramSocket);
    }

}
