import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalbricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map = new MapGenerator(3, 7);

    public GamePlay() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.Timer = new Timer(this.delay, this);
        this.Timer.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        this.map.draw((Graphics2D)g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        g.setColor(Color.white);
        g.setFont(new Font("serif", 1, 25));
        g.drawString("" + this.score, 590, 30);
        g.setColor(Color.yellow);
        g.fillRect(this.playerX, 550, 100, 8);
        g.setColor(Color.GREEN);
        g.fillOval(this.ballposX, this.ballposY, 20, 20);
        if (this.ballposY > 570) {
            this.play = false;
            this.ballXdir = 0;
            this.ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", 1, 30));
            g.drawString("    Game Over Score: " + this.score, 190, 300);
            g.setFont(new Font("serif", 1, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }

        if (this.totalbricks == 0) {
            this.play = false;
            this.ballYdir = -2;
            this.ballXdir = -1;
            g.setColor(Color.red);
            g.setFont(new Font("serif", 1, 30));
            g.drawString("    Game Over: " + this.score, 190, 300);
            g.setFont(new Font("serif", 1, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        this.Timer.start();
        if (this.play) {
            if ((new Rectangle(this.ballposX, this.ballposY, 20, 20)).intersects(new Rectangle(this.playerX, 550, 100, 8))) {
                this.ballYdir = -this.ballYdir;
            }

            label48:
            for(int i = 0; i < this.map.map.length; ++i) {
                for(int j = 0; j < this.map.map[0].length; ++j) {
                    if (this.map.map[i][j] > 0) {
                        int brickX = j * this.map.bricksWidth + 80;
                        int brickY = i * this.map.bricksHeight + 50;
                        int bricksWidth = this.map.bricksWidth;
                        int bricksHeight = this.map.bricksHeight;
                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(this.ballposX, this.ballposY, 20, 20);
                        if (ballrect.intersects(rect)) {
                            this.map.setBricksValue(0, i, j);
                            --this.totalbricks;
                            this.score += 5;
                            if (this.ballposX + 19 > rect.x && this.ballposX + 1 < rect.x + bricksWidth) {
                                this.ballYdir = -this.ballYdir;
                                break label48;
                            }

                            this.ballXdir = -this.ballXdir;
                            break label48;
                        }
                    }
                }
            }

            this.ballposX += this.ballXdir;
            this.ballposY += this.ballYdir;
            if (this.ballposX < 0) {
                this.ballXdir = -this.ballXdir;
            }

            if (this.ballposY < 0) {
                this.ballYdir = -this.ballYdir;
            }

            if (this.ballposX > 670) {
                this.ballXdir = -this.ballXdir;
            }
        }

        this.repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            if (this.playerX >= 600) {
                this.playerX = 600;
            } else {
                this.moveRight();
            }
        }

        if (e.getKeyCode() == 37) {
            if (this.playerX < 10) {
                this.playerX = 10;
            } else {
                this.moveLeft();
            }
        }

        if (e.getKeyCode() == 10 && !this.play) {
            this.ballposX = 120;
            this.ballposY = 350;
            this.ballXdir = -1;
            this.ballYdir = -2;
            this.score = 0;
            this.playerX = 310;
            this.totalbricks = 21;
            this.map = new MapGenerator(3, 7);
            this.repaint();
        }

    }

    public void moveRight() {
        this.play = true;
        this.playerX += 20;
    }

    public void moveLeft() {
        this.play = true;
        this.playerX -= 20;
    }
}

