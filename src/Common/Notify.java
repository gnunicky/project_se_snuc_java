package Common;

import java.util.GregorianCalendar;

/**
 * La classe rappresenta le notifiche che il Server invia all'utente per 
 * comunicare un determinato evento
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class Notify extends Message {

    private TypeNotify notify;

    /**
     * Costruttore della classe Notify
     * 
     * @param notify tipo di notifica
     * @param content contenuto della notifica
     * @param date data di invio della notifica
     * @param sender nickname del mittente
     * @param type tipo di messaggio
     */
    public Notify(
            TypeNotify          notify,
            String              content,
            GregorianCalendar   date,
            String              sender,
            Enum                type
            ) 
    {
        super(content, date, sender, type);
        this.notify = notify;
    }

    /**
     * Il metodo ritorna il tipo di notifica (es. CONNECTION_ACCEPTED, 
     * UPDATE_LIST_ROOMS..)
     * 
     * @return tipo di notifica
     */
    public TypeNotify getNotify() {
        return notify;
    }

    /**
     * Il metodo imposta il tipo di notifica
     * 
     * @param notify tipo di notifica
     */
    public void setNotify(TypeNotify notify) {
        this.notify = notify;
    }
}
