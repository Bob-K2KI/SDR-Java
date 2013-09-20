/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrwindow;

/**
 *
 * @author guillermo
 */
public class Bartlett implements WindowInterface
{
    public float[] calculateCoeficients(int numSamples)
    {
        float coeficients[] = new float[numSamples];
        for (int i = 0; i<numSamples; i++)
        {
            coeficients[i] = 1-Math.abs(1.0F*(i-((numSamples-1)/2))/((numSamples-1)/2));            
        }
        return coeficients;
    }
    
}
