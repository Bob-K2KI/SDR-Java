/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrwindow;

/**
 *
 * @author guillermo
 */
public class BlackmanNuttall implements WindowInterface 
{
    /**
     *
     * @param numSamples
     * @return
     */
    @Override
    public float[] calculateCoeficients(int numSamples)
    {
        float coeficients[] = new float[numSamples];
        double a0=0.3635819, a1=0.4891775, a2=0.1365995, a3=0.0106411;
        for (int i = 0; i<numSamples; i++)
        {
            coeficients[i] = (float) (a0-a1*Math.cos((2*Math.PI*i)/(numSamples-1))+a2*Math.cos((4*Math.PI*i)/(numSamples-1))-a3*Math.cos((6*Math.PI*i)/(numSamples-1)));            
//            System.out.println(coeficients[i]);
        }
//        System.out.println("Estoy en Blackman-Nuttal");
        return coeficients;
    }    
    
}
