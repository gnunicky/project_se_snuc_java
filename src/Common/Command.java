package Common;

import java.util.GregorianCalendar;

/**
 * Rappresenta un comando che l'utente invia al server al fine di ricevere 
 * il servizio richiesto quale ad esempio la lista delle stanze.  
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class Command extends Message {
    
    /**
     * Costruttore della classe Command
     * 
     * @param content contenuto del comando
     * @param date data in cui è stato inviato il comando
     * @param sender nickname del mittente
     * @param type tipo di messaggio
     */
      public Command(
            String              content,
            GregorianCalendar   date,
            String              sender,
            Enum                type
            )
      {
            super(content,date,sender,type);           
      }
      
}
