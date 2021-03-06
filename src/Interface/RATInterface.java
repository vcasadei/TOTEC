/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import CyanTestingTools.AAT;
import CyanTestingTools.CAT;
import CyanTestingTools.CPA;
import CyanTestingTools.CPC;
import CyanTestingTools.RAT;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;

/**
 *
 * @author vitorcasadei
 */
public class RATInterface extends javax.swing.JFrame {

    /**
     * Creates new form CPCInterface
     */
    public RATInterface(String folder) {
        setVisible(true);
        initComponents();
        if(!folder.isEmpty()){
            this.FolderTF.setText(folder);
        }
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        FolderTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        OKbt = new javax.swing.JButton();
        Cancelbt = new javax.swing.JButton();
        SelectFolderbt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Execute RAT");
        setMinimumSize(new java.awt.Dimension(286, 127));

        FolderTF.setForeground(new java.awt.Color(1, 1, 1));
        FolderTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FolderTFActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 1, 1));
        jLabel1.setText("Execute RAT at Folder:");

        OKbt.setText("Execute");
        OKbt.setToolTipText("Execute");
        OKbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OKbtMouseClicked(evt);
            }
        });

        Cancelbt.setText("Cancel");
        Cancelbt.setToolTipText("Calcel");
        Cancelbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CancelbtMouseClicked(evt);
            }
        });

        SelectFolderbt.setText("...");
        SelectFolderbt.setToolTipText("Select Folder");
        SelectFolderbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectFolderbtMouseClicked(evt);
            }
        });
        SelectFolderbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectFolderbtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FolderTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(OKbt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelbt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SelectFolderbt)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FolderTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectFolderbt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OKbt)
                    .addComponent(Cancelbt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FolderTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FolderTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FolderTFActionPerformed

    private void SelectFolderbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectFolderbtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelectFolderbtActionPerformed

    private void SelectFolderbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectFolderbtMouseClicked
        // TODO add your handling code here:
        File file = new File(AAT.selectDirectory());
        this.FolderTF.setText(file.toString());
    }//GEN-LAST:event_SelectFolderbtMouseClicked

    private void CancelbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelbtMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelbtMouseClicked

    private void OKbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OKbtMouseClicked
        // TODO add your handling code here:
        boolean isOK = true;
        String folder;
        folder = "";

        if(!FolderTF.getText().equals("")){
            folder = FolderTF.getText();
        } else {
            FolderTF.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK = false;
        }
                      
      
        if(isOK){  
            String retorno = "";
            retorno = RAT.main(new String[]{folder});
            if(retorno.isEmpty()){
                this.dispose();
                SuccessMessage ok = new SuccessMessage("RAT");
            } else {
                this.dispose();
                ErrorMessage error = new ErrorMessage("RAT", retorno);
            }
        }
        
        
        
    }//GEN-LAST:event_OKbtMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RATInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RATInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RATInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RATInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RATInterface("").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelbt;
    private javax.swing.JTextField FolderTF;
    private javax.swing.JButton OKbt;
    private javax.swing.JButton SelectFolderbt;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
