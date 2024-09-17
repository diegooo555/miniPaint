package views;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class VerticalMenu extends JPanel {
    private JButton buttonSettings;
    private JButton buttonTools;
    private JButton buttonAbout;
    private JPanel subMenuSettings;
    private JPanel subMenuTools;
    private Font font;

    public VerticalMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0xE1F0FA));
        font = new Font("Arial", Font.BOLD, 16);

        createButtons();
        createSubMenus();
        addComponents();
    }

    private void createButtons() {
        buttonSettings = createMenuButton("Configuración");
        buttonTools = createMenuButton("Herramientas");
        buttonAbout = createMenuButton("Acerca de");
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        return button;
    }

    private void createSubMenus() {
        subMenuSettings = createSubMenu(new String[] { "Color", "Limpiar", "Salir" });
        subMenuTools = createSubMenu(new String[] { "Linea", "Circulo", "Rectangulo" });
        subMenuSettings.setVisible(false);
        subMenuTools.setVisible(false);
    }

    private JPanel createSubMenu(String[] items) {
        JPanel subMenu = new JPanel();
        subMenu.setLayout(new BoxLayout(subMenu, BoxLayout.Y_AXIS));
        subMenu.setBackground(Color.black);
        subMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        subMenu.setBackground(new Color(0xE1F0FA));
        subMenu.setBorder(new LineBorder(Color.CYAN));

        for (String item : items) {
            JButton subButton = createMenuButton(item);
            subButton.setFont(new Font("Arial", Font.BOLD, 14));
            subMenu.add(subButton);

            if ("Salir".equals(item)) {
                ImageIcon icon = new ImageIcon( getClass().getResource("/images/eraser.png") );
                Image img = icon.getImage();
                Image resizedImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        
                // Crear el ImageIcon redimensionado
                ImageIcon resizedIcon = new ImageIcon(resizedImg);
                subButton.setIcon(resizedIcon);
                subButton.addActionListener(e -> {
                    int answer = JOptionPane.showConfirmDialog(null, "¿Está seguro de salir?", "Cerrar MiniPaint",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        System.exit(0);
                    }
                });
            }
        }

        return subMenu;
    }

    private void addComponents() {
        add(buttonSettings);
        add(subMenuSettings);
        add(buttonTools);
        add(subMenuTools);
        add(buttonAbout);
        buttonSettings.addActionListener(e -> toggleSubMenu(subMenuSettings));
        buttonTools.addActionListener(e -> toggleSubMenu(subMenuTools));
        buttonAbout.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "MINIPAINT\n 1\n Taller 1 Programación II\n Diego && Camilo \n 10/09/2024"));
    }

    private void toggleSubMenu(JPanel subMenu) {
        subMenu.setVisible(!subMenu.isVisible());
        revalidate();
        repaint();
    }
}