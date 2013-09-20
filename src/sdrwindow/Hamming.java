/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrwindow;

/**
 *
 * @author guillermo
 */
public class Hamming implements WindowInterface 
{
    /**
     *
     * @param numSamples
     * @return
     */
    @Override
    public float[] calculateCoeficients(int N)
    {
      float coeficients[] = new float[N];
      double a = 0.54, b=1-a;
      
      for (int n = 0; n<N; n++)
      {
      coeficients[n] = (float) (a-b*Math.cos((2*Math.PI*n)/(N-1)));
//      System.out.println(coeficients[n]);
      }
//      System.out.println("Estoy en Hamming");
      return coeficients;
    }    
    
}
