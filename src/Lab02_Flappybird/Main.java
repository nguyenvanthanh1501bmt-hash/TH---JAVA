package Lab02_Flappybird;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo cửa sổ
        JFrame mainWindow = new JFrame("Flappy Bird");

        // 2. Thiết lập kích thước 360x640
        mainWindow.setSize(360, 640);

        // 3. Không cho phép thay đổi kích thước (Yêu cầu Bài 1)
        mainWindow.setResizable(false);
        // 4. Nút tắt và canh giữa
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);

        // 5. Khởi tạo game panel (Vùng chứa hình nền)
        GameBackground gameBackground = new GameBackground();
        mainWindow.add(gameBackground);

        // 7. Hiển thị màn hình
        mainWindow.setVisible(true);
    }
}