/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Skype;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author eh5i0
 */
public class Client_Video extends JPanel {

    private static Image img;
   public static  int exit1=-1;
    public static int port=6231;
    public Client_Video() {
        
        this.setSize(500,500);
        this.setBackground(Color.red);
        this.setVisible(true);
    }

    public static void setimage(Image g) {
        img = g;
        // this.setIconImage(g);
       // this.repaint();
    }

    public  void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
        this.repaint();
        // g.drawRect(20,10,10,10);
    }

    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(port);
        Socket s = ss.accept();
        
        out.println("sevr:");
       
        setimage(ImageIO.read(s.getInputStream()));

    }

    public static void main(String args[]) throws IOException, Exception {
      
        JFrame frame = new JFrame(" client camera");
        Client_Video imagepanel = new Client_Video();
        
        frame.setSize(650, 450);
        frame.setLocation(600,0);
        frame.setVisible(true);
        frame.add(imagepanel);
        imagepanel.setVisible(true);
        
       
        
        while (true) {
             frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent evt) {
            
         Server_Video.exit=0; 
        // System.exit(0);
     }
       });
            
             Socket se=new Socket("localhost",port);
             
       setimage(ImageIO.read(se.getInputStream()));
         frame.repaint();
          
        }
        
    }
}
