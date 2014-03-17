package sdrjava;

//import sdrinput.*;
//import sdrinput.InputInterface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;



public final class MainScreen extends JFrame implements WindowListener
{    
    public static SMeterBean sMeterIzq;
    public static SMeterBean sMeterDer;
    public static JTextArea mensajeArea;
    public static Graphs graficos;
    private static JRadioButton grabarWAV;
    private static JRadioButton grabarMuestras;
    JRadioButton izqPlusDer;
    JRadioButton derPlusIzq;
    JRadioButton canalIzq;
    JRadioButton canalDer;
    JRadioButton correccionIQ;
    JButton pararButton;
    JButton recibirButton;
    private static JComboBox windowBox, entradaBox,filterTypeBox, modeBox;
    private static JTextField freqLOField;
    private static JTextField tuningFreqField;
    private static JTextField filterBWField;
    private Input input;
    private Font letras = new Font(null, Font.PLAIN, 10);
    public static JCheckBox sound = new JCheckBox();
    public static JCheckBox filter = new JCheckBox();
    JPanel parksMcclellan;
    Border border;
    

    public MainScreen()
    {
        this.parksMcclellan = new JPanel(new GridLayout(2,3));
        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        graficos = new Graphs();
        setTitle("SdrJava Receiver Project - Free Software for Hams (by now !)");
        setDefaultCloseOperation(3);
//        Dimension d1 = new Dimension(150, 20);
        Border border = LineBorder.createGrayLineBorder();
        addWindowListener(this);
        
        windowBox = new JComboBox();
        windowBox.addItem("None");
        windowBox.addItem("Rectangular");
        windowBox.addItem("Triangular");
        windowBox.addItem("Parzen");
        windowBox.addItem("Welch");
        windowBox.addItem("Hamming");
        windowBox.addItem("Hann");
        windowBox.addItem("Cosine");
        windowBox.addItem("Blackman");
        windowBox.addItem("Nuttall");
        windowBox.addItem("BlackmanNuttall");
        windowBox.addItem("FlatTop");
        windowBox.addItem("BlackmanHarris");
        windowBox.addItem("Power of Cosine");
        windowBox.addItem("Gaussian");
        windowBox.addItem("Tukey");
        windowBox.addItem("Plank-Taper");
        windowBox.addItem("DPSS or Slepian");
        windowBox.addItem("Kaiser");
        windowBox.addItem("Dolph–Chebyshev");
        windowBox.addItem("Exponential or Poisson");
        windowBox.addItem("Bartlett–Hann");
        windowBox.addItem("Planck–Bessel");
        windowBox.addItem("Hann–Poisson");
        windowBox.addItem("Lanczos");
        windowBox.addItem("Rife-Vincent");
        windowBox.addItem("Bartlett");
        windowBox.setEditable(false);

        modeBox = new JComboBox();
        modeBox.addItem("AM");
        modeBox.addItem("NFM");
        modeBox.addItem("WFM");
        modeBox.addItem("LSB");
        modeBox.addItem("USB");
        modeBox.addItem("CW");
        modeBox.addItem("Packet Radio");
        modeBox.addItem("ATV");
        modeBox.addItem("TV");
        modeBox.setEditable(false);
        
        entradaBox = new JComboBox();
        entradaBox.addItem("Sound Card");
        entradaBox.addItem("Noise");
        entradaBox.addItem("RTL2832U");
        entradaBox.setEditable(false);
        recibirButton = new JButton("Receive");
        pararButton = new JButton("Stop");
        pararButton.setEnabled(false);
        
        filterTypeBox = new JComboBox();
        filterTypeBox.addItem("BPF");
        filterTypeBox.addItem("NOTCH");
        filterTypeBox.setEditable(false);
        filterTypeBox.setFont(letras);
        
        JLabel graficoLabel = new JLabel();
        graficoLabel.setText("Graph");
        JLabel filterType = new JLabel ("Type");
        JLabel modoMod = new JLabel("Mode");
        JLabel entradaLab = new JLabel("Input");
        JLabel frecuenciaLO = new JLabel("Frequency LO");
        JLabel megaHertz = new JLabel("KHz");
        megaHertz.setFont(letras);
        JLabel fitlterType = new JLabel("Type");
        filterType.setFont(letras);
        JLabel megaHertz2 = new JLabel("KHz");
        JLabel megaHertz3 = new JLabel("KHz");
        JLabel nivelPromedio = new JLabel("AVG Level");
        JLabel nivelIzq = new JLabel("Izq.");
        JLabel nivelDer = new JLabel("Der.");
        JLabel espacio = new JLabel("                              ");
        JLabel tuningFreq = new JLabel("Tuning Frequency");
        JLabel filterBW = new JLabel("Filter BW");
        filterBW.setFont(letras);
        JLabel mute = new JLabel("Mute");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu configMenu = new JMenu("Configuration");
        JMenuItem verConfiguracion = new JMenuItem("View Configuration");
        configMenu.add(verConfiguracion);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(helpMenu);
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem aboutAction = new JMenuItem("About");
        fileMenu.add(exitAction);
        helpMenu.add(aboutAction);
        mensajeArea = new JTextArea(6, 20);
        mensajeArea.setEditable(false);
        mensajeArea.setBorder(border);
        mensajeArea.setText("Messages");
        freqLOField = new JTextField("1000");
        tuningFreqField = new JTextField("1003");
        filterBWField = new JTextField("100");
        filterBWField.setFont(letras);
        JScrollPane scrollingArea = new JScrollPane(mensajeArea);
        scrollingArea.createVerticalScrollBar();
        scrollingArea.setPreferredSize(new Dimension(10, 10));
        scrollingArea.setVisible(true);
        grabarMuestras = new JRadioButton("Save Samples");
        grabarMuestras.setSelected(true);
        grabarWAV = new JRadioButton("Save WAV");
        ButtonGroup grupoBotonesGrabacion = new ButtonGroup();
        grupoBotonesGrabacion.add(grabarWAV);
        grupoBotonesGrabacion.add(grabarMuestras);
        canalIzq = new JRadioButton("I      ");
        canalDer = new JRadioButton("Q     ");
        izqPlusDer = new JRadioButton("I + Q");
        izqPlusDer.setSelected(true);
        derPlusIzq = new JRadioButton("Q + I");
        ButtonGroup grupoIzqDer = new ButtonGroup();
        grupoIzqDer.add(canalIzq);
        grupoIzqDer.add(canalDer);
        grupoIzqDer.add(izqPlusDer);
        grupoIzqDer.add(derPlusIzq);
        correccionIQ = new JRadioButton("I-Q Correction");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        sMeterIzq = new SMeterBean();
        sMeterIzq.setCurrentAmount(10);
        sMeterDer = new SMeterBean();
        sMeterDer.setCurrentAmount(10);
        recibirButton.setFont(letras);
        pararButton.setFont(letras);
        parksMcclellan.setBorder(border);
        parksMcclellan.add(filter);
        parksMcclellan.add(filterBW);
        parksMcclellan.add(filterType);
        parksMcclellan.add(filterBWField);
        parksMcclellan.add(megaHertz);
        parksMcclellan.add(filterTypeBox);
        
        
        
          


        layout.linkSize(0, new Component[] 
        {
            recibirButton, frecuenciaLO, pararButton, sMeterIzq, sMeterDer,  freqLOField, tuningFreqField
        });
        layout.linkSize(1, new Component[] {
            frecuenciaLO, sMeterIzq, sMeterDer,  freqLOField, tuningFreqField
        });
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup().addContainerGap()
                            .addComponent(recibirButton, 20, 20, 30)
                            .addComponent(pararButton, 20, 20, 30))
                    .addComponent(frecuenciaLO)
                            .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addComponent(freqLOField)
                                .addComponent(megaHertz2))
                        .addComponent(tuningFreq)
                            .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addComponent(tuningFreqField)
                                .addComponent(megaHertz3))
   /*                         .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addComponent(filter)
                                .addComponent(filterBW, 80, 100, 100)
                                .addComponent(filterType))
                            .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addComponent(filterBWField, 40, 50, 60)
                                .addComponent(megaHertz, 20, 20, 20)
                                .addComponent(filterTypeBox, 40, 50, 60))*/
//                            .addComponent(filterType)
                            .addComponent(parksMcclellan, 100, 100,200)
                            .addComponent(windowBox, 0, 85, 150)
                            .addComponent(modoMod)
                            .addComponent(modeBox, 0, 85, 150)
                            .addComponent(entradaLab)
                            .addComponent(entradaBox, 0, 85, 150)
                            .addComponent(espacio)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(canalIzq)
                                .addComponent(canalDer))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(izqPlusDer)
                                .addComponent(derPlusIzq))
                            .addComponent(correccionIQ)
                            .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addComponent(sound)
                                .addComponent(mute))
                        .addComponent(grabarWAV)
                        .addComponent(grabarMuestras)
                        .addComponent(espacio)
                        .addComponent(nivelPromedio)
                            .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addComponent(nivelIzq)
                                .addComponent(sMeterIzq))
                            .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addComponent(nivelDer)
                                .addComponent(sMeterDer)))
                 .addComponent(graficos))
                 .addComponent(scrollingArea));
        
        layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(recibirButton, 20, 20, 20)
                        .addComponent(pararButton, 20, 20, 20))
                .addComponent(frecuenciaLO)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(freqLOField)
                        .addComponent(megaHertz2))
                .addComponent(tuningFreq)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(tuningFreqField)
                        .addComponent(megaHertz3))
 /*                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(filter)
                        .addComponent(filterBW)
                        .addComponent(filterType))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(filterBWField, 20, 20, 20)
                        .addComponent(megaHertz, 20, 20, 20)
                        .addComponent(filterTypeBox, 20, 20, 20))*/
//                .addComponent(filterType, 15, 20, 30))
                .addComponent(parksMcclellan, 40, 40, 50)
                .addComponent(windowBox, 20, 20, 30)
                .addComponent(modoMod, 15, 20, 30)
                .addComponent(modeBox, 20, 20, 30)
                .addComponent(entradaLab, 15, 20, 30)
                .addComponent(entradaBox, 20, 20, 30)
                .addComponent(espacio)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(canalIzq)
                .addComponent(canalDer)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(izqPlusDer)
                .addComponent(derPlusIzq))
                .addComponent(correccionIQ)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(sound)
                    .addComponent(mute))
                .addComponent(grabarWAV)
                .addComponent(grabarMuestras)
                .addComponent(espacio)
                .addComponent(nivelPromedio)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(nivelIzq)
                .addComponent(sMeterIzq)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(nivelDer)
                .addComponent(sMeterDer)))
                .addComponent(graficos))
                .addComponent(scrollingArea));
        setSize(1024, 768);
        if(ConfigurationBean.debugL1)
            setArea((new StringBuilder()).append("\r\n").append(frecuenciaLO.getSize()).toString().toString());
        setVisible(true);
        
        
         recibirButton.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent evt)
             {
                 recibirButtonActionPerformed(evt);
             }
         });

         pararButton.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent evt)
             {
                 pararButtonActionPerformed(evt);
             }
          });


         grabarWAV.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent evt)
             {
                 grabarWAVActionPerformed(evt);
             }
         }
         );
         
         grabarMuestras.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent evt)
             {
                 grabarMuestrasActionPerformed(evt);
             }
         });
         
         exitAction.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent evt)
             {
                 exitActionPerformed(evt);
             }
         });
         
         verConfiguracion.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent evt)
             {
                 configMenuActionPerformed(evt);
             }
        });
         
         entradaBox.addActionListener (new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent evt) 
             {
                 entradaActionPerformed(evt);
             }
         });
    }// Fin de createAndShowGUI()

    
    
    @Override
    public void windowClosing(WindowEvent e)
    {
        setArea("\r\nWindowListener method called: windowClosing.");
        ActionListener task;
        task = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                alreadyDisposed = true;
            }

        boolean alreadyDisposed;
        };
        Timer timer = new Timer(500, task);
        timer.setInitialDelay(2000);
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
        setArea("\r\nWindowListener method called: windowOpened.");
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        setArea("\r\nWindowListener method called: windowClosed.");
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
        setArea("\r\nWindowListener method called: windowActivated.");
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        setArea("\r\nWindowListener method called: windowDeactivated.");
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        setArea("\r\nWindowListener method called: windowIconified.");
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        setArea("\r\nWindowListener method called: windowDeiconified.");
    }

    private void recibirButtonActionPerformed(ActionEvent evt)
    {
        try
        {
            pararButton.setEnabled(true);
            recibirButton.setEnabled(false);
            if(ConfigurationBean.debugL1)
            setArea("\r\nReceive pressed");
            input = new Input();
            input.execute();
        }
        catch(Exception e)
        {
            System.out.println("Error"+e.toString());
            setArea((new StringBuilder()).append("\r\n").append(e.toString()).toString());
        }
    }

    private void pararButtonActionPerformed(ActionEvent evt)
    {
        pararButton.setEnabled(false);
        recibirButton.setEnabled(true);
        input.cancelInput();
    }


    private void grabarMuestrasActionPerformed(ActionEvent evt)
    {
        if(ConfigurationBean.debugL1)
            setArea("\r\nSe van a grabar las muestras en c:\\prueba.txt\n");
    }

    private void grabarWAVActionPerformed(ActionEvent evt)
    {
        if(ConfigurationBean.debugL1)
            setArea("\r\nSe va a grabar un WAV\n");
    }

    private void exitActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

    public void configMenuActionPerformed(ActionEvent evt)
    {
        ConfigurationBean config = new ConfigurationBean("View configuration");
    }
    
    public void entradaActionPerformed(ActionEvent evt)
    {
//        System.out.println("Command: " + evt.getActionCommand());
        ItemSelectable is = (ItemSelectable)evt.getSource();
        setArea("Instancia de Input: "+Input.getInstance());
        if (Input.getInstance()!=null)
        input.cancelInput();
        input = new Input();
        input.execute();
    }

    public void itemStateChanged(ItemEvent e)
    {
        JMenuItem source = (JMenuItem)(JMenuItem)e.getSource();
        String s = (new StringBuilder()).append("Item event detected.\n    Event source: ").append(source.getText()).append(" (an instance of ").append("\n").append("    New state: ").append(e.getStateChange() != 1 ? "unselected" : "selected").toString();
        System.out.println(s);
    }
    public static String getFilterBWField()
    {
        return filterBWField.getText();
    }
    
    public static String getFreqLOField()
    {
        return freqLOField.getText();
    }
    
    public static String getTunningFreqField()
    {
        return tuningFreqField.getText();
    }
    public static  String getWindow()
    {
        return windowBox.getSelectedItem().toString();
    }
    
    
    public static  String getInput()
    {
        return entradaBox.getSelectedItem().toString().replaceAll(" ","");
    }
    
    
    public static  String getMode()
    {
        return modeBox.getSelectedItem().toString().replaceAll(" ","");
    }
    
    
    public static  String getFilterType()
    {
        return filterTypeBox.getSelectedItem().toString();
    }
    
    public static  Boolean getGrabarWav()
    {
        if(grabarWAV.isSelected())
            return true;
        else
            return false;
    }
    
    public static  Boolean getSound()
    {
        if(sound.isSelected())
            return true;
        else
            return false;
    }
    
    
    public static  Boolean getGrabarMuestras()
    {
        if(grabarMuestras.isSelected())
            return true;
        else
            return false;
    }
    

    public static void setArea(String texto)
    {
        mensajeArea.append(texto);
    }

    static private String selectedString(ItemSelectable is) 
    {
        Object selected[] = is.getSelectedObjects();
        return ((selected.length == 0) ? "null" : (String)selected[0]);
    }







}
