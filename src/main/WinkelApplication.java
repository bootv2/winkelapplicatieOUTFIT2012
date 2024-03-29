package main;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
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
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 500;
    public static final String NAME = "Outfit4You";
    public static final String CURRENCY = "\u20AC";
    /** static fonts which are used within the application */
    public static final Font FONT_10_PLAIN = new Font("Verdana", Font.PLAIN, 10);
    public static final Font FONT_10_BOLD = new Font("Verdana", Font.BOLD, 10);
    public static final Font FONT_12_BOLD = new Font("Verdana", Font.BOLD, 12);
    public static final Font FONT_16_BOLD = new Font("Verdana", Font.BOLD, 16);
    /** database manager */
    private Dbmanager dbManager;
    private QueryManager queryManager;
    /** models used in the application */
    private model.Basket basket;
	public int activeId;
    public int elevationLevel;
    public model.Client activeClient;
    public boolean management;
    public view.DiscountManagement manager;
    /** klant */
    private model.Klant klant;
    /** the main window */
    private JFrame mainWindow;
    public DecimalFormat currencyFormat = new DecimalFormat("###.##");
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
        klant = queryManager.getKlant(1);
        
        mainWindow = new JFrame(NAME);
        mainWindow.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        /** Make the window closing [x] button on the frame active */
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                shutdown();
            }
        });
        
        BufferedImage bgimg = null;
        
        try {
            File file = new File("src/assets/background.png");
            bgimg = ImageIO.read(file);
        }
        catch (Exception e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
        
        model.BackgroundPanel bgpanel = new model.BackgroundPanel(bgimg, model.BackgroundPanel.SCALED);

        mainWindow.setContentPane(bgpanel);
        mainWindow.getContentPane().setLayout(new BorderLayout());
        showPanel(new view.Login());
        
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
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
        System.exit(0);
    }

    /**
     * @return the instance of this class
     */
    public static WinkelApplication getInstance() {
        return instance;
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
    
    public static model.Klant getKlant() {
        return getInstance().klant;
    }
    
    public static void setTitel(String titel) {
        getInstance().mainWindow.setTitle(titel);
    }
    
    public static void setSize(Dimension size) {
        getInstance().mainWindow.setSize(size);
    }
    
    public static void center() {
        getInstance().mainWindow.setLocationRelativeTo(null);
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
