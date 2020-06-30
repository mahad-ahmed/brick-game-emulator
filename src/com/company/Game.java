package com.company;
import java.util.ArrayList;

public interface Game {
    void start();
    void render();
    void DpadRight();
    void DpadLeft();
    void DpadUp();
    void DpadDown();
    void h();

    ArrayList<GObject> objects=new ArrayList<>();
}