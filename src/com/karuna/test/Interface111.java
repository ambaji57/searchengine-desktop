package com.karuna.test;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Interface111 extends JFrame implements ActionListener , KeyListener
{

  //declaring elements for interface
  JButton searchbtn, deletebtn,forwardbtn,backwardbtn;
  JTextField text;
  JTextArea testarea;
  JPanel panel1;
  JScrollPane pane; 
  
 public Interface111() {

 // this is for container

  Container c= getContentPane();
   c.setLayout(null);
   setSize(600,800);
   setVisible(true);

  //fileds definations
  searchbtn = new JButton("Search");
  searchbtn.setVisible(true);
  searchbtn.setBounds(250,80,120,30);
  forwardbtn = new JButton("Next");
  forwardbtn.setVisible(true);
  forwardbtn.setBounds(370,80,80,30);
  backwardbtn = new JButton("Previous");
  backwardbtn.setVisible(true);
  backwardbtn.setBounds(450,80,120,30);
  deletebtn = new JButton();
  deletebtn.setVisible(true);
  Font f= new Font("Serif",Font.PLAIN,14);
  text= new JTextField(150);
  text.setFont(f);
  text.setVisible(true);
  text.setBounds(50,80,200,30);
  testarea = new JTextArea();
  testarea.setBounds(10,150,500,500);
  testarea.setFont(f);
  testarea.setVisible(true);
//panel creation
  pane= new JScrollPane(testarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  pane.setBounds(30,150,500,500);
  panel1 = new JPanel(new GridBagLayout());
   

// adding components to contentpane
  c.add(text);
   c.add(searchbtn);
   c.add(forwardbtn);
   c.add(backwardbtn);
  c.add(pane);
 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 searchbtn.addActionListener(this);
 searchbtn.addKeyListener(this);
 forwardbtn.addActionListener(this);
 forwardbtn.addKeyListener(this);
 
 }

public void actionPerformed(ActionEvent e)
{
  testarea.setText("");
  String[] files;
  if(e.getSource()==searchbtn) {
  String str= text.getText();
  SearchFiles s= new SearchFiles();
  try {
   files=s.indexsearchfiles(str);
        //  System.out.println("am kannaiahchinni am working here fine");
          //System.out.println(str1);

   for(int i=0;i<files.length;i++){
      int k=i+1;
    testarea.append("*******Data from the file*******"+" "+files[i]);
    testarea.append("\n");
    testarea.append("\n");
      FileInputStream fis = new FileInputStream(files[i]);
      DataInputStream dis = new DataInputStream(fis);
       while(dis.available()!=0) {
           String dataline=dis.readLine();
           if(dataline.indexOf(str)>0) {
             testarea.append(dataline);
             testarea.append("\n");
             }
         }
    testarea.append("\n");
        
   }
   }
   catch (Exception exp)
   {}
  }
  if(e.getSource()==forwardbtn)
   {
   SearchFiles s= new SearchFiles();
   s.next();  
  
   }
}

public void keyPressed(KeyEvent key)
{
  testarea.setText("");
   String[] filed; 
  if(key.getKeyCode()==key.VK_ENTER) {
     String str=text.getText();
     SearchFiles s = new SearchFiles();
      try {
           filed = s.indexsearchfiles(str);
         // System.out.println("am chinni am working here fine");
           for(int i=0;i<filed.length;i++) {
               int k=i+1;
              testarea.append(k+" "+filed[i]);
               testarea.append("\n");
              }
        }
      catch(Exception exp)
       {}
     }
    
}
public void keyReleased(KeyEvent key)
{
}
public void keyTyped(KeyEvent key)
{
}


public static void main(String[] arg)
{

  Interface1 in = new Interface1();
}

}
