/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import static backend.Tipo.Nave;
import java.awt.Color;
import java.awt.*;

/**
 *
 * @author jonatanpache implementation de Cola Circular
 */
public class Cola {
    private int max = 200;
    private int F, A;
    private PCB array[];

    public Cola() {
        this.init();
    }

    public void init() {
        this.array = new PCB[max];
        this.A = -1;
    }

    public int length() {
        if (this.A == -1) {
            return 0;
        } else {
            if (this.F <= this.A) {
                return (this.A - this.F) + 1;
            } else {
                return this.A + (this.max - this.F + 1);
            }
        }
    }

    public void meter(PCB P) {
        if (this.length() == this.max) {
            throw new UnsupportedOperationException("Cola llena");
        }
        if (this.A == -1) {//todavia no hay pcb en la cola
            this.A = 0;
            this.F = 0;
            this.array[this.A] = P;
        } else {
            this.A = ((this.A % this.max) + 1 == 200) ? 0 : (this.A % this.max) + 1;
            this.array[this.A] = P;
        }
        
    }

    public PCB sacar() {
        if (this.length() == 0) {
            throw new UnsupportedOperationException("Cola vacia");
        }
        if (this.F == this.A) {
            PCB p= this.array[this.F];
            this.init();
            return p;
        } else {
            int index = this.F % this.max;
            this.F = ( this.F+1 == this.max) ? 0 : this.F+1;
            return this.array[index];
        }
    }
    
    public void clean(){
        this.init();
    }
    /*public static void main(String[] args) {
        PCB pcbCurrent = new PCB(
                0,
                new Color(20, 206, 0),
                new Point((1 * 250) + 50, 70),
                50,
                50
        );
        PCB pcbCurrent1 = new PCB(
                0,
                new Color(20, 206, 0),
                new Point((1 * 250) + 50, 70),
                50,
                50
        );
        PCB pcbCurrent2 = new PCB(
                0,
                new Color(20, 206, 0),
                new Point((1 * 250) + 50, 70),
                50,
                50
        );
        Cola cola = new Cola();
        cola.meter(pcbCurrent);
        cola.meter(pcbCurrent);
        cola.meter(pcbCurrent);
        cola.meter(pcbCurrent);
        cola.meter(pcbCurrent1);
        cola.meter(pcbCurrent2);
        System.out.println(cola.length());
        cola.sacar();
        cola.sacar();
        cola.sacar();
        PCB p1=cola.sacar();
        System.out.println(p1.location);
        cola.meter(p1);
        System.out.println(cola.length());
        cola.meter(pcbCurrent1);
        cola.meter(pcbCurrent2);
        System.out.println(cola.length());
        
        
        
        

    }*/
}
