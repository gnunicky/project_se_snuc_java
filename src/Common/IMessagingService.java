package Common;

/**
 * L'interfaccia si occupa della gestione dei comandi inviati al server
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public interface IMessagingService {

    /**
     * Il metodo permette di gestire un comando in base al tipo di comando 
     * inviato
     * 
     * @param cmd comando
     * @return <code>true</code> se il comando è stato riconosciuto e gestito
     *         <code>false</code> se il comando non è stato riconosciuto
     */
    public boolean commandHandler(Command cmd);

}
