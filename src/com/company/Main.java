package com.company;

public class Main {

    public static void main(String[] args) {
        Brick myframe=new Brick(new Tetris());
        myframe.setVisible(true);
        Brick.game.render();
        Brick.game.start();
    }
}
