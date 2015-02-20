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

package Snuc;

import Common.IUser_Interaction;
import Snuc.gui.UserViewGUI;
import Snuc.gui.UserViewText;

/**
 * La classe contiene il main del lato del Client
 * 
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class SnucMain {
    
    
        public static void main(String[] args) {
         String modality = "guimode";
         User user=new User();
         UserController controller=new UserController();
         controller.setUser(user);
         IUser_Interaction interface_;       
         switch(modality) {
                case "textmode":
                    interface_=new UserViewText(controller,user);
                    ((UserViewText)interface_).start();
                    break;
                case "guimode":
                    java.awt.EventQueue.invokeLater(new Runnable() {
                       public void run() {
                        IUser_Interaction interface_;  
                        interface_=new UserViewGUI(controller,user);
                        ((UserViewGUI)interface_).setVisible(true);
                        controller.setView(interface_);
                       }
                    });
                    break;
                default: 
                    System.out.println("Wrong of parameters!");
                    System.out.println("Uses: java PowCommandLine <textmode || guimode >");
                break;
            }
    }

}
