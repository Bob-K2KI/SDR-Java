package sdrgraph;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeListener;





class CorrectionPanel extends JPanel
 {
	   public static JSlider corrISlider,corrQSlider, corrPhaseSlider;
           public static float result [] = new float[3];

	   CorrectionPanel(boolean orientation)
	   {
	    setLayout(new GridLayout(1,2));
	    SliderListener auditor = new SliderListener();
	    if (orientation)
            {
                corrISlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 1);
                corrQSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 1);
                corrPhaseSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
            }
            else
            {
                corrISlider = new JSlider(JSlider.VERTICAL, -100, 100, 1);
                corrQSlider = new JSlider(JSlider.VERTICAL, -100, 100, 1);
                corrPhaseSlider = new JSlider(JSlider.VERTICAL, 1, 10, 1);
            }
	    corrISlider.addChangeListener((ChangeListener) auditor);
	    add(corrISlider);
	    corrQSlider.addChangeListener(auditor);
	    add(corrQSlider);
            corrPhaseSlider.addChangeListener(auditor);
	    add(corrPhaseSlider);

	    //xSlider.setLabelTable(xSlider.createStandardLabels(20));
	    //xSlider.setMajorTickSpacing(200);
	    corrISlider.setMinorTickSpacing(20);
	    corrISlider.setPaintTicks(true);
	    corrISlider.setPaintLabels(true);

	    //ySlider.setMajorTickSpacing(200);
	    corrQSlider.setMinorTickSpacing(20);
	    corrQSlider.setPaintTicks(true);
	    corrQSlider.setPaintLabels(true);
            
            //ySlider.setMajorTickSpacing(200);
	    corrPhaseSlider.setMinorTickSpacing(20);
	    corrPhaseSlider.setPaintTicks(true);
	    corrPhaseSlider.setPaintLabels(true);
	   }//
            public static float[] correction()
            { 
                
                result[0]=((float)corrISlider.getValue());
                result[1]=((float)corrQSlider.getValue());
                result[2]=((float)(corrPhaseSlider.getValue()/10));
                return (result);
            }

	   class SliderListener implements ChangeListener
	   {
	     public void stateChanged(ChangeEvent e)
	     {
	       JSlider source = (JSlider)e.getSource();
               
               sdrdsp.GetSamples.result = correction();
             }

           }
}
