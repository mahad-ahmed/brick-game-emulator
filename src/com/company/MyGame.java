package com.company;

import java.nio.file.Path;

public class MyGame implements Game {

    public MyGame() {
        String mypath="Alphabets\\";
        objects.add(new GObject(new java.io.File(mypath+"H.txt"), 0, 1));
        objects.add(new GObject(new java.io.File(mypath+"E.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
        objects.add(new GObject(new java.io.File(mypath+"L.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
        objects.add(new GObject(new java.io.File(mypath+"L.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
        objects.add(new GObject(new java.io.File(mypath+"O.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
        objects.add(new GObject(new java.io.File(mypath+"W.txt"), 0, objects.get(objects.size()-1).getY()+objects.get(objects.size()-1).getHeight()+2));
        objects.add(new GObject(new java.io.File(mypath+"O.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
        objects.add(new GObject(new java.io.File(mypath+"R.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
        objects.add(new GObject(new java.io.File(mypath+"L.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
        objects.add(new GObject(new java.io.File(mypath+"D.txt"), objects.get(objects.size()-1).getX()+objects.get(objects.size()-1).getWidth()+1, objects.get(objects.size()-1).getY()));
    }

    @Override
    public void render() {
        for(int i=0;i<objects.size();i++) {
            objects.get(i).render();
        }
    }

    @Override
    public void start() {
        //
    }

    @Override
    public void DpadRight() {
        objects.get(objects.size()-1).setX(objects.get(objects.size()-1).getX()+1);
    }

    @Override
    public void DpadLeft() {
        objects.get(objects.size()-1).setX(objects.get(objects.size()-1).getX()-1);
    }

    @Override
    public void DpadUp() {
        objects.get(objects.size()-1).setY(objects.get(objects.size()-1).getY()-1);
    }

    @Override
    public void DpadDown() {
        objects.get(objects.size()-1).setY(objects.get(objects.size()-1).getY()+1);
    }

    @Override
    public void h() {
//        if(!objects.get(objects.size()-2).getHidden()) {
//            objects.get(objects.size()-2).setHidden(true);
//        }
//        else {
//            objects.get(objects.size()-2).setHidden(false);
//        }
        objects.get(objects.size()-5).rotateRight();
    }
}