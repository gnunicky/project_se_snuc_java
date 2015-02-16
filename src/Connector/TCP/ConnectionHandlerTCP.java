package Connector.TCP;


import Connector.ConnectionFactory;
import Connector.ConnectionHandler;
import Connector.Dispatcher;
import Connector.ProxyUser;
import SnucServer.MessagingService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * La classe opera lato server e ha come obittivo quello di far connettere 
 * mediante gli protocollo TCP User con il sistema MessagingService. Utilizzando
 * il protocollo TCP , ad ogni User che viene connesso viene associato un canale
 * di comunicazione che permette lo scambio di messaggi.
 * Ad ogni connessione questa classe interagisce con il servizio di messagistica
 * facendo in modo che l'utente venga inserito nell'elenco degli utenti online.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class ConnectionHandlerTCP extends ConnectionHandler {

    
    private ServerSocket ss;

    
    /**
     * Costruttore del ConnectionHandlerTCP.
     */
    public ConnectionHandlerTCP(){}
    
    
    /**
     * Chiama ciclicamente il metodo listeningConnection. Viene chiamato quando 
     * viene lanciato il Thread.
     */
    @Override
    public void run() {
        try{
            ss = new ServerSocket(getPort());
        }
        catch(Exception e){e.printStackTrace();}
        while (Thread.currentThread().isInterrupted() != true)
            listeningConnection();
    }

    
    @Override
    public void listeningConnection() {
        try {
            System.out.println("Waiting connection...");
            Socket cs = ss.accept();          
            System.out.println("Connected: " + cs);
            
            //Creo gli stream di imput e di output********************************
            ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
            String nickName = (String) ois.readUTF();

            ObjectOutputStream oos = new ObjectOutputStream(cs.getOutputStream());
            String msg_server = "...Checking...";
            oos.writeUTF(msg_server);
            //********************************************************************

            MessagingService service = (MessagingService) super.getServer();

            nickName=service.examineNick(nickName);

            Dispatcher dispatcher
                    = ConnectionFactory.getConnectionFactory().createDispathcer();
            dispatcher.setConnection(cs);
            dispatcher.setMessagingService(service);
            ((DispatcherTCP)dispatcher).setInputStream(ois);

            ProxyUser user = ConnectionFactory.getConnectionFactory().createProxyUser();
            //user.setConnection(cs);
            ((ProxyUserTCP)user).setOutputStream(oos);

            service.getOnlineUsers().put(nickName, user);
            new Thread(dispatcher).start();
        } 
        catch (IOException e){e.printStackTrace();}
    }
    
    
}
