package views;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private JLabel colorLabel;
    private JLabel elementsLabel;
    private Font customFont;

    public BottomPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(0xE1F0FA));

        customFont = new Font("Arial", Font.BOLD, 16);

        colorLabel = new JLabel("Color: Black");
        colorLabel.setFont(customFont);
        elementsLabel = new JLabel("Cantidad Elementos: 0");
        elementsLabel.setFont(customFont);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(0xE1F0FA));
        leftPanel.add(colorLabel);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(0xE1F0FA));
        rightPanel.add(elementsLabel);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
    }

    public void updateColor(Color color) {
        colorLabel.setText("Color: " + colorToString(color));
    }

    public void updateElementCount(int count) {
        elementsLabel.setText("Cantidad Elementos: " + count);
    }

    private String colorToString(Color color) {
        if (color.equals(Color.BLACK))
            return "Black";
        if (color.equals(Color.RED))
            return "Red";
        if (color.equals(Color.BLUE))
            return "Blue";
        if (color.equals(Color.GREEN))
            return "Green";
        return String.format("#%06X", (0xFFFFFF & color.getRGB()));
    }
}