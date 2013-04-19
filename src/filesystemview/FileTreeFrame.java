/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesystemview;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeFrame extends JFrame {
  private JTree fileTree;

  boolean opened = false;
  private FileSystemModel fileSystemModel;

  private JTextArea fileDetailsTextArea = new JTextArea();
	


  public FileTreeFrame(String directory) {
    super("JTree FileSystem Viewer");
    fileDetailsTextArea.setEditable(false);
    fileSystemModel = new FileSystemModel(new File(directory));
    fileTree = new JTree(fileSystemModel);
    fileTree.setEditable(true);
        
    
    fileTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent event) {
        final File file = (File) fileTree.getLastSelectedPathComponent();
        String split;
        split = file.getName();
        int aux = split.indexOf(".");
        if(aux == -1 || aux > 0){
        
          try {
              
              fileDetailsTextArea.setText(getFileDetails(file));
              if(file.isFile()){
                    
                   // Runtime.getRuntime().exec("gedit " + file.getPath());
                  opened = false;
                  
              }
          } catch (FileNotFoundException ex) {
              Logger.getLogger(FileTreeFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      }
    });

    fileTree.addKeyListener(new KeyListener() {
             
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        //if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                        File file = (File) fileTree.getLastSelectedPathComponent();
                         String split;
                        split = file.getName();
                        int aux = split.indexOf(".");
                        if(aux == -1 || aux > 0){
                        //}
                        if(e.getKeyCode() == 10){
                            
                            split = file.getPath();
                            System.out.println(split);

                            try {
                                Runtime.getRuntime().exec("geany " + split);
                            } catch (IOException ex) {
                                Logger.getLogger(FileTreeFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        }

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
        fileTree), new JScrollPane(fileDetailsTextArea));
    
    getContentPane().add(splitPane);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    int width = 1024;
    setSize(width, 768);
    int a = (int) Math.round(width*0.2);
    splitPane.setDividerLocation(a);
    setVisible(true);
  }
  
  public void keyTyped(KeyEvent e) {
        System.out.println("pertei");
        //displayInfo(e, "KEY TYPED: ");
    }

  private String getFileDetails(final File file) throws FileNotFoundException {
    if (file == null)
      return "";
    final StringBuffer buffer = new StringBuffer();
    int ext;
    if(!file.isDirectory() || file.isFile()){
        buffer.append("File Name: " + file.getName() + "\n");
        ext = file.getName().indexOf(".");
        if(ext != -1){
            buffer.append("File Extension: " + file.getName().substring(ext).toUpperCase() + "\n");
        };
        
        buffer.append("Path: " + file.getPath() + "\n");
        buffer.append("Size: " + file.length()/1024 + " kB\n");
        buffer.append("---------------------------------------------------------\n\n");
        try{
            BufferedReader in = new BufferedReader(new FileReader(file));
            while (in.ready()) {
                //Leitura de uma linha do arquivo
                buffer.append("    " + in.readLine() + "\n");
            }
                   
                

            }catch (IOException e) {

        }
        
    } else {
        buffer.append("Name: " + file.getName() + "\n");
        buffer.append("Path: " + file.getPath() + "\n");
        buffer.append("Size: " + file.length()/1024 + " kB\n");
    }

   
    
    return buffer.toString();
  }

  public static void main(String args[]) {
    new FileTreeFrame("/home/");
  }
}

class FileSystemModel implements TreeModel {
  private File root;

  private Vector listeners = new Vector();

  public FileSystemModel(File rootDirectory) {
    root = rootDirectory;
  }

  public Object getRoot() {
    return root;
  }

  public Object getChild(Object parent, int index) {
    File directory = (File) parent;
    String[] children = directory.list();
    return new TreeFile(directory, children[index]);
  }

  public int getChildCount(Object parent) {
    File file = (File) parent;
    if (file.isDirectory()) {
      String[] fileList = file.list();
      if (fileList != null)
        return file.list().length;
    }
    return 0;
  }

  public boolean isLeaf(Object node) {
    File file = (File) node;
    return file.isFile();
  }

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

  public void valueForPathChanged(TreePath path, Object value) {
    File oldFile = (File) path.getLastPathComponent();
    String fileParentPath = oldFile.getParent();
    String newFileName = (String) value;
    File targetFile = new File(fileParentPath, newFileName);
    oldFile.renameTo(targetFile);
    File parent = new File(fileParentPath);
    int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
    Object[] changedChildren = { targetFile };
    fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);

  }

  private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
    TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
    Iterator iterator = listeners.iterator();
    TreeModelListener listener = null;
    while (iterator.hasNext()) {
      listener = (TreeModelListener) iterator.next();
      listener.treeNodesChanged(event);
    }
  }

  public void addTreeModelListener(TreeModelListener listener) {
    listeners.add(listener);
  }

  public void removeTreeModelListener(TreeModelListener listener) {
    listeners.remove(listener);
  }

    void addKeyListener(KeyListener keyListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  private class TreeFile extends File {
    public TreeFile(File parent, String child) {
      super(parent, child);
    }

    public String toString() {
      return getName();
    }
  }
}
