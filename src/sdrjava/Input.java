package sdrjava;

import javax.swing.SwingWorker;
import sdrdsp.FilterSamples;
import sdrinput.InputContext;
import sdrinput.InputInterface;
import static sdrjava.MainScreen.getInput;
import sdrfilter.*;



public class Input extends SwingWorker
{
    public static  Class inputClass;
    sdrinput.InputContext context;
    
    
    @Override
    public Void doInBackground()
    {
        try
        {
           
//            System.out.println(coefs.length);
            inputClass = Class.forName("sdrinput."+getInput());            
            context = new InputContext((InputInterface)inputClass.newInstance());
            context.getSamples();
        }
        catch (Exception e){System.out.println (e.toString());}
        return null;
    }
    public void cancelInput()
    {
        context.cancelSamples();
    }
    
    public static Class getInstance()
   {
       return(inputClass);
   }
    
}
