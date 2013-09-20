package sdrgraph;


import javax.swing.event.*;
import java.awt.event.*;



public class EventHandler implements ActionListener
{
  public void actionPerformed (ActionEvent evt)
  {
    Object source = evt.getSource ();
    // si se presiona el bot�n o se da 'enter' en alg�n campo de texto
//    if ( source == BtnGraficar || source == Tffun)
//    {
//      ZG.repaint();
//    }
  }
}
