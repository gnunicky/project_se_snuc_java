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
        String modality = "guimode";
        User user = new User();
        UserController controller = new UserController();
        controller.setUser(user);
        IUser_Interaction interface_;
        switch (modality) {
            case "textmode":
                interface_ = new UserViewText(controller, user);
                ((UserViewText) interface_).start();
                break;
            case "guimode":
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        IUser_Interaction interface_;
                        interface_ = new UserViewGUI(controller, user);
                        ((UserViewGUI) interface_).setVisible(true);
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
