/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Skype;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author eh5i0
 */
public class Frame extends JPanel{
    
      JFrame f;
    
    public Frame()
    {
           f=new JFrame("Face");
       f.setDefaultCloseOperation(EXIT_ON_CLOSE);
         f.setSize(400,400);
       f.setVisible(true);
      f.add(this);
    }
    
    
    int x=20;
    int y=30;
    
    
     public void paint( Graphics g)
  {
   g.drawRect(x,y ,x+20, y+20);
   
  
 // g.clearRect(x,y ,x+20, y+20);
  }
     
    public void p()
     {
           while(true)
       {
               try {
                   Thread.sleep(100);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
               }
           
           x=x+10;
           if(x==400)
               x=0;
         
          f.repaint();
       }
     }
     public static void main(String args[]) throws Exception {
     
         Frame f1=new Frame();
       f1.p();
    
     
     } 
}
