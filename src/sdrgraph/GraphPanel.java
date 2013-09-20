package sdrgraph;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import java.awt.*; //para uar fuente arial 10.
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.*;
import java.text.DecimalFormat;
import sdrjava.ConfigurationBean;
//import java.awt.image.*;



public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener
 {
    boolean isFreq;
    Font ft7  = new Font("Arial",Font.PLAIN,7);
    Font ft10 = new Font("Arial",Font.PLAIN,10);
    int offsetX, offsetY,x0, y0;
    public static int escalaX = 5, escalaY = 10;
    boolean dragging;
    private int Galto, Gancho;
    private Border colorLine = BorderFactory.createLineBorder(new Color(220,220,220));
    static BasicStroke grosor1 = new BasicStroke(0.5f);
    final static float dash1[] = {5.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
    public double valxi=0.0, valxi1=0.0, valyi=0.0,valyi1=0.0;
    float valores [];
    double xmin,xmax,imgx;
    DecimalFormat myFormatter;
    
    public GraphPanel(int Galto, int Gancho, int escalaX, int escalaY,int x0, int y0, boolean isFreq)
    {
        this.escalaX = escalaX;
        this.escalaY = escalaY;
        this.Galto = Galto;
        this.Gancho = Gancho;
        this.x0 = x0;
        this.y0 = y0;
        this.isFreq = isFreq;
	offsetX=x0; offsetY=y0;
	addMouseListener(this);       //auditores para eventos de mouse
	addMouseMotionListener(this);
        this.setBorder(colorLine);
        this.setBackground(Color.white);
        this.setVisible(true);
        myFormatter = new DecimalFormat("0.00");
        myFormatter.setDecimalSeparatorAlwaysShown(false);
    }
	   //manejo de eventos de mouse
    public void mousePressed(MouseEvent evt)
    {
        if (dragging)
            return;
        int x = evt.getX(); // clic inicial
	int y = evt.getY();
	offsetX = x - x0;
	offsetY = y - y0;
	dragging = true;
    }
    
    public void mouseReleased(MouseEvent evt)
    {
        dragging = false;
        repaint();
    }
    
    public void mouseDragged(MouseEvent evt)
    {
        if (dragging == false)
            return;
        int x = evt.getX(); // posici�n del mouse
        int y = evt.getY();
        x0 = x - offsetX;  // mover origen usando suma vectorial
        y0 = y - offsetY;
        repaint();
    }

	    //el resto hace nada

    public void mouseMoved(MouseEvent evt) {}
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graficar(g, x0, y0);     //x0,y0 se inicalizan en init
    }
    
   
    
    void Graficar(Graphics ap, int xg, int yg)
    {
        int xi=0,yi=0,xi1=0,yi1=0,numPuntos=1;
        int cxmin,cxmax,cymin,cymax;
        
              //convertimos el objeto ap en un objeto Graphics2D para usar los metodos Java2D
        Graphics2D g = (Graphics2D) ap;
     	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(ft10);
        g.setPaint(new Color(0,0,150));
              //Dibujar el eje Y
        g.draw(new Line2D.Double(xg, 10, xg, Galto-10));
              //eje X
        g.draw(new Line2D.Double(10, yg, Gancho-10, yg));
        xmin=xg;
        xmax=(1.0*(Gancho-xg));
        cxmin=(int)Math.round(xmin); //pantalla
        cxmax=(int)Math.round(xmax);
        cymin=(int)Math.round(1.0*(yg-Galto));
        cymax=(int)Math.round(1.0*yg);
        numPuntos=Gancho; //num pixels
        g.setPaint(Color.gray);
        g.setFont(ft7);
        //marcas en los ejes (ticks)
       // if(escalaX>3)
        {
            for(int i=0;i<cxmax;i=i+35)
            {
                g.draw(new Line2D.Double(xg+i, yg-2, xg+i , yg+2));
                if(i>0) g.drawString(""+ myFormatter.format(calculateXScale(isFreq, i)), xg+i-2, yg+12);
                if(i<0) g.drawString(""+i, xg+i-6, yg+12);
            }
        }
        //if(escalaY>3)
        {
            for(int i=cymin+1;i<cymax;i=i+25)
            {
                g.draw(new Line2D.Double(xg-2, yg-i, xg+2 , yg-i));
                if(i>0) g.drawString(""+i, xg-12,yg-i+3 );
                if(i<0) g.drawString(""+i, xg-14,yg-i+3 );
            }
        }
          
        g.setPaint(new Color(50,100,0));
        g.setStroke(grosor1);
        try
        {
            for(int i=0; i<valores.length-1; i++)
            {
                valxi =xmin +i;
                valyi = valores[i];
                valxi1=xmin+(i+1);
                valyi1 = valores[i+1];
                
                
                xi      =(int)Math.round((valxi));
                yi      =(int)Math.round(valyi);
                xi1     =(int)Math.round((valxi1));
                yi1     =(int)Math.round(valyi1);
                  //control de discontinuidades groseras y complejos
//                  if(dist(valxi,valyi,valxi1,valyi1)< 1000)
//                  {
                g.draw(new Line2D.Double((xi),yg-yi,(xi1),(yg-yi1)));
//                g.draw(new Line2D.Double(xg+xi,yg-yi,xg+xi1,yg-yi1));
//                  }
            }//fin del for
        }
        catch(Exception e){}
    }//
     public static void setEscalaX(int escalaX)
    {
        escalaX = escalaX;
    }
     private float calculateXScale(boolean isFreq, int i)
     {
         if(isFreq)
         {
            return ((((i*ConfigurationBean.getSampleRate()/(Gancho-20)))-ConfigurationBean.getSampleRate()/2));
         }
         else return i*1000/ConfigurationBean.getSampleRate();

     }
}

