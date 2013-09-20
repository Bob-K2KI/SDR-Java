package sdrgraph;


import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*; //para usar fuente arial 10.



public class Graph2D extends JPanel
{
    Font ft10 = new Font("Arial",Font.PLAIN,10);
    private boolean zoomPos = true, zoomEnable, corrEnable = true, corrPos = true;
    private Border colorLine;
    GraphPanel ZG;
    public static int escalaX, escalaY;
    
    public Graph2D(int Gancho, int Galto, boolean zoomEn, int escalaX, int escalaY, int x0, int y0, boolean isFreq)
    {
        Graph2D.escalaX = escalaX;
        Graph2D.escalaY = escalaY;
        this.zoomEnable = zoomEn;
        JPanel DisplayPanel = new JPanel();
        DisplayPanel.setLayout(new BorderLayout(1,1));
        ZG = new GraphPanel(Galto, Gancho, escalaX, escalaY, x0, y0, isFreq);// panel zona grafica
        
        colorLine = BorderFactory.createLineBorder(new Color(220,220,220));
        DisplayPanel.setBorder(colorLine);
        TitledBorder rotuloZoom, rotuloCorr;
        rotuloZoom = BorderFactory.createTitledBorder(" Zoom");
        rotuloZoom.setTitleColor(new Color(0,0,128));
        rotuloZoom.setTitleFont(ft10);
        rotuloCorr = BorderFactory.createTitledBorder("Correction");
        rotuloCorr.setTitleColor(new Color(0,0,128));
        rotuloCorr.setTitleFont(ft10);
        
        DisplayPanel.setPreferredSize( new Dimension(Gancho,Galto));
        
        DisplayPanel.add("Center" , ZG );
        if(zoomEnable)
        {
            ZoomPanel SP = new ZoomPanel(zoomPos);
            SP.setBorder(rotuloZoom);
            SP.setPreferredSize( new Dimension(30*Gancho/100,20*Galto/100));
            if (zoomPos) DisplayPanel.add("North",   SP);
            else DisplayPanel.add("West",   SP);
        }
        if(corrEnable)
        {
            CorrectionPanel CP = new CorrectionPanel(corrPos);
            CP.setBorder(rotuloCorr);
            CP.setPreferredSize( new Dimension(30*Gancho/100,20*Galto/100));
            if (zoomPos) DisplayPanel.add("South",   CP);
            else DisplayPanel.add("East",   CP);
        }
        DisplayPanel.add(ZG);
        this.add(DisplayPanel);
        DisplayPanel.setBackground(Color.white);
        DisplayPanel.setVisible(true);
    }
    
    public void setZoomPos(boolean pos)
    {
        zoomPos = pos;
    }
    public void setZoomEnable(boolean enable)
    {
        zoomEnable = enable;
    }

 /*public void setSize2(int height, int width)
 {
     Galto = height;
     Gancho = width;
 }*/
 
    public void setBorder(Border borde)
    {
        colorLine = borde;
    }
    public void addPoint(float[] valores)
    {
        ZG.valores = valores;
        ZG.repaint(); 
    }
    public static void setEscalaX(int escalaX)
    {
        escalaX = escalaX;
    }


}