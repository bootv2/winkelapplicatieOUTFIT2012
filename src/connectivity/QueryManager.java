package connectivity;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;
import model.Client;

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
                        result.getString("omschrijving"));
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
                Product tempProd = new Product(result2.getInt("product_id"), result2.getInt("categorie_id"), result2.getString("naam"), result2.getString("omschrijving"), result2.getDouble("prijs"), result2.getDouble("korting"));
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
        
        String sql = "UPDATE product SET korting='" + discount + "' WHERE product_id='" + id + "'";
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
                        result.getDouble("korting"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return product;
    }
    
    public Client getClient(int klantId)
    {
        Client temp = new Client();
        try {
            String sql = "SELECT * FROM klant WHERE id='" + klantId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next())
            {
                temp = new Client(result.getInt("id"), result.getString("naam"), result.getString("adres"), result.getString("postcode"), result.getString("woonplaats"));
                
            }
        }
        catch(SQLException e)
        {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return temp;
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
                        result.getDouble("korting")));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return products;
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
        String SQL = "INSERT INTO `gebruikers` (gebruikersnaam, wachtwoord, id) VALUES('" + username + "', '" + password + "', '" + id + "' )";
        result = dbmanager.insertQuery(SQL);
    }
    
    public boolean clientLogin(String username, String password)
    {
        String sql = "SELECT id FROM gebruikers WHERE gebruikersnaam='" + username + "' AND wachtwoord='" + password + "'";
        ResultSet result = dbmanager.doQuery(sql);
        try {
            if(result.next())
            {
                int id = result.getInt(1);
                main.WinkelApplication.getInstance().activeId = id;
                main.WinkelApplication.getInstance().elevationLevel = 0;
                main.WinkelApplication.getInstance().activeClient = main.WinkelApplication.getQueryManager().getClient(id);
                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void setOrder(model.Basket basket, String naam, String adres,
            String postcode, String woonplaats, String opmerking, String betaalmethode, int status) {
        String SQL_order = "INSERT INTO `order` (naam, adres, postcode, woonplaats, opmerking, betaalmethode, datum, orderstatus)"
                + " VALUES('" + naam + "', '" + adres + "', '" + postcode + "', '"
                + woonplaats + "', '" + opmerking + "', '" + betaalmethode + "', CURDATE(), '" + status + "' )";
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
            int aantal = basket.getProductAmount(product);
            String SQL_orderProduct = "INSERT INTO orderregel (product_id,order_id,aantal) VALUES (" + product_id + "," + order_id + "," + aantal + ")";
            dbmanager.insertQuery(SQL_orderProduct);
        }
    }
}
