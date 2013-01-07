/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import main.WinkelApplication;
import model.Basket;
import model.Gebruiker;
import model.Klant;
import model.Product;

/**
 *
 * @author Sarfaraaz
 */
public class BestellingUitchecken extends javax.swing.JPanel {
    private Gebruiker gebruiker;
    private Basket basket;
    private Klant klant;
    
    /**
     * Creates new form BestellingUitchecken
     */
    public BestellingUitchecken(Gebruiker gebruiker, Basket basket) {
        this.gebruiker = gebruiker;
        this.basket = basket;
        
        initComponents();
        WinkelApplication.setSize(getPreferredSize());
        WinkelApplication.center();
        getKlanten();
    }
    
    private void getKlanten() {
        List<Klant> klanten = WinkelApplication.getQueryManager().getKlantList();

        for (Klant klant : klanten) {
            cb_klanten.addItem(klant.getId() + ": " + klant.getVoornaam() + " " + klant.getAchternaam());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cb_klanten = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_zoekklant = new javax.swing.JTextField();
        btn_zoek = new javax.swing.JButton();
        lbl_adres = new javax.swing.JLabel();
        lbl_postcode = new javax.swing.JLabel();
        lbl_woonplaats = new javax.swing.JLabel();
        lbl_telefoon = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cb_betaalmethodes = new javax.swing.JComboBox();
        lbl_image = new javax.swing.JLabel();
        btn_uitchecken = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(596, 308));
        setMinimumSize(new java.awt.Dimension(575, 264));
        setPreferredSize(new java.awt.Dimension(596, 308));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Bestelling uitchecken");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Klant selecteren"));

        cb_klanten.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_klantenItemStateChanged(evt);
            }
        });

        jLabel2.setText("Naam");

        jLabel3.setText("Klant");

        tf_zoekklant.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_zoek.setText("Zoeken");
        btn_zoek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_zoekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_zoek)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_klanten, 0, 202, Short.MAX_VALUE)
                            .addComponent(tf_zoekklant))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_zoekklant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_klanten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_zoek)
                .addGap(5, 5, 5))
        );

        lbl_adres.setText("Adres: testadres");

        lbl_postcode.setText("Postcode: 0000 XX");

        lbl_woonplaats.setText("Woonplaats: Amsterdam");

        lbl_telefoon.setText("Telefoon: 020-xxxxxxx");

        lbl_email.setText("Email: John.Doe@email.com");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Betaalmethode"));

        jLabel4.setText("Methode");

        cb_betaalmethodes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "iDeal", "Paypal", "Acceptgiro", "Bankoverschrijving", "Creditcard" }));
        cb_betaalmethodes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_betaalmethodesItemStateChanged(evt);
            }
        });

        lbl_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/betaalmethodes/ideal.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_betaalmethodes, 0, 141, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_betaalmethodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_uitchecken.setText("Uitchecken");
        btn_uitchecken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uitcheckenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_telefoon, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_woonplaats, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_postcode, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_adres, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lbl_email, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_uitchecken, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lbl_adres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_postcode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_woonplaats)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_telefoon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_uitchecken)
                            .addComponent(lbl_email)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_klantenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_klantenItemStateChanged
        int klantid = Integer.parseInt(cb_klanten.getSelectedItem().toString().substring(0, cb_klanten.getSelectedItem().toString().indexOf(":")));
        
        klant = WinkelApplication.getQueryManager().getKlant(klantid);
        
        lbl_adres.setText("Adres: " + klant.getAdres());
        lbl_postcode.setText("Postcode: " + klant.getPostcode());
        lbl_woonplaats.setText("Woonplaats: " + klant.getWoonplaats());
        lbl_telefoon.setText("Telefoon: " + klant.getTelefoon());
        lbl_email.setText("Email: " + klant.getEmail());
    }//GEN-LAST:event_cb_klantenItemStateChanged

    private void btn_zoekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_zoekActionPerformed
        for (int i = 0; i < cb_klanten.getItemCount(); i++) {
            if (cb_klanten.getItemAt(i).toString().contains(tf_zoekklant.getText())) {
                cb_klanten.setSelectedIndex(i);
            }
        }
    }//GEN-LAST:event_btn_zoekActionPerformed

    private void cb_betaalmethodesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_betaalmethodesItemStateChanged
        switch (cb_betaalmethodes.getSelectedIndex()) {
            case 0:
                lbl_image.setIcon(new ImageIcon("src/assets/betaalmethodes/ideal.png"));
                break;
            case 1:
                lbl_image.setIcon(new ImageIcon("src/assets/betaalmethodes/paypal.jpg"));
                break;
            case 2:
                lbl_image.setIcon(new ImageIcon("src/assets/betaalmethodes/acceptgiro.jpg"));
                break;
            case 3:
                lbl_image.setIcon(new ImageIcon("src/assets/betaalmethodes/bank.png"));
                break;
            case 4:
                lbl_image.setIcon(new ImageIcon("src/assets/betaalmethodes/creditcard.jpg"));
                break;
            default:
                lbl_image.setIcon(new ImageIcon("src/assets/betaalmethodes/ideal.png"));
                break;
        }
    }//GEN-LAST:event_cb_betaalmethodesItemStateChanged

    private void btn_uitcheckenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uitcheckenActionPerformed
        WinkelApplication.getQueryManager().setOrder(basket, klant.getVoornaam() + " " + klant.getAchternaam(), klant.getAdres(), klant.getPostcode(), klant.getWoonplaats(), cb_betaalmethodes.getSelectedItem().toString());
        WinkelApplication.getInstance().showPanel(new MainMenu1(gebruiker));
    }//GEN-LAST:event_btn_uitcheckenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_uitchecken;
    private javax.swing.JButton btn_zoek;
    private javax.swing.JComboBox cb_betaalmethodes;
    private javax.swing.JComboBox cb_klanten;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbl_adres;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_postcode;
    private javax.swing.JLabel lbl_telefoon;
    private javax.swing.JLabel lbl_woonplaats;
    private javax.swing.JTextField tf_zoekklant;
    // End of variables declaration//GEN-END:variables
}