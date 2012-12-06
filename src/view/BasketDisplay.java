package view;

import main.WinkelApplication;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Product;

public class BasketDisplay extends JPanel implements ActionListener, Observer {

    public BasketDisplay() {
        super();
        setLayout(null);
        setBackground(new Color(151, 186, 225));
        setSize(190, 200);
        setLocation(550, 20);
        WinkelApplication.getBasket().addObserver(this);
        initComponents();
    }

    private void initComponents() {
        removeAll();
        model.Basket basket = WinkelApplication.getBasket();
        // if product basket is to big, resize the basket frame
        if (basket.size() > 6) {
            setSize(190, (300 + ((basket.size() - 6) * 10)));
        }

        JLabel lblTitle = new JLabel();
        lblTitle.setText("Winkelmand");
        lblTitle.setBounds(5, 5, 150, 20);
        lblTitle.setFont(WinkelApplication.FONT_12_BOLD);
        add(lblTitle);

        int verticalPosition = 40;

        for (Product product : basket.getProducts()) {
            JLabel lblProduct = new JLabel(basket.getProductAmount(product) +
                    " - " + product.toString());
            lblProduct.setBounds(5, verticalPosition, 130, 20);
            lblProduct.setFont(WinkelApplication.FONT_10_PLAIN);
            if(product.hasDiscount())
            {
                lblProduct.setForeground(Color.red);
            }
            add(lblProduct);

            JLabel lblPrice;
            
            if(product.hasDiscount())
            {
                lblPrice = new JLabel(WinkelApplication.CURRENCY + WinkelApplication.currencyFormat.format(product.getPrice() - ((product.getDiscount()/100) * product.getPrice())));
            }
            else
            {
                lblPrice = new JLabel(WinkelApplication.CURRENCY + WinkelApplication.currencyFormat.format(product.getPrice()));
            }
            
            lblPrice.setBounds(140, verticalPosition, 150, 20);
            lblPrice.setFont(WinkelApplication.FONT_10_PLAIN);
            add(lblPrice);

            verticalPosition += 20;
        }

        JLabel lblTotal = new JLabel("Totaal: ");
        lblTotal.setBounds(5, verticalPosition, 50, 20);
        lblTotal.setFont(WinkelApplication.FONT_10_BOLD);
        add(lblTotal);

        JLabel lblTotalPrice = new JLabel(WinkelApplication.CURRENCY + WinkelApplication.currencyFormat.format(basket.getTotalCosts()));
        lblTotalPrice.setBounds(140, verticalPosition, 50, 20);
        lblTotalPrice.setFont(WinkelApplication.FONT_10_BOLD);
        add(lblTotalPrice);

        int btnOffset = getHeight() - 25;
        JButton btnGoToPay = new JButton("Betalen...");
        btnGoToPay.setBounds(5, btnOffset, 180, 20);
        btnGoToPay.setFont(WinkelApplication.FONT_12_BOLD);
        btnGoToPay.addActionListener(this);
        if (basket.size() <= 0) {
            btnGoToPay.setEnabled(false);
        }
        add(btnGoToPay);
    }

    @Override
    public void update(Observable observable, Object arg) {
        initComponents();
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        WinkelApplication.getInstance().showPanel(new Payment());
    }
}
