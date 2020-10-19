/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Skype;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author eh5i0
 */
public class panel extends JPanel{
    
       int x=20;
    int y=30;
    
    
     public void paint( Graphics g)
  {
   g.drawRect(x,y ,x+20, y+20);
   
  
 // g.clearRect(x,y ,x+20, y+20);
  }
    
}
