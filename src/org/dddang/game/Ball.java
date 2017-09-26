package org.dddang.game;

import java.awt.*;

public class Ball {
    //定義小球坐標、方向、大小、速度
    int x, y, d, speed;
    //定義小球顏色
    Color ballColor;

    //構造方法
    public Ball(int x, int y, int d, int speed, Color ballColor) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.speed = speed;
        this.ballColor = ballColor;
    }

    //自定義畫小球的方法
    public void drawBall(Graphics g) {
        g.setColor(ballColor);
        g.fillOval(x, y, d, d);
    }

    //自定義小球移動方法
    public void moveBall(int px, int py, int pd, int bx, int by, int bd) {
        if (px + pd / 2 > bx + bd / 2) {
            if (py + pd / 2 > by + bd / 2) {
                x += speed;
                y += speed;
            } else {
                x += speed;
                y -= speed;
            }
        } else {
            if (py + pd / 2 > by + bd / 2) {
                x -= speed;
                y += speed;
            } else {
                x -= speed;
                y -= speed;
            }
        }
    }
}

