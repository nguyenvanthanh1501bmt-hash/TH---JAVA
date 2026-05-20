package Lab03;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProductCard extends StackPane {

    private Product product;

    public ProductCard(Product product) {
        this.product = product;

        VBox content = new VBox(8);
        content.setPadding(new Insets(10));
        content.setPrefSize(200, 235);

        Label nameLabel = new Label(product.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        nameLabel.setTextFill(Color.web("#4d4d4d"));
        nameLabel.setMaxWidth(180);
        nameLabel.setTextOverrun(OverrunStyle.ELLIPSIS);

        Label descLabel = new Label(product.getShortDescription());
        descLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        descLabel.setTextFill(Color.web("#a0a0a0"));
        descLabel.setMaxWidth(180);
        descLabel.setTextOverrun(OverrunStyle.ELLIPSIS);

        ImageView imageView = new ImageView(loadImage(product.getImagePath()));
        imageView.setFitWidth(220);
        imageView.setFitHeight(130);
        imageView.setPreserveRatio(true);

        StackPane imageBox = new StackPane(imageView);
        imageBox.setPrefWidth(260);
        imageBox.setPrefHeight(150);
        imageBox.setAlignment(Pos.CENTER);

        Label brandLabel = new Label(product.getBrand());
        brandLabel.setFont(Font.font("Arial", 13));
        brandLabel.setTextFill(Color.web("#555555"));

        Label priceLabel = new Label(product.getPrice());
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        priceLabel.setTextFill(Color.web("#4d4d4d"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottom = new HBox(brandLabel, spacer, priceLabel);
        bottom.setAlignment(Pos.CENTER_LEFT);

        Region imageSpacer = new Region();
        VBox.setVgrow(imageSpacer, Priority.ALWAYS);

        content.getChildren().addAll(
                nameLabel,
                descLabel,
                imageBox,
                imageSpacer,
                bottom
        );

        getChildren().add(content);

        setCursor(Cursor.HAND);
        setNormalStyle();

        setOnMouseEntered(e -> {
            if (!getStyle().equals(selectedStyle())) {
                setHoverStyle();
            }
        });

        setOnMouseExited(e -> {
            if (!getStyle().equals(selectedStyle())) {
                setNormalStyle();
            }
        });
    }

    public Product getProduct() {
        return product;
    }

    public void setNormalStyle() {
        setStyle(normalStyle());
    }

    public void setHoverStyle() {
        setStyle(hoverStyle());
    }

    public void setSelectedStyle() {
        setStyle(selectedStyle());
    }

    private Image loadImage(String path) {
        var inputStream = getClass().getResourceAsStream(path);

        if (inputStream == null) {
            throw new RuntimeException("Không tìm thấy ảnh: " + path);
        }

        return new Image(inputStream);
    }

    private String normalStyle() {
        return """
                -fx-background-color: #f1f1f1;
                -fx-background-radius: 8;
                -fx-border-radius: 8;
                -fx-border-color: transparent;
                -fx-border-width: 1;
                """;
    }

    private String hoverStyle() {
        return """
                -fx-background-color: #e9e9e9;
                -fx-background-radius: 8;
                -fx-border-radius: 8;
                -fx-border-color: #bdbdbd;
                -fx-border-width: 1;
                """;
    }

    private String selectedStyle() {
        return """
                -fx-background-color: #f4f4f4;
                -fx-background-radius: 8;
                -fx-border-radius: 8;
                -fx-border-color: #5b91ff;
                -fx-border-width: 1.5;
                """;
    }
}