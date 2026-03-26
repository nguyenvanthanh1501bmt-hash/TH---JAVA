package Lab02_Flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class GameBackground extends JPanel implements ActionListener, KeyListener, MouseListener {
    Image backgroundImage;
    Image topPipeImg, bottomPipeImg;
    Timer gameloop, pipeloop;
    Bird bird;
    boolean start = false;
    boolean gameover = false;
    double score = 0;
    ArrayList<Pipe> pipes;

    public GameBackground() {
        // Load ảnh
        this.backgroundImage = new ImageIcon("src/images/lab02/flappybirdbg.png").getImage();
        this.topPipeImg = new ImageIcon("src/images/lab02/toppipe.png").getImage();
        this.bottomPipeImg = new ImageIcon("src/images/lab02/bottompipe.png").getImage();

        this.bird = new Bird();
        this.pipes = new ArrayList<>();

        // Vòng lặp chính (60 FPS)
        gameloop = new Timer(1000 / 60, this);
        gameloop.start();

        // Tạo ống (1.5 giây một cặp)
        pipeloop = new Timer(1500, e -> placePipes());
        pipeloop.start();

        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    void placePipes() {
        int gap = 160; // Khoảng trống giữa 2 ống trên và dưới
        int randomPipeY = (int) (0 - 512/4 - Math.random() * (512/2));

        // Thêm ống trên
        pipes.add(new Pipe(topPipeImg, 360, randomPipeY));
        // Thêm ống dưới
        pipes.add(new Pipe(bottomPipeImg, 360, randomPipeY + 512 + gap));
    }

    boolean checkCollision(Pipe pipe) {
        //check chạm ống
        return bird.x < pipe.x + pipe.width &&   // Cạnh trái chim < Cạnh phải ống
                bird.x + bird.width > pipe.x &&   // Cạnh phải chim > Cạnh trái ống
                bird.y < pipe.y + pipe.height &&  // Cạnh trên chim < Cạnh dưới ống
                bird.y + bird.heigh > pipe.y;    // Cạnh dưới chim > Cạnh trên ống
    }

    void collision(){
        for(Pipe pipe : this.pipes){
            if(checkCollision(pipe)){
                gameOver();
            }

            if(bird.x > pipe.x && !pipe.isPassed){
                score = score + 0.5;
                pipe.isPassed = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        collision();
        if(this.start) {
            bird.dropdown();

            // Di chuyển và xử lý ống
            for (Pipe pipe: pipes) {
                pipe.x -= 3; // Tốc độ ống chạy sang trái
            }

            // Xóa ống cũ (ống chạy ra khỏi màn hình)
            pipes.removeIf(p -> p.x + p.width < 0);

            // Kiểm tra thua cuộc (Chạm đất/trần)
            if (bird.y >= 640 - bird.heigh || bird.y <= 0) {
                gameOver();
                this.start = false;
            }
            repaint();
        }
    }

    void gameOver() {
        gameover = true;
        gameloop.stop();
        pipeloop.stop();
        System.out.println("Game Over!");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 1. Luôn vẽ hình nền trước
        g.drawImage(backgroundImage, 0, 0, 360, 640, null);

        // 2. Vẽ chim và ống
        bird.paintComponent(g);
        for (Pipe p : pipes) {
            g.drawImage(p.img, p.x, p.y, p.width, p.height, null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        g.drawString(String.valueOf(this.score), 165, 60);

        // 3. Vẽ các lớp thông báo đè lên trên
        if (!start && !gameover) {
            // Màn hình chờ bắt đầu
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("PRESS SPACE TO START", 10, 300);
        }

        if (gameover) {
            // Màn hình báo thua
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 60, 300);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(Color.WHITE);
            g.drawString("Press Space to Restart", 75, 340);
        }
    }

    //Nhấn cách
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.jump();
        }

        if(this.gameover){
            //restart game
            bird.y = 300;
            bird.gravity = 0.5;
            bird.velocity = 0;

            this.start = false;
            gameloop.start();

            pipes.clear();
            pipeloop.start();
            gameover = false;

            score = 0;
        }
        this.start = true;
    }

    //Nhấp chuột
    @Override
    public void mousePressed(MouseEvent e) {
        bird.jump();
        this.start = true;
    }

    // Override interface
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}