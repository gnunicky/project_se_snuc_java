package SnucServer;

import Common.Command;
import Common.Message;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * La classe opera lato Server e si occupa della gestione della comunicazione
 * del Server con il Client, della ricezione e dell'invio dei messaggi.
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class UserConnectionHandler implements Runnable {

    final private Socket cs;
    final private MessagingService server;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    /**
     * Costruttore della classe UserConnectionHandler
     *
     * @param cs socket
     * @param server server
     */
    public UserConnectionHandler(Socket cs, MessagingService server) {
        this.cs = cs;
        this.server = server;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted() != true) {
            Message msg = receiveMessage();
            if (msg != null) {
                dispatch(msg);
            }
        }
    }

    /**
     * Il metodo si occupa della ricezione dei messaggi da parte del Server
     *
     * @return messaggio ricevuto
     */
    public Message receiveMessage() {
        try {
            return (Message) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * In base al tipo di messaggio che viene passato come parametro, chiama
     * l'oppurtuno metodo per la gestione di tale messaggio.
     *
     * @param msg messaggio che verrà  smistato nel metodo opportuno
     * @return <code>true</code> se il messaggio è stato gestito correttamente
     * <code>false</code> se il messaggio non è stato gestito correttamente
     */
    private boolean dispatch(Message msg) {
        if (msg instanceof Command) {
            return server.commandHandler((Command) msg);
        }
        return false;
    }

    /**
     * Il metodo si occupa dell'invio dei messaggi da parte del Server
     *
     * @param m messaggio da inviare
     */
    public void sendMessage(Message m) {
        try {
            oos.writeObject(m);
            oos.flush();
        } catch (Exception e) {
        }
    }

    /**
     * Imposta lo stream di lettura
     *
     * @param ois stream di lettura associato associato alla socket
     */
    protected void setInputStream(ObjectInputStream ois) {
        this.ois = ois;
    }

    /**
     * Imposta lo stream di scrittura
     *
     * @param oos stream di scrittura associato alla socket
     */
    protected void setOutputStream(ObjectOutputStream oos) {
        this.oos = oos;
    }

}
