package com.karuna.test;

import javax.swing.*;
import java.awt.*;
public class jframe{
  public static void main(String[] args){
  JFrame frame = new JFrame("Frame in Java Swing");
  JMenu menu= new JMenu();
  
  frame.setSize(400, 400);
  frame.setVisible(true);
  frame.setBackground( new Color(233,1,1));
  Color color=new Color(255,0,0);
  JPanel panel= new JPanel();
  panel.setSize(100,100);
  panel.setBackground(color);
  JButton btn=new JButton("submit");
  btn.setVisible(true);
 // btn.setSize(200,200);
  panel.add(btn);
 frame.add(panel);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
