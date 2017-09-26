package org.dddang.game;

import java.awt.*;

public class SmallBall {
    //定義小球坐標、方向、大小、速度
    int x, y, d;
    //定義小球顏色
    Color ballColor;

    //構造方法
    public SmallBall(int x, int y,  int d, Color ballColor) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.ballColor = ballColor;
    }

    //自定義畫小球的方法
    public void drawSmallBall(Graphics g) {
        g.setColor(ballColor);
        g.fillRect(x, y, d, d);
    }
}
