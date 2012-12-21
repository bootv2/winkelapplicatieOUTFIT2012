package view;

import connectivity.QueryManager;
import main.WinkelApplication;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Category;

public class CategoryList extends JPanel implements MouseListener {
    private final int verticalPosition = 60;
    private final int offset = 40;

    public CategoryList() {
        super();
        setLayout(null);
        initComponents();
    }

    /** create the gui for this page */
    private void initComponents() {
        addTitle();
        addcategoryItems();
        addBasket();
    }

    /** add the page's title */
    private void addTitle() {
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Winkelapplicatie");
        lblTitle.setBounds(20, 20, 150, 20);
        lblTitle.setFont(WinkelApplication.FONT_16_BOLD);
        add(lblTitle);
    }

    /** add the different catergories to the page */
    private void addcategoryItems() {
        QueryManager queryManager = WinkelApplication.getQueryManager();
        List<Category> categories = queryManager.getCategories();

        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            JLabel lblDot = new JLabel("\u2022");
            lblDot.setFont(WinkelApplication.FONT_12_BOLD);
            lblDot.setBounds(20, verticalPosition + i * offset, 10, 20);
            add(lblDot);

            JLabel lblCategorie = new JLabel();
            lblCategorie.setName(String.valueOf(category.getCategoryId()));
            lblCategorie.setText(category.getName());
            lblCategorie.setBounds(35, verticalPosition + i * offset, 200, 20);
            lblCategorie.setFont(WinkelApplication.FONT_12_BOLD);
            lblCategorie.addMouseListener(this);
            lblCategorie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(lblCategorie);

            JLabel lblDescription = new JLabel();
            lblDescription.setText(category.getDescription());
            lblDescription.setBounds(35, verticalPosition  + i * offset + 15, 200, 20);
            lblDescription.setFont(WinkelApplication.FONT_10_PLAIN);
            add(lblDescription);
        }
    }

    /** add the basket view into this page */
    private void addBasket() {
        BasketDisplay basket = new BasketDisplay();
        this.add(basket);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        
        JLabel label = (JLabel) event.getSource();
        int categoryId = Integer.parseInt(label.getName());
        ProductList productList = new ProductList(categoryId);

        WinkelApplication.getInstance().showPanel(productList);
        
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawLine(20, 45, 540, 45);
    }
}
