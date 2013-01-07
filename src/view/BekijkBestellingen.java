/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import main.WinkelApplication;
import model.Gebruiker;
import model.Order;
import model.Product;

/**
 *
 * @author Sarfaraaz
 */
public class BekijkBestellingen extends javax.swing.JPanel {
    private Gebruiker gebruiker;
    private static Color lbl_color = new Color(51, 102, 255);
    private static Color lbl_hovercolor = new Color(51, 51, 255);
    
    /**
     * Creates new form BekijkBestellingen
     */
    public BekijkBestellingen(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
        
        initComponents();
        WinkelApplication.setSize(getPreferredSize());
        WinkelApplication.center();
        
        ListSelectionModel model = table_orders.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
            listOrderProducts(e);
        }
        });
        
        getOrders();
    }
    
    private void getOrders() {
        List<Order> orders = WinkelApplication.getQueryManager().getOrders();
        DefaultTableModel model = (DefaultTableModel) table_orders.getModel();
        
        for (Order order : orders) {
            model.addRow(new Object[] {
                new Integer(order.getId()),
                order.getKlant().getVoornaam() + " " + order.getKlant().getAchternaam(),
                order.getKlant().getAdres(),
                order.getKlant().getPostcode(),
                order.getKlant().getWoonplaats(),
                order.getBetaalmethode(),
                order.getDatum(),
            });
        }
    }
    
    private void getOrderProducts(int orderid) {
        List<Product> producten = WinkelApplication.getQueryManager().getOrderProducts(orderid);
        DefaultTableModel model = (DefaultTableModel) table_producten.getModel();

        for (Product product : producten) {
            model.addRow(new Object[] {
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getAantal(),
            });
        }
    }
    
    private void listOrderProducts(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        
        DefaultTableModel pmodel = (DefaultTableModel) table_producten.getModel();
        pmodel.setRowCount(0);
        
        ListSelectionModel model = (ListSelectionModel)e.getSource();
        int index = model.getMinSelectionIndex();
        int orderid = new Integer(table_orders.getValueAt(index, 0).toString());
        getOrderProducts(orderid);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table_orders = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_producten = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lbl_back = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(817, 479));
        setMinimumSize(new java.awt.Dimension(817, 479));
        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Orders");

        jScrollPane1.setOpaque(false);

        table_orders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Naam", "Adres", "Postcode", "Woonplaats", "Betaalmethode", "Datum"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_orders.setOpaque(false);
        jScrollPane1.setViewportView(table_orders);
        table_orders.getColumnModel().getColumn(0).setMinWidth(60);
        table_orders.getColumnModel().getColumn(0).setPreferredWidth(60);
        table_orders.getColumnModel().getColumn(0).setMaxWidth(60);
        table_orders.getColumnModel().getColumn(1).setResizable(false);
        table_orders.getColumnModel().getColumn(1).setPreferredWidth(450);
        table_orders.getColumnModel().getColumn(2).setMinWidth(100);
        table_orders.getColumnModel().getColumn(2).setPreferredWidth(100);
        table_orders.getColumnModel().getColumn(2).setMaxWidth(100);
        table_orders.getColumnModel().getColumn(3).setMinWidth(100);
        table_orders.getColumnModel().getColumn(3).setPreferredWidth(100);
        table_orders.getColumnModel().getColumn(3).setMaxWidth(100);
        table_orders.getColumnModel().getColumn(4).setMinWidth(130);
        table_orders.getColumnModel().getColumn(4).setPreferredWidth(130);
        table_orders.getColumnModel().getColumn(4).setMaxWidth(130);
        table_orders.getColumnModel().getColumn(5).setMinWidth(100);
        table_orders.getColumnModel().getColumn(5).setPreferredWidth(100);
        table_orders.getColumnModel().getColumn(5).setMaxWidth(100);
        table_orders.getColumnModel().getColumn(6).setMinWidth(100);
        table_orders.getColumnModel().getColumn(6).setPreferredWidth(100);
        table_orders.getColumnModel().getColumn(6).setMaxWidth(100);

        jScrollPane2.setOpaque(false);

        table_producten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product", "Prijs", "Aantal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_producten.setOpaque(false);
        jScrollPane2.setViewportView(table_producten);
        table_producten.getColumnModel().getColumn(0).setMinWidth(100);
        table_producten.getColumnModel().getColumn(0).setPreferredWidth(100);
        table_producten.getColumnModel().getColumn(0).setMaxWidth(100);
        table_producten.getColumnModel().getColumn(1).setResizable(false);
        table_producten.getColumnModel().getColumn(1).setPreferredWidth(450);
        table_producten.getColumnModel().getColumn(2).setMinWidth(100);
        table_producten.getColumnModel().getColumn(2).setPreferredWidth(100);
        table_producten.getColumnModel().getColumn(2).setMaxWidth(100);
        table_producten.getColumnModel().getColumn(3).setMinWidth(100);
        table_producten.getColumnModel().getColumn(3).setPreferredWidth(100);
        table_producten.getColumnModel().getColumn(3).setMaxWidth(100);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Producten");

        lbl_back.setForeground(new java.awt.Color(51, 102, 255));
        lbl_back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_back.setText("Terug naar hoofdmenu");
        lbl_back.setAlignmentY(0.0F);
        lbl_back.setMaximumSize(new java.awt.Dimension(130, 14));
        lbl_back.setMinimumSize(new java.awt.Dimension(130, 14));
        lbl_back.setPreferredSize(new java.awt.Dimension(130, 14));
        lbl_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_backMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_backMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseEntered
        lbl_back.setText("<html><u>Terug naar hoofdmenu</u></html>");
        lbl_back.setForeground(lbl_hovercolor);
    }//GEN-LAST:event_lbl_backMouseEntered

    private void lbl_backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseExited
        lbl_back.setText("Terug naar hoofdmenu");
        lbl_back.setForeground(lbl_color);
    }//GEN-LAST:event_lbl_backMouseExited

    private void lbl_backMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMousePressed
        WinkelApplication.getInstance().showPanel(new view.MainMenu1(gebruiker));
    }//GEN-LAST:event_lbl_backMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_back;
    private javax.swing.JTable table_orders;
    private javax.swing.JTable table_producten;
    // End of variables declaration//GEN-END:variables
}