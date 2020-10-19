


package ServerProject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author OldSpice
 */
public class DisplayWindow extends JPanel  {

    private static BufferedImage image;
  
    public BufferedImage getimage() {
        return image;
    }

    public void setimage(BufferedImage newimage) {
        image = newimage;
    }

    public void paint(Graphics g) {
        
        super.paint(g);
        if (this.image == null) {
            return;
        }
      
         g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
          
    }

    
    public static void main(String args[]) throws Exception {
        DisplayWindow display=new DisplayWindow();
        JFrame frame = new JFrame(" client camera");
        DisplayWindow display1=new DisplayWindow();
        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setBounds(20,20 ,200,200);
        display1.setBounds(40,40 ,200,200);
        frame.add(display);
        frame.add(display1);
        frame.setVisible(true);

    
    }
        
  
    
  
 
}