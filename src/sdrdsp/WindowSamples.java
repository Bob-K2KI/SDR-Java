package sdrdsp;



import sdrmath.CooleyTuckey;
import sdrjava.MainScreen;


/**
 *
 * @author guillermo
 */
public class WindowSamples 
{
    
    float coeficientes[];
    
    
    /**
     *
     * @param entrada
     * @param length
     */
    public float[][] ApplyWindow (float entrada[][], int length)
    {
        try
        {
        coeficientes = new float [length / 2];
        Class windowClass;
        windowClass = Class.forName("sdrwindow."+MainScreen.getWindow());
        sdrwindow.WindowContext context;
        context = new sdrwindow.WindowContext((sdrwindow.WindowInterface)windowClass.newInstance());
        float[] result;
        result = context.getCoefficients(length/2);
        
        
        for(int i=0; i<length/2; i++)
        {
            entrada[0][i]= entrada[0][i]* result[i];
            entrada[1][i]= entrada[1][i]* result[i];
        }
        
        }
        catch(Exception e){MainScreen.setArea(e.toString());}
        return(entrada);
    }
    
    
    
    
}
