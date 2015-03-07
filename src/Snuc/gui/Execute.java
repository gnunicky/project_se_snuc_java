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

package Snuc.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Tale classe manda in esecuzione la documentazione della Javadoc e una guida 
 * dell'applicazione per l'interfaccia grafica.
 * N.B. Bisogna avere installato:
 * - Browser: Google Chrome su Windows 7 e Mozilla Firefox su Gnu-Linux
 * - Lettore Pdf: Adobe Acrobat XI su Windows 7 e l'applicazione di default dei
 * pdf su Debian-like/Ubuntu/Linux Mint 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class Execute {

        public void openBrowser() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                File filea = new File("doc/javadoc/index.html");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                str=str.substring(1);
                System.out.println(str);
                //String browser = "C:/Program Files/Internet Explorer/iexplore.exe ";
                String browser = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe "; 
                Runtime.getRuntime().exec(browser + str);
            } else if (os.contains("Linux")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                File filea = new File("doc/javadoc/index.html");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                String cmd = "xdg-open " + str;
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader pbr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = pbr.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = pbr.readLine();
                }
            } else if (os.contains("Mac OS X")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                File filea = new File("doc/javadoc/index.html");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                String cmd = "open " + str;
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader pbr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = pbr.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = pbr.readLine();
                }
            } 
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }
    
    public void openPdf() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                File filea = new File("doc/guide.pdf");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                str=str.substring(1);
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + str);
                
            } else if (os.contains("Linux")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                File filea = new File("doc/guide.pdf");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                String cmd = "xdg-open " + str;
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader pbr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = pbr.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = pbr.readLine();
                }
            } else if (os.contains("Mac OS X")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                File filea = new File("doc/guide.pdf");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                String cmd = "open " + str;
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader pbr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = pbr.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = pbr.readLine();
                }
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }
   
    public void openClearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                Process p = Runtime.getRuntime().exec("clear");
                BufferedReader pbr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = pbr.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = pbr.readLine();
                }
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }
}

    

