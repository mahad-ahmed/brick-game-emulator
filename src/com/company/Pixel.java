package com.company;
import java.awt.*;
import java.util.ArrayList;

public class Pixel {
    private Color color;
    Color OnColor;
    Color OffColor;
    boolean ON;
    ArrayList<String> ObjectIdList;
    //boolean blink;
    int w;
    int x;
    int y;

    public Pixel(int W, int X, int Y, Color OnC, Color OffC) {
        ObjectIdList =new ArrayList<>();
        OnColor =OnC;
        OffColor=OffC;
        color=OffC;
        w=W;
        x=X;
        y=Y;
    }

    public void on(int id) {
        if(!ObjectIdList.contains(id+"")) {
            ObjectIdList.add(id+"");
        }
        color=OnColor;
        ON=true;
    }

    public void off(int id) {
        if(ObjectIdList.contains(id+"")) {
            ObjectIdList.remove(id+"");
            color=OffColor;
            ON=false;
        }
    }

    public void drawAtOrigin(Graphics g) {
        this.draw(g, 0, 0);
    }

    public void draw(Graphics g, int X, int Y) {
        this.draw(g, X, Y, 3);
    }

    public void draw(Graphics g, int X, int Y, int gp) {
        g.setColor(color);
        g.drawRect(X, Y, w, w);
        g.fillRect(X+gp, Y+gp, w-(int)(gp*1.75), w-(int)(gp*1.75));
    }

    public void draw(Graphics g) {
        this.draw(g, x, y);
    }
}
