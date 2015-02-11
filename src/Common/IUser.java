package Common;

import java.util.GregorianCalendar;

/**
 * L'interfaccia si occupa della ricezione delle notifiche e dei messaggi 
 * pubblici e privati
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public interface IUser {
    
    /**
     * Il metodo permette la ricezione delle notifiche inviate dal server
     * 
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param calendar data di invio della notifica
     * @param sender nickname del mittente
     */
    public abstract void receiveNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender
    );
    
    /**
     * Il metodo permette la ricezione delle notifiche pubbliche inviate dal 
     * server
     * 
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param calendar data di invio della notifica
     * @param sender nickname del mittente
     * @param roomName nome della stanza in cui sarà inviata la notifica pubblica
     */
    public abstract void receivePublicNotify(
            TypeNotify          type,
            String              content,
            GregorianCalendar   calendar,
            String              sender,
            String              roomName
    );
    

    

}