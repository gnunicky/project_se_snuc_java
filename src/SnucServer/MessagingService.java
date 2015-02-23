/**
 * SNUC  is a program written in Java SE (version 1.8.0_31) during a project of 
 * course Software Engineering in University of Catania academic year 2014-15.
 * SNUC is Smart Network University Communications.
 * 
 * Copyright (C) 2015 onwards Leandro Russo (leandrorusso90@gmail.com)
 * Copyright (C) 2015 onwards Invincibile Daniele (d.invincibile@gmail.com)
 * Copyright (C) 2015 onwards Nicola Didomenico (nicola.didomenico@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public Licens along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package SnucServer;

import Common.IMessagingService;
import Common.IUser;
import Common.TypeNotify;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * La classe implementa l'interfaccia IMessagingService e rappresenta il server 
 * della nostro progetto. Provvederà alla gestione dei comandi che gli eventuali
 * client invieranno al server e all'inoltro dei messaggi. Si occuperà anche 
 * della gestione delle notifiche che il server invierà ai vari utenti per 
 * notificarli di un particolare evento. In questa iterazione tale classe si
 * occupa anche del caricamento da file delle stanze presenti nel server, mentre
 * nelle iterazioni successive il compito di creare le stanze spetterà
 * ai vari amministratori.
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class MessagingService implements IMessagingService,Observer {

    final private Map<String,Room> rooms;    
    final private UserOnline usersOnLine;
    
    /**
     * Costruttore della classe MessagingService
     * 
     * @param fileRoomName nome del file in cui sono presenti le stanze da
     * caricare nella lista delle stanze presenti nel server.
     * 
     * @throws IOException eccezione lanciata
     */
    public MessagingService(String fileRoomName) throws IOException {       
        rooms = new HashMap();
        usersOnLine=new UserOnline();
               
        //Caricamento stanze da file
        loadRoom(fileRoomName);        
        attachObserver();      
    }

    
//-----------------Metodi dell'interfaccia IMessagingService ------------------------------------- 

    
    @Override
    public boolean commandHandler(
            String cmd,
            String sender)
    {               
        CommandParser cp = new CommandParser(cmd);
        try{
            switch (cp.getCommand()){                      
                case "/listRooms":  listRoom(sender);                   break;
                case "/join":
                    if(cp.getParameter(1)==null){
                        sendNotify(TypeNotify.BAD_COMMAND,"Missing parameter",sender);
                        return false;
                    }
                    join(cp.getParameter(1),sender);
                break;
                default:
                    sendNotify(TypeNotify.BAD_COMMAND,"Bad command!",sender);
                    return false;
            }
        }
        catch(NullPointerException e){
            sendNotify(TypeNotify.BAD_COMMAND,"Sintax Error!",sender);
            return false;
        }
        return true;
    }

//-------------------------------------------------------------------------------------------------
     
    
    
    /**
     * Il metodo permette inviare all'user la notifica contenente la lista 
     * delle stanze presenti nel server
     *
     * @param sender nickname del mittente che ha richiesto la lista delle 
     * stanze presenti nel server
     */
    private void listRoom(String sender) {
        sendNotify(TypeNotify.UPDATE_LIST_ROOMS, getRoomsName(), sender);
    }
    
    
    /**
     * Il metodo aggiunge l'utente alla stanza 
     *
     * @param roomName il nome della stanza in cui l'utente vuole registrarsi
     * @param sender nickname del mittente che ha inviato il comando
     */
    private void join(String roomName, String sender) {
        Room room = rooms.get(roomName);
        if (room != null)                       //Controllo se esiste una stanza con nome inviato
            addUser(sender, roomName);
        else
            sendNotify(TypeNotify.BAD_COMMAND,roomName+" not found!",sender);
    } 
//-------------------------------------------------------------------------------------------------    
    
    /**
     * Il metodo esamina il nickname dell'utente che vuole collegarsi al server
     * al fine di controllare se esiste già un utente collegato con lo stesso
     * nickname. Nel caso in cui il nickname è già utilizzato da un altro utente
     * il metodo provvede all'aggiunta del carattere '_' all'inizio del nick
     * fin quando il nickname risulta libero.
     * 
     * @param proposeNick nickname proposto
     * @return nickname confermato 
     */
    public String examineNick(String proposeNick) {
        while (isAlreadyUsed(proposeNick))
            proposeNick = "_" + proposeNick;
        return proposeNick;
    }
    
    /**
     * Il metodo verifica se il nickname dell'utente che vuole collegarsi al 
     * server è già utilizzato da un altro utente.
     * 
     * @param nick nickname 
     * @return <code>true</code> se il nickname è già utilizzato
     *         <code>false</code> se il nickname è libero
     */
    private boolean isAlreadyUsed(String nick) {
        return usersOnLine.containsKey(nick);  
    }
    
    /**
     * Il metodo ritorna il riferimento all'oggetto UserOnline
     * 
     * @return riferimento all'oggetto UserOnline
     */
    public UserOnline getOnlineUsers(){return usersOnLine;}
    
    /**
     * Il metodo permette l'inserimento dell'utente nella lista degli utenti
     * registrati alla stanza
     * 
     * @param user nickname dell'utente
     * @param roomName nome della stanza
     * @return <code>true</code> se l'utente è stato inserito correttamente
     *         <code>false</code> se l'utente è già registrato nella stanza
     */
    public boolean addUser(String user, String roomName) {
        Room room = rooms.get(roomName);
        return room.addUser(user);
    }

    /**
     * Il metodo ritorna la lista delle stanze presenti nel server
     * 
     * @return lista delle stanze
     */
    public String getRoomsName() {
        String list="";
        for(Room room:rooms.values())
            list=list.concat(room.getName()+"\n");
        return list;
    }
    
    /**
     * Il metodo permette l'invio della notifica ad un utente
     * 
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param sender nickname dell'utente a cui verrà spedita la notifica
     */
    private void sendNotify(TypeNotify type,String content,String sender){
        usersOnLine.get(sender).receiveNotify(type,content,getSeverDate(),sender);        
    }
    
    /**
     * * Il metodo permette l'invio della notifica pubblica agli utenti
     * 
     * @param type tipo di notifica
     * @param content contenuto della notifica
     * @param sender nickname dell'utente a cui verrà spedita la notifica
     * @param roomName nome della stanza relativa alla notifica pubblica
     */
    private void sendPublicNotify(TypeNotify type,String content,String sender,String roomName){
        Room room = rooms.get(roomName);
        for (String nick :room.getUsers())          
            usersOnLine.get(nick).receivePublicNotify(type,content,getSeverDate(),sender,roomName);
    }
     
    /**
     * Il metodo caricherà da file le stanze inserendole nella lista di quelle
     * presenti nel server
     * 
     * @param fileName nome del file
     * @return <code>true</code> operazione completata correttamente
     *         <code>false</code> operazione non riuscita
     */
    private boolean loadRoom(String fileName){
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine())!=null)
                rooms.put(line,new Room(line));                
            br.close();
            return true;
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("File error!");
        }
        return false;
    }
     
    /**
     * Il metodo effettua l'attach dell'osservatore riguardo UserOnline e Room
     */
    private void attachObserver(){
        usersOnLine.addObserver(this);              //Attach osservatore utenti online
        for(Room room:rooms.values())               //Attach osservatore per le stanze
            room.addObserver(this);
    }
    
    /**
     * Il metodo verrà chiamato quando sarà effettuata una modifica dello stato
     * dell'oggetto osservato. Nel nostro caso il metodo verrà chiamato se 
     * un nuovo utente si collegherà al server o se un utente si registrerà in
     * una stanza
     * 
     * @param o oggetto osservato
     * @param arg argomento
     */
    @Override
    public void update(Observable o, Object arg) {
        
        if (o instanceof Room) {
            Room room=(Room)o;
            String sender=(String)arg;
            sendPublicNotify(
                    TypeNotify.UPDATE_LIST_USERS,   //Tipo notifica
                    room.getUsersToString(),        //Content: Lista di utenti come striga
                    sender,                         //Sender
                    room.getName()                  //Nome stanza
            );
            sendPublicNotify(
                    TypeNotify.ADD_USER_TO_ROOM,
                    null,
                    sender,
                    room.getName()
            );
        }
        else if (o instanceof UserOnline) {
            String sender=(String)arg;
            String serverMessage = welcomeText(sender);
            sendNotify(TypeNotify.CONNECTION_ACCEPT, serverMessage,sender);
        }
    }
    //--------------------------------------------------------------------------
    
    
    
    
    //*******************************Medodi ausialiari del server******************************************
    /**
     * Il metodo ritorna all'utente il messaggio di benvenuto al servizio di 
     * messaggistica
     * 
     * @param user nickname dell'utente
     * @return messaggio di benvenuto
     */
    public static String welcomeText(String user){
        String date= getSeverDate().getTime().toString();
        return
                "*********************************************************************************\n"+
                "* Welcome to the Smart  Network  University  Communications         \t*\n"+
                "* "+user +" - "+date+"\t\t\t*\n"+
                "* This  server  was   created   Sat  February  7  2015  at  13:00:00\t*\n"+
                "* GNU General Public Licens	(GPL)\t\t\t*\n"+
                "* by   Russo Leandro  ,  Invincibile  Daniele ,  Didomenico  Nicola\t*\n"+
                "*********************************************************************************"
                ;
    }
    
    /**
     * Il metodo ritorna la data del server
     * 
     * @return data
     */
    protected static GregorianCalendar getSeverDate() {
        //------Incapsula l'ora del server nel calendar -------
        GregorianCalendar gc = new GregorianCalendar();
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);
        int hour = gc.get(Calendar.HOUR_OF_DAY);
        int min = gc.get(Calendar.MINUTE);
        int sec = gc.get(Calendar.SECOND);
        //------------------------------------------------------
        return new GregorianCalendar(year, month, day, hour, min, sec);
    }
//********************************************************************************************************

    @Override
    public void publicMessage(String msg, String sender, String roomName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}