/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrinput;

import sdrjava.ConfigurationBean;
import sdrjava.DialogBean;
import sdrjava.MainScreen;
import static sdrjava.MainScreen.setArea;

/**
 *
 * @author guillermo
 */
public class InputContext 
{
     private InputInterface input;

    public InputContext(InputInterface input)
    {
        this.input = input;
    }

    public Void getSamples()
    {
        try
            {
//                System.out.println("Estoy en Context");
                input.getInput();
                
            }
            catch(Exception e)
            {
                MainScreen.mensajeArea.setText((new StringBuilder()).append("\r\nProblemas con el InputContext\n").append(e.toString()).toString());
                System.exit(1);
            }
        return null;
        
    }   
    public void cancelSamples()
    {
        try
        {
            if(ConfigurationBean.debugL1)
                setArea("\r\nStop pressed");
            input.cancelSamples();
        }
        catch(Exception e)
        {
            setArea("\r\nNothing receiving \n");
        }
    }
    
}
