package sdrjava;

import java.awt.*;
import java.awt.event.*;



public class DialogBean extends Frame
    implements ActionListener
{

    public DialogBean(String mensaje)
    {
        final Dialog miDialogo = new Dialog(this);
        miDialogo.setTitle("Comunicaci\363n importante - cerrar esta ventana");
        miDialogo.setLocation(400, 300);
        Label label = new Label(mensaje);
        setFont(new Font("Helvetica", 0, 20));
        miDialogo.add(label);
        miDialogo.pack();
        miDialogo.show();
        miDialogo.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e)
            {
                miDialogo.dispose();
            }


        }
);
        
        
        
        
//        jButtonexit.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent e) {
//                System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
//            frame.dispose();
//            }
//        });
        
        
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        MainScreen.setArea("Estoy en el actionperformed");
    }
}
