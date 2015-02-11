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

import java.util.HashMap;
import java.util.Map;

/**
 * La classe analizza i comandi che l'utente invia al server al fine di 
 * individuare il nome del comando e gli eventuali parametri di esso
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
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
