/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author jonatanpache
 */
public class PCB {

    public int PID;
    public int tipo;
    public Color Color;
    public Point location;
    public Point dir;
    public int Width, Height;
    public long hora, retardo;

    @Override
    public String toString() {
        return "PCB{" + "tipo=" + tipo + ", Color=" + Color + ", location=" + location + ", Width=" + Width + ", Height=" + Height + '}';
    }

    public PCB(int tipo, Color Color, Point location, int Width, int Height, long retardo, long hora, Point dir) {
        this.tipo = tipo;
        this.Color = Color;
        this.location = location;
        this.Width = Width;
        this.Height = Height;
        this.retardo=retardo;
        this.hora=hora;
        this.dir=dir;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Color getColor() {
        return Color;
    }

    public void setColor(Color Color) {
        this.Color = Color;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getDir() {
        return dir;
    }

    public void setDir(Point dir) {
        this.dir = dir;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public long getHora() {
        return hora;
    }

    public void setHora(long hora) {
        this.hora = hora;
    }

    public long getRetardo() {
        return retardo;
    }

    public void setRetardo(long retardo) {
        this.retardo = retardo;
    }
    
    
}

enum Tipo {
    Nave,
    Balau,
    Balan
}
