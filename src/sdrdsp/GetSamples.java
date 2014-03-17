package sdrdsp;

import sdrfilter.Filter;
import sdrjava.ConfigurationBean;
import sdrjava.Input;
import sdrjava.MainScreen;
import sdrdemod.AM;
import sdrjava.Graphs;




public class GetSamples
{
    float temp[];
    public static float[] result = new float[2];
    float entrada[][];
    int indice;
    float [][]espectro;
    Filter filterI, filterQ;
    FilterSamples muestrasFiltro;
    public float[] coefs;
    Output output;
    
    public GetSamples()
    {
        try
        {      
            output = new Output();
            result[0] = 0.0f; 
            result[1] = 0.0f;
            indice = 0;
        
            espectro = new float [2][((int)ConfigurationBean.getFramesSec())/10];
            muestrasFiltro = new FilterSamples();
        
            coefs = (float[])muestrasFiltro.order.clone();
       
            filterI = new Filter(coefs);
            filterQ = new Filter(coefs);
        }
        catch(Exception e){System.out.println("Error Clase GetSamples - Constructor");}
    }
    
    
    
    public void receiveManySamples(byte manySamples[], boolean bigEndian)
    {
        try
        {
            byte[] temp = new byte[4];
        
        
            for (int i=0; i<manySamples.length; i=i+4 )
            {
                temp[0]= manySamples[i];
                temp[1]= manySamples[i+1];
                temp[2]= manySamples[i+2];
                temp[3]= manySamples[i+3];
                Convert (temp, bigEndian);
            }
        }
        catch(Exception e){System.out.println("Error Clase GetSamples - Funcion receiveManySamples");} 
    }
    
    

    public void Convert(byte valor[], boolean bigEndian)
    {
        try
        {
            int magIzq = 0;
            int magDer = 0;
            short temp[] = new short[valor.length / 2]; 
            short temp2[] = new short[valor.length / 2];  
            GetFFT getFFT = new GetFFT();
            WindowSamples windowSamples = new WindowSamples();
            AM am = new AM();
            if(bigEndian)
            {
                for(int j = 0; j < valor.length; j += 2)
                {
                    short a = (short)((valor[j] & 0xff) << 8);
                    short b = (short)(valor[j + 1] & 0xff);
                    temp[j / 2] = (short)(a | b);
                }

            } 
            else
            {
                for(int j = 0; j < 3; j += 2)
                {
                    short a = (short)((valor[j + 1] & 0xff) << 8);
                    short b = (short)(valor[j] & 0xff);
                    temp[j / 2] = (short)(a | b);
                }
            

            }
        
        //System.out.println(result[0]+"  "+result[1]);
            if(MainScreen.filter.isSelected())
            {
                espectro[0][indice] = filterI.getOutputSample(temp[0]);
                espectro[1][indice] = filterQ.getOutputSample(temp[1]);
                temp2[0]=(short)espectro[0][indice];
                temp2[1]=(short)espectro[1][indice];
            }
            else
            {
                espectro[0][indice] = (temp[0]+result[0]);
                espectro[1][indice] = temp[1]+result[1]+(temp[0])*result[1];
                temp2[0]=temp[0];
                temp2[1]=temp[1];
            }
               
            output.doInBackground();
            output.demodSignal(temp2);
            magIzq += Math.abs(temp2[0]);
            magDer += Math.abs(temp2[1]);
            indice ++;
              
            if(indice >= ((int)ConfigurationBean.getFramesSec())/10)
            {
                 
                MainScreen.sMeterIzq.setCurrentAmount(magIzq); // espectro[0].length);
                MainScreen.sMeterDer.setCurrentAmount(magDer); // espectro[0].length);
                  
                getFFT.CalculateFFT(windowSamples.ApplyWindow(espectro, 2*espectro[0].length));
                indice = 0;
            }
        
        }

        catch(Exception e)
        {
            System.out.println("Error Clase GetSamples - Funcion Convert"+ e.toString());
            System.out.println("\nLargo de valor: "+valor.length);
        }
    }
}
