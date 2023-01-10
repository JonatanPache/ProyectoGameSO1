/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.Cola;
import backend.PCB;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author jonatanpache
 */
public class NavesGenerator {

    public int brickWidth;
    public int brickHeight;
    public boolean impact = false;
    public Cola Q;

    public NavesGenerator(Cola Q) {
        this.Q = Q;
        brickWidth = 60;
        brickHeight = 60;
    }

    public void draw(Graphics2D g) {
        int length = this.Q.length();
        for (int i = 0; i < length; i++) {
            PCB pcbCurrent = this.Q.sacar();
            System.out.println(pcbCurrent.getLocation());
            if (pcbCurrent.tipo == 0) {  //nave
                g.setColor(Color.CYAN);
                g.fillRect(pcbCurrent.location.x, pcbCurrent.location.y, brickWidth, brickHeight);

                // this is just to show separate brick, game can still run without it
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
                g.drawRect(pcbCurrent.location.x, pcbCurrent.location.y, brickWidth, brickHeight);
            } else if (pcbCurrent.tipo == 1) { //balaNave
                g.setColor(pcbCurrent.Color);
                g.fillOval(pcbCurrent.location.x+(brickWidth/2)-1, pcbCurrent.location.y+brickHeight, pcbCurrent.Width, pcbCurrent.Height);
            }else if (pcbCurrent.tipo == 2) { //bala
                g.setColor(pcbCurrent.Color);
                g.fillOval(pcbCurrent.location.x+(brickWidth/2)-1, pcbCurrent.location.y+brickHeight, 20, 20);
            }
            this.Q.meter(pcbCurrent);
        }
    }

    public void setBrickValue(int value, int row, int col) {
        //naves[col] = value;
    }
}
