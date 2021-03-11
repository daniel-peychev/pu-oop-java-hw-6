package com.body;

import java.awt.*;

public class SnakeBody {
    private int xCoordinates, yCoordinates, width, height;

    public SnakeBody(int xCoordinates, int yCoordinates, int tileSize){
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        width = tileSize;
        height = tileSize;
    }
    public void tick(){

    }
    public void drawSnake(Graphics gr){
        gr.setColor(Color.BLACK);
        gr.fillRect(xCoordinates * width, yCoordinates * height, width, height);
        gr.setColor(Color.BLUE);
        gr.fillRect(xCoordinates * width + 2, yCoordinates * height + 2,width, height);


    }
    public int getxCoordinates(){
        return xCoordinates;
    }
    public void setxCoordinates(int xCoordinates){
        this.xCoordinates = xCoordinates;
    }
    public int getyCoordinates(){
        return yCoordinates;
    }
    public void setyCoordinates(int yCoordinates){
        this.yCoordinates = yCoordinates;
    }
}
