package com.company;

import java.awt.*;

public class Screen {
//    int ScreenWidth;
//    int ScreenHeight;
    public Pixel pixels[][];

    public Screen(int sw, int sh, int w, int gap, Color OnColor, Color OffColor) {
//        ScreenWidth=sw;
//        ScreenHeight=sh;
        pixels=new Pixel[sw/(w+gap)][sh/(w+gap)];
        System.out.println(pixels.length+" "+pixels[0].length);
        int x=0, y=0;
        for(int i=0; i<pixels[0].length;i++) {
            for(int j=0;j<pixels.length;j++) {
                pixels[j][i]=new Pixel(w, x, y, OnColor, OffColor);
                x=x+w+gap;
            }
            x=0;
            y=y+w+gap;
        }
    }

    public void render(Graphics g) {
        this.render(g, 0, 0);
    }

    public void render(Graphics g, int sx, int sy) {
        g.translate(sx, sy);
        for(int i=0; i<pixels[0].length;i++) {
            for(int j=0;j<pixels.length;j++) {
                pixels[j][i].draw(g);           //j=x
            }
        }
        g.translate(-sx, -sy);
    }

    public void clearScreen() {
//        for(int i=0; i<pixels[0].length;i++) {
//            for(int j=0;j<pixels.length;j++) {
//                pixels[j][i].off();
//            }
//        }

        for(int i=0; i< Game.objects.size();i++) {
            Game.objects.get(i).remove();
        }
    }
}