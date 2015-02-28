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

import Common.IUser_Interaction;
import Snuc.User;
import Snuc.UserController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Tale classe implenta l'interfaccia grafica.
 * @author Russo Leandro, Invincibile Daniele, Didomenico Nicola
 */
public class UserViewGUI extends javax.swing.JFrame implements IUser_Interaction {

    
    /**
     * Creates new form UserViewGUImode
     */

    //Variabili non autogenerate da netbeans
    final private Execute cmd;
    final private JPanelDesktop JPS;
    final private JFrameFormConfigNetwork JFFCN;
    
    final private DefaultListModel listRoomsModel;
    final private DefaultListModel listUsersModel;
    final private Map<String,JTextArea> jtextAreaMap;
    final private UserController controller;
    final private User user;
    //--------------------------------------

    public UserViewGUI(UserController controller,User user) {
        
        this.controller=controller;
        this.user=user;
        
        cmd=new Execute();
        JPS = new JPanelDesktop(this);
        JFFCN= new JFrameFormConfigNetwork();
        
        
        initComponents();
        
        //Espandi JFrame
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dim = new Dimension (tool.getScreenSize());
        int height = (int) dim.getHeight();
        int width = (int) dim.getWidth();
        setSize(width, height);
        setLocation(width/2 - getWidth()/2,height/2 - getHeight()/2);
        this.setTitle("SNUC");
        // Setta Icona nella JFrame
        setIcon();
        
        
                
        //Server per lavore con gli Item della lista delle rooms
        listRoomsModel= new DefaultListModel();
        listUsersModel=new DefaultListModel();
        jtextAreaMap=new HashMap();
        jtextAreaMap.put("log",JPS.getjTextAreaLog()); //mettro dentro la mappa la TextArea log già instanziata prima
        JPS.getjTextAreaLog().setName("log");
    }
     
    
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/iconsiuc.png")));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDesktopPanel = new javax.swing.JDesktopPane();
        jMenuBarMenu = new javax.swing.JMenuBar();
        jMenuSiuc = new javax.swing.JMenu();
        jMenuItemConfigNetwork = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuServer = new javax.swing.JMenu();
        jMenuItemConnect = new javax.swing.JMenuItem();
        jMenuItemDisconnect = new javax.swing.JMenuItem();
        jMenuItemList = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemDoc = new javax.swing.JMenuItem();
        jMenuItemGuide = new javax.swing.JMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jDesktopPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jDesktopPanel, gridBagConstraints);

        jMenuSiuc.setText("Snuc");

        jMenuItemConfigNetwork.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemConfigNetwork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/config.png"))); // NOI18N
        jMenuItemConfigNetwork.setText("ConfigNetwork");
        jMenuItemConfigNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConfigNetworkActionPerformed(evt);
            }
        });
        jMenuSiuc.add(jMenuItemConfigNetwork);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/exit.png"))); // NOI18N
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuSiuc.add(jMenuItemExit);

        jMenuBarMenu.add(jMenuSiuc);

        jMenuServer.setText("Server");

        jMenuItemConnect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/connect.png"))); // NOI18N
        jMenuItemConnect.setText("Connect");
        jMenuItemConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnectActionPerformed(evt);
            }
        });
        jMenuServer.add(jMenuItemConnect);

        jMenuItemDisconnect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDisconnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/disconnect.png"))); // NOI18N
        jMenuItemDisconnect.setText("Disconnect");
        jMenuItemDisconnect.setEnabled(false);
        jMenuServer.add(jMenuItemDisconnect);

        jMenuItemList.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/getlist.png"))); // NOI18N
        jMenuItemList.setText("List");
        jMenuItemList.setEnabled(false);
        jMenuItemList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemListActionPerformed(evt);
            }
        });
        jMenuServer.add(jMenuItemList);

        jMenuBarMenu.add(jMenuServer);

        jMenuHelp.setText("Help");

        jMenuItemDoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/doc.png"))); // NOI18N
        jMenuItemDoc.setText("Documentation");
        jMenuItemDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDocActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemDoc);

        jMenuItemGuide.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItemGuide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/question.png"))); // NOI18N
        jMenuItemGuide.setText("Guide");
        jMenuItemGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuideActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemGuide);

        jMenuItemAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Snuc/gui/image/info.png"))); // NOI18N
        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBarMenu.add(jMenuHelp);

        setJMenuBar(jMenuBarMenu);

        setSize(new java.awt.Dimension(1034, 630));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemConfigNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConfigNetworkActionPerformed
        JFFCN.setVisible(true);
    }//GEN-LAST:event_jMenuItemConfigNetworkActionPerformed

    private void jMenuItemConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnectActionPerformed
        try {
            String nick = JFFCN.getjTextFieldNickname().getText();
            String address = JFFCN.getjTextFieldAddress().getText();
            int port = Integer.parseInt(JFFCN.getjTextFieldPort().getText());
            controller.connect(nick,address,port);
            jDesktopPanel.removeAll();
            jDesktopPanel.add(JPS);
            jDesktopPanel.revalidate();
            JPS.setVisible(true);
            jMenuItemDisconnect.setEnabled(true);
            jMenuItemList.setEnabled(true);
            jMenuItemConnect.setEnabled(false);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong port");
        }
        catch (UnknownHostException  e) {
            JOptionPane.showMessageDialog(null, "Wrong address");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection failed!");
        }


    }//GEN-LAST:event_jMenuItemConnectActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        JOptionPane.showMessageDialog(null, "  Smart Network University Communications - GNU GPL  \n"
                                          + "             Project Software Engineering A.A. 2014-15\n"
                                          + "                  by Russo, Invincibile, Didomenico\n"
                                          + "       Prof.Orazio Tomarchio - Università di Catania");
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDocActionPerformed
        cmd.openBrowser();
    }//GEN-LAST:event_jMenuItemDocActionPerformed

    private void jMenuItemGuideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuideActionPerformed
        cmd.openPdf();
    }//GEN-LAST:event_jMenuItemGuideActionPerformed

    private void jMenuItemListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemListActionPerformed
        controller.executeCommand("/listRooms");
    }//GEN-LAST:event_jMenuItemListActionPerformed
    
    public void jButtonSendActionPerformed(){
        String line=JPS.getjTextFieldMessage().getText();
        if(line.charAt(0)=='/')
            controller.executeCommand(line);
        else{
            String name="";
            try{
                name=JPS.getjTabbedPaneRoom().getSelectedComponent().getName();
                if(name.charAt(0)=='#')
                    controller.sendPublicMessage(line,name);
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"Errore invio messaggio","Errore invio messaggio",JOptionPane.ERROR_MESSAGE);
            }
        }
        JPS.getjTextFieldMessage().setText("");
    }
    
    public void jListRoomsMouseClicked() {                                        

        String nameRoom=(String)listRoomsModel.get(JPS.getjListRooms().getSelectedIndex());
        if(user.getUserList(nameRoom)==null){
            controller.executeCommand("/join '"+nameRoom+"'");
        }
        else
            updateUsersToRoom(nameRoom,user.getUserList(nameRoom).toArray());
    }
    
    public void jListUsersMouseClicked() {                                        
        try{
            String receiver = (String) listUsersModel.get(JPS.getjListUsers().getSelectedIndex());
            if(receiver.equals(user.getNick())) return;
            addPanel(receiver);
            selectPanel(receiver);
            JPS.getjListUsers().removeAll();
        }
        catch(ArrayIndexOutOfBoundsException e){}
    }
    public void jTabbedPaneRoomMouseClicked() {                                             

       String  roomName=JPS.getjTabbedPaneRoom().getSelectedComponent().getName();
       
       listUsersModel.removeAllElements();
       if((roomName!=null)&&(!roomName.equals("log"))){
            Object[] users=user.getUserList(roomName).toArray();
            for(int i=0;i<users.length;i++)
                listUsersModel.addElement((String)users[i]);
       }
       JPS.getjListRooms().setModel(listRoomsModel);
    }  
    
    @Override
    public void updateRoomList(String list){
        listRoomsModel.removeAllElements();
        
        String rooms[]=list.split("\n");
        for(int i=0; i<rooms.length;i++)
            listRoomsModel.addElement(rooms[i]);       
        JPS.getjListRooms().setModel(listRoomsModel);
    }
    
    @Override
    public void updateUsersToRoom(String nameRoom, Object[] users){
        //pulisco la lista degli utenti
        listUsersModel.removeAllElements();
                
        addPanel(nameRoom);
             
        selectPanel(nameRoom);
        
        //Aggiorno la lista degli utenti 
        for(int i=0; i<users.length;i++)
            listUsersModel.addElement((String)users[i]);       
        JPS.getjListUsers().setModel(listUsersModel);      
    }
    @Override
    public void printLogRoom(String log, String room){
        try{
            jtextAreaMap.get(room).append("\n"+log);
         }
         catch(NullPointerException e){e.printStackTrace();}
    }

    @Override
    public void printLog(String log){
        JPS.getjTextAreaLog().append("\n"+log);
    }
    @Override
    public void printPublicMessage(String content, String room){
        try{
            jtextAreaMap.get(room).append("\n"+content);
         }
         catch(NullPointerException e){e.printStackTrace();}
    }
    
    @Override
    public void printPrivateMessage(String content, String sender) {
        try {
            addPanel(sender);
            jtextAreaMap.get(sender).append("\n" + content);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPanel;
    private javax.swing.JMenuBar jMenuBarMenu;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemConfigNetwork;
    private javax.swing.JMenuItem jMenuItemConnect;
    private javax.swing.JMenuItem jMenuItemDisconnect;
    private javax.swing.JMenuItem jMenuItemDoc;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemGuide;
    private javax.swing.JMenuItem jMenuItemList;
    private javax.swing.JMenu jMenuServer;
    private javax.swing.JMenu jMenuSiuc;
    // End of variables declaration//GEN-END:variables

private boolean addPanel(String name){
        //Verifico se il pannello della stanza già esiste
        if(jtextAreaMap.get(name)==null){
            JTextArea x=new JTextArea();
            x.setName(name);
            jtextAreaMap.put(name,x);          
            JPS.getjTabbedPaneRoom().add(name,x);
            return true;
        }
        return false;
    }
    
    //Per selezionare il pannello --------------------------
    private boolean selectPanel(String name) {
        int len = JPS.getjTabbedPaneRoom().getComponents().length;
        for (int i = 0; i < len; i++) {
            if (JPS.getjTabbedPaneRoom().getTitleAt(i).equals(name)){
                JPS.getjTabbedPaneRoom().setSelectedIndex(i);
                return true;
            }
        }
        return false;
    }
}
