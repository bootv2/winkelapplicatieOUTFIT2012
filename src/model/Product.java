package model;

public class Product {

    private int productId;
    private int categorieId;
    private String name;
    private String description;
    private double price;
    private int voorraad;
    private String image_path;
    private int aantal;
	private double discount;
    private boolean hasDiscount;

    public Product() {
        this(-1, -1, "", "", 0.0, 0, "", 0.0);
    }

    public Product(int product_id, int categorie_id, String name, String description, double price, int voorraad, String image_path, double discount) {
        this.productId = product_id;
        this.categorieId = categorie_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.voorraad = voorraad;
        this.image_path = image_path;
		this.discount = discount;
        if(this.discount>0.d)
            hasDiscount = true;
        else
            hasDiscount = false;
    }
    
    public Product(String name, double price, int aantal) {
        this.name = name;
        this.price = price;
        this.aantal = aantal;
		hasDiscount = false;
    }
    
    public Product(int id)
    {
        this.productId = id;
        Product temp = main.WinkelApplication.getQueryManager().getProduct(id);
        this.aantal = temp.getAantal();
        this.categorieId = temp.getCategorieId();
        this.description = temp.getDescription();
        this.discount = temp.getDiscount();
        if(this.discount > 0)
            this.hasDiscount = true;
        else
            this.hasDiscount = false;
        
        this.image_path = temp.getImagePath();
        this.name = temp.getName();
        this.price = temp.getPrice();
        this.voorraad = temp.getVoorraad();
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the categorieId
     */
    public int getCategorieId() {
        return categorieId;
    }

    /**
     * @param categorieId the categorieId to set
     */
    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }
	
	public double getDiscount()
    {
        return this.discount;
    }
    
    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public boolean hasDiscount()
    {
        return this.hasDiscount;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        boolean value;
        if (obj instanceof Product) {
            value = this.productId == ((Product)obj).productId;
        } else {
            value = super.equals(obj);
        }
        return value;
    }

    @Override
    public int hashCode() {
        return 13 * 3 + this.productId;
    }
    
    public String getImagePath() {
        return this.image_path;
    }
    
    public void setImagePath(String image_path) {
        this.image_path = image_path;
    }

    /**
     * @return the voorraad
     */
    public int getVoorraad() {
        return voorraad;
    }

    /**
     * @param voorraad the voorraad to set
     */
    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }

    /**
     * @return the aantal
     */
    public int getAantal() {
        return aantal;
    }

    /**
     * @param aantal the aantal to set
     */
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}
