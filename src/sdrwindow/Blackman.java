/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrwindow;

import java.math.*;

/**
 *
 * @author guillermo
 */
public class Blackman implements WindowInterface
{
    /**
     *
     * @param numSamples
     * @return
     */
    @Override
    public float[] calculateCoeficients(int numSamples)
    {
        double a=0.16, a0=0.42659, a1=0.49656, a2=0.076879;
        float coeficients[] = new float[numSamples];
        for (int i = 0; i<numSamples; i++)
        {
            coeficients[i] = (float) (a0-(a1*Math.cos((2*Math.PI*i)/(numSamples-1)))+a2*Math.cos((4*Math.PI*i)/(numSamples-1)));            
//            System.out.println(coeficients[i]);
        }
//        System.out.println("Estoy en Blackman");
        return coeficients;
    }    
}
