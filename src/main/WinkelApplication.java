package main;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.Client;
import java.text.*;


/**
 *
 * @author Administrator
 */


public final class WinkelApplication {

    /** Define frame width, height and name*/
    public static final DecimalFormat currencyFormat = new DecimalFormat("###.##");
    public static final int FRAME_WIDTH = 755;
    public static final int FRAME_HEIGHT = 480;
    public static final String NAME = "WinkelApplicatie";
    public static final String CURRENCY = "€";
    public int activeId;
    public int elevationLevel;
    public model.Client activeClient;
    public boolean management;
    public view.DiscountManagement manager;
    /** static fonts which are used within the application */
    public static final Font FONT_10_PLAIN = new Font("Verdana", Font.PLAIN, 10);
    public static final Font FONT_10_BOLD = new Font("Verdana", Font.BOLD, 10);
    public static final Font FONT_12_BOLD = new Font("Verdana", Font.BOLD, 12);
    public static final Font FONT_16_BOLD = new Font("Verdana", Font.BOLD, 16);
    public static Client client;
    /** database manager */
    private Dbmanager dbManager;
    private QueryManager queryManager;
    /** models used in the application */
    private model.Basket basket;
    /** the main window */
    private JFrame mainWindow;
    /** singleton of the application */
    private static WinkelApplication instance = new WinkelApplication();

    private WinkelApplication() {
    }

    public void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting LookAndFeelClassName: " + e);
        }
        // create and initialize the connectivity
        dbManager = new Dbmanager();
        dbManager.openConnection();
        queryManager = new QueryManager(dbManager);

        // create an empty basket
        basket = new model.Basket();
    }

    public void startup() {
        mainWindow = new JFrame(NAME);
        mainWindow.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        client = queryManager.getClient(1);

        /** Make the window closing [x] button on the frame active */
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                shutdown();
            }
        });

        mainWindow.getContentPane().setLayout(new BorderLayout());
        showPanel(new view.MainMenu());

        mainWindow.setVisible(true);
        manager = new view.DiscountManagement();
    }

    public void showPanel(JPanel panel) {
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
        mainWindow.getContentPane().validate();
        mainWindow.getContentPane().repaint();
    }

    public void exit() {
        mainWindow.setVisible(false);
        shutdown();
    }

    private void shutdown() {
        mainWindow.dispose();
        dbManager.closeConnection();
    }

    /**
     * @return the instance of this class
     */
    public static WinkelApplication getInstance() {
        return instance;
    }
    
    public static Client getKlant()
    {
        return getInstance().client;
    }

    /**
     * @return the queryManager
     */
    public static QueryManager getQueryManager() {
        return getInstance().queryManager;
    }

    /**
     * @return the basket
     */
    public static model.Basket getBasket() {
        return getInstance().basket;
    }
    
    public void test(model.Client client)
    {
        queryManager.newClient(client);
    }

    public static void main(String args[]) {
        final WinkelApplication applicatie = WinkelApplication.getInstance();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    applicatie.initialize();
                    applicatie.startup();
                } catch (Exception e) {
                    System.out.println("Application" + applicatie.getClass().getName() + "failed to launch");
                    System.out.println(e.getMessage());
                }
                
        }
        });
    }
}
