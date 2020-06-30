package com.company;

public class Tetris implements Game {
    String mypath;
    GObject myobj;
    GObject map;
    int mapHeight;

    public Tetris() {
        mypath="TetrisShapes\\";
        //objects.add(new GObject(new java.io.File(mypath+"A.txt"), 10, 10));
        objects.add(new GObject(new java.io.File(mypath+(char) ((new java.util.Random().nextInt(6)) + 65) + ".txt")));
        objects.get(objects.size()-1).setHidden(true);
        objects.add(new GObject(new java.io.File(mypath+(char) ((new java.util.Random().nextInt(6)) + 65) + ".txt")));
        myobj=objects.get(objects.size()-1);
    }

    @Override
    public void start() {
        new MyClass().run();
    }

    @Override
    public void render() {
        for(int i=0;i<objects.size();i++) {
            objects.get(i).render();
        }
    }

    @Override
    public void DpadRight() {
        if(Brick.game!=null && !Game.objects.get(Game.objects.size()-1).touchingLowerEdge()) {
            myobj.setX(myobj.getX() + 1);
        }
    }

    @Override
    public void DpadLeft() {
        if(Brick.game!=null && !Game.objects.get(Game.objects.size()-1).touchingLowerEdge()) {
            myobj.setX(myobj.getX() - 1);
        }
    }

    @Override
    public void DpadUp() {
        myobj.rotateRight();
    }

    @Override
    public void DpadDown() {
//        objects.get(objects.size()-1).setY(objects.get(objects.size()-1).getY()+1);
        if(!myobj.willCollide(0, 1)) {
            myobj.setY(myobj.getY()+1);
        }
        if(myobj.collided()||myobj.touchingLowerEdge()) {
            
            objects.add(new GObject(new java.io.File(mypath + (char) ((new java.util.Random().nextInt(6)) + 65) + ".txt")));
            myobj.render();
            myobj=objects.get(objects.size()-1);
        }
        myobj.render();
//        System.out.println(Brick.scr.pixels[myobj.getX()+1][myobj.getY()+1].ObjectIdList.size());
    }

    @Override
    public void h() {
        objects.add(new GObject(new java.io.File(mypath + (char) ((new java.util.Random().nextInt(6)) + 65) + ".txt"), myobj.getX(), myobj.getY()));
        objects.get(objects.size()-2).setHidden(true);
        objects.remove(objects.size()-2);
        myobj=objects.get(objects.size()-1);
    }

    class MyClass implements Runnable {
        @Override
        public void run() {
            Brick.game.h();
            while(true) {
                if(Brick.game!=null) {
                    DpadDown();
                }
                try {
                    Thread.sleep(1000);
                }
                catch(Exception rarePokemon) {  }
            }
        }
    }
}