package sdrwindow;


public class WindowContext 
{
    private WindowInterface filter;

    public WindowContext(WindowInterface filter)
    {
        this.filter = filter;
    }

    public float[] getCoefficients(int a)
    {
        return this.filter.calculateCoeficients(a);
    }    
}
