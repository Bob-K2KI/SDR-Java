/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrdsp;

import sdrgraph.ProcessValues;
import sdrjava.MainScreen;
import sdrmath.CooleyTuckey;

/**
 *
 * @author guillermo
 */
public class GetFFT 
{
    float salida[];
    ProcessValues process = new ProcessValues();
    
        
        
    public void CalculateFFT(float [][] entrada)
    {          
//        System.out.println(entrada[0].length);
        CooleyTuckey fastFourierTransform = new CooleyTuckey();
        salida = fastFourierTransform.fftMag(entrada);
        process.graphValues(entrada, salida);
    }
    

    
}
