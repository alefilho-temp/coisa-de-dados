package models;

public class ProductModel {
    int id = -1; // int
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    String name; // varchar(30) ex: categoria tal
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    float price;
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    float discount;
    public float getDiscount() {
        return discount;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    String imagePath; // varchar(255) ex: C:\Program Files\Microsoft Visual Studio\2022\Community\dotnet\net8.0\runtime\shared\Microsoft.NETCore.App\8.0.10\imageName.png
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    int categoryId;
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    int sellerId;
    public int getSellerId() {
        return sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public ProductModel() {

    }

    public ProductModel(int id, String name, float price, float discount, String imagePath, String description, int categoryId, int sellerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.imagePath = imagePath;
        this.description = description;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
    }
}
