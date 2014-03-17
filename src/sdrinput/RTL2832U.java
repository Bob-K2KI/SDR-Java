/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrinput;

//import javax.swing.SwingWorker;
import sdrdsp.GetSamples;
//import sdrjava.ConfigurationBean;
//import sdrjava.MainScreen;

/**
 *
 * @author guillermo
 */
public class RTL2832U implements InputInterface
{
    private byte data[];
    private boolean canceled;
    private GetSamples muestras;
    public RTL2832U()
    {
        muestras = new GetSamples();
        canceled = false;
    }
 
    @Override
    public void getInput()
    {
//        System.out.println(ConfigurationBean.getFramesSec()*2/5);
//        data = new byte[((int)ConfigurationBean.getFramesSec())*2/5];
        data = new byte[4];
//        MainScreen.setArea("Noise started");
        while (!canceled)
        {
//            for (int i = 0; i<((int)ConfigurationBean.getFramesSec())*2/5;i++)
            for (int i = 0; i<4;i++)
            {
                data[i] = (byte)((2 * Math.random()*10)-10.0f);              
            }
            muestras.Convert(data, false);
        }
    }
 
    @Override
    public void cancelSamples()
    {
        canceled = true;
//        MainScreen.setArea("Noise canceled");
    }
    

	
}
