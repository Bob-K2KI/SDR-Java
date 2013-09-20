/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrwindow;

/**
 *
 * @author guillermo
 */
public class Triangular implements WindowInterface 
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
//        System.out.println("Estoy en triangular");
        for (int i = 0; i<numSamples; i++)
        {
            coeficients[i] = 1-Math.abs(1.0F*(i-((numSamples-1)/2))/((numSamples+1)/2));  
//            System.out.println(1.0*(i-((numSamples-1)/2))/((numSamples+1)/2));
//            System.out.println(coeficients[i]);
        }
        
        return coeficients;
    }
    
}
