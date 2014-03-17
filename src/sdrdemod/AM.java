package sdrdemod;

import sdraudio.AudioOut;
import sdrinput.SoundCard;
import sdrjava.MainScreen;


public class AM implements OutputInterface
{
    private float ratio = 0.000001f;
    float outFloat, mean;
    public static float outFloatArray[]=new float[2048];
    float outInt;
    byte outByte[];
    int count;
    public static int j, h;
    AudioOut audio;
    public static byte outData[]= new byte[8192];
    

   
    public AM()
    {
        try
        {
            count = 1;
//            i=0;
            mean = 0;
            audio = new AudioOut();
        }
        catch(Exception e){System.out.println("Error en la clase AM constructor"+e.toString());}
    }
    @Override
    public void demodSamples(short[] sample)
    {
        try
        {
            outInt = (float)(Math.sqrt((Math.pow(sample[0],2)+Math.pow(sample[1],2))));
            outFloatArray[j]=outInt;
            j= j+1;
            if (j>=2048)
            {
                j=0;
                h=0;
                outFloatArray = mean(outFloatArray);
                for (int i=0;i<outFloatArray.length;i=i+1)
                {
                    outByte = sdrmath.Byte.float2ByteArray(outFloatArray[i]);
                    outData[h] = outByte[1];
                    outData[h+1] = outByte[0];
                    outData[h+2] = outByte[1];
                    outData[h+3] = outByte[0];
                    h=h+4;
                }
                if(MainScreen.getSound())
                {
                } 
                else 
                {
                    SoundCard.startSound(outData);
                }
            }
        }        
        catch(Exception e){System.out.println("Error en la clase AM demodSamples()"+e.toString());}        
    }
    
   private float[] mean(float[] outFloat)
   {
       for(int i=0; i<outFloat.length;i++)
       {
           mean = mean + outFloat[i];
       }
       mean = mean/outFloat.length;
       for(int k=0; k<outFloat.length;k++)
       {
           outFloat[k] = outFloat[k] - mean;
       }
       
       return outFloat;
   }
   
    
}
