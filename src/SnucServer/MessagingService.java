package SnucServer;

import Common.Command;
import Common.IMessagingService;
import Common.Notify;
import Common.TypeNotify;
import Common.PublicNotify;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe implementa l'interfaccia IMessagingService e rappresenta il server
 * della nostro progetto. Provvederà alla instaurazione della connessione dei
 * client ponendosi in ascolto in una porta specifica. Si occupa inoltre della
 * gestione dei comandi che gli eventuali client invieranno al server e della
 * gestione delle notifiche che il server invierà  ai vari utenti per
 * notificarli di un particolare evento. In questa iterazione tale classe si
 * occupa anche del caricamento da file delle stanze presenti nel server, mentre
 * nelle iterazioni successive il compito di creare le stanze spetterà  ai vari
 * amministratori.
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class MessagingService implements IMessagingService, Runnable {
    
     /**
     * Costruttore della classe MessagingService
     *
     * @param port porta in cui si pone in ascolto il server
     * @throws IOException eccezione lanciata
     */
    public MessagingService(int port) throws IOException {


    }
    
    @Override
    public boolean commandHandler(Command cmd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
