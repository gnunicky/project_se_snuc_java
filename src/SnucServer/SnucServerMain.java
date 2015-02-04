package SnucServer;

import java.io.IOException;

/**
 * La classe contiene il main del lato del Server
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class SnucServerMain {

    public static void main(String[] arg) {
        try {
            MessagingService server = new MessagingService(7777);
            new Thread(server).start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore avvio server");
        }
    }

}
