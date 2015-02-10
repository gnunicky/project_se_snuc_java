package Common;

import java.util.GregorianCalendar;

/**
 * La classe rappresenta le notifiche pubbliche che il Server invia agli utenti
 * per comunicare un determinato evento
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class PublicNotify extends Notify {

    private String room;

    /**
     * Costruttore della classe PublicNotify
     *
     * @param notify tipo di notifica
     * @param content contenuto della notifica
     * @param date data di invio della notifica
     * @param sender nickname del mittente
     * @param type tipo di messaggio
     * @param roomName nome della stanza
     */
    public PublicNotify(
            TypeNotify notify,
            String content,
            GregorianCalendar date,
            String sender,
            Enum type,
            String roomName
    ) {
        super(notify, content, date, sender, type);
        this.room = roomName;
    }

    /**
     * Il metodo ritorna il nome della stanza relativa alla notifica pubblica
     *
     * @return nome della stanza
     */
    public String getRoom() {
        return room;
    }

    /**
     * Il metodo imposta il nome della stanza relativa alla notifica pubblica
     *
     * @param room nome della stanza
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Il metodo imposta il formato del testo della notifica pubblica
     *
     * @param type tipo della notifica
     * @param content contenuto del messaggio
     * @param calendar data del messaggio
     * @param sender nickname del mittente
     * @param roomName nome della stanza
     * @return notifica pubblica formattata
     */
    public static String textFormat(
            TypeNotify type,
            String content,
            GregorianCalendar calendar,
            String sender,
            String roomName) {
        switch (type) {
            case ADD_USER_TO_ROOM:
                return calendar.getTime().toString() + " "
                        + sender + " "
                        + "has joined in "
                        + roomName;
        }
        return "";
    }

}
