
package sdrdemod;

import sdrjava.ConfigurationBean;
import sdrjava.DialogBean;
import sdrjava.MainScreen;
import static sdrjava.MainScreen.setArea;

/**
 *
 * @author guillermo
 */
public class OutputContext 
{
     private OutputInterface output;

    public OutputContext(OutputInterface output)
    {
            this.output = output;
    }

    public void demodSamples(short[] samples)
    {
        try
        {
            output.demodSamples(samples);
        }
        catch(Exception e){System.out.println("Error en OutputContext "+e.toString());}
    }   
    
    
    
   
    
}
