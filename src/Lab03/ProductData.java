package Lab03;

import Lab04.ProductDAO;

import java.util.List;

public class ProductData {
    public static List<Product> getProducts() {
        return ProductDAO.getProducts();
    }
}