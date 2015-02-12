package Connector;

import Connector.TCP.DispatcherTCP;
import Connector.TCP.ConnectionHandlerTCP;
import Connector.TCP.ProxyMessagingServiceTCP;
import Connector.TCP.ProxyUserTCP;

/**
 * Si occuperà di creare oggetti indispensabili per la connessione relativi al 
 * protocollo TCP.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class ConnectionFactoryTCP extends ConnectionFactory {
    
    
    /**
     * Costruttore della ConnectionFactoryTCP.
     */
    public ConnectionFactoryTCP(){}
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ProxyMessagingServiceTCP.
     * @return istanza di tipo ProxyMessagingServiceTCP
     */
    @Override
    public  ProxyMessagingService createProxyMessagingSevice(){
        return new ProxyMessagingServiceTCP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo createProxyUserTCP.
     * @return istanza di tipo ProxyUserTCP
     */
    @Override
    public  ProxyUser createProxyUser(){
        return new ProxyUserTCP();
    }
    
    
     /**
     * Crea l'istanza di un oggetto di tipo DispatcherTCP.
     * @return istanza di tipo DispatcherTCP
     */
    @Override
    public  Dispatcher createDispathcer(){
        return new DispatcherTCP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ConnectionHandlerTCP.
     * @return istanza di tipo ConnectionHandlerTCP
     */
    @Override
    public  ConnectionHandler createConnectionHandler(){
        return new ConnectionHandlerTCP();
    }
}
