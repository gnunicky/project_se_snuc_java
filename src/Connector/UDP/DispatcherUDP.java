package Connector.UDP;


import Common.Message;
import Connector.Dispatcher;
import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class DispatcherUDP extends Dispatcher{
    
    

    
    
    /**
     * Costruttore del DispatcherUDP.
     */
    public DispatcherUDP(){}

    @Override
    public Message receive() throws Exception {
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
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
