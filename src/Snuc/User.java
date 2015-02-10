package Snuc;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * La classe rappresenta l'utente del sistema
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class User {

    private String nick;
    private Map<String, Collection<String>> roomsMap;

    /**
     * Costruttore della classe User
     */
    public User() {
        roomsMap = new HashMap();
    }

    /**
     * Il metodo imposta il nickname dell'utente
     *
     * @param nick nickname dell'utente
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Il metodo ritorna il nickname dell'utente
     *
     * @return nickname dell'utente
     */
    public String getNick() {
        return nick;
    }

    /**
     * Il metodo ritorna, in base al nome della stanza selezionata in cui è
     * registrato l'utente, la lista aggiornata degli utenti registrati a tale
     * stanza
     *
     * @param room nome della stanza
     * @return lista degli utenti(nickname) registrati alla stanza
     */
    public Collection<String> getUserList(String room) {
        return roomsMap.get(room);
    }

    /**
     * Il metodo permette l'aggiornamento della lista degli utenti registrati
     * nella specifica stanza
     *
     * @param roomName nome della stanza
     * @param list lista degli utenti registrati nella stanza
     */
    public void updateListRoom(String roomName, String[] list) {
        roomsMap.put(roomName, new LinkedList());
        Collection c = roomsMap.get(roomName);
        for (int i = 0; i < list.length; i++) {
            c.add(list[i]);
        }
    }

}
