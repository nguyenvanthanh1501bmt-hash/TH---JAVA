# Lab 04 - Kết nối MongoDB cho Lab 03

## Mô tả

Bài thực hành 04 xây dựng cơ sở dữ liệu MongoDB để lưu thông tin sản phẩm và truy vấn sản phẩm từ cơ sở dữ liệu.

Giao diện hiển thị sản phẩm vẫn sử dụng lại từ **Lab 03**.  
Package **Lab04** chịu trách nhiệm kết nối MongoDB, seed dữ liệu mẫu và truy vấn danh sách sản phẩm.

## Công nghệ sử dụng

- Java
- JavaFX
- Maven
- MongoDB
- MongoDB Java Driver

## Cấu trúc chính

```text
src/
├── Lab03/
│   ├── Lab03.java
│   ├── Product.java
│   ├── ProductData.java
│   └── ...
└── Lab04/
    ├── MongoConnection.java
    ├── ProductDAO.java
    └── SeedData.java