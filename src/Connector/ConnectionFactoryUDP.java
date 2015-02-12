package Connector;

import Connector.UDP.ProxyUserUDP;
import Connector.UDP.ConnectionHandlerUDP;
import Connector.UDP.DispatcherUDP;
import Connector.UDP.ProxyMessagingServiceUDP;

/**
 * Si occuperà di creare oggetti indispensabili per la connessione relativi al
 * protocollo UDP.
 * @author Russo Leandro,Invincibile Daniele,Didomenico Nicola
 */
public class ConnectionFactoryUDP extends ConnectionFactory{
    
    
    /**
     * Costruttore della ConnectionFactoryUDP.
     */
    public ConnectionFactoryUDP(){}
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ProxyMessagingServiceUDP.
     * @return istanza di tipo ProxyMessagingServiceUDP
     */
    @Override
    public  ProxyMessagingService createProxyMessagingSevice(){
        return new ProxyMessagingServiceUDP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ProxyUserUDP.
     * @return istanza di tipo ProxyUserUDP
     */
    @Override
    public  ProxyUser createProxyUser(){
        return new ProxyUserUDP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo DispatcherUDP.
     * @return istanza di tipo DispatcherUDP
     */
    @Override
    public  Dispatcher createDispathcer(){
        return new DispatcherUDP();
    }
    
    
    /**
     * Crea l'istanza di un oggetto di tipo ConnectionHandlerUDP.
     * @return istanza di tipo ConnectionHandlerUDP
     */
    @Override
    public  ConnectionHandler createConnectionHandler(){
        return new ConnectionHandlerUDP();
    }
}
