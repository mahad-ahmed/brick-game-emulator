package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Brick extends JFrame {
    public static final int CW = 255;
    public static final int CH = 360;//sw=90
    public static Canvas canvas;
    public static Game game;
    public static Screen scr=new Screen(CW, CH, 11, 2, Color.BLACK, Color.DARK_GRAY.brighter());

    public Brick(Game GAME) {
        canvas = new Canvas();
        game=GAME;
        canvas.setPreferredSize(new Dimension(CW, CH));
        Container cp = getContentPane();
        cp.add(canvas, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Brick");
        this.setVisible(true);
        requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        game.DpadRight();
                        break;
                    case KeyEvent.VK_LEFT:
                        game.DpadLeft();
                        break;
                    case KeyEvent.VK_UP:
                        game.DpadUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        game.DpadDown();
                        break;
                    case KeyEvent.VK_H:
                        game.h();
                        break;
                }
            }
        });
    }

    class Canvas extends JPanel {

        public Canvas() {
            setBackground(new Color(119, 160, 144).darker());
            //setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
//            scr.clearScreen();
            scr.render(g, 5, 4);
//            try {
//                Thread.sleep(1);
//            }
//            catch(Exception myUselessException) { }
//            repaint();
//            scr.pixels[16][10].color=Color.RED;
        }
    }
}