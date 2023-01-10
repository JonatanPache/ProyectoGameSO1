/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author jonatanpache
 */
public class CanonGenerator {

    public int map[];
    public int brickWidth;
    public int brickHeight;

    public CanonGenerator(int col) {
        map = new int[col];
        for (int i = 0; i < map.length; i++) {
            map[i] = 1;
        }
        brickWidth = 540 / col;
        brickHeight = 150/2;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                g.setColor(Color.CYAN);
                g.fillRect( 250,50, brickWidth, brickHeight);

                // this is just to show separate brick, game can still run without it
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
                g.drawRect(250, 50, brickWidth, brickHeight);
            }

        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[col] = value;
    }
}
