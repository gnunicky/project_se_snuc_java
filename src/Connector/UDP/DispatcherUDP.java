package Connector.UDP;


import Common.Message;
import Connector.Dispatcher;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * La classe dispatcher si occupa della ricezione dei messaggi e nell'invocare
 * l'opportuna procedura per la gestione di quel messaggio. Ogni qual volta viene
 * instanziato un oggetto di questo tipo è necessario impostare l'oggetto 
 * che gestisce la connessione. Utilizzando un protocollo UDP tale oggetto sarà
 * una DatagramSocket. Una volta impostata la socket udp,viene chiamato il metodo 
 * start sul riferimento all'oggetto che lancia il thread che rimane il ascolto
 * dei messaggi.
 * Questa classe può essere utilizzato sia lato client che lato server a patto
 * che venga settato il riferimento all'oggetto IUser o IMessagingService che 
 * rappresentano rispettivamente il client e il server.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class DispatcherUDP extends Dispatcher{
    
    
    private DatagramSocket cs;
    int MAX_LENGTH_MESSAGE=4048;
    
    
    /**
     * Costruttore del DispatcherUDP.
     */
    public DispatcherUDP(){}

    
    @Override
    public Message receive() throws Exception {
        
        byte [] incomingMessage= new byte[MAX_LENGTH_MESSAGE];
        DatagramPacket incomingPacket = new DatagramPacket(incomingMessage,incomingMessage.length);
        cs.receive(incomingPacket);        
        /*System.out.println(
                Thread.currentThread()+
                " packet received by " +
                incomingPacket.getAddress()+
                " porta: "+
                incomingPacket.getPort()
        );*/
        return (Message)BytesUtil.toObject(incomingPacket.getData());
    }
    
    
     /**
     * Questo metodo fa in modo che questo oggetto possa ricevere continuamente 
     * messaggi. Viene chiamato quando viene fatto avviare il Thread.
     */
    @Override
    public void run(){
        try{            
            //System.out.println("Il Dispatcher e' in ascolto a: " +cs+" "+port);
            while(Thread.currentThread().isInterrupted()!=true){
                Message m=receive();
                dispatch(m); 
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    
    /**
     * Viene impostata l'oggetto DatagramSocket che permetterà la comunicazione.
     * @param datagramSocket datagramSocket necessaria per comunicazione di rete.
     */
    @Override
    public void setConnection(Object datagramSocket) {
        if(datagramSocket instanceof DatagramSocket) cs=(DatagramSocket)datagramSocket;
    }
    
    
    /**
     * Ritorna l'oggetto DatagramSocket che permetterà la comunicazione. 
     * @return riferimento alla datagramSocket impostata.
     */
    @Override
    public DatagramSocket getConnection() {
        return cs;
    }
    
}
