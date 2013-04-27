/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import CyanTestingTools.AAT;
import CyanTestingTools.CPA;
import CyanTestingTools.CPC;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;

/**
 *
 * @author vitorcasadei
 */
public class CPAInterface extends javax.swing.JFrame {

    /**
     * Creates new form CPCInterface
     */
    public CPAInterface(String folder) {
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
        Parameterjs = new javax.swing.JScrollPane();
        ParameterTa = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        SelectFolderbt = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ProjAuthortf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Execute CPA");
        setMinimumSize(new java.awt.Dimension(286, 298));

        FolderTF.setForeground(new java.awt.Color(1, 1, 1));
        FolderTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FolderTFActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 1, 1));
        jLabel1.setText("Execute CPA at Folder:");

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

        ParameterTa.setColumns(20);
        ParameterTa.setRows(5);
        Parameterjs.setViewportView(ParameterTa);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 1, 1));
        jLabel3.setText("Insert the execution parameters:");

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

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 1, 1));
        jLabel4.setText("Insert author name:");

        ProjAuthortf.setForeground(new java.awt.Color(1, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FolderTF, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SelectFolderbt)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ProjAuthortf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Parameterjs, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(OKbt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cancelbt)
                .addGap(61, 61, 61))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(9, 9, 9)
                .addComponent(ProjAuthortf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Parameterjs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        String folder, proj, t, p, parameter;
        folder = proj = parameter = "";
        String [] parameters;

        if(!FolderTF.getText().equals("")){
            folder = FolderTF.getText();
        } else {
            FolderTF.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK = false;
        }
        
        if(!ProjAuthortf.getText().equals("")){
            proj = ProjAuthortf.getText();
        } else {
            ProjAuthortf.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK = false;
        }
        
        
        if(!ParameterTa.getText().equals("")){
            parameter = ParameterTa.getText();
            parameters = parameter.split(" ");
        } else {
            ParameterTa.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK = false;
        }
        if(isOK){  
            String retorno = "";
            retorno = CPA.main(new String[]{folder, proj, 
                    parameter});
            if(retorno.isEmpty()){
                this.dispose();
                SuccessMessage ok = new SuccessMessage("CPA");
            } else {
                this.dispose();
                ErrorMessage error = new ErrorMessage("CPA", retorno);
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
            java.util.logging.Logger.getLogger(CPAInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CPAInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CPAInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CPAInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPAInterface("").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelbt;
    private javax.swing.JTextField FolderTF;
    private javax.swing.JButton OKbt;
    private javax.swing.JTextArea ParameterTa;
    private javax.swing.JScrollPane Parameterjs;
    private javax.swing.JTextField ProjAuthortf;
    private javax.swing.JButton SelectFolderbt;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
