package sdraudio;

import sdrinput.SoundCard;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import static sdrinput.SoundCard.audioFormat;
import sdrjava.ConfigurationBean;
import sdrjava.DialogBean;
import sdrjava.Input;



public class AudioOut
{        
    public void outSound(byte[] samples )
    {
     SoundCard.outSon(samples);
    }
}


	