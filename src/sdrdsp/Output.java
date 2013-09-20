package sdrdsp;

import javax.swing.SwingWorker;
import static sdrjava.MainScreen.getMode;
import sdrdemod.OutputContext;
import sdrdemod.OutputInterface;



public class Output extends SwingWorker
{
    public static  Class outputClass;
    sdrdemod.OutputContext context;
    
    
    @Override
    public Void doInBackground()
    {
        try
        {
            outputClass = Class.forName("sdrdemod."+getMode());            
            context = new OutputContext((OutputInterface)outputClass.newInstance());
        }
        catch (Exception e){System.out.println ("Error 4"+e.toString());}
        return null;
    }
    public void demodSignal(short[] samples)
    {
            context.demodSamples(samples);   
    }
    
    public static Class getInstance()
   {
       return(outputClass);
   }
    
}
