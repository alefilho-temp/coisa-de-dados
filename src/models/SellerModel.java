package models;

public class SellerModel {
    int id = -1;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public SellerModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
