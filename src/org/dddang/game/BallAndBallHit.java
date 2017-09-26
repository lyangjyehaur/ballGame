/*
 * Edit by LyangJyehaur
 */

package org.dddang.game;

public class BallAndBallHit {
    public boolean eat(int px,int py,int pd,SmallBall ball){
        boolean flag = false;
        //球1圓心x
        int b1x = ball.x+ball.d/2;
        //球1圓心y
        int b1y = ball.y+ball.d/2;
        //球2圓心x
        int b2x = px+pd/2;
        //球1圓心x
        int b2y = py+pd/2;
        //圓心距
        double e = Math.sqrt((b2x-b1x)*(b2x-b1x)+(b2y-b1y)*(b2y-b1y));
        if(e<pd/2+ball.d/2){
            flag = true;
        }
        return flag;
    }
    public boolean hit(int px,int py,int pd,Ball ball){
        boolean flag = false;
        //球1圓心x
        int b1x = ball.x+ball.d/2;
        //球1圓心y
        int b1y = ball.y+ball.d/2;
        //球2圓心x
        int b2x = px+pd/2;
        //球1圓心x
        int b2y = py+pd/2;
        //圓心距
        double e = Math.sqrt((b2x-b1x)*(b2x-b1x)+(b2y-b1y)*(b2y-b1y));
        if(e<pd/2+ball.d/2){
            flag = true;
        }
        return flag;
    }
    public boolean hit(Ball b1,SmallBall b2){
        boolean flag = false;
        //球1圓心x
        int b1x = b1.x+b1.d/2;
        //球1圓心y
        int b1y = b1.y+b1.d/2;
        //球2圓心x
        int b2x = b2.x+b2.d/2;
        //球1圓心x
        int b2y = b2.y+b2.d/2;
        //圓心距
        double e = Math.sqrt((b2x-b1x)*(b2x-b1x)+(b2y-b1y)*(b2y-b1y));
        if(e<b1.d/2+b2.d/2){
            flag = true;
        }
        return flag;
    }
}
