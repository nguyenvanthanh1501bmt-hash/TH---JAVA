package Lab03;

public class Product {
    private String name;
    private String brand;
    private String price;
    private String description;
    private String shortDescription;
    private String imagePath;

    public Product(String name, String brand, String price,
                   String description, String shortDescription, String imagePath) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.shortDescription = shortDescription;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImagePath() {
        return imagePath;
    }
}