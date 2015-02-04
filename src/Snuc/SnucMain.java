package Snuc;

import Common.IUser_Interaction;
import Snuc.gui.UserViewGUI;
import Snuc.gui.UserViewText;

/**
 * La classe contiene il main del lato del Client
 *
 * @author Russo Leandro, Invincibile Daniele e Didomenico Nicola
 */
public class SnucMain {

    public static void main(String[] args) {
        String modality = "textmode";

        IUser_Interaction interface_;
        switch (modality) {
            case "textmode":
                interface_ = new UserViewText();
                ((UserViewText) interface_).start();
                break;
            case "guimode":

                break;
            default:
                System.out.println("Wrong of parameters!");
                System.out.println("Uses: java PowCommandLine <textmode || guimode >");
                break;
        }
    }

}
