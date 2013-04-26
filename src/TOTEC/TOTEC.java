/*
 * 
 * Author: Vitor Casadei
 * Date: 29/04/2013
 * Info: Cyan Testing Tools
 * 
 */

/* Package TOTEC */
package TOTEC;

/* Java imports */
import CyanTestingTools.AAT;
import CyanTestingTools.CP;
import CyanTestingTools.RAT;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Enumeration;
import java.util.List;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;



/* Class TOTEC implements the FileTree Model of the Graphic Program */
public class TOTEC extends JFrame implements ActionListener, ItemListener {
    
    /* An Jtree Object that is used to model the Directory Tree */
    private JTree fileTree;
    /* Used to implement the JTree Model */
    private FileSystemModel fileSystemModel;
    /* The Window default width and heigth */
    private int width = 1024;
    private int heigth = 768;
    /* An JTextArea with the file Details */
    private JTextArea fileDetailsTextArea = new JTextArea();
    
    static boolean flag;
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

                menuItem = new JMenuItem("Select Folder AAT");
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

                menuItem = new JMenuItem("Select Folder CCC");
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

                menuItem = new JMenuItem("Select Folder RAT");
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

                menuItem = new JMenuItem("Select Folder CPC");
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

                menuItem = new JMenuItem("Select Folder CAT");
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

                menuItem = new JMenuItem("Select Folder CP");
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

                menuItem = new JMenuItem("Select Folder CPA");
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
        File fileEx = (File) fileTree.getLastSelectedPathComponent();
        File fileSel;
        switch(source.getText()){
            case "Reload File System Tree":
                createTree("/home/", 1);
                break;
            case "Exit":
                super.dispose();
                break;
            case "Execute CPC":
                
                break;
            case "Select Folder CPC":
                
                break;
            case "Execute CAT":
                
                break;
            case "Select Folder CAT":
                
                break;
            case "Execute CP":
                
                break;
            case "Select Folder CP":
                
                break;
            case "Execute CPA":
                
                break;
            case "Select Folder CPA":
                
                break;
            case "Execute AAT":
                
                if (fileEx.isDirectory() && fileIsOk(fileEx)) {
                    AAT.main(new String[]{fileEx.getPath()});
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: The Selected Item is not a Directory or not a supported one.");
                }
                break;
            case "Select Folder AAT":
                fileSel = new File(AAT.selectDirectory());
                if (fileSel.isDirectory() && fileIsOk(fileSel)) {
                    AAT.main(new String[]{fileSel.getPath()});
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: The Selected Item is not a Directory or not a supported one.");
                }
                break;
            case "Execute RAT":
                
                if (fileEx.isDirectory() && fileIsOk(fileEx)) {


                    String retorno = "";
                    retorno = RAT.main(new String[]{fileEx.getPath()});

                    JOptionPane.showMessageDialog(null, retorno, "RAT has finished", JOptionPane.INFORMATION_MESSAGE);


                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: The Selected Item is not a Directory or not a supported one.");
                }
                break;
            case "Select Folder RAT":
                fileSel = new File(RAT.selectDirectory());
                if (fileSel.isDirectory() && fileIsOk(fileSel)) {
                    RAT.main(new String[]{fileSel.getPath()});
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: The Selected Item is not a Directory or not a supported one.");
                }
                break;
                      
            
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
    
    /* TOTEC constructor Method */
    public TOTEC(String directory) {
        /* Sets the Window title to the super class (JFrame) */
        super("TOTEC (Tools for Teste Cases) - Cyan Language Testing Tools");
        /* Set the JTextArea not Editable */
        fileDetailsTextArea.setEditable(false);
        /* Creates a new FileSystemModel with the directory */
        
        createTree(directory, 1); 
        
        setJMenuBar(createMenuBar());
        
    }
    
    private void createTree(final String directory, int row){
        fileSystemModel = new FileSystemModel(new File(directory));
        
        /* Creates the JTree based on the FileSystemModel */
        fileTree = new JTree( fileSystemModel);
        /* Sets the JTree Editable */
        fileTree.setEditable(true);
        /* Get the focus to the second element on the tree */
        fileTree.setSelectionRow(row);
            
//        TreeModel model = fileTree.getModel();
//        if (model != null) {
//            Object root = model.getRoot();
//            System.out.println(root.toString());
//            walk(model, root, "");
//        } else {
//            System.out.println("Tree is empty.");
//        }
        
        /* Creates and Listener to the Tree node Selected */
        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            /* Inner Method that implements the usability of the JTree */
            @Override
            public void valueChanged(TreeSelectionEvent event) {
                /* Defines an File object as an JTree component */
                final File file = (File) fileTree.getLastSelectedPathComponent();
//                System.out.println(fileTree.getRowForPath(fileTree.getSelectionPath()));
                

                /* Ignores the files that have an . at the begin of it's name */
                if (file.getName().indexOf(".") == -1 || file.getName().indexOf(".") > 0) {
                    /* Try/Catch that tries to call the setText Method */
                    try {
                        /* Prints at the Text Area the File Details or the File Content, if not a Directory */
                        fileDetailsTextArea.setText(getFileDetails(file));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TOTEC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        /* Creates an KeyListener for the JTree */
        fileTree.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            /* Method that implements the keyPressed Event */
            @Override
            public void keyPressed(KeyEvent e) {
                /* Defines an File object as an JTree component */
                File file = (File) fileTree.getLastSelectedPathComponent();
                /* If the file attends to some rules */
                if (fileIsOk(file)) {
                    if (e.getKeyCode() == 10) {

                        try {
                            /* Obsolet call */
//                            Runtime.getRuntime().exec("geany " + split);
                            /* If is a File creates a Desktop Object */
                            if (file.isFile()) {
                                Desktop desktop = null;
                                if (Desktop.isDesktopSupported()) {
                                    desktop = Desktop.getDesktop();
                                }
                                /* Opens the file with the default editor */
                                desktop.open(file);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(TOTEC.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        if(e.getKeyCode() == 32){
                           createTree(directory , 1);
                          fileTree.collapseRow(fileTree.getRowForPath(fileTree.getSelectionPath()));
                          fileTree.expandRow(fileTree.getRowForPath(fileTree.getSelectionPath()));
                          
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        /* Add an JSplitPane object and puts the fileTree in this element */
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(fileTree), new JScrollPane(fileDetailsTextArea));
        getContentPane().add(splitPane);
        /* Set the Default Close Operation */
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /* Set the default size of the Window */
        setSize(width, heigth);
        /* Set the default width of the JSplitPane according to the Window width */
        splitPane.setDividerLocation((int) Math.round(width * 0.2));
        /* Set the Window to the center of the computer screen */
        setLocationRelativeTo(null);
        /* Maximize the root Window */
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        
//        setContentPane(demo.createContentPane());
 

    }
   
    
protected void walk(TreeModel model, Object o){
    int  cc;
    int a;
    cc = model.getChildCount(o);
    
    for( int i=0; i < cc; i++) {
      Object child = model.getChild(o, i );
      
      if (model.isLeaf(child)){
          a = 0;
//        System.out.println(child.toString());
      }else {
//        System.out.print(child.toString()+"--");
        walk(model,child); 
        }
     }
   }

    /* Method that verifies if the file can be opened or showed */
    private boolean fileIsOk(File file) {
        String split = file.getName();
        if (split.indexOf(".") == -1 || split.indexOf(".") > 0) {
            if ((split.indexOf(".rar") == -1 && split.indexOf(".zip") == -1 && split.indexOf(".tar.gz") == -1)) {
                return true;
            }
        }
        return false;
    }

    /* Method that returns the file details or the file content */
    private String getFileDetails(final File file) throws FileNotFoundException {
        /* Se o arquivo for nulo/não existir */
        if(file == null) {
            return "";
        }
        /* Buffer utilizado para salvar a mensagem de saída */
        StringBuffer buffer = new StringBuffer();
        /* Se não for um diretório e puder ser lido */
        if(file.isFile() && fileIsOk(file)) {
            /* Adiciona um cabeçalho */
            buffer.append("File Name: ").append(file.getName()).append("\n");
            int extension = file.getName().indexOf(".");
            if (extension != -1) {
                buffer.append("File Extension: ").append(file.getName().substring(extension).toUpperCase()).append("\n");
            };

            buffer.append("Path: ").append(file.getPath()).append("\n");
            buffer.append("Size: ").append(file.length() / 1024).append(" kB\n");
            buffer.append("---------------------------------------------------------\n\n");
            
            /* Try/Catch Statement that tries to open and read the file content */
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                while (in.ready()) {
                    //Reading the file
                    buffer.append("    ").append(in.readLine()).append("\n");
                }
            } catch (IOException e) {
                System.out.println("Error reading the file");
            }
        } else {
            /* If the file cannot be opened or is a directory */
            buffer.append("Name: ").append(file.getName()).append("\n");
            buffer.append("Path: ").append(file.getPath()).append("\n");
            buffer.append("Size: ").append(file.length() / 1024).append(" kB\n");
        }

        /* return the buffer */
        return buffer.toString();
    }

    /* Main Method */
    public static void main(String args[]) {
        /* Calls the class constructor */
        flag = true;
        TOTEC totec = new TOTEC("/home/");
       
    }
}

/* Class that implements the TreeModel's abstract methods */
class FileSystemModel implements TreeModel {
    /* File and Vector Objects */
    private File root;
    private Vector listeners = new Vector();

    /* Constructor of the class */
    public FileSystemModel(File rootDirectory) {
        root = rootDirectory;
    }

    /* Method that returns the Root file */
    @Override
    public Object getRoot(){
        return root;
    }

    /* Method that returns the Child file */
    @Override
    public Object getChild(Object parent, int index){
        File directory = (File) parent;
        String[] children = directory.list();
        return new FileSystemModel.TreeFile(directory, children[index]);
    }

    /* Method that returns the number of child files */
    @Override
    public int getChildCount(Object parent){
        File file = (File) parent;
        if (file.isDirectory()) {
            String[] fileList = file.list();
            if (fileList != null) {
                return file.list().length;
            }
        }
        return 0;
    }

    /* Method that returns true if the file is a leaf in the Tree Model */
    @Override
    public boolean isLeaf(Object node) {
        File file = (File) node;
        return file.isFile();
    }

    /* Method that returns the child index */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File file = (File) child;
        String[] children = directory.list();
        for (int i = 0; i < children.length; i++) {
            if (file.getName().equals(children[i])) {
                return i;
            }
        }
        return -1;
    }

    /* Method that reloads the path if happened some change */
    @Override
    public void valueForPathChanged(TreePath path, Object value) {
        File oldFile = (File) path.getLastPathComponent();
        String fileParentPath = oldFile.getParent();
        String newFileName = (String) value;
        File targetFile = new File(fileParentPath, newFileName);
        oldFile.renameTo(targetFile);
        File parent = new File(fileParentPath);
        int[] changedChildrenIndices = {getIndexOfChild(parent, targetFile)};
        Object[] changedChildren = {targetFile};
        fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);

    }

    /* Method that calls a function to update the Nodes if some change happened */
    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
        Iterator iterator = listeners.iterator();
        TreeModelListener listener = null;
        while (iterator.hasNext()) {
            listener = (TreeModelListener) iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    /* Method that adds a TreeModel Listener */
    @Override
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(listener);
    }

    /* Method that removes a TreeModel Listenet */
    @Override
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(listener);
    }

    /* Method that adds a keyListenet */
    void addKeyListener(KeyListener keyListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /* SubClass that extends File */
    private class TreeFile extends File {

        /* Constuctor Method */
        public TreeFile(File parent, String child) {
            super(parent, child);
        }

        /* Method that returns the file name */
        @Override
        public String toString() {
            return getName();
        }
    }
}

