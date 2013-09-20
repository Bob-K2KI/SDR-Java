package sdrfilter;

import sdrjava.ConfigurationBean;
import sdrjava.MainScreen;
import sdrjava.*;
import java.lang.*;


public class FilterSpecification
{
    private float mainFrequency = (Float.parseFloat(MainScreen.getTunningFreqField())); 
    public String shape = MainScreen.getFilterType();
    public float f1 = 2000, f2 = 3000f, ft = 500, fN = ConfigurationBean.getSampleRate()/2;
    
//    public float f1 = (mainFrequency-Float.parseFloat(MainScreen.getFilterBWField()));
//    public float f2 = (mainFrequency+Float.parseFloat(MainScreen.getFilterBWField()));
    
    
    //fN = Frecuencia de Niquist fs/2
    //ft = ancho de la banda de transición 
    //f1 = frecuencia central
    //f2 = extremo de frecuncia
    public float dBatten = 60f;
    public float dBripple = 0.5f;
    public int order = -1; // -1 causes estimation
    public int mod = 0; // >0 causes order % mod == 0
}