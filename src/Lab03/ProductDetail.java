package Lab03;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class ProductDetail extends VBox {

    private ImageView imageView;
    private Label nameLabel;
    private Label priceLabel;
    private Label brandLabel;
    private Label descriptionLabel;

    public ProductDetail(Product product) {
        setPrefWidth(280);
        setSpacing(12);
        setAlignment(Pos.TOP_LEFT);

        imageView = new ImageView(loadImage(product.getImagePath()));
        imageView.setFitWidth(260);
        imageView.setFitHeight(210);
        imageView.setPreserveRatio(true);

        Region line = new Region();
        line.setPrefHeight(1);
        line.setStyle("-fx-background-color: #bdbdbd;");

        nameLabel = new Label(product.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 21));
        nameLabel.setTextFill(Color.web("#4d4d4d"));

        priceLabel = new Label(product.getPrice());
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        priceLabel.setTextFill(Color.web("#4d4d4d"));

        brandLabel = new Label(product.getBrand());
        brandLabel.setFont(Font.font("Arial", 13));
        brandLabel.setTextFill(Color.web("#555555"));

        descriptionLabel = new Label(product.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        descriptionLabel.setTextFill(Color.web("#9b9b9b"));

        getChildren().addAll(
                imageView,
                line,
                nameLabel,
                priceLabel,
                brandLabel,
                descriptionLabel
        );
    }

    public void changeProduct(Product product) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(160), this);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fadeOut.setOnFinished(e -> {
            imageView.setImage(loadImage(product.getImagePath()));
            nameLabel.setText(product.getName());
            priceLabel.setText(product.getPrice());
            brandLabel.setText(product.getBrand());
            descriptionLabel.setText(product.getDescription());

            setScaleX(0.95);
            setScaleY(0.95);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(220), this);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            ScaleTransition scale = new ScaleTransition(Duration.millis(220), this);
            scale.setFromX(0.95);
            scale.setFromY(0.95);
            scale.setToX(1);
            scale.setToY(1);

            fadeIn.play();
            scale.play();
        });

        fadeOut.play();
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResourceAsStream(path));
    }
}