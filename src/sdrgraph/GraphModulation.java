/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrgraph;

/**
 *
 * @author guillermo
 */
public class GraphModulation 
{
    public GraphModulation(float[][] entrada)
    {
        float[] posModI= new float[entrada[0].length];
        float[] negModI= new float[entrada[0].length];
        float[] posModQ= new float[entrada[0].length];
        float[] negModQ= new float[entrada[0].length];
        for (int i = 0; i<entrada[0].length;i++)
        {
            if (entrada[0][i]>0)posModI[i]= entrada[0][i];
            else negModI[i]= entrada[0][i];
            if (entrada[1][i]>0)posModQ[i]= entrada[1][i];
            else negModI[i]= entrada[1][i];
        }
        
    }
    
}
