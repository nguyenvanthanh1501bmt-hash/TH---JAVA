package Lab03;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Lab03 extends Application {

    private ProductDetail productDetail;
    private List<ProductCard> productCards = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        List<Product> products = ProductData.getProducts();

        HBox root = new HBox(25);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: white;");

        productDetail = new ProductDetail(products.get(0));

        GridPane productGrid = new GridPane();
        productGrid.setHgap(10);
        productGrid.setVgap(10);

        for (int i = 0; i < products.size(); i++) {
            ProductCard card = new ProductCard(products.get(i));

            int col = i % 4;
            int row = i / 4;

            productGrid.add(card, col, row);
            productCards.add(card);

            card.setOnMouseClicked(e -> {
                selectProduct(card);
            });
        }

        productCards.get(0).setSelectedStyle();

        root.getChildren().addAll(productDetail, productGrid);

        Scene scene = new Scene(root, 1180, 600);

        stage.setTitle("Lab03 - Adidas Store");
        stage.setScene(scene);
        stage.show();
    }

    private void selectProduct(ProductCard selectedCard) {
        for (ProductCard card : productCards) {
            card.setNormalStyle();
        }

        selectedCard.setSelectedStyle();
        productDetail.changeProduct(selectedCard.getProduct());
    }

    public static void main(String[] args) {
        launch(args);
    }
}