package Lab04;

import Lab03.Product;

import java.util.List;

public class SeedData {
    private SeedData() {
    }

    public static List<Product> defaultProducts() {
        return List.of(
                new Product(
                        "4DFWD PULSE SHOES",
                        "Adidas",
                        "$160.00",
                        "This product is excluded from all promotional discounts and offers.",
                        "This product is excluded fr...",
                        "/images/lab03/img1.png"
                ),
                new Product(
                        "FORUM MID SHOES",
                        "Adidas",
                        "$100.00",
                        "Classic basketball-inspired shoes with a clean white and blue design.",
                        "This product is excluded fr...",
                        "/images/lab03/img2.png"
                ),
                new Product(
                        "SUPERNOVA SHOES",
                        "Adidas",
                        "$150.00",
                        "Comfortable running shoes built for daily training and street style.",
                        "NMD City Stock 2",
                        "/images/lab03/img3.png"
                ),
                new Product(
                        "Adidas Daily Shoes",
                        "Adidas",
                        "$160.00",
                        "Lightweight sneakers designed for comfort, performance and everyday wear.",
                        "NMD City Stock 2",
                        "/images/lab03/img4.png"
                ),
                new Product(
                        "Adidas Navy Shoes",
                        "Adidas",
                        "$120.00",
                        "Dark navy Adidas sneakers with a modern sporty look.",
                        "NMD City Stock 2",
                        "/images/lab03/img5.png"
                ),
                new Product(
                        "4DFWD PULSE RED SHOES",
                        "Adidas",
                        "$160.00",
                        "Bright red performance shoes for running and training.",
                        "This product is excluded fr...",
                        "/images/lab03/img6.png"
                )
        );
    }
}