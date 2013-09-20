package sdrgraph;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeListener;





class ZoomPanel extends JPanel
 {
	   public static JSlider xSlider,ySlider; // Manejo de escala
           public static float result [] = new float[2];

	   ZoomPanel(boolean orientation)
	   {
	    setLayout(new GridLayout(1,2));
	    SliderListener auditor = new SliderListener();
	    if (orientation)
            {
                xSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
                ySlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
            }
            else
            {
                xSlider = new JSlider(JSlider.VERTICAL, 1, 100, 1);
                ySlider = new JSlider(JSlider.VERTICAL, 1, 100, 1);
            }
	    xSlider.addChangeListener((ChangeListener) auditor);
	    add(xSlider);
	    ySlider.addChangeListener(auditor);
	    add(ySlider);

	    //xSlider.setLabelTable(xSlider.createStandardLabels(20));
	    //xSlider.setMajorTickSpacing(200);
	    xSlider.setMinorTickSpacing(20);
	    xSlider.setPaintTicks(true);
	    xSlider.setPaintLabels(true);

	    //ySlider.setMajorTickSpacing(200);
	    ySlider.setMinorTickSpacing(20);
	    ySlider.setPaintTicks(true);
	    ySlider.setPaintLabels(true);
	   }//
            public static float[] ajusteEscala()
            { 
                
                result[0]=((float)xSlider.getValue());
                result[1]=((float)ySlider.getValue());
                // se ejecuta si se 'oyó' algún cambio en algún Slider
//   	 	GraphPanel.escalaX =(int) xSlider.getValue();    	                    
//                GraphPanel.escalaY =(int) ySlider.getValue();
//                System.out.println(((float)xSlider.getValue()/100));
//                GraphPanel.repaint(); 
                return (result);
            }//

	   class SliderListener implements ChangeListener
	   {
	     public void stateChanged(ChangeEvent e)
	     {
	       JSlider source = (JSlider)e.getSource();
               
               sdrdsp.GetSamples.result = ajusteEscala();
             }

           }
}
