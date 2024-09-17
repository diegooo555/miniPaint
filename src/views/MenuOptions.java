package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuOptions extends JMenuBar{
    private JMenu menuSettings;
    private JMenu menuTools;
    private JButton buttonAbout;
    private JMenuItem changeItemMenu;
    private Font font;
    
    public MenuOptions(JMenuItem changeButtonMenu){
        changeItemMenu = changeButtonMenu;
        menuSettings = new JMenu("Ver");
        menuTools = new JMenu("Herramientas");
        buttonAbout = new JButton("Acerca de");
        font = new Font("Arial", Font.BOLD, 16);
        this.setPreferredSize(new Dimension(this.getWidth(), 50));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(0xE1F0FA));
        setMenuSettings();
        setMenuTools();
        setButtonAbout();
    }

    private void setMenuSettings(){
        JMenuItem color = new JMenuItem("Color");
        JMenuItem clean = new JMenuItem("Limpiar");
        JMenuItem exit = new JMenuItem("Salir");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int answer = JOptionPane.showConfirmDialog(null, "¿Está seguro de salir?", "Cerrar MiniPaint", JOptionPane.YES_NO_OPTION);
               if (answer == 0) {
                System.exit(0);
               }
            }
        });

        menuSettings.setFont(font);
        menuSettings.add(color);
        menuSettings.add(clean);
        menuSettings.add(changeItemMenu);
        menuSettings.add(exit);
        this.add(menuSettings);
    }

    private void setMenuTools(){
        JMenuItem line = new JMenuItem("Linea");
        JMenuItem circle = new JMenuItem("Circulo");
        JMenuItem rectangle = new JMenuItem("Rectangulo");
        menuTools.setFont(font);
        menuTools.add(line);
        menuTools.add(circle);
        menuTools.add(rectangle);
        this.add(menuTools);
    }


    private void setButtonAbout() {
        // Crear un JButton en lugar de JMenu
        buttonAbout = new JButton("Acerca de");
        buttonAbout.setFont(font);

        buttonAbout.setBorderPainted(false);

        buttonAbout.setContentAreaFilled(false);
        buttonAbout.setFocusPainted(false);

        buttonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a ejecutar cuando se hace clic en el JButton
                JOptionPane.showMessageDialog(null, "MINIPAINT\n 1\n Taller 1 Programación II\n Diego && Camilo \n 10/09/2024");
            }
        });

        // Agregar el JButton al JMenuBar
        this.add(buttonAbout);
    }


}
