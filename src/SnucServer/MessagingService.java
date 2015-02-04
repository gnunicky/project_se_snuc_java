package SnucServer;

import Common.Command;
import Common.IMessagingService;
import Common.TypeNotify;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe implementa l'interfaccia IMessagingService e rappresenta il server
 * della nostro progetto. Provvederà alla instaurazione della connessione dei
 * client ponendosi in ascolto in una porta specifica. Si occupa inoltre della
 * gestione dei comandi che gli eventuali client invieranno al server e della
 * gestione delle notifiche che il server invierà  ai vari utenti per
 * notificarli di un particolare evento. In questa iterazione tale classe si
 * occupa anche del caricamento da file delle stanze presenti nel server, mentre
 * nelle iterazioni successive il compito di creare le stanze spetterà  ai vari
 * amministratori.
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class MessagingService implements IMessagingService, Runnable {

    final int port;
    final private ServerSocket ss;
    final private Map<String, Room> rooms;
    final private Map<String, UserConnectionHandler> usersOnline;

    /**
     * Costruttore della classe MessagingService
     *
     * @param port porta in cui si pone in ascolto il server
     * @throws IOException eccezione lanciata
     */
    public MessagingService(int port) throws IOException {

        this.port = port;
        ss = new ServerSocket(port);
        rooms = new HashMap();
        usersOnline = new HashMap();

        //Caricamento stanze da file
        loadRoom("config/Room.txt");
    }

//----------------------------------Thread per istaurazione connessione -------------------------------------
    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted() != true) {
            try {
                listeningConnection();
            } catch (Exception e) {
                System.out.println("Errore connessione!!");
            }
        }
    }

    /**
     * Questo metodo si occupa di ascoltare le rischieste di connessioni degli
     * User.
     *
     * @throws Exception eccezione lanciata
     */
    private void listeningConnection() throws Exception {
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

        String confirmNick = examineNick(nickName);

    }
//-----------------------------------------------------------------------------------------------------------

    /**
     * Il metodo permette l'inserimento dell'utente nella lista degli utenti
     * registrati alla stanza
     *
     * @param user nickname dell'utente
     * @param roomName nome della stanza
     * @return <code>true</code> se l'utente è stato inserito correttamente
     * <code>false</code> se l'utente è già registrato nella stanza
     */
    public boolean addUser(String user, String roomName) {
        Room room = rooms.get(roomName);
        return room.addUser(user);
    }

    /**
     * Il metodo verifica se il nickname dell'utente che vuole collegarsi al
     * server è già  utilizzato da un altro utente.
     *
     * @param nick nickname
     * @return <code>true</code> se il nickname è già  utilizzato
     * <code>false</code> se il nickname è libero
     */
    public boolean isAlreadyUsed(String nick) {
        return usersOnline.containsKey(nick);
    }


    @Override
    public boolean commandHandler(Command cmd)
    {               
        return true;
    }

    /**
     * Il metodo ritorna la lista delle stanze presenti nel server
     *
     * @return lista delle stanze
     */
    public String getRoomsName() {
        String list = "";
        Collection<Room> c = rooms.values();
        for (Room r : c) {
            list = list.concat(r.getName() + "\n");
        }
        return list;
    }

    /**
     * Il metodo permette l'invio della notifica ad un utente
     *
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param sender nickname dell'utente a cui verrà  spedita la notifica
     */
    private void sendNotify(TypeNotify type, String content, String sender) {

    }

    /**
     * Il metodo permette l'invio della notifica pubblica agli utenti
     *
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param sender nickname dell'utente a cui verrà  spedita la notifica
     * @param roomName nome della stanza relativa alla notifica pubblica
     */
    private void sendPublicNotify(TypeNotify type, String content, String sender, String roomName) {

    }

    /**
     * Il metodo esamina il nickname dell'utente che vuole collegarsi al server
     * al fine di controllare se esiste già  un utente collegato con lo stesso
     * nickname. Nel caso in cui il nickname è già  utilizzato da un altro
     * utente il metodo provvede all'aggiunta del carattere '_' all'inizio del
     * nick fin quando il nickname risulta libero.
     *
     * @param proposeNick nickname proposto
     * @return nickname confermato
     */
    public String examineNick(String proposeNick) {
        while (isAlreadyUsed(proposeNick)) {
            proposeNick = "_" + proposeNick;
        }
        return proposeNick;
    }

    /**
     * Il metodo ritorna la mappa contenente la lista degli utenti connessi al
     * server
     *
     * @return mappa di utenti
     */
    Map getOnlineUsers() {
        return usersOnline;
    }

    /**
     * Il metodo caricherà  da file le stanze inserendole nella lista di quelle
     * presenti nel server
     *
     * @param fileName nome del file
     * @return <code>true</code> operazione completata correttamente
     * <code>false</code> operazione non riuscita
     */
    private boolean loadRoom(String fileName) {
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                rooms.put(line, new Room(line));
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("File error!");
        }
        return false;
    }

    /**
     * Permette inviare all'user la lista delle stanze presenti nel server
     *
     * @param sender mittente che ha inviato il messaggio
     */
    private void listRoom(String sender) {
        sendNotify(TypeNotify.UPDATE_LIST_ROOMS, getRoomsName(), sender);
    }

    /**
     * Aggiunge l'utente alla stanza e invia le notifiche rigurdanti i
     * cambiamenti di stato agli utenti registrati nella stanza
     *
     * @param roomName il nome della stanza dove stavi collegado
     * @param sender mittente che ha inviato il messaggio
     */
    private void join(String roomName, String sender) {
        Room room = rooms.get(roomName);
        if (room != null) {         //Controllo se esiste una stanza con nome inviato
            addUser(sender, roomName);
            sendPublicNotify(TypeNotify.UPDATE_LIST_USERS, room.getUsersToString(), sender, roomName);
            sendPublicNotify(TypeNotify.ADD_USER_TO_ROOM, null, sender, roomName);
        }
        else
            sendNotify(TypeNotify.BAD_COMMAND,"Room not found!",sender);
    }
    //--------------------------------------------------------------------------
//*******************************Medodi ausialiari del server******************************************

    /**
     * Il metodo ritorna all'utente il messaggio di benvenuto al servizio di
     * messaggistica
     *
     * @param user nickname dell'utente
     * @return messaggio di benvenuto
     */
    public static String welcomeText(String user) {
        String date = getSeverDate().getTime().toString();
        return
                "*********************************************************************************\n"+
                "* Welcome to the Smart  Network  University  Communications         \t*\n"+
                "* "+user +" - "+date+"\t\t\t*\n"+
                "* This  server  was   created   Sat  February  7  2015  at  13:00:00\t*\n"+
                "* GNU General Public Licens	(GPL)\t\t\t*\n"+
                "* by   Russo Leandro  ,  Invincibile  Daniele ,  Didomenico  Nicola\t*\n"+
                "*********************************************************************************"
                ;
    }

    /**
     * Il metodo ritorna la data del server
     *
     * @return data
     */
    protected static GregorianCalendar getSeverDate() {
        //------Incapsula l'ora del server nel calendar -------
        GregorianCalendar gc = new GregorianCalendar();
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);
        int hour = gc.get(Calendar.HOUR_OF_DAY);
        int min = gc.get(Calendar.MINUTE);
        int sec = gc.get(Calendar.SECOND);
        //------------------------------------------------------
        return new GregorianCalendar(year, month, day, hour, min, sec);
    }
//********************************************************************************************************

}
