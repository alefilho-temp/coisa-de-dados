package models;

public class CategoryModel {
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

    String imagePath; // varchar(255) ex: C:\Program Files\Microsoft Visual Studio\2022\Community\dotnet\net8.0\runtime\shared\Microsoft.NETCore.App\8.0.10\imageName.png
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String color; // varchar(18) ex: rgb(255, 255, 255)
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public CategoryModel(int id, String name, String imagePath, String color) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.color = color;
    }
}
