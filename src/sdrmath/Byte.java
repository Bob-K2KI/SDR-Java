package sdrmath;

import java.nio.ByteBuffer;


public class Byte 
{
    public static byte [] long2ByteArray (long value)
    {
        return ByteBuffer.allocate(8).putLong(value).array();
    }

    public static byte [] float2ByteArray (float value)
    {  
        return ByteBuffer.allocate(4).putFloat(value).array();
    }
    
    public static byte [] int2ByteArray (int value)
    {  
        return ByteBuffer.allocate(4).putInt(value).array();
    }
    
    
    public static byte [] short2ByteArray (int value)
    {  
        return ByteBuffer.allocate(2).putInt(value).array();
    }
}
