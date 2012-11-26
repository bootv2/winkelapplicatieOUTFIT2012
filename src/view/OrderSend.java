package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.WinkelApplication;

public class OrderSend extends JPanel implements ActionListener, MouseListener {

    public OrderSend() {
        super();
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        // display title
        JLabel lblTitle1 = new JLabel();
        lblTitle1.setText("Winkelapplicatie");
        lblTitle1.setBounds(20, 20, 150, 20);
        lblTitle1.setFont(WinkelApplication.FONT_16_BOLD);
        lblTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTitle1.addMouseListener(this);
        add(lblTitle1);

        JLabel lblTitle2 = new JLabel();
        lblTitle2.setText("-");
        lblTitle2.setBounds(170, 20, 20, 20);
        lblTitle2.setFont(WinkelApplication.FONT_16_BOLD);
        add(lblTitle2);

        JLabel lblTitle3 = new JLabel();
        lblTitle3.setText("Order verzonden");
        lblTitle3.setBounds(190, 20, 500, 20);
        lblTitle3.setFont(WinkelApplication.FONT_16_BOLD);
        add(lblTitle3);

        JLabel lblMessage = new JLabel();
        lblMessage.setText("Uw order is gemaakt. Wij zullen deze zo spoedig mogelijk verzenden.");
        lblMessage.setBounds(20, 80, 500, 20);
        lblMessage.setFont(WinkelApplication.FONT_12_BOLD);
        add(lblMessage);

        JButton btnGoBack = new JButton("Terug naar de winkelapplicatie");
        btnGoBack.setBounds(20, 100, 250, 20);
        btnGoBack.setFont(WinkelApplication.FONT_10_BOLD);
        btnGoBack.addActionListener(this);
        add(btnGoBack);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        WinkelApplication.getInstance().showPanel(new view.CategoryList());
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        WinkelApplication.getInstance().showPanel(new view.CategoryList());
    }
}
