package Snuc;

import Common.Message;
import Common.Notify;
import Common.PublicNotify;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * La classe opera lato Client e si occupa della gestione della connessione 
 * del Client con il Server, dell'invio di comandi del Client e della relativa 
 * ricezione di notifiche.
 *
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class UserConnection implements Runnable {

    private Socket socket;
    final private String address;
    final private int port;
    final private UserController controller;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    /**
     * Costruttore della classe UserConnection
     *
     * @param address indirizzo del server con cui deve comunicare
     * @param port porta in cui il server resta in ascolto
     * @param controller UserController relativo all'User
     */
    public UserConnection(String address, int port, UserController controller) {
        this.address = address;
        this.port = port;
        this.controller = controller;
    }

    /**
     * Il metodo si occupa della instaurazione della connessione del Client con
     * il Server
     *
     * @param nick nickname dell'utente
     * @return nickname confermato dopo aver verificato se il nickname proprosto
     * dall'utente era disponibile.
     * @throws Exception l'eccezione viene lanciata nel caso in cui non è
     * possibile instaurare la connessione
     */
    public String connect(String nick) throws Exception {

        socket = new Socket(address, port);

        //Creo gli stream di imput e di output**********************************
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeUTF(nick);
        oos.flush();

        ois = new ObjectInputStream(socket.getInputStream());
        String reply = (String) ois.readUTF();
        System.out.println(reply);
        //**********************************************************************

        Notify confirm = (Notify) ois.readObject();

        controller.logHandler(confirm.getContent());

        new Thread(this).start();
        return confirm.getSender();
    }

    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted() != true) {
            Message msg = receiveMessage();
            dispatch(msg);
        }
    }

    /**
     * Il metodo si occupa della ricezione dei messaggi inviati dal server
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
     * @param msg messaggio che verrà smistato nel metodo opportuno
     */
    private void dispatch(Message msg) {
        if (msg instanceof PublicNotify) {
            controller.receivePublicNotify((PublicNotify) msg);
        } else if (msg instanceof Notify) {
            controller.receiveNotify((Notify) msg);
        }
    }

    /**
     * Il metodo si occupa dell'invio dei messaggi al Server
     *
     * @param msg messaggio da inviare al server
     * @return <code>true</code> se il messaggio è stato inviato correttamente
     * <code>false</code> se il messaggio non è stato inviato
     */
    public boolean senderMessage(Message msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("ConnectionHandler: Errore in invio messaggio");
            return false;
        }
    }

}
