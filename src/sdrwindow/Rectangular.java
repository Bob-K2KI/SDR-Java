/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrwindow;

/**
 *
 * @author guillermo
 */
public class Rectangular implements WindowInterface
{
    public float[] calculateCoeficients(int numSamples)
    {
        float coeficients[] = new float[numSamples];
        for (int i = 0; i<numSamples; i++)
        {
            coeficients[i] = 1;   
        }
//        System.out.println("Estoy en Rectangular");
        return coeficients;
    }
    
}
