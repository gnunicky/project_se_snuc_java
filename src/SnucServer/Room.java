package SnucServer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * La classe rappresenta le stanze presenti nel server
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class Room {

    final private String name;

    final private Collection<String> users, banUser;

    /**
     * Costruttore della classe Room
     *
     * @param name nome della stanza
     */
    public Room(String name) {
        this.name = name;
        this.users = new ArrayList();
        this.banUser = new ArrayList();
    }

    /**
     * Il metodo ritorna il nome della stanza
     *
     * @return nome della stanza
     */
    public synchronized String getName() {
        return name;
    }

    /**
     * Il metodo permette l'inserimento di un utente nella lista degli utenti
     * registrati alla stanza
     *
     * @param u nickname dell'utente
     * @return <code>true</code> se l'utente è stato inserito correttamente
     * <code>false</code> se l'utente è già  registrato nella stanza
     */
    public synchronized boolean addUser(String u) {
        if (!users.contains(u)) {
            users.add(u);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Il metodo permette la rimozione dell'utente dalla lista degli utenti
     * registrati alla stanza
     *
     * @param u nickname dell'utente
     */
    public synchronized void delUser(String u) {
        users.remove(u);
    }

    /**
     * Il metodo verifica se la stanza è vuota
     *
     * @return <code>true</code> se la stanza è vuota <code>false</code> se la
     * stanza non è vuota e quindi è presente almeno un utente nella lista degli
     * utenti registrati alla stanza
     */
    public synchronized Boolean emptyRoom() {
        return users.isEmpty();
    }

    /**
     * Il metodo restituisce la lista degli utenti registrati alla stanza
     *
     * @return lista degli utenti registrati alla stanza sottoforma di stringa
     */
    public synchronized String getUsersToString() {
        String list = "";
        for (String u : users) {
            list = list.concat(u + "\n");
        }
        return list;
    }

    /**
     * Il metodo restituisce la lista degli utenti registrati alla stanza
     *
     * @return lista degli utenti registrati alla stanza
     */
    public synchronized Collection<String> getUsers() {
        return users;
    }

}
