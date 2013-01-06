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

public class PersoneelBeheer extends javax.swing.JFrame {

    private Gebruiker gebruiker;
    
    /**
     * Creates new form KlantenOverzicht
     */
    public PersoneelBeheer(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
        
        initComponents();
        
        setLocationRelativeTo(null);
        setTitle("Personeelbeheer");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        getGebruikers();
        handleEvents();
    }
    
    private JPopupMenu createMenu() {
        JPopupMenu menu = new JPopupMenu("Test");
        
        JMenuItem bewerkpersoneel = new JMenuItem("Bewerk personeel");
        bewerkpersoneel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = table_personeel.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                Object o = model.getValueAt(row, 0);
                int id = ((Integer)o).intValue();
                
                BewerkPersoneel bewerkpersoneel = new BewerkPersoneel(PersoneelBeheer.this, id);
                bewerkpersoneel.setVisible(true);
            }
        });
        
        menu.add(bewerkpersoneel);
                   
            JMenuItem verwijderpersoneel = new JMenuItem("Verwijder personeel");
            verwijderpersoneel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int row = table_personeel.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                    Object o = model.getValueAt(row, 0);
                    int id = ((Integer)o).intValue();
                    verwijderGebruiker(id);
                }
            });
            
            menu.add(verwijderpersoneel);
        
        return menu;
    }
    
    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
        model.setRowCount(0);
        getGebruikers();
    }
    
    public void handleEvents() {
        btn_nieuwepersoneel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                NieuwePersoneel nieuwepersoneel = new NieuwePersoneel(PersoneelBeheer.this);
                nieuwepersoneel.setVisible(true);
            }
        });
        
        btn_verwijderpersoneel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = table_personeel.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                Object o = model.getValueAt(row, 0);
                int id = ((Integer)o).intValue();
                verwijderGebruiker(id);
            }
        });
        
        
        //Tabel klanten rechtsklik menu
        table_personeel.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int row = table_personeel.rowAtPoint(e.getPoint());
                
                if (row >= 0 && row < table_personeel.getRowCount()) {
                    table_personeel.setRowSelectionInterval(row, row);
                }
                else {
                    table_personeel.clearSelection();
                }
                
                int rowindex = table_personeel.getSelectedRow();
                
                if (rowindex < 0)
                    return;
                
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu menu = createMenu();
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    
    public void verwijderGebruiker(int id) {
        WinkelApplication.getQueryManager().deleteGebruiker(id);
        refreshTable();
    }
    
    public void getGebruikers() {
        List<Gebruiker> gebruikers = WinkelApplication.getQueryManager().getGebruikersList();
        DefaultTableModel model = (DefaultTableModel) table_personeel.getModel();
        
        for(Gebruiker gebruiker : gebruikers) {
            model.addRow(new Object[] {
                    new Integer(gebruiker.getId()),
                    gebruiker.getVoornaam(),
                    gebruiker.getAchternaam(),
                    gebruiker.getGebruikersnaam(),
                    gebruiker.getWachtwoord(),
                    gebruiker.getAccounttype(),
                    gebruiker.getEmail()});
        }
    }
    
    public JTable getTable() {
        return table_personeel;
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
        btn_nieuwepersoneel = new javax.swing.JButton();
        btn_verwijderpersoneel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_personeel = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(855, 524));
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jPanel1.setPreferredSize(new java.awt.Dimension(850, 497));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        menu_panel.setMaximumSize(new java.awt.Dimension(850, 32));
        menu_panel.setMinimumSize(new java.awt.Dimension(850, 32));
        menu_panel.setPreferredSize(new java.awt.Dimension(850, 32));
        menu_panel.setLayout(new javax.swing.BoxLayout(menu_panel, javax.swing.BoxLayout.X_AXIS));

        btn_nieuwepersoneel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/addcustomer.png"))); // NOI18N
        btn_nieuwepersoneel.setToolTipText("Voeg een nieuwe personeelslid toe");
        btn_nieuwepersoneel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nieuwepersoneel.setIconTextGap(2);
        btn_nieuwepersoneel.setMaximumSize(new java.awt.Dimension(32, 32));
        btn_nieuwepersoneel.setMinimumSize(new java.awt.Dimension(32, 32));
        btn_nieuwepersoneel.setPreferredSize(new java.awt.Dimension(32, 32));
        menu_panel.add(btn_nieuwepersoneel);

        btn_verwijderpersoneel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/deletecustomer.png"))); // NOI18N
        btn_verwijderpersoneel.setToolTipText("Verwijder een personeelslid");
        btn_verwijderpersoneel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_verwijderpersoneel.setIconTextGap(2);
        btn_verwijderpersoneel.setMaximumSize(new java.awt.Dimension(32, 32));
        btn_verwijderpersoneel.setMinimumSize(new java.awt.Dimension(32, 32));
        btn_verwijderpersoneel.setPreferredSize(new java.awt.Dimension(32, 32));
        menu_panel.add(btn_verwijderpersoneel);

        jPanel1.add(menu_panel);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(1000, 465));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1000, 465));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 465));
        jScrollPane1.setRequestFocusEnabled(false);

        table_personeel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_personeel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Voornaam", "Achternaam", "Gebruikersnaam", "Wachtwoord", "Accounttype", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_personeel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table_personeel.setMaximumSize(new java.awt.Dimension(1000, 465));
        table_personeel.setMinimumSize(new java.awt.Dimension(1000, 465));
        table_personeel.setPreferredSize(new java.awt.Dimension(880, 465));
        table_personeel.setRowHeight(20);
        table_personeel.setSurrendersFocusOnKeystroke(true);
        table_personeel.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_personeel);
        table_personeel.getColumnModel().getColumn(0).setMinWidth(50);
        table_personeel.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_personeel.getColumnModel().getColumn(0).setMaxWidth(50);
        table_personeel.getColumnModel().getColumn(1).setMinWidth(150);
        table_personeel.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_personeel.getColumnModel().getColumn(1).setMaxWidth(150);
        table_personeel.getColumnModel().getColumn(2).setMinWidth(150);
        table_personeel.getColumnModel().getColumn(2).setPreferredWidth(150);
        table_personeel.getColumnModel().getColumn(2).setMaxWidth(150);
        table_personeel.getColumnModel().getColumn(3).setMinWidth(100);
        table_personeel.getColumnModel().getColumn(3).setPreferredWidth(100);
        table_personeel.getColumnModel().getColumn(3).setMaxWidth(100);
        table_personeel.getColumnModel().getColumn(4).setMinWidth(100);
        table_personeel.getColumnModel().getColumn(4).setPreferredWidth(100);
        table_personeel.getColumnModel().getColumn(4).setMaxWidth(100);
        table_personeel.getColumnModel().getColumn(5).setMinWidth(150);
        table_personeel.getColumnModel().getColumn(5).setPreferredWidth(150);
        table_personeel.getColumnModel().getColumn(5).setMaxWidth(150);
        table_personeel.getColumnModel().getColumn(6).setMinWidth(250);
        table_personeel.getColumnModel().getColumn(6).setPreferredWidth(250);
        table_personeel.getColumnModel().getColumn(6).setMaxWidth(250);

        jPanel1.add(jScrollPane1);

        getContentPane().add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_nieuwepersoneel;
    private javax.swing.JButton btn_verwijderpersoneel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu_panel;
    private javax.swing.JTable table_personeel;
    // End of variables declaration//GEN-END:variables
}
