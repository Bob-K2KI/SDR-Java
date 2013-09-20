package sdrjava;

import sdrgraph.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;



public class Graphs extends Panel
{
    Graph2D chartIzq, chartDer, chartFreq;

    public Graphs()
    {
        javax.swing.border.Border borde = BorderFactory.createLineBorder(Color.gray, 1);
        Font ft1 = new Font("Monospaced", 1, 18);
        JLabel domTiempo = new JLabel("Time");
        domTiempo.setSize(100, 15);
        domTiempo.setLocation(390, 0);
        domTiempo.setBackground(Color.WHITE);
        domTiempo.setFont(ft1);
        domTiempo.setAlignmentX(1.0F);
        JLabel domFrecuencia = new JLabel("Frequency");
        domFrecuencia.setSize(400, 20);
        domFrecuencia.setLocation(350, 220);
        domFrecuencia.setBackground(Color.WHITE);
        domFrecuencia.setFont(ft1);
        domFrecuencia.setAlignmentX(1.0F);
        chartIzq = new Graph2D(400,190, sdrjava.ConfigurationBean.getEnableZoomControlI(),1,1,15,95,false);
        chartIzq.setSize(400, 220);
        chartIzq.setLocation(0, 20);
        chartIzq.setBorder(borde);
        chartDer = new Graph2D(400,190, sdrjava.ConfigurationBean.getEnableZoomControlQ(),1,1,15,95,false);
        chartDer.setSize(400, 220);
        chartDer.setLocation(420, 20);
        chartDer.setBorder(borde);
        chartFreq = new Graph2D(820,270, sdrjava.ConfigurationBean.getEnableZoomControlFreq(),1,5,410,135,true);
        chartFreq.setSize(820, 270);
        chartFreq.setLocation(0,250);
        setLayout(null);
        add(domTiempo);
        add(domFrecuencia);
        add(chartIzq);
        add(chartDer);
        add(chartFreq);
        setVisible(true);
    }

    public void setValores(float valTiempo[][], float valFrequency[])
    {
        try
        {
        float valTiempoIzq[] = new float [valTiempo[0].length];
        float valTiempoDer[] = new float [valTiempo[0].length];
        float valChartFreq[] = new float [valFrequency.length];
//        System.out.println(valTiempo[0].length);
        for (int j = valFrequency.length/2; j>0; j--)
        {
            valChartFreq[(valFrequency.length/2)-j] = valFrequency[j];
            valChartFreq[(valFrequency.length/2)+j-1] = valFrequency[valFrequency.length-j];
        }  
        for(int i = 0; i < valTiempo[0].length; i++)
        {
            valTiempoIzq[i] = valTiempo[0][i];
            valTiempoDer[i] = valTiempo[1][i];
        }
            
            chartIzq.addPoint(valTiempoIzq);
            chartDer.addPoint(valTiempoDer);
            chartFreq.addPoint(valChartFreq);
        }
        catch(Exception e){System.out.println (e.toString());}
    }

}
