package org.dddang.game;

import javax.swing.*;
import java.awt.*;

public class BallGameJFrame extends JFrame {
    int width = 1920,height=1080;
    public BallGameJFrame(){
        this.setTitle("測試窗體");
        int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
        int sh = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((sw-width)/2,(sh-height)/2,width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        BallGameJPanel bgp = new BallGameJPanel();
        bgp.gameStart();
        this.add(bgp);
        this.setVisible(true);
    }
    public static void main(String[] args){
        new BallGameJFrame();
    }
}
