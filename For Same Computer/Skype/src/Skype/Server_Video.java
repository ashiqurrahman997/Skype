/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package Skype;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author OldSpice
 */
public class Server_Video extends JPanel implements ActionListener {

    private static BufferedImage image;
    private JButton button = new JButton("capture");
    Socket s;
    ServerSocket ss;
    int count = 1;
    static int exit=-9;
    
   public int port=6231;
   
    public Server_Video() throws IOException {
       // super();
        ss = new ServerSocket(port);
          this.setLocation(600,200);  
        button.addActionListener((ActionListener) this);
        this.add(button);
        
        
    }

    private BufferedImage getimage() {
        return image;
    }

    private void setimage(BufferedImage newimage) {
        image = newimage;
    }

    public void paint(Graphics g) {
        
        super.paint(g);
        if (this.image == null) {
            return;
        }
        
 g.drawImage(this.image,15,50, this.image.getWidth(), this.image.getHeight(),null);
        g.draw3DRect(10,45,this.image.getWidth()+10,this.image.getHeight()+10, true);
       g.draw3DRect(7,42,this.image.getWidth()+16,this.image.getHeight()+16, true);
       
    }

    
    
    
    
    public static void main(String args[]) throws Exception {
        JFrame frame = new JFrame("Server ");
     //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocation(600,200);  
       System.loadLibrary("opencv_java2413");
        
   // CascadeClassifier faceDetector=new CascadeClassifier("C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_silverware.xml");
  //  CascadeClassifier faceDetector=new CascadeClassifier("C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_profileface.xml");
     CascadeClassifier faceDetector = new CascadeClassifier("C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
        Server_Video frame_server = new Server_Video();

        frame.add(frame_server);
        
        frame.setVisible(true);
       
        Mat Mat_Image = new Mat();
        
        MatToBufImg mat2Buf = new MatToBufImg();
        
        VideoCapture camera = null;
        try {
            camera = new VideoCapture(0);
        } catch (Exception xx) {
            xx.printStackTrace();
        }
        if (camera.open(0)) {
            while (true) {
           // Server_Audio.main(null);
            
                camera.read(Mat_Image);
                
                if (!Mat_Image.empty()) {
                 frame.setSize(Mat_Image.width()+50, Mat_Image.height()+100);
                 
                      MatOfRect faceDetections = new MatOfRect();
                      
         faceDetector.detectMultiScale(Mat_Image, faceDetections);
         
                     for (Rect rect : faceDetections.toArray()) {
                         
        Core.rectangle(Mat_Image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                
                new Scalar(0, 255, 0));// mat2Buf, mat2Buf);
                    }
                     
         System.out.println("face detection: " + faceDetections.toArray().length);
         
                 if (faceDetections.toArray().length == 0) {
                        System.out.println(" Face is not here !");
              }
                 
                mat2Buf.setMatrix(Mat_Image, ".jpg");
                    
                frame_server.setimage(mat2Buf.getBufferedImage());
                  
                frame_server.sentToClient(frame_server.getimage());
                 
              
                 frame_server.repaint();
               
               if(!frame.isDisplayable())
                   System.exit(0);
               
                }
                     else {
                    System.out.println("problems with webcam image capture");
                    break;
                }
                   if(exit==0)
                {
                    System.out.println("Finished");
                    camera.release();
                    
                    System.exit(exit);
                }
                frame.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent evt) {
            
           exit=0;
           System.out.println(" ses");
               // 
   }
  });
                    
            }
        }
       
        camera.release();
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
                    BufferedImage buffered = null;

        BufferedImage bi = image;
        
        ImageIcon ii = null;
        
        ii = new ImageIcon(bi);
        
        Image newimg =bi.getScaledInstance(ii.getIconWidth(),ii.getIconHeight(), java.awt.Image.SCALE_SMOOTH);
        
        ii = new ImageIcon(newimg);
        
        Image i2 = ii.getImage();
        
        image = new BufferedImage(i2.getWidth(null), i2.getHeight(null),BufferedImage.SCALE_SMOOTH);
        
        image.getGraphics().drawImage(i2,0,0, null);
        
        RenderedImage rendered = null;
        
        if (i2 instanceof RenderedImage)
        {
            rendered = (RenderedImage) i2;
        }
        else {
            
          
            {
                buffered = new BufferedImage(
                        ii.getIconWidth(),
                        ii.getIconHeight(),
                        BufferedImage.TYPE_3BYTE_BGR);
            }
            
           
            Graphics2D g = buffered.createGraphics();
            g.drawImage(i2,0,0, null);
            
            g.dispose();
            rendered = buffered;
        }
        
        try {
            
            ImageIO.write(rendered, "JPEG", new File("saved.jpg"));
           // receiver(bi);
       System.out.print("Sent");
                                    
       // this.repaint();

        } catch (Exception ex) {
        }
        JOptionPane.showMessageDialog(this," Iamge Saved");
    }
    
    
    
    public  void sentToClient (BufferedImage img ) throws IOException
    {
       Socket s = ss.accept();

       OutputStream out=s.getOutputStream();
       
       ImageIO.write(img,"JPG",out);  
     
      s.close();
    }
    
 
}