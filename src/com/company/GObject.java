package com.company;
import java.io.*;
import java.util.ArrayList;

public class GObject {
    public static int count;
    public int id;
    private int shape[][];
    public String name;
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean hidden;

    public GObject(int SHAPE[][]) {
        shape=SHAPE;
        width=SHAPE.length;
        height=SHAPE[0].length;
        id=count;
        count+=1;
    }

    public GObject(int SHAPE[][], String ID) {
        this(SHAPE);
        name =ID;
    }

    public GObject(int SHAPE[][], int X, int Y) {
        this(SHAPE);
        setX(X);
        setY(Y);
    }

    public GObject(int SHAPE[][], int X, int Y, String ID) {
        this(SHAPE, X, Y);
        name =ID;
    }

    public GObject(File SHAPEFILE) {
        shape=getShapeFromFile(SHAPEFILE);
        width=shape.length;
        height=shape[0].length;
        id=count;
        count+=1;
    }

    public GObject(File SHAPEFILE, String ID) {
        this(SHAPEFILE);
        name =ID;
    }

    public GObject(File SHAPEFILE, int X, int Y) {
        this(SHAPEFILE);
        setX(X);
        setY(Y);
    }

    public GObject(File SHAPEFILE, int X, int Y, String ID) {
        this(SHAPEFILE, X, Y);
        name =ID;
    }

    private static int[][] getShapeFromFile(java.io.File file) {
        int[][] anArray=new int[1][1];

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            ArrayList<int[]> mylist=new ArrayList<>();
            int arr[];
            int max=0;  //idk man, looks useless

            while (line!=null) {
                arr=new int[line.length()];
                if(line.length()>max) {
                    max=line.length();
                }
                for(int i=0;i<line.length();i++) {
                    arr[i]=Integer.parseInt(line.charAt(i)+"");
                }
                mylist.add(arr);
                line = br.readLine();
            }
            anArray=new int[max][mylist.size()];
            for(int i=0;i<mylist.size();i++) {
                for(int j=0;j<max;j++) {
                    anArray[j][i]=mylist.get(i)[j];
                }
            }
        }
        catch(Exception ball) {
            ball.printStackTrace();
        }
        return anArray;
    }

    public void render() {
        if(!hidden) {
            for(int i=0;i<height;i++) {
                for(int j=0;j<width;j++) {
                    if(shape[j][i]==1) {
                        Brick.scr.pixels[j+x][i+y].on(this.id);
                        Brick.canvas.repaint();
//                        if(Brick.scr.pixels[j+x][i+y].ObjectIdList.size()>1) {
//                            System.out.println("omg a collision");
//                        }
                    }
//                else if(shape[j][i]==2) {     blink to be implemented later...maybe
//                    //
//                }
                }
            }
            Brick.canvas.repaint();
        }
    }

    public void render(int sx, int sy) {
        remove();
        setAt(sx, sy);
        this.render();
    }

    public void setHidden(boolean HIDDEN) {
        hidden=HIDDEN;
        if(hidden) {
            remove();
        }
        else {
            render();
        }
        if(Brick.game!=null) {
            Brick.game.render();
//            Brick.canvas.repaint();
        }
    }

    public void setX(int X) {
        if(X>=0 && X+width-1<Brick.scr.pixels.length) {
            remove();
            x=X;
            if(Brick.game!=null) {
                Brick.game.render();
//                Brick.canvas.repaint();
            }
        }
    }

    public void setY(int Y) {
        if((Y>=0 && Y+height-1<Brick.scr.pixels[0].length)) {
            remove();
            y=Y;
            if(Brick.game!=null) {
                Brick.game.render();
//                Brick.canvas.repaint();
            }
        }
    }

    public void setAt(int X, int Y) {
        remove();
        if(X>=0 && X+width-1<Brick.scr.pixels.length) {
            remove();
            x=X;
        }
        if(Y>=0 && Y+height-1<Brick.scr.pixels[0].length) {
            remove();
            y=Y;
        }
        if(Brick.game!=null) {
            Brick.game.render();
//            Brick.canvas.repaint();
        }
//        setX(X);
//        setY(Y);
    }

    public void changeShape(int arr[][]) {
        if(x+arr.length-1<Brick.scr.pixels.length && y+arr[0].length-1<Brick.scr.pixels[0].length) {
            this.remove();
            this.shape=arr;
            this.width=shape.length;
            this.height=shape[0].length;
            this.render();
        }
    }

    public void rotateRight() {
        int arr[][]=new int[getHeight()][getWidth()];
        for(int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                arr[j][i]=shape[i][height-(j+1)];
            }
        }
        changeShape(arr);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void remove() {
        for(int i=0;i<shape[0].length;i++) {
            for(int j=0;j<shape.length;j++) {
                if(shape[j][i]==1) {
                    Brick.scr.pixels[j+x][i+y].off(this.id);
                }
            }
        }
    }

    public boolean getHidden() {
        return hidden;
    }

    public boolean touchingLeftEdge() {
        if(x<=0) {
            for(int i=0;i<height;i++) {
                if(shape[x][i]==1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean touchingRightEdge() {
        if(x+width>=Brick.scr.pixels.length) {
            for(int i=0;i<height;i++) {
                if(shape[width-1][i]==1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean touchingUpperEdge() {
        if(y<=0) {
            for(int i=0;i<width;i++) {
                if(shape[i][y]==1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean touchingLowerEdge() {            //just above ground
        if(y+height>=Brick.scr.pixels[0].length) {
            for(int i=0;i<width;i++) {
                if(shape[i][height-1]==1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collided() {
        for(int i=0;i<this.getHeight();i++) {
            for(int j=0;j<this.getWidth();j++) {
                if(Brick.scr.pixels[this.getX()+j][this.getY()+i].ObjectIdList.size()>1 && Brick.scr.pixels[this.getX()+j][this.getY()+i].ObjectIdList.contains(this.id+"")) {         //remove check from set or not?
                    return true;
                }
            }
        }
        return false;
    }

    public boolean willCollide(int dx, int dy) {
        if(getX()+dx<0||getX()+getWidth()+dx>Brick.scr.pixels.length||getY()+dy<0||getY()+getHeight()+dy>Brick.scr.pixels[0].length) {
            return true;
        }
        //LATER
        for(int i=getY()+getHeight();i<=getY()+getHeight()+dy;i++) {
            int tmp=this.getY()+i;
            if(tmp>=Brick.scr.pixels[0].length) {
                break;
            }
            for(int j=getX();j<this.getWidth()+dx;j++) {
                if(Brick.scr.pixels[this.getX()+j][tmp].ObjectIdList.size()>1) {
                    return true;
                }
            }
        }
        return false;
    }
}