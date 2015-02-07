package Snuc.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;

/**
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
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
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                File filea = new File("doc/javadoc/index.html");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                String cmd = "/usr/bin/firefox " + str;
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
                
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                File filea = new File("doc/guide.pdf");
                URI fileUri = filea.toURI();
                String str = fileUri.getPath();
                String cmd = "gnome-open " + str;
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

    

