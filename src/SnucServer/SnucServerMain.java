package SnucServer;

import Connector.ConnectionFactory;
import Connector.ConnectionHandler;
import java.io.IOException;

/**
 * La classe contiene il main del lato del Server
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class SnucServerMain {

    public static void main(String[] arg) {
        try {
            MessagingService server = new MessagingService("config/Room.txt");
                        
            ConnectionHandler connectionHandler=
                    ConnectionFactory.getConnectionFactory().createConnectionHandler();
            connectionHandler.setPort(7777);
            connectionHandler.setService(server);
            
            new Thread(connectionHandler).start(); //Thread in attesa di connessioni...
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore avvio server");
        }
    }

}
