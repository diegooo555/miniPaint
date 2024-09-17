package views;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Tools extends JPanel{
    
    private DrawingBoard drawingBoard;
    private String[] urlImages;
    private String[] allTools;
    private Window window;
    private JButton draw;
    private JButton clean;
    private JButton eraser;
    private JButton[] allButtons;

    public Tools(DrawingBoard drawingBoard, Window window){
        this.window = window;
        this.drawingBoard = drawingBoard;
        allTools = new String[3];
        urlImages = new String[3];
        clean = new JButton("Limpiar");
        draw = new JButton("Dibujar");
        eraser = new JButton("Color");
        setUrlImages();
        setNameTools();
        allButtons = new JButton[3];
        allButtons[0] = clean;
        allButtons[1] = draw;
        allButtons[2] = eraser;
        
        createAllButtons(allButtons);
    }

    private void setUrlImages(){
        urlImages[0] = "/images/eraser.png";
        urlImages[1] = "/images/pencil.png";
        urlImages[2] = "/images/palette.png";
    }

    private void setNameTools(){
        allTools[0] = "Cursor";
        allTools[1] = "Draw";
        allTools[2] = "Eraser";
    }


    private void createAllButtons(JButton[] buttons){
        for (int i = 0; i < urlImages.length; i++) {
            ImageIcon icon = new ImageIcon( getClass().getResource(urlImages[i]) );
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            buttons[i].setPreferredSize(new Dimension(200, 100));
            buttons[i].setIcon(resizedIcon);

        }

        clean.addActionListener(e -> {
            drawingBoard.cleanDrawBoard();
            window.setTool("Cursor");
        });

        draw.addActionListener(e -> {
            System.out.println("Cambiando tool");
            window.setTool("Draw");
        });

        eraser.addActionListener((e ->{

            //drawingBoard.eraserPoints();
            window.setTool("Eraser");
        }));

        add(clean);
        add(draw);
        add(eraser);
    }
}
