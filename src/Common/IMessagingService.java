package Common;

/**
 * Interfaccia che permette l'invio di messaggi pubblici o privati e che si 
 * occupa della gestione dei comandi inviati al server
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public interface IMessagingService {
    

    /**
     * Il metodo permette di gestire un comando in base al tipo di comando 
     * inviato
     * 
     * @param cmd comando
     * @param sender nickname del mittente
     * @return <code>true</code> se il comando è stato riconosciuto e gestito
     *         <code>false</code> se il comando non è stato riconosciuto
     */
    public boolean commandHandler(
            String cmd,
            String sender
    );
    


}
