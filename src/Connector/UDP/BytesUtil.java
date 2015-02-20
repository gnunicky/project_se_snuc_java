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

package Connector.UDP;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * La classe permette di compiere le operazioni necessarie per convertire byte in
 * oggetti e viceversa.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class BytesUtil {

    
    /**
     * Costruttore di BytesUtil (non utilizzato).
     */
    public BytesUtil(){}
    
    
    /**
     * Il metodo trasforma l'oggetto che viene passato come parametro in un
     * array di byte.
     * @param obj Oggetto da serializzare in byte.
     * @return oggetto serializzato in un array di byte.
     * @throws IOException in caso qualsosa vado storto nella processo di 
     * serializzazione.
     */
    public static byte[] toByteArray(Object obj) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        return bytes;
    }

    
    /**
     * Il metodo trasforma un array di byte che viene passato come parametro in
     * un oggetto.
     * @param bytes array di byte da portare in un oggetto.
     * @return array di byte deserializzato in un oggetto.
     * @throws IOException in caso qualsosa vado storto nella processo di
     * deserializzazione.
     * @throws ClassNotFoundException se i byte non corrispondono ad un oggetto.
     */
    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException{
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
        return obj;
    }

}
