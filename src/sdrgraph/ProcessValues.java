/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrgraph;

import sdrjava.MainScreen;

/**
 *
 * @author guillermo
 */
public class ProcessValues 
{
    public void graphValues(float [][] entrada, float salida[])
    {
        int ancho = sdrjava.ConfigurationBean.getAncho();
        int step = 0;
        float outGraphY[] = new float[ancho-20]; 
//        System.out.println(entrada[0].length);
        try
        {
            if (salida.length < ancho-20)
            {
                for (int i=1; i<=salida.length-1; i++)
                {
                    entrada[0][i-1]=entrada[0][i-1];
                    entrada[1][i-1]=entrada[1][i-1];
                    step = (int)(i*(ancho-20))/salida.length;
                    outGraphY[step] = (float)(10.0*Math.log10(salida[i-1]*salida[i-1]));
                }
                for(int j = 0; j<outGraphY.length-1; j++)
                { 
                    if (outGraphY[j] == 0&& j>0)
                        outGraphY[j] = ((outGraphY[j-1]+outGraphY[j+1]/2));
                }
            }
            if (salida.length == ancho-20)
            {
                for (int i = 0; i<salida.length; i++)
                {
                    outGraphY[i] = (float) (10.0*Math.log10(salida[i]));
                }
            }
            if (salida.length > ancho-20)
            {
                 for (int i=1; i<=salida.length-1; i++)
                {
                    entrada[0][i-1]=entrada[0][i-1]/50;
                    entrada[1][i-1]=entrada[1][i-1]/50;
                }
                int j = salida.length/(ancho-20);
                for (int i = 0; i < ancho-20; i=i+1)
                {
                    outGraphY[i] = (float)(Math.log10(salida[i*j]))*50.0f;
                }
            }
        }
        catch(Exception e){System.out.println(e+"yyyyy");}
        MainScreen.graficos.setValores(entrada, outGraphY);
            
    }
    
}
