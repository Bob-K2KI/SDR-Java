/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrwindow;

/**
 *
 * @author guillermo
 */
public class Cosine implements WindowInterface
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
        for (int i = 0; i<numSamples; i++)
        {
            coeficients[i] = (float) Math.cos(((Math.PI*i)/(numSamples-1))-(Math.PI/2)); 
//            System.out.println(coeficients[i]);
        }
//        System.out.println("Estoy en Cosine");
        return coeficients;
    }    
    
}
