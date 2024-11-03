package com.cb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class CasseBriques extends JPanel implements ActionListener, KeyListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // Balle
    private int ballX = WIDTH / 2, ballY = HEIGHT - 100;
    private int ballDirX = 2, ballDirY = -3;
    private int ballSize = 20;

    // Raquette
    private int playerX = WIDTH / 2 - 50;
    private int playerWidth = 100, playerHeight = 15;

    // Grille de briques
    private int rows = 5, columns = 10;
    private int brickWidth = 70, brickHeight = 20;
    private int[][] bricks; // Stocke les "dommages" pour chaque brique

    // Variables de jeu
    private int lives = 3;
    private int score = 0;
    private boolean gameOver = false;

    // Timer pour la boucle de jeu
    private Timer timer;

    public CasseBriques() {
        // Initialisation des briques avec dommages multiples
        bricks = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                bricks[i][j] = i < 2 ? 3 : (i < 4 ? 2 : 1); // Les premières lignes nécessitent plus de coups
            }
        }

        // Initialisation du timer et des écouteurs
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fond de l'écran
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Dessiner les briques
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (bricks[i][j] > 0) {
                    int brickX = j * brickWidth + 60;
                    int brickY = i * brickHeight + 50;
                    switch (bricks[i][j]) {
                        case 3 -> g.setColor(Color.RED); // Briques nécessitant 3 coups
                        case 2 -> g.setColor(Color.ORANGE); // Briques nécessitant 2 coups
                        case 1 -> g.setColor(Color.YELLOW); // Briques nécessitant 1 coup
                    }
                    g.fillRect(brickX, brickY, brickWidth, brickHeight);
                }
            }
        }

        // Dessiner la raquette
        g.setColor(Color.BLUE);
        g.fillRect(playerX, HEIGHT - 50, playerWidth, playerHeight);

        // Dessiner la balle
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ballSize, ballSize);

        // Afficher les vies et le score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Vies : " + lives, 10, 20);
        g.drawString("Score : " + score, WIDTH - 100, 20);

        // Afficher Game Over si le jeu est terminé
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over", WIDTH / 2 - 100, HEIGHT / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        // Mouvement de la balle
        ballX += ballDirX;
        ballY += ballDirY;

        // Rebonds contre les murs
        if (ballX < 0 || ballX > WIDTH - ballSize) {
            ballDirX *= -1;
        }
        if (ballY < 0) {
            ballDirY *= -1;
        }
        if (ballY > HEIGHT) {
            lives--;
            if (lives <= 0) {
                gameOver = true;
            } else {
                resetBall(); // Réinitialiser la balle si une vie est perdue
            }
        }

        // Collision avec la raquette
        if (new Rectangle(ballX, ballY, ballSize, ballSize).intersects(new Rectangle(playerX, HEIGHT - 50, playerWidth, playerHeight))) {
            ballDirY *= -1;
        }

        // Collision avec les briques
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (bricks[i][j] > 0) {
                    int brickX = j * brickWidth + 60;
                    int brickY = i * brickHeight + 50;
                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballX, ballY, ballSize, ballSize);

                    if (ballRect.intersects(brickRect)) {
                        bricks[i][j]--; // Réduire les "dommages" de la brique
                        score += 10; // Ajouter des points au score
                        ballDirY *= -1;
                    }
                }
            }
        }

        // Repeindre le panneau
        repaint();
    }

    private void resetBall() {
        ballX = WIDTH / 2;
        ballY = HEIGHT - 100;
        ballDirY = -3;
        // Donner un angle aléatoire à la balle
        ballDirX = new Random().nextInt(5) - 2; // Valeur aléatoire entre -2 et 2
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX < WIDTH - playerWidth) {
                playerX += 20;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX > 0) {
                playerX -= 20;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CasseBriques game = new CasseBriques();
        frame.setTitle("Jeu Casse-Briques");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
    }
}
