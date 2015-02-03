package Common;

/**
 * L'interfaccia si occupa della ricezione delle notifiche
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public interface IUser {
    
    /**
     * Il metodo permette la ricezione delle notifiche inviate dal server
     * 
     * @param notify notifica inviata dal server
     */
    public abstract void receiveNotify(Notify notify);
    
    /**
     * Il metodo permette la ricezione delle notifiche pubbliche inviate dal 
     * server
     * 
     * @param notify notifica pubblica inviata dal server
     */    
    public abstract void receivePublicNotify(PublicNotify notify);
    
    
}
