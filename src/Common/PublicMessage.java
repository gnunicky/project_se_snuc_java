package Common;

import java.util.GregorianCalendar;

/**
 * La classe rappresenta i messaggi pubblici che un utente può inviare a tutti 
 * gli altri utenti registrati nella stessa stessa stanza
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class PublicMessage extends Message {

    private String room;

    /**
     * Costruttore della classe PublicMessage
     * 
     * @param room nome della stanza in cui si vuole inviare il messaggio pubblico
     * @param content contenuto del messaggio pubblico
     * @param date data di invio del messaggio
     * @param sender nickname del mittente
     * @param type tipo di messaggio
     */
    public PublicMessage(
            String              room,
            String              content,
            GregorianCalendar   date,
            String              sender,
            Enum                type
    )
    {
        super(content, date, sender,type);
        this.room = room;
    }

    /**
     * Il metodo imposta il nome della stanza in cui si vuole inviare il messaggio
     * pubblico
     * 
     * @param room nome della stanza
     */
    public void setRoom(String room) {
        this.room = room;
    }
    
    /**
     * Il metodo ritorna il nome della stanza relativa al messaggio pubblico
     * 
     * @return nome della stanza
     */     
    public String getRoom() {
        return room;
    }
    
    @Override
    public String toString(){
        return 
                super.getDate().toZonedDateTime().getHour()+":"+
                super.getDate().toZonedDateTime().getMinute()+":"+
                super.getDate().toZonedDateTime().getSecond()+" "+
                super.getSender()+">> "+
                super.getContent();        
    }
    

}
