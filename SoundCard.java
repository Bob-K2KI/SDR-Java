package sdrinput;

import sdrdsp.GetSamples;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.SwingWorker;
import sdrinput.InputInterface;
import sdrinput.InputInterface;
import sdrjava.ConfigurationBean;
import sdrjava.DialogBean;
import sdrjava.MainScreen;
import sdrmath.Endian;



public class SoundCard implements InputInterface 
{
    byte data[];
    private boolean escribirWAV;
    private boolean escribirMuestras;
    private boolean salida;
    public static TargetDataLine line;
    public static SourceDataLine outline;
    private AudioInputStream inputStream;
    private File file;
    private String outputFilename;
    private javax.sound.sampled.AudioFileFormat.Type targetType;
    GetSamples muestras;
    public boolean cancel = false;
    //    FileWriter linea_tx;
    //    ByteArrayOutputStream out;
    //    File f;    
    //    public static int canales[][];
    public static AudioFormat audioFormat;
    public static javax.sound.sampled.DataLine.Info info;
    Mixer mixer;

    public SoundCard()
    {
        escribirWAV = false;
        escribirMuestras = false;
        salida = false;
        outputFilename = "Audio.wav";
        targetType = javax.sound.sampled.AudioFileFormat.Type.WAVE;
        mixer = null;
        
        try
        {
            muestras = new GetSamples();
            escribirWAV = MainScreen.getGrabarWav();
            escribirMuestras = MainScreen.getGrabarMuestras();
            audioFormat = new AudioFormat(ConfigurationBean.encoding, ConfigurationBean.sampleRate, ConfigurationBean.bitsChannel, ConfigurationBean.numCanales, ConfigurationBean.bytesFrame, ConfigurationBean.framesSeg, ConfigurationBean.bigEndian);
            info = new javax.sound.sampled.DataLine.Info(TargetDataLine.class, audioFormat);
            line = (TargetDataLine)AudioSystem.getLine(info);
            MainScreen.setArea("\nLineInfo: "+AudioSystem.getLine(info).toString());
            line.open(audioFormat);
            inputStream = new AudioInputStream(line);
            file = new File(outputFilename);
            DialogBean bien = new DialogBean("Sound card started");
            Endian b = new Endian(); 
        }
        catch(Exception e)
        {
            MainScreen.setArea((new StringBuilder()).append("\n").append(e.toString()).toString());
            DialogBean fallo = new DialogBean("ERROR Cant start sound card");
        }
        captureAudio();
    }

  
    public void getInput()
    {
    
        try
        {
            //line.start();
//            System.out.println("Recibiendo muestras");
            MainScreen.setArea((new StringBuilder()).append("\n").append(line.getFormat().toString()).toString());
            if(escribirWAV)
                AudioSystem.write(inputStream, targetType, file);
            if(escribirMuestras)
            {         
                
                salida = true;
                line.start();
                data = new byte[line.getBufferSize() / 5];
                if(ConfigurationBean.debugL3) MainScreen.mensajeArea.append("Largo de data: "+data.length);
//                canales = new int[2][data.length / 4];
                MainScreen.setArea("\n Buffer size: "+Integer.toString(line.getBufferSize()));
//                out = new ByteArrayOutputStream();
                while(salida) 
                {
                    
                    int numBytesRead = line.read(data, 0, data.length);
//                    System.out.println("Longitud"+data.length);
//                    muestras.receiveManySamples(data, false);
                    muestras.receiveManySamples(data, false);
//                    out.write(data, 0, numBytesRead);
                }
            }
        }
        catch(Exception e)
        {
            MainScreen.setArea("Error 3"+e.getMessage());
        }
        
    }

    public void cancelSamples()
    {
        try
        {
            if(escribirMuestras)
            {
                salida = false;
                line.stop();
//                linea_tx.close();
//                out.close();
            }
            cancel = false;
            line.close();
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
public static void outSon(byte[] samples)
{
    try
    {
//        final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat, 1);
//        final SourceDataLine soundLine = (SourceDataLine) AudioSystem.getLine(info);
//        soundLine.open(audioFormat);
//        soundLine.start();
        DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, audioFormat);
//        outline = (SourceDataLine)AudioSystem.getLine(info2);
//        outline.open(audioFormat);
//        outline.start();
 //       System.out.println(samples[0]+" "+samples[1]);
//        int nBytesWritten = outline.write(samples, 0, samples.length);
    }
    catch(Exception e){System.out.println("Error 15"+e.toString());}
}
   
private void captureAudio(){
    try
    {
      Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
      System.out.println("Available mixers:");
      for(int cnt = 0; cnt < mixerInfo.length;cnt++)
      {
      	System.out.println(cnt+"= "+mixerInfo[cnt].
      	getName());
      }//end for loop
    }
      catch(Exception e){}

}
 

}
