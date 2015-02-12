package Connector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Dichiara l'interfaccia per le ConnectionFactory relative alla tipo
 * di comunicazione.
 * Delega la creazione di oggetti utili alla comunicazione alle sue sottoclassi.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public abstract class ConnectionFactory {
    
    /**
     * Costruttore della ConnectionFactory
     */
    public ConnectionFactory(){}
    
    /**
     * In base ad opportuno file di configurazione Confing.txt si occuperà di
     * istanziare la Factory concreta relativa alla tipologia di comunicazione
     * che si vuole utilizzare.
     * @return istanza di tipo ConnectionFactory relativa al tipo di comunicazione
     * di rete.
     */
    public static ConnectionFactory getConnectionFactory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config/Config.txt"));
            String line = br.readLine();
            if (line.equalsIgnoreCase("TCP")) {
                return new ConnectionFactoryTCP();
            } else {
                return new ConnectionFactoryUDP();
            }
        } catch (IOException e) {
            System.out.println("File di configurazione non trovato!!");
            return null;
        }
    }
    
    
    /**
     * Crea oggetti di tipo ProxyMessagingSevice in base alla configurazione che
     * viene data
     * @return istanza di tipo ProxyMessagingSevice
     */
    public abstract ProxyMessagingService createProxyMessagingSevice();
    
    
    /**
     * Crea oggetti di tipo ProxyUser in base alla configurazione che
     * viene data
     * @return istanza di tipo ProxyUser
     */
    public abstract ProxyUser createProxyUser();
    
    
     /**
     * Crea oggetti di tipo Dispathcer in base alla configurazione che
     * viene data
     * @return istanza di tipo Dispathcer
     */
    public abstract Dispatcher createDispathcer();
    
    
     /**
     * Crea oggetti di tipo ConnectionHandler in base alla configurazione che
     * viene data
     * @return istanza di tipo ConnectionHandler
     */
    public abstract ConnectionHandler createConnectionHandler();
}
