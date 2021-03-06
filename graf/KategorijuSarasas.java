/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

import ds.FinansuValdymas;
import ds.Kategorija;
import java.util.ArrayList;

/**
 *
 * @author 20173025
 */
public class KategorijuSarasas extends javax.swing.JFrame {
FinansuValdymas a = null;
    /**
     * Creates new form KategorijuSarasas
     */
    public KategorijuSarasas(FinansuValdymas b) {
        a = b;
        initComponents();
        atnaujintiKategorijuLentele();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Kategoriju Sarasas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A", "sss"},
                {"B", "ddd"}
            },
            new String [] {
                "Pavadinimas", "Aprasymas"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void atnaujintiKategorijuLentele(){
        ArrayList<Kategorija> sar = a.gautiKategorijuSarasa();
        String[][] mas = new String[sar.size()][2];
        int i=0;
        for(Kategorija k:sar){
            mas[i][0] = k.getPavadinimas();
            mas[i][1] = k.getAprasymas();
            i++;
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            mas,
            new String [] {
                "Pavadinimas", "Aprasymas"
            }
        ));
    }
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
