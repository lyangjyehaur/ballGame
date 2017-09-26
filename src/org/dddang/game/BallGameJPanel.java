package org.dddang.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class BallGameJPanel extends JPanel implements MouseMotionListener {
    //玩家球大小
    int d = 30;
    //玩家球坐標
    int px = (1920 - d) / 2;
    int py = (1080 - d) / 2;
    //玩家球顏色
    Color playerBallColor;
    //關卡
    int level = 1;
    //遊戲結束開關
    boolean gameOverFlag = false, gameWinFlag = false;
    //計時
    double time = 0;
    //NPC球集合
    List<Ball> balls = new ArrayList<>();
    //可食用小球集合
    List<SmallBall> smallBalls = new ArrayList<>();
    //NPC球對象
    Ball ball;
    //可食用小球對象
    SmallBall smallBall;
    //得分（重量）
    double score;

    //構造方法
    public BallGameJPanel() {
        //生成50個可食用小球
        for (int i = 0; i < 50; i++) {
            addSmallBall();
        }
        //生成關卡對應的NPC小球
        for (int i = 0; i < level; i++) {
            addBall();
        }
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //遍歷集合畫小球
        for (int i = 0; i < smallBalls.size(); i++) {
            smallBall = smallBalls.get(i);
            smallBall.drawSmallBall(g);
        }
        for (int i = 0; i < balls.size(); i++) {
            ball = balls.get(i);
            ball.drawBall(g);
            Graphics2D g2 = (Graphics2D) g;
            //顯示小球大小
            g2.setFont(new Font("Microsoft Jhenghei", Font.BOLD, 25));
            g2.drawString(""+ball.d,(ball.x+(ball.d/2)-15),ball.y-3);
        }
        g.setColor(Color.pink);
        g.setColor(playerBallColor);
        g.fillOval(px, py, d, d);
        Graphics2D g2 = (Graphics2D) g;
        //顯示玩家球大小
        g2.setFont(new Font("Microsoft Jhenghei", Font.BOLD, 25));
        g2.drawString(""+d,(px+(d/2)-15),py-3);
        //提示文字
        g2.setColor(Color.red);
        g2.setFont(new Font("Microsoft Jhenghei", Font.BOLD, 50));
            //顯示重量（單位轉換）
            if (score < 1000) {
                g2.drawString("重量：" + String.format("%1$.0f", score) + " 克", 50, 100);
            } else if (score >= 1000 && score < 1000000) {
                g2.drawString("重量：" + String.format("%1$.2f", score / 1000) + " 千克", 50, 100);
            } else {
                g2.drawString("重量：" + String.format("%1$.2f", score / 1000000) + " 噸", 50, 100);
            }
            //顯示關卡數
            g2.drawString("第 " + level + " 關", 820, 100);
            //顯示秒數
            g2.drawString("耗時：" + String.format("%1$.0f", time / 100) + " 秒", 1500, 100);
            //遊戲結束
            if (gameOverFlag) {
                g2.setFont(new Font("Microsoft Jhenghei", Font.BOLD, 100));
                g2.drawString("遊戲結束", 770, 500);
            g2.setFont(new Font("Microsoft Jhenghei", Font.BOLD, 80));
            g2.drawString("你被喫掉了，挺過了 "+(level-1)+" 關", 540, 600);
        }
        //遊戲勝利
        if (gameWinFlag) {
            g2.setFont(new Font("Microsoft Jhenghei", Font.BOLD, 100));
            g2.drawString("大吉大利", 770, 500);
        }
    }

    public void addBall() {
        int d = (int) (Math.random() * 40 + this.d);
        int x = (int) (Math.random() * 1920 - d);
        int y = (int) (Math.random() * 1080 - d);
        int speed = (int) (Math.random() * 5 + 1);
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        Color ballColor = new Color(r, g, b);
        ball = new Ball(x, y, d, speed, ballColor);
        balls.add(ball);
    }

    public void addSmallBall() {
        int d = (int) (Math.random() * 4 + 5);
        int x = (int) (Math.random() * 1980 - d);
        int y = (int) (Math.random() * 1080 - d);
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        Color ballColor = new Color(r, g, b);
        smallBall = new SmallBall(x, y, d, ballColor);
        smallBalls.add(smallBall);
    }

    public void gameStart() {
        new Thread() {
            public void run() {
                while (true) {
                    //秒數自增
                    time++;
                    //可食用小球
                    for (int i = 0; i < smallBalls.size(); i++) {
                        smallBall = smallBalls.get(i);
                        BallAndBallHit bab = new BallAndBallHit();
                        boolean flag = bab.eat(px, py, d, smallBall);
                        if (flag) {
                            if (d > smallBall.d) {
                                //增加玩家球大小
                                d = d + smallBall.d / 8;
                                //增加重量
                                score += smallBall.d;
                                //喫掉
                                smallBalls.remove(i);
                                //添加
                                addSmallBall();
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < balls.size(); i++) {
                        ball = balls.get(i);
                        ball.moveBall(px, py,d, ball.x, ball.y,ball.d);
                        BallAndBallHit bab = new BallAndBallHit();
                        boolean flag = bab.hit(px, py, d, ball);
                        if (flag) {
                            if (d > ball.d) {
                                d = d + ball.d / 8;
                                score += ball.d;
                                balls.remove(i);
                                //addBall();
                                break;
                            } else {
                                //遊戲結束
                                gameOverFlag = true;
                                repaint();
                                this.stop();
                            }
                        }
                    }
                    //判斷NPC球被喫完
                    if (balls.size() == 0) {
                        //關卡加1
                        level += 1;
                        //10關遊戲勝利
                        if (level > 10) {
                            gameWinFlag = true;
                            repaint();
                            this.stop();
                        }
                        //大小減半
                        d = d / 2;
                        //產生隨機顏色
                        playerBallColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                        //生成關卡對應數量的NPC小球
                        for (int i = 0; i < level; i++) {
                            addBall();
                        }
                    }
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        px = e.getX() - d / 2;
        py = e.getY() - d / 2;
    }
}
