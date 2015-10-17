package com.karuna.test;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Interface1 extends JFrame implements ActionListener , KeyListener , ItemListener
{

  //declaring elements for interface
  JButton searchbtn, createindexbtn,forwardbtn,backwardbtn,Save;
  JTextField text,index_text;
  JTextArea testarea;
  JMenuItem jmenu;
  JPopupMenu pmenu;
  JPanel panel1;
   JLabel total,searching;
  JScrollPane pane; 
  Checkbox check;
   int length=0;
   int start=0,end=0,numdis=10;
  
 public Interface1() {

 // this is for container

  Container c= getContentPane();
   c.setLayout(null);
   setLocation(350,5);
   setSize(700,800);
   c.setBackground(Color.WHITE);
   setVisible(true);
    setTitle("Search Engine");
  //font class for text font in textarea
    Font f= new Font("gautami",Font.PLAIN, 14);
    Font f1= new Font("gautami",Font.PLAIN, 14);

  //fileds definations
   check= new Checkbox("Do u want to create index please select the checkbox to enable the fileds");
  check.setBounds(10,10,600,40);
   check.setFont(f1);
  // check.setColor(Color.RED);
  createindexbtn= new JButton("Create Index");
  createindexbtn.setVisible(false);
  createindexbtn.setBounds(250,58,170,30);
  searchbtn = new JButton("Search");
  searchbtn.setVisible(true);
  searchbtn.setBounds(250,95,120,30);
  forwardbtn = new JButton("Next");
  forwardbtn.setVisible(true);
  forwardbtn.setBounds(370,95,80,30);
  backwardbtn = new JButton("Previous");
  backwardbtn.setVisible(true);
  backwardbtn.setBounds(450,95,120,30);
  Save = new JButton("Save");
  Save.setVisible(true);
  Save.setBounds(570,95,80,30);
  text= new JTextField(150);
  text.setVisible(true);
   text.setFont(f);
   text.requestFocus();
  text.setBounds(50,95,200,30);
  index_text= new JTextField(150);
  index_text.setVisible(false);
  index_text.setBounds(50,58,200,30);
  testarea = new JTextArea();
  testarea.setBounds(10,170,777777700,600);
  testarea.setVisible(true);
   testarea.setFont(f);
   testarea.setLineWrap(true);
    total= new JLabel("");
   total.setVisible(false);
   total.setBounds(30,130,200,20);
   searching = new JLabel("");
   searching.setVisible(false);
   searching.setBounds(250,130,300,20);

//panel creation
  pane= new JScrollPane(testarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  pane.setBounds(30,150,650,520);
  panel1 = new JPanel(new GridBagLayout());
  pmenu= new JPopupMenu();
  jmenu=  new JMenuItem("cut");
  pmenu.add(jmenu);
  jmenu= new JMenuItem("paste");
  pmenu.add(jmenu);
  jmenu = new JMenuItem("copy");
  pmenu.add(jmenu);
 jmenu.addActionListener(this);
// adding components to contentpane
   c.add(index_text);
   c.add(check);
   c.add(createindexbtn);
  c.add(text);
   c.add(searchbtn);
   c.add(forwardbtn);
   c.add(backwardbtn);
   c.add(Save);
   c.add(total);
     c.add(searching);
  c.add(pane);
 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 searchbtn.addActionListener(this);
// searchbtn.addKeyListener(this);
 forwardbtn.addActionListener(this);
 backwardbtn.addActionListener(this);
 createindexbtn.addActionListener(this);
  Save.addActionListener(this);
  check.addItemListener(this);
testarea.addMouseListener(new MouseAdapter()
{
   public void mousePressed(MouseEvent event)
    {
       checkForTriggerEvent(event);
    }
   public void mouseReleased(MouseEvent event)
   {
         checkForTriggerEvent(event);
    }
   private void checkForTriggerEvent(MouseEvent event)
   {
        if(event.isPopupTrigger())
           pmenu.show(event.getComponent(),event.getX(),event.getY());
    }
}
);
}
 

public void actionPerformed(ActionEvent e)
{
	System.out.println(" action performed ... ");
	
  String s1= e.getActionCommand();
  if(e.getSource()== "cut")
   {
     JOptionPane.showMessageDialog(null,"am cut option");
    }
  String[] files;
  String str1= text.getText();
  System.out.println(" input string  "+ str1);
  Pattern pattern = Pattern.compile("^[a-z0-9]");
  Matcher matcher = pattern.matcher(str1);
  CodeConverter cc = new CodeConverter();
  String str="";
  String deva="";
  if(matcher.find())
  {
    str=str1;
   // deva=cc.convertWx2Unicode(str1,"Devanagari");
  }
  else {
	  CodeConverter c= new CodeConverter();
   str=c.convertUnicode2Wx(str1,"Devanagari");
   deva= str;
   }
  if(e.getSource()==searchbtn) {
  SearchFiles1 s= new SearchFiles1();
  testarea.setText("");
  try {
     start=0;
     end=0;
     str ="త";
   files=s.indexsearchfiles(str);
   length=files.length;
   if(length!=0)  {
    total.setText(length+"  total matching files");
     total.setVisible(true);
   end=Math.min(files.length,numdis);
    searching.setText("you are searching...."+"file "+start+"..."+end+"th files");
   searching.setVisible(true);
   for(int i=start;i<end;i++){
      int k=i+1;
     testarea.append("********Data from the file********"+"  "+files[i].toUpperCase()); 
     testarea.append("\n");
     testarea.append("\n");
     FileInputStream fis= new FileInputStream(files[i]);
     DataInputStream dis= new DataInputStream(fis);
       while(dis.available()!=0)
        {
         String dataline=dis.readLine();
         StringTokenizer st= new StringTokenizer(str);
          if(st.countTokens()>1){
              while(st.hasMoreTokens()){
                     String kh=st.nextToken();
                  if(dataline.indexOf(kh)>0) {
                   //  deva=cc.convertWx2Unicode(kh,"Devanagari");
                     //String test= cc.convertWx2Unicode(dataline,"Devanagari");
                     testarea.append(dataline);
                      testarea.append("\n"); 
                     }
              }
            }
           else {
               if(dataline.indexOf(str)>0) {
    		testarea.append(dataline);
                     //testarea.append(cc.convertWx2Unicode(dataline,"Devanagari"));
                     //System.out.println(dataline);
    		testarea.append("\n");
              }
            }
        }
     testarea.append("\n");
   }
    }
    else {
     total.setText("no files are matched");
   searching.setText("");
    total.setVisible(true);
    }
   }
   catch (Exception exp)
   {
	   exp.printStackTrace();
   }
   
  }
  //code for next button
  if(e.getSource()==forwardbtn)
  {
   if(end<length)
   {
    testarea.setText("");
     start=end;
     end=end+10;
       searching.setText("you are searching...."+"file "+start+"..."+end+"th files");
      try {
     SearchFiles1 s= new SearchFiles1();
      files=s.indexsearchfiles(str);
            for(int i=start;i<end;i++){
                int k=i+1;
     testarea.append("********Data from the file********"+"  "+files[i]); 
     testarea.append("\n");
     testarea.append("\n");
     FileInputStream fis= new FileInputStream(files[i]);
     DataInputStream dis= new DataInputStream(fis);
       while(dis.available()!=0)
        {
         String dataline=dis.readLine();
          StringTokenizer stk= new StringTokenizer(str);
           if(stk.countTokens()>1)
            {
             while(stk.hasMoreTokens()) {
                 if(dataline.indexOf(stk.nextToken())>0) {
         
                     //testarea.append(cc.convertWx2Unicode(dataline,"Devanagari"));
                    testarea.append(dataline);
                    testarea.append("\n");
                  }  

               }

             }
          else { 
           if(dataline.indexOf(str)>0) {
              //    testarea.append(cc.convertWx2Unicode(dataline,"Devanagari"));
    		testarea.append(dataline);
    		testarea.append("\n");
              }
            }
          }
         testarea.append("\n");
        }  
        }
      catch(Exception ex){}
   }
   else {
         JOptionPane.showMessageDialog(null,"no files");
        } 
  }
  // code for previous button
  if(e.getSource()==backwardbtn)
  {
     if(end>10){
        start=start-10;
        end=end-10;
          testarea.setText("");
            searching.setText("you are searching...."+"file "+start+"..."+end+"th files");
        try {
          SearchFiles1 s = new SearchFiles1();
          files=s.indexsearchfiles(str);
            for(int i=start;i<end;i++){
                int k=i+1;
                testarea.append("*******Data from the file*******"+"  "+files[i]);
                testarea.append("\n");
                testarea.append("\n");
                FileInputStream fis= new FileInputStream(files[i]);
                DataInputStream dis = new DataInputStream(fis);
                   while(dis.available()!=0)  {
                     String dataline=dis.readLine();
                      StringTokenizer stk = new StringTokenizer(str);
                       if(stk.countTokens() > 1) 
                       {
                         while(stk.hasMoreTokens()) {
                              if(dataline.indexOf(stk.nextToken())>0) {
                                 //   testarea.append(cc.convertWx2Unicode(dataline,"Devanagari"));
                                    testarea.append(dataline);
                                    testarea.append("\n");                      
                                 }
                           }
                        }
                     else {
                    	 	if(dataline.indexOf(str)>0) {
                		testarea.append(dataline);
                               //testarea.append(cc.convertWx2Unicode(dataline,"Devanagari"));
                		testarea.append("\n");
                       		}
                      }
                     }
                  testarea.append("\n");
              }
         }
       catch(Exception x) {}
       }
    else {
         JOptionPane.showMessageDialog(null,"no previous files");
         }
   }
    
    if(e.getSource()==Save)
     {
              String data= testarea.getText();
         JFileChooser filechooser = new JFileChooser();
         filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          int result = filechooser.showSaveDialog(this);
          if(result != 1)
           {
             File savefile= filechooser.getSelectedFile();
              String name=savefile.toString();
              File outfile = new File(name);
              try
                {
                  BufferedWriter br = new BufferedWriter(new FileWriter(outfile));
                  br.write(data);
                  br.close();
                 JOptionPane.showMessageDialog(null,savefile.toString());
                }
               catch(Exception w)
                 {  }
           }
          else {
              JOptionPane.showMessageDialog(null, "error creating file");
             }

     }
  // write code here for file chooser
    if(e.getSource()==createindexbtn)
      {
        int s;
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
         int result = filechooser.showOpenDialog(this);
         if(result == JFileChooser.CANCEL_OPTION)
          {
          }
         if(result !=1)
          {
         File filename=filechooser.getSelectedFile();
          String file = filename.toString();
           index_text.setText(file);
          if(file == null) {
            JOptionPane.showMessageDialog(null,"filename is null");
           }
          else {
               IndexFiles infiles= new IndexFiles();
             s= infiles.createindex(file); 
             if(s==1)
                JOptionPane.showMessageDialog(null,"delete index file and try again");
              else
                JOptionPane.showMessageDialog(null,"index is created");
                check.setState(false);
                index_text.setVisible(false);
                createindexbtn.setVisible(false);
                  
           }
        
      }
 }
}

public void itemStateChanged(ItemEvent event)
 {
   if(event.getSource()==check)
    {
     if(check.getState()==true){
        JOptionPane.showMessageDialog(null,"Please delete index folder form ur searchengine directory");
       createindexbtn.setVisible(true);
       index_text.setVisible(true);}
     else {
       createindexbtn.setVisible(false);
       index_text.setVisible(false);}
    }
 }

public void keyPressed(KeyEvent key)
{
  testarea.setText("");
   String[] filed; 
  if(key.getKeyCode()==key.VK_ENTER) {
     String str=text.getText();
     SearchFiles1 s = new SearchFiles1();
      try {
           filed = s.indexsearchfiles(str);
          System.out.println("am chinni am working here fine");
           for(int i=0;i<filed.length;i++) {
               int k=i+1;
              testarea.append(k+" "+filed[i]);
               testarea.append("\n");
              }
        }
      catch(Exception exp)
       {
    	  
    	  exp.printStackTrace();
       }
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
