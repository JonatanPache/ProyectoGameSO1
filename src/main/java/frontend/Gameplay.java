package frontend;

import backend.Cola;
import backend.PCB;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int life = 3;
    private int level = 0;
    private boolean play = false;
    private boolean gameOver = false;
    private boolean impact = false;
    private int score = 0;
    private int totalBricks = 48;
    int status = 0;
    final static int MENU = 0;
    final static int GAME = 1;
    private Timer timer;
    private int delay = 8;
    private int gameState = MENU;
    private NavesGenerator map;
    public Cola Q; //Cola de naves,balas,dirBalas
    PCB User;
    int Easy = 10;
    int Medium = 9;
    int Hard = 8;
    private int gameDif = Easy;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.Q = new Cola();
        this.User = new PCB(
                3,
                Color.green,
                new Point(310, 550),
                70,
                8,
                10,
                System.currentTimeMillis(),
                new Point(0, 0)
        );
        timer = new Timer(delay, this);
        timer.start();
    }

    public void initJuego() {

        if (level < 4) {
            Random r = new Random();
            int low = 10;
            int high = 600;
            int result = r.nextInt(high - low) + low;
            PCB pcbNave = new PCB(
                    0,
                    new Color(20, 206, 0),
                    new Point((1 * result) + 50, 70),
                    50,
                    50,
                    10,
                    System.currentTimeMillis(),
                    new Point(1, 70)
            );
            this.Q.meter(pcbNave);
            PCB pcbBala = new PCB(
                    1,
                    new Color(20, 206, 0),
                    new Point((1 * result) + 50, 70),
                    20,
                    20,
                    10,
                    System.currentTimeMillis(),
                    new Point((1 * result) + 50, 1)
            );
            this.Q.meter(pcbBala);
            map = new NavesGenerator(this.Q);
        } else if (level >= 4 && level < 6) {
            Random r = new Random();
            int low = 10;
            int high = 600;
            int result = r.nextInt(high - low) + low;
            PCB pcbNave = new PCB(
                    0,
                    new Color(20, 206, 0),
                    new Point((1 * result) + 50, 70),
                    50,
                    50,
                    9,
                    System.currentTimeMillis(),
                    new Point(1, 70)
            );
            this.Q.meter(pcbNave);
            int result1 = r.nextInt(high - low) + low;
            PCB pcbNave1 = new PCB(
                    0,
                    new Color(249, 255, 51),
                    new Point((2 * result1) + 50, 70),
                    50,
                    50,
                    9,
                    System.currentTimeMillis(),
                    new Point(-1, 70)
            );
            this.Q.meter(pcbNave1);
            PCB pcbBala = new PCB(
                    1,
                    new Color(20, 206, 0),
                    new Point((1 * result) + 50, 70),
                    20,
                    20,
                    this.gameDif - 1,
                    System.currentTimeMillis(),
                    new Point(1, 2)
            );
            this.Q.meter(pcbBala);
            map = new NavesGenerator(this.Q);
        } else if (level >= 6) {
            PCB pcbNave = new PCB(
                    0,
                    new Color(249, 255, 51),
                    new Point((1 * 100) + 50, 70),
                    50,
                    50,
                    9,
                    System.currentTimeMillis(),
                    new Point(1, 70)
            );
            this.Q.meter(pcbNave);
            PCB pcbBala = new PCB(
                    1,
                    new Color(20, 206, 0),
                    new Point((1 * 100) + 50, 70),
                    20,
                    20,
                    10,
                    System.currentTimeMillis(),
                    new Point(1, 2)
            );
            this.Q.meter(pcbBala);
            PCB pcbNave1 = new PCB(
                    0,
                    new Color(20, 206, 0),
                    new Point((2 * 120) + 50, 70),
                    50,
                    50,
                    9,
                    System.currentTimeMillis(),
                    new Point(1, 70)
            );
            this.Q.meter(pcbNave1);
            PCB pcbNave2 = new PCB(
                    0,
                    new Color(51, 252, 255),
                    new Point((3 * 120) + 50, 70),
                    50,
                    50,
                    10,
                    System.currentTimeMillis(),
                    new Point(-1, 70)
            );
            this.Q.meter(pcbNave2);
            PCB pcbBala2 = new PCB(
                    1,
                    new Color(20, 206, 0),
                    new Point((3 * 120) + 50, 70),
                    20,
                    20,
                    10,
                    System.currentTimeMillis(),
                    new Point(1, 2)
            );
            this.Q.meter(pcbBala2);
            map = new NavesGenerator(this.Q);
        }

    }

    public void drawHeart(Graphics g, int x, int y, int width, int height) {
        int[] triangleX = {
            x - 2 * width / 18,
            x + width + 2 * width / 18,
            (x - 2 * width / 18 + x + width + 2 * width / 18) / 2};
        int[] triangleY = {
            y + height - 2 * height / 3,
            y + height - 2 * height / 3,
            y + height};
        g.fillOval(
                x - width / 12,
                y,
                width / 2 + width / 6,
                height / 2);
        g.fillOval(
                x + width / 2 - width / 12,
                y,
                width / 2 + width / 6,
                height / 2);
        g.fillPolygon(triangleX, triangleY, triangleX.length);
    }

    public void paint(Graphics g) {
        // background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        // borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        switch (gameState) {
            case GAME:
                boolean limpiar = false;
                if (impact) {
                    play = false;
                    limpiar = true;
                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("serif", Font.BOLD, 50));
                    g.drawString("WIN", 270, 190);

                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("serif", Font.BOLD, 30));
                    g.drawString("Level:" + level + "  " + "Scores:" + score, 200, 250);

                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("serif", Font.BOLD, 20));
                    g.drawString("Presiona (Enter) para seguir!", 180, 350);

                }
                if (gameOver) {
                    limpiar = true;
                    if (life == 1) {
                        play = false;
                        g.setColor(Color.RED);
                        g.setFont(new Font("serif", Font.BOLD, 30));
                        g.drawString("Game Over", 240, 250);

                        g.setColor(Color.RED);
                        g.setFont(new Font("serif", Font.BOLD, 30));
                        g.drawString("Scores: " + score, 230, 300);

                        g.setColor(Color.RED);
                        g.setFont(new Font("serif", Font.BOLD, 20));
                        g.drawString("Presiona (Enter) para comenzar de nuevo.", 180, 350);
                    } else {
                        play = false;
                        g.setColor(Color.RED);
                        g.setFont(new Font("serif", Font.BOLD, 50));
                        g.drawString("Lost", 300, 210);

                        g.setColor(Color.RED);
                        g.setFont(new Font("serif", Font.BOLD, 30));
                        g.drawString("Level:" + level + "  " + "Scores: " + score, 220, 300);

                        g.setColor(Color.RED);
                        g.setFont(new Font("serif", Font.BOLD, 20));
                        g.drawString("Presiona (Enter) para continuar.", 180, 350);
                    }
                }

                if (!limpiar && this.Q.length() > 0) {
                    int len = this.Q.length();
                    for (int i = 0; i < len; i++) {
                        PCB pcb = this.Q.sacar();
                        if (pcb.tipo == 0) { //nave -- rect
                            g.setColor(pcb.Color);
                            g.fillRect(pcb.location.x, pcb.location.y, pcb.Width, pcb.Height);
                        } else if (pcb.tipo == 1) { //balaNave -oval
                            g.setColor(pcb.Color);
                            g.fillOval(pcb.location.x, pcb.location.y, pcb.Width, pcb.Height);
                        } else if (pcb.tipo == 2) { //balaUser -- oval
                            g.setColor(pcb.Color);
                            g.fillOval(pcb.location.x, pcb.location.y, pcb.Width, pcb.Height);
                        }
                        this.Q.meter(pcb);
                    }
                }
                if (!limpiar && this.User != null) {
                    g.setColor(this.User.Color);
                    g.fillRect(this.User.location.x, this.User.location.y, this.User.Width, this.User.Height);
                }
                // the scores 		
                g.setColor(Color.white);
                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("" + score, 620, 30);
                // the level
                g.setColor(Color.white);
                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Level: " + level, 220, 30);

                // the dificulad
                g.setColor(Color.white);
                g.setFont(new Font("serif", Font.BOLD, 20));
                String mode = "";
                if (this.gameDif == Easy) {
                    mode = "Easy";
                } else if (this.gameDif == Medium) {
                    mode = "Medium";
                }
                g.drawString("Mode: " + mode, 390, 30);
                // lifes
                for (int k = 0; k < life; k++) {
                    drawHeart(g, (k * 30) + 15, 18, 25, 20);
                }
                // when you won the game

                break;
            case MENU:
                drawMenu(g);
                //this.cicloJuego();
                break;
        }

        g.dispose();
    }

    private void drawMenu(Graphics g) {
        // Title
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 40));
        g.drawString("Game SO1 ", 230, 200);

        g.setColor(Color.ORANGE);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Presiona (E=Easy, M=Medium, H=Hard) to play!", 110, 500);

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (this.User.location.x >= 600) {
                this.User.location.x = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.User.location.x < 10) {
                this.User.location.x = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            PCB balaUser = new PCB(
                    2,
                    Color.green,
                    new Point(this.User.location.x, this.User.location.y - 61),
                    20,
                    20,
                    8,
                    System.currentTimeMillis(),
                    new Point(this.User.location.x, -1)
            );
            this.Q.meter(balaUser);
            repaint();
            //disparar();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                this.User.location.x = 310;
            }
            if (this.gameState == MENU) {
                this.gameState = GAME;
                initJuego();
                repaint();
            }
            if (impact) {
                this.impact = false;
                level += 1;
                switch (level) {
                    case 4:

                }
                this.Q.clean();
                initJuego();
                repaint();
            }
            if (gameOver) {
                this.gameOver = false;
                this.life += -1;
                if (this.life == 0) {
                    this.level = 0;
                    this.score = 0;
                    this.life = 3;
                }
                this.Q.clean();
                initJuego();
                repaint();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            if (!play) {
                play = true;
                this.User.location.x = 310;
            }
            if (this.gameState == MENU) {
                this.gameState = GAME;
                initJuego();
                repaint();
                this.gameDif = Easy;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            if (!play) {
                play = true;
                this.User.location.x = 310;
            }
            if (this.gameState == MENU) {
                this.gameState = GAME;
                this.gameDif = Medium;
                initJuego();
                repaint();

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            if (!play) {
                play = true;
                this.User.location.x = 310;
            }
            if (this.gameState == MENU) {
                this.gameState = GAME;
                this.gameDif = Hard;
                initJuego();
                repaint();

            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void moveRight() {
        play = true;
        this.User.location.x += 20;
    }

    public void moveLeft() {
        play = true;
        this.User.location.x -= 20;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            int length = this.Q.length();
            boolean noMeter = false;
            //impact = true;
            for (int i = 0; i < length; i++) {

                PCB pcbCurrent = this.Q.sacar();
                noMeter = false;
                int velo = 1;
                if (this.gameDif == Medium) {
                    velo = 2;
                } else if (this.gameDif == Hard) {
                    velo = 3;
                }
                if (pcbCurrent.tipo == 0) {  //nave

                    //impact = false;
                    if (pcbCurrent.location.x + 60 >= 692) {
                        pcbCurrent.dir.x = -velo;
                    }
                    if (pcbCurrent.location.x <= 0) {
                        pcbCurrent.dir.x = +velo;
                    }
                    pcbCurrent.location.x += pcbCurrent.dir.x;
                    if (pcbCurrent.hora + pcbCurrent.retardo < System.currentTimeMillis()) {

                        if (level < 4) {
                            PCB bala = new PCB(
                                    1,
                                    Color.green,
                                    new Point(pcbCurrent.location.x, pcbCurrent.location.y),
                                    20,
                                    20,
                                    8,
                                    System.currentTimeMillis(),
                                    new Point(1, velo)
                            );
                            this.Q.meter(bala);
                            repaint();
                        } else if (level >= 4 && level < 6) {
                            PCB bala;
                            if (pcbCurrent.hora + pcbCurrent.retardo % 2 == 0) {
                                bala = new PCB(
                                        1,
                                        Color.green,
                                        new Point(pcbCurrent.location.x, pcbCurrent.location.y),
                                        20,
                                        20,
                                        8,
                                        System.currentTimeMillis(),
                                        new Point(-1, velo)
                                );
                            } else {
                                bala = new PCB(
                                        1,
                                        Color.green,
                                        new Point(pcbCurrent.location.x, pcbCurrent.location.y),
                                        20,
                                        20,
                                        8,
                                        System.currentTimeMillis(),
                                        new Point(1, velo)
                                );
                            }

                            this.Q.meter(bala);
                            repaint();
                        } else if (level >= 6) {
                            PCB bala;
                            if (pcbCurrent.hora + pcbCurrent.retardo % 2 == 0) {
                                bala = new PCB(
                                        1,
                                        Color.green,
                                        new Point(pcbCurrent.location.x, pcbCurrent.location.y),
                                        20,
                                        20,
                                        8,
                                        System.currentTimeMillis(),
                                        new Point(-2, velo)
                                );
                            } else {
                                bala = new PCB(
                                        1,
                                        Color.green,
                                        new Point(pcbCurrent.location.x, pcbCurrent.location.y),
                                        20,
                                        20,
                                        8,
                                        System.currentTimeMillis(),
                                        new Point(2, velo)
                                );
                            }

                            this.Q.meter(bala);
                            repaint();
                        }
                    }
                    pcbCurrent.hora = System.currentTimeMillis();
                } else if (pcbCurrent.tipo == 1) { //balaNave

                    if (new Rectangle(pcbCurrent.location.x, pcbCurrent.location.y, pcbCurrent.Width, pcbCurrent.Height)
                            .intersects(new Rectangle(this.User.location.x, this.User.location.y, this.User.Width, this.User.Height))) {
                        gameOver = true; // si la balaNave impacta con mi nave, gameover
                        repaint();
                    } else if (pcbCurrent.location.y + pcbCurrent.dir.y >= 600) {
                        i++; //si ya salio de la pantalla, no meter en la cola, prara guardar espacio
                        noMeter = true;
                    } else {
                        if (pcbCurrent.location.x + 60 >= 692) {
                            pcbCurrent.dir.x = -velo;
                        }
                        if (pcbCurrent.location.x == 0) {
                            pcbCurrent.dir.x = +velo;
                        }
                        pcbCurrent.location.y += pcbCurrent.dir.y;  // si aun esta dentro de la ventana
                        if (level >= 4 && level < 6) {
                            pcbCurrent.location.x += pcbCurrent.dir.x;
                        } else if (level >= 6 && (Math.abs(pcbCurrent.location.y - 300) > 0 || Math.abs(pcbCurrent.location.y - 300) < 20)) {
                            if (this.User.location.x > pcbCurrent.location.x) {
                                pcbCurrent.dir.x = +velo+1;
                                pcbCurrent.location.x += pcbCurrent.dir.x;
                            } else {
                                pcbCurrent.dir.x = -2;
                                pcbCurrent.location.x += pcbCurrent.dir.x;
                            }
                        }
                        int len = Q.length();
                        boolean desaparecer = false;
                        for (int j = 0; j < len; j++) {
                            PCB turn = Q.sacar();
                            if (turn.tipo == 2) { // balaUser --solo este tipo, para verificar si se impactan las dos balas, desaperecen
                                if (new Rectangle(pcbCurrent.location.x, pcbCurrent.location.y, pcbCurrent.Width, pcbCurrent.Height)
                                        .intersects(new Rectangle(turn.location.x, turn.location.y, turn.Width, turn.Height))) {
                                    score += 2; //si impacta, acumula dos puntos
                                    desaparecer = true; // desaparece esta balaUser
                                    noMeter = true; //desaparece la balaNave
                                    j++;
                                    i++;
                                }
                            }
                            if (!desaparecer) {
                                Q.meter(turn);
                            }
                            repaint();
                        }
                    }
                } else if (pcbCurrent.tipo == 2) { //balaUser

                    pcbCurrent.location.y += pcbCurrent.dir.y;
                    if (pcbCurrent.location.y < -10) {
                        noMeter = true;
                        i++;
                    } else {
                        int len = Q.length();
                        boolean desaparecer = false;
                        for (int j = 0; j < len; j++) {
                            PCB turn = Q.sacar();
                            if (turn.tipo == 0) { // nave
                                if (new Rectangle(pcbCurrent.location.x, pcbCurrent.location.y, pcbCurrent.Width, pcbCurrent.Height)
                                        .intersects(new Rectangle(turn.location.x, turn.location.y, turn.Width, turn.Height))) {
                                    impact = true;
                                    score += 5;
                                }

                            } else if (turn.tipo == 1) { //balaNave
                                if (new Rectangle(pcbCurrent.location.x, pcbCurrent.location.y, pcbCurrent.Width, pcbCurrent.Height)
                                        .intersects(new Rectangle(turn.location.x, turn.location.y, turn.Width, turn.Height))) {
                                    score += 2;
                                    desaparecer = true;
                                    noMeter = true;
                                    j++;
                                    i++;
                                }
                            }

                            if (!desaparecer) {
                                Q.meter(turn);
                            }
                            repaint();
                        }
                    }
                }
                if (!noMeter) {
                    this.Q.meter(pcbCurrent);
                }
                repaint();
            }

            //repaint();
        }
    }
}
