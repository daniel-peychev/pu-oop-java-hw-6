package com.food;

import com.board.Screen;

import java.awt.*;

public class Food {
    private int xCoordinates, yCoordinates, width, height;

    public Food(int xCoordinates, int yCoordinates, int tileSize){
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        width = tileSize;
        height = tileSize;
    }
    public void tick(){
    }
    public void drawFood(Graphics gr){
        gr.setColor(Color.MAGENTA);
        gr.fillRect(xCoordinates * width, yCoordinates * height, width, height);
    }
    public int getxCoordinates(){
        return xCoordinates;
    }
    public int getyCoordinates(){
        return yCoordinates;
    }
    public void setyCoordinates(int yCoordinates){
        this.yCoordinates = yCoordinates;
    }
}
