/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author vitorcasadei
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author vitorcasadei
 */
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
package TOTEC;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
 
/* PopupMenuDemo.java requires images/middle.gif. */
 
/*
 * Like MenuDemo, but with popup menus added.
 */
/**
 *
 * @author vitorcasadei
 */
public class PopupMenuDemo  implements ActionListener, ItemListener {

    String newline = "\n";
 
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
 
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("File");
            menu.setMnemonic(KeyEvent.VK_F);
            menu.getAccessibleContext().setAccessibleDescription(
                    "File Menu");
            menuBar.add(menu);

            //a group of JMenuItems
            menuItem = new JMenuItem("Reload File System Tree",
                                     KeyEvent.VK_R);
            //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
            menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_R, ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Reload File System Tree");
            menuItem.addActionListener(this);
            menu.add(menuItem);

            //a group of radio button menu items
            menu.addSeparator();
            menuItem = new JMenuItem("Exit",
                                     KeyEvent.VK_E);
            //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
            menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_E, ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Exit this Program");
            menuItem.addActionListener(this);
            menu.add(menuItem);
 
        //Build second menu in the menu bar.
        menu = new JMenu("Cyan Testing Tools");
        menu.setMnemonic(KeyEvent.VK_Y);
        menu.getAccessibleContext().setAccessibleDescription(
                "Cyan Testing Tools Menu");
        menuBar.add(menu);
        
            
            submenu = new JMenu("AAT");
            submenu.setMnemonic(KeyEvent.VK_T);

                menuItem = new JMenuItem("Execute AAT");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_1, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);

                menuItem = new JMenuItem("Select Folder");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_2, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);
            menu.add(submenu);
            
            
            submenu = new JMenu("CCC");
            submenu.setMnemonic(KeyEvent.VK_C);

                menuItem = new JMenuItem("Execute CCC");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_3, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);

                menuItem = new JMenuItem("Select Folder");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_4, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);
            menu.add(submenu);
            
            
            submenu = new JMenu("RAT");
            submenu.setMnemonic(KeyEvent.VK_R);

                menuItem = new JMenuItem("Execute RAT");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_5, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);

                menuItem = new JMenuItem("Select Folder");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_6, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);
            menu.add(submenu);
            
            menu.addSeparator();
            
            submenu = new JMenu("CPC");
            submenu.setMnemonic(KeyEvent.VK_P);

                menuItem = new JMenuItem("Execute CPC");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_7, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);

                menuItem = new JMenuItem("Select Folder");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_8, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);
            menu.add(submenu);
            
            submenu = new JMenu("CAT");
            submenu.setMnemonic(KeyEvent.VK_T);

                menuItem = new JMenuItem("Execute CAT");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_9, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);

                menuItem = new JMenuItem("Select Folder");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_0, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);
            menu.add(submenu);
            
            submenu = new JMenu("CP");
            submenu.setMnemonic(KeyEvent.VK_O);

                menuItem = new JMenuItem("Execute CP");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_A, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);

                menuItem = new JMenuItem("Select Folder");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_B, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);
            menu.add(submenu);
            
            submenu = new JMenu("CPA");
            submenu.setMnemonic(KeyEvent.VK_A);

                menuItem = new JMenuItem("Execute CPA");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_F, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);

                menuItem = new JMenuItem("Select Folder");
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_G, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                submenu.add(menuItem);
            menu.add(submenu);
            
            //Build second menu in the menu bar.
            menu = new JMenu("Help");
            menu.setMnemonic(KeyEvent.VK_H);
            menu.getAccessibleContext().setAccessibleDescription(
                    "Help Menu");
            menuBar.add(menu);
            
            menuItem = new JMenuItem("Program Info",
                                     KeyEvent.VK_I);
            //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Program Information");
            menuItem.addActionListener(this);
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Developer",
                                     KeyEvent.VK_D);
            //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Developer Information");
            menuItem.addActionListener(this);
            menu.add(menuItem);
            
            menuItem = new JMenuItem("The Cyan Language",
                                     KeyEvent.VK_L);
            //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "The Cyan Language Information");
            menuItem.addActionListener(this);
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Program Documentation",
                                     KeyEvent.VK_M);
            //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "TOTEC Program Documentation");
            menuItem.addActionListener(this);
            menu.add(menuItem);
            
            
            

        return menuBar;
    }
 

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")";
        if(source.getText().equals("Reload File System Tree")){
//            createTree("/home/", 1);
        }
//        output.append(s + newline);
//        output.setCaretPosition(output.getDocument().getLength());
        System.out.println(s);
    }
 
    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
//        output.append(s + newline);
//        output.setCaretPosition(output.getDocument().getLength());
        System.out.println(s);
    }
 
    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
 

 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
 
 
   
 
}

