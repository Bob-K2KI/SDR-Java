package sdrdsp;

import sdrfilter.FilterSpecification;
import sdrfilter.ParksMcClellan;
import sdrjava.ConfigurationBean;
import sdrjava.MainScreen;

public class FilterSamples 
{
    public float[] order;
    public FilterSamples()
    {
        try
        {    
            ParksMcClellan filter = new ParksMcClellan();
            FilterSpecification specification = new FilterSpecification();
            order = ParksMcClellan.design(specification);
            if (ConfigurationBean.debugL3) MainScreen.mensajeArea.append("Orden = "+order.length);
        }
        catch(Exception e){System.out.println("Error en FilterSamples");}
    }
    
}
