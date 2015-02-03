package Common;

/**
 * Interfaccia che si occupa dell'aggiornamento dell'intefaccia grafica
 * utilizzata dagli utenti del servizio
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public interface IUser_Interaction {

    /**
     * Il metodo permette l'aggiornamento della lista delle stanze
     * 
     * @param list lista delle stanze presenti nel server
     */
    public void updateRoomList(String list);

    /**
     * Il metodo permette l'aggiornamento degli utenti presenti nella stanza 
     * selezionata
     * 
     * @param nameRoom nome della stanza
     * @param users utenti registati nella stanza
     */     
    public void updateUsersToRoom(String nameRoom, Object[] users);

    /**
     * Il metodo stampa in un'apposita area relativa alla stanza il log della
     * registrazione alla stanza
     * 
     * @param log log della registrazione
     * @param room nome della stanza
     */
    public void printLogRoom(String log, String room);

    /**
     * Il metodo stampa in un'apposita area il log della connessione al server
     * 
     * @param log log della connessione al server
     */
    public void printLog(String log);
    
}
