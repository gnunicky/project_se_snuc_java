package Common;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * La classe rappresenta il tipo generale di messaggio che un utente invia al
 * server.
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public abstract class Message implements Serializable {

    private String content;

    private GregorianCalendar date;

    private String sender;

    private Enum type;

    /**
     * Costruttore della classe Message
     *
     * @param content contenuto del messaggio
     * @param date data di invio del messaggio
     * @param sender nickname del mittente
     * @param type tipo di messaggio
     */
    public Message(
            String content,
            GregorianCalendar date,
            String sender,
            Enum type
    ) {
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.type = type;
    }

    /**
     * Il metodo ritorna la data in cui è stato spedito il messaggio
     *
     * @return data in cui è stato inviato il messaggio
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * Il metodo ritorna il contenuto del messaggio
     *
     * @return contenuto del messaggio
     */
    public String getContent() {
        return content;
    }

    /**
     * Il metodo ritorna il nickname del mittente del messaggio
     *
     * @return nickname del mittente del messaggio
     */
    public String getSender() {
        return sender;
    }

    /**
     * Il metodo ritorna il tipo di messaggio (es. NOTIFY, PUBLICNOTIFY e
     * COMMAND)
     *
     * @return tipo di messaggio
     */
    public Enum getType() {
        return type;
    }

    /**
     * Il metodo imposta il contenuto del messaggio
     *
     * @param content contenuto del messaggio
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Il metodo imposta la data del messaggio
     *
     * @param date data del messaggio
     */
    public void setGregorianCalendar(GregorianCalendar date) {
        this.date = date;
    }

    /**
     * Il metodo imposta il nickname del mittente del messaggio
     *
     * @param sender nickname del mittente del messaggio
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        if ((date != null) && (content != null) && (sender != null)) {
            return getDate().toZonedDateTime().getHour() + ":"
                    + getDate().toZonedDateTime().getMinute() + ":"
                    + getDate().toZonedDateTime().getSecond() + " "
                    + getSender() + ">> "
                    + getContent();
        }
        return "";
    }

    /**
     * Il metodo imposta il formato del testo del messaggio
     *
     * @param content contenuto del messaggio
     * @param date data del messaggio
     * @param sender nickname del mittente
     * @return messaggio formattato
     */
    public static String textFormat(
            String content,
            GregorianCalendar date,
            String sender) {
        String hour = date.toZonedDateTime().getHour() + "";
        String minute = date.toZonedDateTime().getMinute() + "";
        String second = date.toZonedDateTime().getSecond() + "";
        hour = (hour.length() == 1) ? "0" + hour : hour;
        minute = (minute.length() == 1) ? "0" + minute : minute;
        second = (second.length() == 1) ? "0" + second : second;
        String msg
                = "["
                + hour + ":"
                + minute + ":"
                + second + "]  "
                + sender + ">>  "
                + content;
        return msg;
    }

}
