/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.WinkelApplication;
import model.Gebruiker;
import model.Klant;

public class KlantenOverzicht extends javax.swing.JFrame {

    private Gebruiker gebruiker;
    
    /**
     * Creates new form KlantenOverzicht
     */
    public KlantenOverzicht(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
        
        initComponents();
        
        if (gebruiker.getAccounttype().equals("systeembeheerder") || gebruiker.getAccounttype().equals("filiaalmanager")) {
            btn_verwijderklant.setVisible(true);
        }
        else {
            btn_verwijderklant.setVisible(false);
        }
        
        setLocationRelativeTo(null);
        setTitle("Klantenbeheer");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        getKlanten();
        handleEvents();
    }
    
    private JPopupMenu createMenu() {
        JPopupMenu menu = new JPopupMenu("Test");
        
        JMenuItem bewerkklant = new JMenuItem("Bewerk klant");
        bewerkklant.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = table_klanten.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                Object o = model.getValueAt(row, 0);
                int id = ((Integer)o).intValue();
                
                BewerkKlant bewerkklant = new BewerkKlant(KlantenOverzicht.this, id);
                bewerkklant.setVisible(true);
            }
        });
        
        menu.add(bewerkklant);
        
        if (gebruiker.getAccounttype().equals("systeembeheerder") || gebruiker.getAccounttype().equals("filiaalmanager")) {            
            JMenuItem verwijderklant = new JMenuItem("Verwijder klant");
            verwijderklant.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int row = table_klanten.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                    Object o = model.getValueAt(row, 0);
                    int id = ((Integer)o).intValue();
                    verwijderKlant(id);
                }
            });
            
            menu.add(verwijderklant);
        }
        
        return menu;
    }
    
    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
        model.setRowCount(0);
        getKlanten();
    }
    
    public void handleEvents() {
        btn_nieuweklant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                NieuweKlant nieuweklant = new NieuweKlant(KlantenOverzicht.this);
                nieuweklant.setVisible(true);
            }
        });
        
        btn_verwijderklant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = table_klanten.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                Object o = model.getValueAt(row, 0);
                int id = ((Integer)o).intValue();
                verwijderKlant(id);
            }
        });
        
        
        //Tabel klanten rechtsklik menu
        table_klanten.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int row = table_klanten.rowAtPoint(e.getPoint());
                
                if (row >= 0 && row < table_klanten.getRowCount()) {
                    table_klanten.setRowSelectionInterval(row, row);
                }
                else {
                    table_klanten.clearSelection();
                }
                
                int rowindex = table_klanten.getSelectedRow();
                
                if (rowindex < 0)
                    return;
                
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu menu = createMenu();
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    
    public void verwijderKlant(int id) {
        WinkelApplication.getQueryManager().deleteKlant(id);
        refreshTable();
    }
    
    public void getKlanten() {
        List<Klant> klanten = WinkelApplication.getQueryManager().getKlantList();
        DefaultTableModel model = (DefaultTableModel) table_klanten.getModel();
        
        for(Klant klant : klanten) {
            model.addRow(new Object[] {
                    new Integer(klant.getId()),
                    klant.getVoornaam(),
                    klant.getAchternaam(),
                    klant.getAdres(),
                    klant.getPostcode(),
                    klant.getWoonplaats(),
                    klant.getTelefoon(),
                    klant.getEmail()});
        }
    }
    
    public JTable getTable() {
        return table_klanten;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu_panel = new javax.swing.JPanel();
        btn_nieuweklant = new javax.swing.JButton();
        btn_verwijderklant = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_klanten = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(855, 524));
        setMinimumSize(new java.awt.Dimension(855, 524));
        setPreferredSize(new java.awt.Dimension(855, 524));
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jPanel1.setPreferredSize(new java.awt.Dimension(850, 497));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        menu_panel.setMaximumSize(new java.awt.Dimension(850, 32));
        menu_panel.setMinimumSize(new java.awt.Dimension(850, 32));
        menu_panel.setPreferredSize(new java.awt.Dimension(850, 32));
        menu_panel.setLayout(new javax.swing.BoxLayout(menu_panel, javax.swing.BoxLayout.X_AXIS));

        btn_nieuweklant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/addcustomer.png"))); // NOI18N
        btn_nieuweklant.setToolTipText("Voeg een nieuwe klant toe");
        btn_nieuweklant.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nieuweklant.setIconTextGap(2);
        btn_nieuweklant.setMaximumSize(new java.awt.Dimension(32, 32));
        btn_nieuweklant.setMinimumSize(new java.awt.Dimension(32, 32));
        btn_nieuweklant.setPreferredSize(new java.awt.Dimension(32, 32));
        menu_panel.add(btn_nieuweklant);

        btn_verwijderklant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/deletecustomer.png"))); // NOI18N
        btn_verwijderklant.setToolTipText("Verwijder een klant");
        btn_verwijderklant.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_verwijderklant.setIconTextGap(2);
        btn_verwijderklant.setMaximumSize(new java.awt.Dimension(32, 32));
        btn_verwijderklant.setMinimumSize(new java.awt.Dimension(32, 32));
        btn_verwijderklant.setPreferredSize(new java.awt.Dimension(32, 32));
        menu_panel.add(btn_verwijderklant);

        jPanel1.add(menu_panel);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(1000, 465));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1000, 465));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 465));
        jScrollPane1.setRequestFocusEnabled(false);

        table_klanten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_klanten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Voornaam", "Achternaam", "Adres", "Postcode", "Woonplaats", "Telefoon", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_klanten.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table_klanten.setMaximumSize(new java.awt.Dimension(1000, 465));
        table_klanten.setMinimumSize(new java.awt.Dimension(1000, 465));
        table_klanten.setPreferredSize(new java.awt.Dimension(1100, 465));
        table_klanten.setRowHeight(20);
        table_klanten.setSurrendersFocusOnKeystroke(true);
        table_klanten.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_klanten);
        table_klanten.getColumnModel().getColumn(0).setMinWidth(50);
        table_klanten.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_klanten.getColumnModel().getColumn(0).setMaxWidth(50);
        table_klanten.getColumnModel().getColumn(1).setMinWidth(150);
        table_klanten.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_klanten.getColumnModel().getColumn(1).setMaxWidth(150);
        table_klanten.getColumnModel().getColumn(2).setMinWidth(150);
        table_klanten.getColumnModel().getColumn(2).setPreferredWidth(150);
        table_klanten.getColumnModel().getColumn(2).setMaxWidth(150);
        table_klanten.getColumnModel().getColumn(3).setMinWidth(100);
        table_klanten.getColumnModel().getColumn(3).setPreferredWidth(100);
        table_klanten.getColumnModel().getColumn(3).setMaxWidth(100);
        table_klanten.getColumnModel().getColumn(4).setMinWidth(100);
        table_klanten.getColumnModel().getColumn(4).setPreferredWidth(100);
        table_klanten.getColumnModel().getColumn(4).setMaxWidth(100);
        table_klanten.getColumnModel().getColumn(5).setMinWidth(150);
        table_klanten.getColumnModel().getColumn(5).setPreferredWidth(150);
        table_klanten.getColumnModel().getColumn(5).setMaxWidth(150);
        table_klanten.getColumnModel().getColumn(6).setMinWidth(150);
        table_klanten.getColumnModel().getColumn(6).setPreferredWidth(150);
        table_klanten.getColumnModel().getColumn(6).setMaxWidth(150);
        table_klanten.getColumnModel().getColumn(7).setMinWidth(250);
        table_klanten.getColumnModel().getColumn(7).setPreferredWidth(250);
        table_klanten.getColumnModel().getColumn(7).setMaxWidth(250);

        jPanel1.add(jScrollPane1);

        getContentPane().add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_nieuweklant;
    private javax.swing.JButton btn_verwijderklant;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu_panel;
    private javax.swing.JTable table_klanten;
    // End of variables declaration//GEN-END:variables
}
