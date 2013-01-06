/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connectivity.QueryManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JLabel;
import main.WinkelApplication;
import model.Category;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import model.Basket;
import model.Gebruiker;
import model.Product;

public class Catalogus extends javax.swing.JPanel {

    private Gebruiker gebruiker;
    private static String cart = "src/assets/icons/addtocart.png";
    private static String carthover = "src/assets/icons/addtocarthover.png";
    private static Color lbl_color = new Color(51, 102, 255);
    private static Color lbl_hovercolor = new Color(51, 51, 255);
    private final int verticalPosition = 10;
    private final int offset = 70;
    private final int imgsize = 64;
    private JScrollPane spane;
    private Component comp;
    private Basket basket;

    /**
     * Creates new form Catalogus
     */
    public Catalogus(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
        basket = new Basket();

        initComponents();
        createControls();
        addcategoryItems();
    }

    private void createControls() {
        spane = new JScrollPane(panel_products);
        spane.setOpaque(false);
        spane.getViewport().setOpaque(false);
        spane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jPanel3.add(spane);
    }

    private JPopupMenu createMenu() {
        JPopupMenu menu = new JPopupMenu("Test");

        JMenuItem bewerkproduct = new JMenuItem("Bewerk product");
        bewerkproduct.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int id = Integer.parseInt(comp.getName());

                BewerkProduct bewerkproduct = new BewerkProduct(Catalogus.this, id);
                bewerkproduct.setVisible(true);
            }
        });

        menu.add(bewerkproduct);

        JMenuItem verwijderproduct = new JMenuItem("Verwijder product");
        verwijderproduct.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int id = Integer.parseInt(comp.getName());

                verwijderProduct(id);
            }
        });

        menu.add(verwijderproduct);

        return menu;
    }

    public void addcategoryItems() {
        QueryManager queryManager = WinkelApplication.getQueryManager();
        List<Category> categories = queryManager.getCategories();

        for (int i = 0; i < categories.size(); i++) {
            final Category category = categories.get(i);

            final JLabel lbl_categorie = new JLabel();
            lbl_categorie.setName(String.valueOf(category.getCategoryId()));
            lbl_categorie.setText(category.getName());
            lbl_categorie.setBounds(35 + imgsize + 10, verticalPosition + i * imgsize + i * 5 + 10, 200, 20);
            lbl_categorie.setFont(new Font("TAHOMA", 0, 18));
            lbl_categorie.setForeground(lbl_color);
            lbl_categorie.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    String format = "<html><u>" + lbl_categorie.getText() + "</u></html>";
                    lbl_categorie.setText(format);
                    lbl_categorie.setForeground(lbl_hovercolor);
                }

                public void mouseExited(MouseEvent evt) {
                    lbl_categorie.setText(category.getName());
                    lbl_categorie.setForeground(lbl_color);
                }

                public void mousePressed(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    int categoryid = Integer.parseInt(label.getName());
                    addProductItems(categoryid);
                }
            });

            panel_categories.add(lbl_categorie);

            JLabel lbl_description = new JLabel();
            lbl_description.setText(category.getDescription());
            lbl_description.setBounds(35 + imgsize + 10, verticalPosition + i * imgsize + i * 5 + 30, 200, 20);
            lbl_description.setFont(WinkelApplication.FONT_10_PLAIN);
            panel_categories.add(lbl_description);

            JLabel lbl_image = new JLabel();
            lbl_image.setIcon(new ImageIcon(category.getImagePath()));
            lbl_image.setBounds(35, verticalPosition + i * imgsize + i * 5, imgsize, imgsize);
            panel_categories.add(lbl_image);
        }
    }

    public void addProductItems(int categoryid) {
        List<Product> products = WinkelApplication.getQueryManager().getProducts(categoryid);
        panel_products.removeAll();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            JLabel lblProduct = new JLabel(product.getName());
            lblProduct.setBounds(70, verticalPosition + i * offset, 340, 20);
            lblProduct.setFont(WinkelApplication.FONT_12_BOLD);
            lblProduct.setName(String.valueOf(product.getProductId()));
            if (gebruiker.getAccounttype().equals("systeembeheerder") || gebruiker.getAccounttype().equals("filiaalmanager")) {
                lblProduct.addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent evt) {
                        if (evt.isPopupTrigger()) {
                            comp = evt.getComponent();
                            JPopupMenu menu = createMenu();
                            menu.show(evt.getComponent(), evt.getX(), evt.getY());
                        }
                    }
                });
            }
            panel_products.add(lblProduct);

            JLabel lblDescription = new JLabel(product.getDescription());
            lblDescription.setBounds(70, verticalPosition + i * offset + 13, 340, 20);
            lblDescription.setFont(WinkelApplication.FONT_10_PLAIN);
            panel_products.add(lblDescription);

            JLabel lblPrice = new JLabel(WinkelApplication.CURRENCY + product.getPrice());
            lblPrice.setBounds(70 + 65, verticalPosition + i * offset + 30, 80, 20);
            lblPrice.setFont(WinkelApplication.FONT_16_BOLD);
            panel_products.add(lblPrice);
            
            JLabel lbl_image = new JLabel();
            lbl_image.setIcon(new ImageIcon(product.getImagePath()));
            lbl_image.setBounds(10, verticalPosition + i * offset, imgsize, imgsize);
            panel_products.add(lbl_image);

            final JLabel btnBestel = new JLabel(new ImageIcon("src/assets/icons/addtocart.png"));
            btnBestel.setBounds(70, verticalPosition + i * offset + 33, 60, 27);
            btnBestel.setName(String.valueOf(product.getProductId()));
            btnBestel.setToolTipText("Bestellen");
            btnBestel.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btnBestel.setIcon(new ImageIcon(carthover));
                }

                public void mouseExited(MouseEvent e) {
                    btnBestel.setIcon(new ImageIcon(cart));
                }
                
                public void mouseReleased(MouseEvent e) {
                    if (btnBestel != null) {
                        Product product = WinkelApplication.getQueryManager().getProduct(Integer.parseInt(btnBestel.getName()));
                        basket.addProduct(product);
                        
                        lbl_aantalproducten.setText(Integer.toString(basket.size()));
                        
                        if(basket.size() == 1)
                            lbl_productenin.setText("product in");
                        else
                            lbl_productenin.setText("producten in");
                    }
                }
            });
            panel_products.add(btnBestel);

            JLabel lbl_voorraad;
            
            if (product.getVoorraad() > 1) {
                lbl_voorraad = new JLabel(String.valueOf(product.getVoorraad()) + " stuks in voorraad");
            }
            else {
                lbl_voorraad = new JLabel(String.valueOf(product.getVoorraad()) + " stuk in voorraad");
            }
            
            lbl_voorraad.setName(String.valueOf(product.getProductId()));
            lbl_voorraad.setBounds(70 + 65, verticalPosition + i * offset + 45, 340, 20);
            lbl_voorraad.setFont(WinkelApplication.FONT_10_PLAIN);
            panel_products.add(lbl_voorraad);
        }

        panel_products.revalidate();
        panel_products.invalidate();
        panel_products.repaint();
        spane.revalidate();
        spane.invalidate();
        spane.repaint();
    }

    private void verwijderProduct(int id) {
        WinkelApplication.getQueryManager().deleteProduct(id);
        addcategoryItems();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_back = new javax.swing.JLabel();
        lbl_aantalproducten = new javax.swing.JLabel();
        lbl_productenin = new javax.swing.JLabel();
        llbl_winkelmandje = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panel_categories = new javax.swing.JPanel();
        panel_products = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(813, 469));
        setMinimumSize(new java.awt.Dimension(813, 469));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(813, 469));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Categorieën");
        jLabel1.setAlignmentY(0.0F);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Producten");
        jLabel2.setAlignmentY(0.0F);

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

        lbl_aantalproducten.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_aantalproducten.setText("0");

        lbl_productenin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_productenin.setText("producten in");
        lbl_productenin.setRequestFocusEnabled(false);

        llbl_winkelmandje.setForeground(new java.awt.Color(51, 102, 255));
        llbl_winkelmandje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        llbl_winkelmandje.setText("winkelmandje");
        llbl_winkelmandje.setAlignmentY(0.0F);
        llbl_winkelmandje.setMaximumSize(new java.awt.Dimension(130, 14));
        llbl_winkelmandje.setMinimumSize(new java.awt.Dimension(130, 14));
        llbl_winkelmandje.setPreferredSize(new java.awt.Dimension(65, 14));
        llbl_winkelmandje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                llbl_winkelmandjeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                llbl_winkelmandjeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                llbl_winkelmandjeMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(172, 172, 172)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(lbl_aantalproducten, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_productenin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(llbl_winkelmandje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_aantalproducten)
                            .addComponent(lbl_productenin)
                            .addComponent(llbl_winkelmandje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jPanel1, gridBagConstraints);

        jPanel3.setMaximumSize(new java.awt.Dimension(700, 400));
        jPanel3.setMinimumSize(new java.awt.Dimension(700, 400));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 400));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        panel_categories.setMaximumSize(new java.awt.Dimension(300, 400));
        panel_categories.setMinimumSize(new java.awt.Dimension(300, 400));
        panel_categories.setOpaque(false);
        panel_categories.setPreferredSize(new java.awt.Dimension(300, 400));

        javax.swing.GroupLayout panel_categoriesLayout = new javax.swing.GroupLayout(panel_categories);
        panel_categories.setLayout(panel_categoriesLayout);
        panel_categoriesLayout.setHorizontalGroup(
            panel_categoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        panel_categoriesLayout.setVerticalGroup(
            panel_categoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jPanel3.add(panel_categories);

        panel_products.setMaximumSize(null);
        panel_products.setName(""); // NOI18N
        panel_products.setOpaque(false);

        javax.swing.GroupLayout panel_productsLayout = new javax.swing.GroupLayout(panel_products);
        panel_products.setLayout(panel_productsLayout);
        panel_productsLayout.setHorizontalGroup(
            panel_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panel_productsLayout.setVerticalGroup(
            panel_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jPanel3.add(panel_products);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_backMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMousePressed
        WinkelApplication.getInstance().showPanel(new view.MainMenu1(gebruiker));
    }//GEN-LAST:event_lbl_backMousePressed

    private void lbl_backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseEntered
        lbl_back.setText("<html><u>Terug naar hoofdmenu</u></html>");
        lbl_back.setForeground(lbl_hovercolor);
    }//GEN-LAST:event_lbl_backMouseEntered

    private void lbl_backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseExited
        lbl_back.setText("Terug naar hoofdmenu");
        lbl_back.setForeground(lbl_color);
    }//GEN-LAST:event_lbl_backMouseExited

    private void llbl_winkelmandjeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_llbl_winkelmandjeMouseEntered
        llbl_winkelmandje.setText("<html><u>winkelmandje</u></html>");
        llbl_winkelmandje.setForeground(lbl_hovercolor);
    }//GEN-LAST:event_llbl_winkelmandjeMouseEntered

    private void llbl_winkelmandjeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_llbl_winkelmandjeMouseExited
        llbl_winkelmandje.setText("winkelmandje");
        llbl_winkelmandje.setForeground(lbl_color);
    }//GEN-LAST:event_llbl_winkelmandjeMouseExited

    private void llbl_winkelmandjeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_llbl_winkelmandjeMousePressed
        WinkelApplication.getInstance().showPanel(new WinkelWagen(gebruiker, basket));
    }//GEN-LAST:event_llbl_winkelmandjeMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_aantalproducten;
    private javax.swing.JLabel lbl_back;
    private javax.swing.JLabel lbl_productenin;
    private javax.swing.JLabel llbl_winkelmandje;
    private javax.swing.JPanel panel_categories;
    private javax.swing.JPanel panel_products;
    // End of variables declaration//GEN-END:variables
}
