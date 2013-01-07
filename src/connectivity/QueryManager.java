package connectivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.WinkelApplication;
import model.Basket;
import model.Category;
import model.Gebruiker;
import model.Klant;
import model.Order;
import model.Product;

public class QueryManager {

    private final Dbmanager dbmanager;

    public QueryManager(Dbmanager dbmanager) {
        this.dbmanager = dbmanager;
    }

    public String getCategoryName(int categoryId) {
        String categoryName = "";
        try {
            String sql = "SELECT naam FROM categorie WHERE categorie_id='" + categoryId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                categoryName = result.getString("naam");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return categoryName;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            String sql = "SELECT * FROM categorie ORDER BY naam ASC";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                Category category = new Category(result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getString("image"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return categories;
    }
	
	public List<model.Product> getCategoryProduce(String name)
    {
        int categoryID = 0;
        String sql = "SELECT categorie_id FROM categorie WHERE naam='" + name + "'";
        ResultSet result = dbmanager.doQuery(sql);
        try {
            while(result.next())
            {
                categoryID = result.getInt("categorie_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "SELECT * FROM product WHERE categorie_id='" + categoryID + "'";
        
        ResultSet result2 = dbmanager.doQuery(sql);
        List<model.Product> tempList = new ArrayList<model.Product>();
        try
        {
            while(result2.next())
            {
                Product tempProd = new Product(result2.getInt("product_id"), result2.getInt("categorie_id"), result2.getString("naam"), result2.getString("omschrijving"), result2.getDouble("prijs"), result2.getInt("voorraad"), result2.getString("image"), result2.getDouble("discount"));
                tempList.add(tempProd);
            }
        }
        catch(SQLException e)
        {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return tempList;
    }
	
	public void setDiscount(double discount, int id)
    {
        
        String sql = "UPDATE product SET discount='" + discount + "' WHERE product_id='" + id + "'";
        dbmanager.insertQuery(sql);
    }

    public Product getProduct(int productId) {
        Product product = new Product();
        try {
            String sql = "SELECT * FROM product WHERE product_id='" + productId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                product = new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs"),
                        result.getInt("voorraad"),
                        result.getString("image"),
                        result.getDouble("discount"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return product;
    }
	
	 public void newClient(model.Client client)
    {
        String SQL_client = "INSERT INTO `klant` (naam, adres, postcode, woonplaats) VALUES('" + client.getNaam() + "', '" + client.getAdres() + "', '" + client.getPostcode() + "', '" + client.getWoonplaats() +  "' )";
        dbmanager.insertQuery(SQL_client);
    }
	
	
    
	
	public void newClientUsername(String username, String password, String naam, String adres)
    {
        int id = 0;
        String SQLID = "SELECT id FROM klant WHERE naam='" + naam + "' AND adres='" + adres + "'";
        ResultSet result = dbmanager.doQuery(SQLID);
        try {
            while(result.next()){ id = result.getInt("id"); }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        String SQL = "INSERT INTO `gebruiker` (gebruikersnaam, wachtwoord, id) VALUES('" + username + "', '" + password + "', '" + id + "' )";
        result = dbmanager.insertQuery(SQL);
    }

    public List<Product> getProducts(int categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT * FROM product WHERE categorie_id='" + categoryId + "' ORDER BY naam ASC";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                products.add(new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs"),
                        result.getInt("voorraad"),
                        result.getString("image"),
                        result.getDouble("discount")));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return products;
    }

    public void updateProduct(Product product, int id) {
        String sql = "UPDATE `product` SET naam='" + product.getName() + "'"
                + ", prijs='" + product.getPrice() + "'"
                + ", omschrijving='" + product.getDescription() + "'"
                + ", voorraad='" + product.getVoorraad() + "'"
                + ", image='" + product.getImagePath() + "'"
                + " WHERE product_id='" + id + "'";

        dbmanager.insertQuery(sql);
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM `product` WHERE product_id= '" + id + "'";
        dbmanager.insertQuery(sql);
    }

    public Gebruiker getGebruiker(String gebruikersnaam, String wachtwoord) {
        Gebruiker gebruiker = null;

        try {
            String sql = "SELECT * FROM gebruiker WHERE gebruikersnaam='" + gebruikersnaam + "' AND wachtwoord='" + wachtwoord + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                gebruiker = new Gebruiker();
                gebruiker.setGebruikersnaam(result.getString("gebruikersnaam"));
                gebruiker.setWachtwoord(result.getString("wachtwoord"));
                gebruiker.setAccounttype(result.getString("accounttype"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }

        return gebruiker;
    }
	
	
	
	public void deleteClient(String klantNaam)
    {

            String sql = "DELETE FROM klant WHERE naam='" + klantNaam + "'";
            ResultSet result = dbmanager.insertQuery(sql);
        

    }
    public void deleteClient(int ID)
    {

            String sql = "DELETE FROM klant WHERE id='" + ID + "'";
            ResultSet result = dbmanager.insertQuery(sql);
        

    }
	
	 public List<model.Client> searchClient(String terms)
    {
        List<model.Client> temp;
        temp = new ArrayList<Client>();
        try {
            String sql = "SELECT * FROM klant WHERE naam LIKE '%" + terms + "%' ORDER BY naam ASC" ;
            ResultSet result = dbmanager.doQuery(sql);
            for(int i=0; result.next(); i++)
            {
                   model.Client tempClient = new Client();
                   tempClient.setNaam(result.getString("naam")); 
                   tempClient.setAdres(result.getString("adres")); 
                   tempClient.setPostcode(result.getString("postcode")); 
                   tempClient.setWoonplaats(result.getString("woonplaats")); 
                   tempClient.setId(result.getInt("id")); 
                
                
                temp.add(tempClient);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return temp;
        
        
    }

    public List<Gebruiker> getGebruikersList() {
        List<Gebruiker> gebruikers = new ArrayList<Gebruiker>();

        try {
            String sql = "SELECT * FROM gebruiker";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                Gebruiker gebruiker = new Gebruiker();
                gebruiker.setId(result.getInt("id"));
                gebruiker.setVoornaam(result.getString("voornaam"));
                gebruiker.setAchternaam(result.getString("achternaam"));
                gebruiker.setGebruikersnaam(result.getString("gebruikersnaam"));
                gebruiker.setWachtwoord(result.getString("wachtwoord"));
                gebruiker.setAccounttype(result.getString("accounttype"));
                gebruiker.setEmail(result.getString("email"));

                gebruikers.add(gebruiker);
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }

        return gebruikers;
    }

    public void addGebruiker(Gebruiker gebruiker) {
        String sql = "INSERT INTO `gebruiker` (voornaam, achternaam, gebruikersnaam, wachtwoord, accounttype, email)"
                + "VALUES('" + gebruiker.getVoornaam() + "', '" + gebruiker.getAchternaam() + "', '" + gebruiker.getGebruikersnaam() + "', '"
                + gebruiker.getWachtwoord() + "', '" + gebruiker.getAccounttype() + "', '" + gebruiker.getEmail() + "')";

        dbmanager.insertQuery(sql);
    }

    public void deleteGebruiker(int id) {
        String sql = "DELETE FROM `gebruiker` WHERE id= '" + id + "'";
        dbmanager.insertQuery(sql);
    }

    public void updateGebruiker(Gebruiker gebruiker, int id) {
        String sql = "UPDATE `gebruiker` SET voornaam='" + gebruiker.getVoornaam() + "'"
                + ", achternaam='" + gebruiker.getAchternaam() + "'"
                + ", gebruikersnaam='" + gebruiker.getGebruikersnaam() + "'"
                + ", wachtwoord='" + gebruiker.getWachtwoord() + "'"
                + ", accounttype='" + gebruiker.getAccounttype() + "'"
                + ", email='" + gebruiker.getEmail() + "'"
                + " WHERE id='" + id + "'";

        dbmanager.insertQuery(sql);
    }

    public Gebruiker getGebruiker(int id) {
        Gebruiker gebruiker = new Gebruiker();

        try {
            String sql = "SELECT * FROM gebruiker "
                    + "WHERE id='" + id + "'";

            ResultSet result = dbmanager.doQuery(sql);

            if (result.next()) {
                gebruiker = new Gebruiker(result.getInt("id"),
                        result.getString("voornaam"),
                        result.getString("achternaam"),
                        result.getString("gebruikersnaam"),
                        result.getString("wachtwoord"),
                        result.getString("accounttype"),
                        result.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }

        return gebruiker;
    }

    public void addKlant(Klant klant) {
        String sql = "INSERT INTO `klant` (voornaam, achternaam, adres, postcode, woonplaats, telefoon, email, notities)"
                + "VALUES('" + klant.getVoornaam() + "', '" + klant.getAchternaam() + "', '" + klant.getAdres() + "', '"
                + klant.getPostcode() + "', '" + klant.getWoonplaats() + "', '" + klant.getTelefoon() + "', '" + klant.getEmail() + "', '" + klant.getNotities() + "')";

        dbmanager.insertQuery(sql);
    }

    public void deleteKlant(int id) {
        String sql = "DELETE FROM `klant` WHERE id= '" + id + "'";
        dbmanager.insertQuery(sql);
    }

    public void updateKlant(Klant klant, int id) {
        String sql = "UPDATE `klant` SET voornaam='" + klant.getVoornaam() + "'"
                + ", achternaam='" + klant.getAchternaam() + "'"
                + ", adres='" + klant.getAdres() + "'"
                + ", postcode='" + klant.getPostcode() + "'"
                + ", woonplaats='" + klant.getWoonplaats() + "'"
                + ", telefoon='" + klant.getTelefoon() + "'"
                + ", email='" + klant.getEmail() + "'"
                + ", notities='" + klant.getNotities() + "'"
                + " WHERE id='" + id + "'";

        dbmanager.insertQuery(sql);
    }

    public Klant getKlant(int id) {
        Klant klant = new Klant();

        try {
            String sql = "SELECT * FROM klant "
                    + "WHERE id='" + id + "'";

            ResultSet result = dbmanager.doQuery(sql);

            if (result.next()) {
                klant = new Klant(result.getInt("id"),
                        result.getString("voornaam"),
                        result.getString("achternaam"),
                        result.getString("adres"),
                        result.getString("postcode"),
                        result.getString("woonplaats"),
                        result.getString("telefoon"),
                        result.getString("email"),
                        result.getString("notities"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }

        return klant;
    }

    public List<Klant> getKlantList() {
        List<Klant> klanten = new ArrayList<Klant>();

        try {
            String sql = "SELECT * FROM klant";
            ResultSet result = dbmanager.doQuery(sql);

            while (result.next()) {
                klanten.add(new Klant(result.getInt("id"),
                        result.getString("voornaam"),
                        result.getString("achternaam"),
                        result.getString("adres"),
                        result.getString("postcode"),
                        result.getString("woonplaats"),
                        result.getString("telefoon"),
                        result.getString("email"),
                        result.getString("notities")));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }

        return klanten;
    }

    public void setOrder(model.Basket basket, String naam, String adres,
            String postcode, String woonplaats, String betaalmethode) {
        String SQL_order = "INSERT INTO `order` (naam, adres, postcode, woonplaats, betaalmethode, datum)"
                + " VALUES('" + naam + "', '" + adres + "', '" + postcode + "', '"
                + woonplaats + "', '" + betaalmethode + "', CURDATE() )";
        int order_id = 0;
        try {
            ResultSet result = dbmanager.insertQuery(SQL_order);
            result.next();
            order_id = result.getInt(1);
        } catch (SQLException e) {
            System.out.println("connectivity.QueryManager.setOrder() Exception:" + e.getMessage());
        }
        List<Product> products = basket.getProducts();
        for (Product product : products) {
            int product_id = product.getProductId();
            int aantal = product.getAantal();
            String SQL_orderProduct = "INSERT INTO orderregel (product_id,order_id,aantal) VALUES (" + product_id + "," + order_id + "," + aantal + ")";
            dbmanager.insertQuery(SQL_orderProduct);
        }
    }

    public List<Product> getOrderProducts(int orderid) {
        List<Product> producten = new ArrayList<Product>();

        try {
            String sql = "SELECT product.product_id, product.naam, product.prijs, orderregel.aantal"
                    + " FROM orderregel"
                    + " LEFT JOIN `product`"
                    + " ON orderregel.product_id = product.product_id"
                    + " WHERE orderregel.order_id = '" + orderid + "'"
                    + " ORDER BY orderregel.product_id";

            ResultSet result = dbmanager.doQuery(sql);

            while (result.next()) {
                Product product = new Product();

                try {
                    product.setProductId(result.getInt("product_id"));
                    product.setName(result.getString("naam"));
                    product.setPrice(result.getDouble("prijs"));
                    product.setAantal(result.getInt("aantal"));

                    producten.add(product);
                } catch (SQLException ex) {
                    Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return producten;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<Order>();

        try {
            String sql = "SELECT DISTINCT orderregel.order_id, `order`.naam, `order`.adres, `order`.postcode, `order`.woonplaats, `order`.betaalmethode, `order`.datum"
                    + " FROM orderregel"
                    + " LEFT JOIN `order`"
                    + " ON orderregel.order_id = `order`.order_id"
                    + " ORDER BY orderregel.order_id";

            ResultSet result = dbmanager.doQuery(sql);

            while (result.next()) {
                Order order = new Order();

                String voornaam = "";
                String achternaam = "";

                try {
                    order.setId(result.getInt("order_id"));
                    order.setBetaalmethode(result.getString("betaalmethode"));
                    order.setDatum(result.getString("datum"));
                    voornaam = result.getString("naam").substring(0, result.getString("naam").indexOf(" "));
                    achternaam = result.getString("naam").substring(result.getString("naam").indexOf(" "));
                    order.setKlant(new Klant(voornaam, achternaam, result.getString("adres"), result.getString("postcode"), result.getString("woonplaats")));

                    orders.add(order);
                } catch (SQLException ex) {
                    Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public void createBackup() throws InterruptedException {
        File file = null;
        Writer w = null;

        try {
            File dir = new File("database");
            String path = dir.getCanonicalPath();
            if (!dir.exists()) {
                dir.mkdir();
            }

            SimpleDateFormat dateformat = new SimpleDateFormat("d-M-y-HH.mm.ss");
            Date date = new Date();
            String filename = "\\" + dateformat.format(date) + ".sql";

            file = new File(dir + "/backup.bat");
            w = new PrintWriter(file);
            w.write("@echo off\n");
            w.write("echo WinkelApplication\n\n");
            w.write("echo Bezig met het backuppen van de database: " + path + filename + "\n");
            w.write("mysqldump -u" + Dbmanager.user + " -p" + Dbmanager.password + " -h" + Dbmanager.host + " --databases " + Dbmanager.database + " > \"" + path + filename + "\"" + "\n");
            w.write("exit\n");
            w.close();
            Process p = Runtime.getRuntime().exec("cmd /c start " + dir + "\\backup.bat");
            file.deleteOnExit();
        } catch (IOException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void restoreBackup(String filename) {
        File file = null;
        Writer w = null;

        try {
            File dir = new File("database");
            String path = dir.getCanonicalPath();
            if (!dir.exists()) {
                dir.mkdir();
            }

            file = new File(dir + "/herstel.bat");
            w = new PrintWriter(file);
            w.write("@echo off\n");
            w.write("echo WinkelApplication\n\n");
            w.write("echo Bezig met het herstellen van de database: " + path + "\\" + filename + "\n");
            w.write("mysql -u" + Dbmanager.user + " -p" + Dbmanager.password + " -h" + Dbmanager.host + " < \"" + path + "\\" + filename + "\"" + "\n");
            w.write("exit\n");
            w.close();
            Process p = Runtime.getRuntime().exec("cmd /c start " + dir + "\\herstel.bat");
            file.deleteOnExit();
        } catch (IOException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public List<Product> getVoorraad(){
        List<Product> producten = new ArrayList<Product>();
        
          try {
            String sql = "SELECT * FROM product";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                Product product = new Product();
                product.setProductId(result.getInt("product_id"));
                product.setAantal(result.getInt("aantal"));
                product.setName(result.getString("naam"));
                producten.add(product);
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return producten;
    }
       
           public void changevoorraad(int id, int aantal){
    String SQL_order = "UPDATE product SET aantal='" + aantal + "' WHERE product_id='" + id + "'";
       dbmanager.insertQuery(SQL_order);
    }

   
}


