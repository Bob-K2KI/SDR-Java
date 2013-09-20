package sdrjava;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.sound.sampled.AudioFormat;
import javax.swing.*;

public class ConfigurationBean extends JFrame 
{
	public static AudioFormat.Encoding encoding = new AudioFormat.Encoding("PCM_SIGNED");
	public static float sampleRate = 8000.0F;
	public static int bitsChannel = 16;
	public static int numCanales = 2;
	public static int bytesFrame = 4;
	public static float framesSeg = 5120.0F;
	public static boolean bigEndian = false;
	private int frequencyYaxis = 1;
        private int frequencyXaxis = 1;
        private static boolean enableZoomControlI = false;
        private static boolean enableZoomControlQ = false;
        private static boolean enableZoomControlFreq = true;
	public static boolean debugL1 = true;
	public static boolean debugL2 = true;
	public static boolean debugL3 = true;
        private static int ancho = 820;
        private JComboBox sampleCBox, framesCBox;
	


	    public ConfigurationBean(String config)
	    {
                sampleCBox = new JComboBox();
                sampleCBox.addItem(8000);
                sampleCBox.addItem(11025);
                sampleCBox.addItem(16000);
                sampleCBox.addItem(22050);
                sampleCBox.addItem(44100);
                framesCBox = new JComboBox();
                framesCBox.addItem(5120);
                framesCBox.addItem(10240);
                framesCBox.addItem(20480);
                framesCBox.addItem(40960);
                
	        final JFrame frame = new JFrame();
	        frame.setLayout(new GridLayout(9, 2, 5, 5));
	        frame.setBackground(Color.red);
	        System.out.println(config);
	        if(config.equals("View configuration"))
	        {
	            javax.swing.border.Border borde = BorderFactory.createLineBorder(Color.gray, 1);
	            JButton salir = new JButton("Exit");
	            JLabel placaSonido = new JLabel("Configuraci\363n de la placa de sonido");
	            JLabel aclaracion = new JLabel("Por ahora no se puede cambiar nada");
	            JLabel encoding = new JLabel("Encoding: "+ConfigurationBean.encoding);
	            JLabel sampleRate = new JLabel("Sample Rate: "+ConfigurationBean.sampleRate);
	            JLabel bitsChannel = new JLabel("Bits per sample: "+ConfigurationBean.bitsChannel);
	            JLabel numCanales = new JLabel("Number of channels: "+ConfigurationBean.numCanales);
	            JLabel bytesFrame = new JLabel("Bytes per frame: "+ConfigurationBean.bytesFrame);
	            JLabel framesSeg = new JLabel("Frames per second: "+ConfigurationBean.framesSeg);
	            JLabel bigEndian = new JLabel("Big Endian: "+ConfigurationBean.bigEndian);
	            JLabel graficos = new JLabel("Graphs Configuration");
	            placaSonido.setBackground(Color.lightGray);
	            graficos.setBackground(Color.lightGray);
	            placaSonido.setOpaque(true);
	            graficos.setOpaque(true);
	            JLabel aclaracion2 = new JLabel("Factores de escala");
	            JLabel escalaFrecuenciaY = new JLabel("Frequency Y axis: "+frequencyYaxis);
	            JLabel vacio1 = new JLabel(" ");
	            JLabel vacio2 = new JLabel("Frequency X axis: "+frequencyXaxis);
	            JLabel vacio3 = new JLabel("Enable Zoom Control I: "+enableZoomControlI);
	            JLabel vacio4 = new JLabel("Enable Zoom Control Q: "+enableZoomControlQ);
	            JLabel vacio5 = new JLabel("Enable Zoom Control Freq: "+enableZoomControlFreq);
	            JLabel vacio6 = new JLabel("");
	            placaSonido.setBorder(borde);
	            graficos.setBorder(borde);
	            placaSonido.setHorizontalAlignment(0);
	            aclaracion.setHorizontalAlignment(0);
	            graficos.setHorizontalAlignment(0);
	            aclaracion2.setHorizontalAlignment(0);
	            frame.getContentPane().add(placaSonido);
	            frame.add(graficos);
	            frame.add(aclaracion);
	            frame.add(aclaracion2);
	            frame.add(encoding);
	            frame.add(escalaFrecuenciaY);
	            frame.add(sampleRate);
	            frame.add(vacio2);
	            frame.add(bitsChannel);
	            frame.add(vacio3);
	            frame.add(numCanales);
	            frame.add(vacio4);
	            frame.add(bytesFrame);
	            frame.add(vacio5);
	            frame.add(framesSeg);
	            frame.add(vacio6);
	            frame.add(bigEndian);
	            frame.setTitle("Ver Configuraci�n");
	        }
//	        this.add(frame);
	        frame.setSize(500, 300);
	        frame.setVisible(true);
                frame.addWindowListener
                (
                    new WindowAdapter() 
                    {
                        public void windowClosing(WindowEvent e)
                        {
                            frame.dispose();
                        }
                    }
                );
	        
	    }
             public void setFrequencyXaxis(int value)
             {
                this.frequencyXaxis = value;
             }
             public void setFrequencyYaxis(int value)
             {
                this.frequencyYaxis = value;
             }
             public void setEnableZoomControlI(boolean value)
             {
                this.enableZoomControlI = value;
             }
             public void setEnableZoomControlQ(boolean value)
             {
                this.enableZoomControlI = value;
             }
             public void setEnableZoomControlFreq(boolean value)
             {
                this.enableZoomControlI = value;
             }
             public int getFrequencyXaxis()
             {
                 return frequencyXaxis;
             }
             public int getFrequencyYaxis()
             {
                 return frequencyYaxis;
             }
             public static boolean getEnableZoomControlI()
             {
                 return enableZoomControlI;
             }
             public static boolean getEnableZoomControlQ()
             {
                 return enableZoomControlQ;
             }
             public static boolean getEnableZoomControlFreq()
             {
                 return enableZoomControlFreq;
             }
             public static int getAncho()
             {
                 return ancho;
             }
             
             public static float getSampleRate()
             {
                 return sampleRate;
             }
             public static float getFramesSec()
             {
                 return framesSeg;
             }
	

}
