package sdrinput;

import sdrdsp.GetSamples;
import java.io.*;

import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import javax.swing.SwingWorker;
import sdrinput.InputInterface;
import sdrinput.InputInterface;
//import static sdrinput.SoundCard.line;
import sdrjava.ConfigurationBean;
import sdrjava.DialogBean;
import sdrjava.MainScreen;
import sdrmath.Endian;



public class SoundCard implements InputInterface 
{
    static byte data[], audioData[];
    private boolean escribirWAV;
    private boolean escribirMuestras;
    
    public static TargetDataLine lineIn;
    public static SourceDataLine lineOut;
    private AudioInputStream inputStream;
    private static AudioInputStream  outputStream;
    private File file;
    private String outputFilename;
    private javax.sound.sampled.AudioFileFormat.Type targetType;
    
    public boolean cancel = false;
    public static AudioFormat audioFormat;
    public static javax.sound.sampled.DataLine.Info info;
    Mixer mixerIn, mixerOut;
    Thread captureThread;
    private static Thread playThread;

    public SoundCard()
    {
        escribirWAV = false;
        escribirMuestras = false;
//        salida = false;
        outputFilename = "Audio.wav";
        targetType = javax.sound.sampled.AudioFileFormat.Type.WAVE;
//        mixerIn = null;
        Mixer mixerIn = AudioSystem.getMixer(captureAudio()[4]);
       
        
        
        try
        {
           
//            Mixer mixerOut = AudioSystem.getMixer(captureAudio()[1]);
            escribirWAV = MainScreen.getGrabarWav();
            escribirMuestras = MainScreen.getGrabarMuestras();
            audioFormat = new AudioFormat(ConfigurationBean.encoding, ConfigurationBean.sampleRate, ConfigurationBean.bitsChannel, ConfigurationBean.numCanales, ConfigurationBean.bytesFrame, ConfigurationBean.framesSeg, ConfigurationBean.bigEndian);
            info = new javax.sound.sampled.DataLine.Info(TargetDataLine.class, audioFormat);
//            lineIn = (TargetDataLine)AudioSystem.getLine(info);
            lineIn= (TargetDataLine)mixerIn.getLine(info);
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,audioFormat);
            lineOut = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
//            lineOut = (SourceDataLine)AudioSystem.getLine(info);
//            lineOut= (SourceDataLine)mixerOut.getLine(info);
            MainScreen.setArea("\nLineInfo: "+AudioSystem.getLine(info).toString());
            lineIn.open(audioFormat);
            inputStream = new AudioInputStream(lineIn);
            file = new File(outputFilename);
            DialogBean bien = new DialogBean("Sound card started");
            Endian b = new Endian(); 
            captureThread = new CaptureThread();
            playThread = new PlayThread();
                  
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("\n").append(e.toString()).toString());
            MainScreen.setArea((new StringBuilder()).append("\n").append(e.toString()).toString());
            DialogBean fallo = new DialogBean("ERROR Cant start sound card");
        }
        ;
    }

  
    public void getInput()
    {
    
     captureThread.start();
        
       
        
    }

    public void cancelSamples()
    {
        try
        {
            if(escribirMuestras)
            {
                
                lineIn.stop();
//                linea_tx.close();
//                out.close();
            }
            cancel = false;
            lineIn.close();
            MainScreen.sMeterIzq.setCurrentAmount(5);
            MainScreen.sMeterDer.setCurrentAmount(5);
            DialogBean cancelado = new DialogBean("Sound card stoped");
            MainScreen.setArea("Sound card stopped");
        }
        catch(Exception e)
        {
            MainScreen.setArea("Error 10"+e.getMessage());
        }
    }
public static void startSound(byte[] audioData)
{
    try
    {
        SoundCard.audioData = audioData;
        
      lineOut.open(audioFormat);
      lineOut.start();
      
      if(playThread.getState().toString()== "NEW") {playThread.start();}
      playThread.run();
/*      int cnt;
      while((cnt = outputStream.read(tempBuffer, 0, tempBuffer.length)) != -1)
      {
        if(cnt > 0)
        {
          lineOut.write(tempBuffer,0,cnt);
        }
      }//end while*/
//      lineOut.drain();
//      lineOut.close();
    } 
    catch (Exception e) 
    {
      System.out.println("Error clase SoundCard - Funcion startSound"+e.toString());
      System.exit(0);
    }//end catch
  }//end playAudio

   
private Mixer.Info[] captureAudio()
{
    Mixer.Info[] mixerInfo= AudioSystem.getMixerInfo();;
    try
    {
      System.out.println("Available mixers:");
      for(int cnt = 0; cnt < mixerInfo.length;cnt++)
      {
      	System.out.println(cnt+"= "+mixerInfo[cnt].
      	getName());
      }//end for loop
    }
      catch(Exception e){ MainScreen.setArea("Error clase SoundCarrd - Funcion captureAudio"+e.getMessage());}
    return mixerInfo;

}
 
class CaptureThread extends Thread
{
    
    private boolean salida;
    public void run()
    {
        
    
        GetSamples muestras;
        
        muestras = new GetSamples();
       try
        {
            salida = false;
            //line.start();
//            System.out.println("Recibiendo muestras");
            MainScreen.setArea((new StringBuilder()).append("\n").append(lineIn.getFormat().toString()).toString());
            if(escribirWAV)
                AudioSystem.write(inputStream, targetType, file);
            if(escribirMuestras)
            {         
                
                salida = true;
                lineIn.start();
                data = new byte[lineIn.getBufferSize() / 5];
                if(ConfigurationBean.debugL3) MainScreen.mensajeArea.append("Largo de data: "+data.length);
//                canales = new int[2][data.length / 4];
                MainScreen.setArea("\n Buffer size: "+Integer.toString(lineIn.getBufferSize()));
//                out = new ByteArrayOutputStream();
                while(salida) 
                {
                
                    int numBytesRead = lineIn.read(data, 0, data.length);
//                    System.out.println("Longitud"+data.length);
//                    muestras.receiveManySamples(data, false);
//                        
                    muestras.receiveManySamples(data, false);
//                    out.write(data, 0, numBytesRead);
//                    System.out.println("Estoy acá");
                }
            }
        }
         catch(Exception e)
        {
            MainScreen.setArea("Error clase CaptureThread - Archivo SoundCard.java - Funcion run "+e.getMessage()+"3435");
            System.out.println("Error clase CaptureThread - Archivo SoundCard.java - Funcion run "+e.getMessage()+"3435");
        }
}
}
class PlayThread extends Thread
{
    

  public void run()
  {
      byte tempBuffer[] = new byte[audioData.length];
        try
        {
            int cnt;
            InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);    
            outputStream = new AudioInputStream(byteArrayInputStream, audioFormat, audioData.length/audioFormat. getFrameSize());
            lineOut.open(audioFormat);
            lineOut.start();

      //Keep looping until the input read method
      // returns -1 for empty stream.
            while((cnt = outputStream.read(tempBuffer, 0, tempBuffer.length)) != -1)
            {
//                System.out.println("Estoy adentro del While");
                if(cnt > 0)
                {
          //Write data to the internal buffer of
          // the data line where it will be
          // delivered to the speaker.
//                System.out.println(tempBuffer);
                    lineOut.write(tempBuffer,0,cnt);
                }//end if
            }//end while
      //Block and wait for internal buffer of the
      // data line to empty.
//      lineOut.drain();
//      lineOut.close();
        }
        catch (Exception e) 
        {
            System.out.println("Error clase PlayThread -  Archivo SoundCard.java - Funcion run"+e.toString());
            MainScreen.setArea("Error clase PlayThread -  Archivo SoundCard.java - Funcion run");
            System.exit(0);
        }//end catch
    }//end run
}//end inner class PlayThread
//=============================================//
}
