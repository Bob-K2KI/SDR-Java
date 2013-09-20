package sdrjava;

import javax.swing.SwingUtilities;



public class SdrJava 
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                MainScreen pantalla = new MainScreen();
            }
        });
    }
}
