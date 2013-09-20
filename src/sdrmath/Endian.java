/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdrmath;

/**
 *
 * @author guillermo
 */
import java.nio.ByteOrder;
import sdrjava.MainScreen;

public class Endian 
{
    public  Endian() 
    {
        ByteOrder b = ByteOrder.nativeOrder();
        if (b.equals(ByteOrder.BIG_ENDIAN)) 
        {
            MainScreen.setArea("\n This machine is Big-endian");
        } 
        else 
        {
            MainScreen.setArea("\n This machine is Little-endian");
        }
    }
} 
