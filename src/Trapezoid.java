
//YUANITA MUHARYANI UTAMI (125150201111017)

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.*;

public class Trapezoid {
 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable() {
   
   @Override
   public void run() {
       System.out.println("-- FUZZY NUMBER TRAPEZOID --");
       System.out.print("Masukkan jumlah trapesium : "); //masukkan inputan
    CartesianFrame frame = new CartesianFrame();
    frame.showUI();
   }
  });
 }
}

class CartesianFrame extends JFrame {
 CartesianPanel panel;
 
 public CartesianFrame() { 
  panel = new CartesianPanel();
  add(panel);
 }
 
 public void showUI() {
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setTitle("Trapezoid");
  setSize(700, 700);
  setVisible(true);
  
 }
}

class CartesianPanel extends JPanel {
  public static double p[];
  public static double q[];
  public static double r[];
  public static double s[];
  public static double alphacut;
  public static int xCoordNumbers, nAwal;
  public static int yCoordNumbers = 10;
  public static int jumlGrafik;
  public CartesianPanel(){
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    p = new double[n];
    q = new double[n];
    r = new double[n];
    s = new double[n];
    int temp = 0;
    System.out.print("Masukkan batas-batas himpunan : ");
    while(temp<n){ 
      p[temp] = in.nextDouble();
      q[temp] = in.nextDouble();
      r[temp] = in.nextDouble();
      s[temp] = in.nextDouble();
      temp++;
    }
    System.out.print("Masukkan nilai alpha-cut : ");
    alphacut = in.nextDouble();
      System.out.print("Masukkan range atas : ");
    int rangeAtas = in.nextInt();
      System.out.print("Masukkan range bawah : ");
    int rangeBawah = in.nextInt(); 
  
    this.alphacut = alphacut;
    this.xCoordNumbers = rangeBawah;
    this.nAwal = rangeAtas;
    this.jumlGrafik = n;
  }
 // x-axis coord constants
 public static final int X_AXIS_FIRST_X_COORD = 50;
 public static final int X_AXIS_SECOND_X_COORD = 600;
 public static final int X_AXIS_Y_COORD = 600;
 
 // y-axis coord constants
 public static final int Y_AXIS_FIRST_Y_COORD = 50;
 public static final int Y_AXIS_SECOND_Y_COORD = 600;
 public static final int Y_AXIS_X_COORD = 50;
 
 public static final int FIRST_LENGTH = 10;
 public static final int SECOND_LENGTH = 5;
 
 // size of start coordinate lenght
 public static final int ORIGIN_COORDINATE_LENGTH = 6;
 
 // distance of coordinate strings from axis
 public static final int AXIS_STRING_DISTANCE = 20;
 
    private void doDrawing(Graphics g, double p, double q, double r, double s, int value) {
        double fuzzy;
        Graphics2D g2d = (Graphics2D) g;
        double Xawal =  X_AXIS_FIRST_X_COORD ;
        double Yawal = Y_AXIS_SECOND_Y_COORD;
        Point2D.Double point = new Point2D.Double(Xawal, Yawal);
        int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)/ xCoordNumbers;
        int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)/ yCoordNumbers;
        if(value == 0){
          g2d.setColor(Color.blue);
        }
        else if(value == 1){
          g2d.setColor(Color.green);
        }
        else if(value == 2){
          g2d.setColor(Color.yellow);
        }
        double tempX = 0.0, tempY=0.0;
        for(int a = nAwal; a <= xCoordNumbers; a++){
            double nilai1, nilai2;
            nilai1 = (a-p)/(q-p);
            nilai2 = (s-a)/(s-r);
            fuzzy = minimum(nilai1, 1.0, nilai2);

            if(fuzzy<0.0)
              fuzzy = 0.0;

            System.out.println(a+" "+fuzzy);
            Xawal = X_AXIS_FIRST_X_COORD + (a * xLength)-3;
            Yawal = Y_AXIS_SECOND_Y_COORD - ((fuzzy*10)* yLength);
            if(a==nAwal){
              tempX = X_AXIS_FIRST_X_COORD + (a * xLength)-3;;
              tempY =  Y_AXIS_SECOND_Y_COORD - ((fuzzy*10)* yLength);
            }
            g2d.draw(new Line2D.Double(tempX, tempY, Xawal, Yawal));
            tempX = X_AXIS_FIRST_X_COORD + (a * xLength)-3;
            tempY =  Y_AXIS_SECOND_Y_COORD - ((fuzzy*10)* yLength);
        }
    }
    public double minimum(double p, double q, double r){
      if((p<=q)&&(p<=r)){
        return p;
      }
      else if((q<=p)&&(q<=r)){
        return q;
      }
      else if((r<=p)&&(r<=q)){
        return r;
      }
        else return 0.0;
    }
    public void alphaCut(Graphics g, double alphacut, int value){
        Graphics2D g2d = (Graphics2D) g;

        double Xawal =  X_AXIS_FIRST_X_COORD ;
        double Yawal = Y_AXIS_SECOND_Y_COORD;
        Point2D.Double point = new Point2D.Double(Xawal, Yawal);
        int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)/ xCoordNumbers;
        int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)/ yCoordNumbers;
        //g2d.setStroke(new BasicStroke(3));
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setColor(Color.red);
        g2d.draw(new Line2D.Double(X_AXIS_FIRST_X_COORD,Y_AXIS_SECOND_Y_COORD - ((alphacut*10)* yLength),
          X_AXIS_FIRST_X_COORD + (xCoordNumbers * xLength)-3,Y_AXIS_SECOND_Y_COORD - ((alphacut*10)* yLength)));
        // double BA, BB;
        double BA = alphacut*(this.q[value] - p[value])+p[value];
        double BB = -alphacut*(this.s[value] - r[value])+s[value];
        System.out.println("Batas Atas Gambar "+(value+1)+" = "+BA);
        System.out.println("Batas Bawah Gambar "+(value+1)+" = "+BB);
        g2d.draw(new Line2D.Double(X_AXIS_FIRST_X_COORD + (BA * xLength)-3,
          Y_AXIS_SECOND_Y_COORD,X_AXIS_FIRST_X_COORD + (BA * xLength)-3,Y_AXIS_SECOND_Y_COORD - ((alphacut*10)* yLength)));
        g2d.draw(new Line2D.Double(X_AXIS_FIRST_X_COORD + (BB * xLength)-3,
          Y_AXIS_SECOND_Y_COORD,X_AXIS_FIRST_X_COORD + (BB * xLength)-3,Y_AXIS_SECOND_Y_COORD - ((alphacut*10)* yLength)));
    }

 public void paintComponent(Graphics g) {
  
  super.paintComponent(g);
  
  Graphics2D g2 = (Graphics2D) g;
  
  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);
  
  // x-axis
  g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD,
     X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
  // y-axis
  g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD,
     Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);
  
  // // x-axis arrow
  g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGTH,
     X_AXIS_Y_COORD - SECOND_LENGTH,
     X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
  g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGTH,
    X_AXIS_Y_COORD + SECOND_LENGTH,
    X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
  
  // // y-axis arrow
  g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGTH,
     Y_AXIS_FIRST_Y_COORD + FIRST_LENGTH,
     Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
  g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGTH, 
     Y_AXIS_FIRST_Y_COORD + FIRST_LENGTH,
     Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
  
  // // draw origin Point
  g2.fillOval(
    X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGTH / 2), 
    Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGTH / 2),
    ORIGIN_COORDINATE_LENGTH, ORIGIN_COORDINATE_LENGTH);
  
  // draw text "X" and draw text "Y"
  g2.drawString("X", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
     X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
  g2.drawString("Y", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
     Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);
  g2.drawString("(0, 0)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE,
     Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);
  
  // numerate axis
  int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)
      / xCoordNumbers;
  int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)
      / yCoordNumbers;
  
  //draw x-axis numbers
  for(int a = 1; a < xCoordNumbers; a++) {
   g2.drawLine(X_AXIS_FIRST_X_COORD + (a * xLength),
     X_AXIS_Y_COORD - SECOND_LENGTH,
     X_AXIS_FIRST_X_COORD + (a * xLength),
     X_AXIS_Y_COORD + SECOND_LENGTH);
   g2.drawString(Integer.toString(a), 
     X_AXIS_FIRST_X_COORD + (a * xLength) - 3,
     X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
  }
  
  // //draw y-axis numbers
  for(int a = 1; a < yCoordNumbers; a++) {
   g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGTH,
     Y_AXIS_SECOND_Y_COORD - (a * yLength), 
     Y_AXIS_X_COORD + SECOND_LENGTH,
     Y_AXIS_SECOND_Y_COORD - (a * yLength));
   g2.drawString("0,"+Integer.toString(a), 
     Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, 
     Y_AXIS_SECOND_Y_COORD - (a * yLength));  
 }
  for(int a=0;a<jumlGrafik;a++){
    doDrawing(g, this.p[a], this.q[a], this.r[a], this.s[a], a);
  }
  for(int a=0;a<jumlGrafik;a++){
    alphaCut(g,this.alphacut, a);
  }
 }

}