/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snuc.gui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class Split {
    
    public void mysplit(){
    
    }
    
    public String[] getsplit(String text,String delemeter){
    List <String> parts = new ArrayList<>();
    text+=delemeter;
    for (int i = text.indexOf(delemeter), j=0; i != -1;) {
        String temp=text.substring(j,i);
            if(temp.trim().length()!=0){
                parts.add(temp);
            }
            j=i+delemeter.length();
            i = text.indexOf(delemeter,j);
        }
    return parts.toArray(new String[0]);
    }
    
    public int getnumer(){
        int i=0;
     return i; 
    }
}
