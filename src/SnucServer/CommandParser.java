package SnucServer;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe analizza i comandi che l'utente invia al server al fine di 
 * individuare il nome del comando e gli eventuali parametri di esso
 * 
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class CommandParser {

    final private Map<String, String> map;
    final private String command;
    final private boolean isCorrect;

    /**
     * Costruttore della classe CommandParser
     * 
     * @param command contenuto del comando
     */
    public CommandParser(String command) {
        this.command = command;
        map = new HashMap();
        isCorrect=parseCommand();
    }

    /**
     * Il metodo permette la suddivisione del comando individuando il nome del
     * comando e i relativi parametri associati ad esso associati 
     * 
     * @return <code>true</code> se è stato inviato un comando in un formato
     *         corretto
     *         <code>false</false> se il formato del comando non è corretto
     */
    private boolean parseCommand() {
        
        if(command.charAt(0)!='/') return false;
        
        
        int count=0;
        for(int i=0;i<command.length();i++){
            char c=command.charAt(i);
            if(c==39) count++;
        }
        if(count%2!=0) return false;
        
        
        if((!command.contains("'"))&&(command.split(" ").length>1)) return false;
        String v[] = command.split("\'");
        v[0]=v[0].replace(" ", "");

        int j = 1;
        for (int i = 0; i < v.length; i++) {
            if (i == 0) {
                map.put("Command", v[i]);
            } else if (!v[i].equals(" ")) {
                map.put("par" + j, v[i]);
                j++;
            }
        }
        return true;
    }
    
    /**
     * Il metodo ritorna il nome del comando
     * 
     * @return nome del comando
     */
    public String getCommand() {
        return isCorrect?map.get("Command"):null;
    }

    /**
     * Il metodo ritorna il parametro associato al comando
     * 
     * @param index indice del parametro
     * @return parametro associato al comando
     */
    public String getParameter(int index) {
        return isCorrect?map.get("par" + index):null;
    }

}