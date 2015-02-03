package Common;

/**
 * La classe è utilizzata per distingure i tipi di notifica
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public enum TypeNotify {
    UPDATE_LIST_USERS,  //Aggiorna la lista delle utenti
    UPDATE_LIST_ROOMS,  //Aggiorna la lista delle stanze
    CONNECTION_ACCEPT,  //Conferma connessione al server
    ADD_USER_TO_ROOM,   //Informa che l'utente si è registrato ad una stanza
    BAD_COMMAND,        //Comando errato
    EXIT_USER_TO_ROOM;  //Informa che l'utente è uscito da una stanza
}
