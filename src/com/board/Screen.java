package com.board;

import com.body.SnakeBody;
import com.food.Food;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JFrame implements Runnable {
    public static final int topMargin = 30;
    public static final int leftMargin = 30;
    private static  final  long serialVersionUID = 1L;
    public static  final  int width = 800, height = 800;
    private Thread thread;
    private boolean running = false;

    private SnakeBody body;
    private ArrayList<SnakeBody> snake;

    private Food food;
    private ArrayList<Food> snakeFood;
    private Random random;

    private  int xCoordinates = 10, yCoordinates = 10;
    private int size = 5;

    private boolean right = true, left = false, up = false, down = false;

     private int tick = 0;

     private Key key;

    public Screen(){
        setFocusable(true);
        addKeyListener(key);
        setPreferredSize(new Dimension(width, height));
        random = new Random();
        snake = new ArrayList<SnakeBody>();
        snakeFood = new ArrayList<>();

        start();

        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SnakeGame");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        this.addKeyListener(new Key());
    }

    public void tick(){
        if(snake.size() == 0){
            body = new SnakeBody(xCoordinates, yCoordinates, 10);
            snake.add(body);
        }

        if(snakeFood.size() == 0){
            int xCoordinates = random.nextInt(80);
            int yCoordinates = random.nextInt(80);

            food = new Food(xCoordinates, yCoordinates, 10);
            snakeFood.add(food);
        }

        for(int i = 0; i < snakeFood.size(); i++){
            if(xCoordinates == snakeFood.get(i).getxCoordinates() && yCoordinates == snakeFood.get(i).getyCoordinates()){
                size++;
                snakeFood.remove(i);
                i--;
            }
        }

        for(int i = 0; i < snake.size(); i++){
            if(xCoordinates == snake.get(i).getxCoordinates() && yCoordinates == snake.get(i).getyCoordinates()){
                if(i != snake.size() - 1){
                    stop();
                }
            }
        }

        checkTeleport();

        tick++;


            if(right) xCoordinates++;
            if(left) xCoordinates--;
            if(up) yCoordinates--;
            if(down) yCoordinates++;

            tick = 0;

            body = new SnakeBody(xCoordinates, yCoordinates, 10);
            snake.add(body);

            if(snake.size() > size){
                snake.remove(0);
            }

    }

    private void checkTeleport(){
        if (xCoordinates >= 80){
            xCoordinates = 0;
        }
        if (xCoordinates < 0){
            xCoordinates = 79;
        }
        if (yCoordinates >= 80){
            yCoordinates = 0;
        }
        if (yCoordinates < 0){
            yCoordinates = 79;
        }
    }

    public void paint(Graphics gr){
        gr.clearRect(0, 0, width, height);
        gr.setColor(Color.BLACK);
        for(int i = 0; i < width / 10; i++){
            gr.drawLine(i * 10, 0, i * 10, height);
        }
        for(int i = 0; i < height / 10; i++){
            gr.drawLine(0, i * 10, width, i * 10);
        }
        for(int i = 0; i < snake.size(); i++){
            snake.get(i).drawSnake(gr);
        }
        for(int i = 0; i < snakeFood.size(); i++){
            snakeFood.get(i).drawFood(gr);

        }
        //GameOver text
        gr.setColor(Color.RED);
        gr.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(gr.getFont());

        //Score
        gr.setColor(Color.RED);
        gr.setFont(new Font("Arial", Font.BOLD, 60));
        FontMetrics metrics2 = getFontMetrics(gr.getFont());
        gr.drawString("Score" + food,(WIDTH - metrics.stringWidth("Score" + food)) / 2, gr.getFont().getSize());
    }


    public void start(){
        running = true;
        thread = new Thread(this, "Game Loop");
        thread.start();
    }
    public void stop(){
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void run(){
        while(running){
            tick();
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private class Key implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT && !left){
                up = false;
                down = false;
                right = true;
            }
            if(key == KeyEvent.VK_LEFT && !right){
                up = false;
                down = false;
                left = true;
            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                right = false;
                up = true;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
