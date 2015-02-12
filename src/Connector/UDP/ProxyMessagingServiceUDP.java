package Connector.UDP;


import Common.Command;
import Common.IUser;
import Common.Message;
import Connector.ConnectionFactory;
import Connector.Dispatcher;
import Connector.ProxyMessagingService;
import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class ProxyMessagingServiceUDP extends ProxyMessagingService{
    
    
    private DatagramSocket cs;
    final private int MAX_LENTGH_REPLY=24;

    
    /**
     * Costruttore del ProxyMessagingServiceUDP.
     */
    public ProxyMessagingServiceUDP() {super();
    }
    
    

    @Override
    public void connect(String nickname, IUser user) throws Exception {
            
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
    public boolean commandHandler(String cmd, String sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
