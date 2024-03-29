package model;

public class Category {

    private int categoryId;
    private String name;
    private String description;
    private String image_path;

    public Category() {
        this(-1, "", "", "");
    }

    public Category(int category_id, String name, String description, String image_path) {
        this.categoryId = category_id;
        this.name = name;
        this.description = description;
        this.image_path = image_path;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
    
    public String getImagePath() {
        return image_path;
    }
}
