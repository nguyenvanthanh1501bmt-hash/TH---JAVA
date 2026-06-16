package Lab04;

import Lab03.Product;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.ascending;

public class ProductDAO {
    private ProductDAO() {
    }

    public static List<Product> getProducts() {
        MongoCollection<Document> collection = MongoConnection.getProductCollection();
        if (collection.countDocuments() == 0) {
            seedIfEmpty();
        }

        return findAll();
    }

    public static List<Product> findAll() {
        MongoCollection<Document> collection = MongoConnection.getProductCollection();
        List<Product> products = new ArrayList<>();

        for (Document document : collection.find().sort(ascending("name"))) {
            products.add(fromDocument(document));
        }

        return products;
    }

    private static void seedIfEmpty() {
        MongoCollection<Document> collection = MongoConnection.getProductCollection();

//        if (collection.countDocuments() > 0) {
//            return;
//        }

        List<Document> documents = new ArrayList<>();
        for (Product product : SeedData.defaultProducts()) {
            documents.add(toDocument(product));
        }

        collection.insertMany(documents);
    }

    private static Product fromDocument(Document document) {
        return new Product(
                document.getString("name"),
                document.getString("brand"),
                document.getString("price"),
                document.getString("description"),
                document.getString("shortDescription"),
                document.getString("imagePath")
        );
    }

    private static Document toDocument(Product product) {
        return new Document("name", product.getName())
                .append("brand", product.getBrand())
                .append("price", product.getPrice())
                .append("description", product.getDescription())
                .append("shortDescription", product.getShortDescription())
                .append("imagePath", product.getImagePath());
    }
}