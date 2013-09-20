package sdrjava;

import java.awt.*;

public class SMeterBean extends Component
{

    public SMeterBean()
    {
        this(Color.lightGray);
    }

    public SMeterBean(Color gaugeColor)
    {
        current = 0;
        total = 20000;
        Height = 5;
        Width = 5;
        current = 80;
        setBackground(gaugeColor);
    }

    @Override
    public void paint(Graphics g)
    {
        int barWidthGreen = (int)(((float)current / (float)total) * (float)getSize().width);
        g.setColor(getBackground());
        g.fill3DRect(0, 0, barWidthGreen, getSize().height - 2, true);
    }

    public void run()
    {
        repaint();
    }

    public void setCurrentAmount(int Amount)
    {
        current = Amount;
        if(current < 0)
            setBackground(Color.lightGray);
        if(current > total)
            current = total;
        if(current < 0)
            current = 0;
        repaint();
    }

    public int getCurrentAmount()
    {
        return current;
    }

    int current;
    int total;
    int Height;
    int Width;
}
