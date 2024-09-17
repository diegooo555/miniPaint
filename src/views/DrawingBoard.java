package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingBoard extends JPanel {
    // Lista para almacenar grupos de puntos
    private ArrayList<ArrayList<Point>> pointGroups;
    private ArrayList<Point> currentGroup; // Grupo de puntos actual
    private float lineThickness = 5.0f;
    private Color customColor;
    private String actualTool;

    private ArrayList<Point> pointsToDelete;

    public DrawingBoard(String actualTool) {
        this.actualTool = actualTool;
        pointGroups = new ArrayList<>();
        currentGroup = new ArrayList<>();
        customColor = new Color(0xF7F7F7);
        pointsToDelete = new ArrayList<>();
        setBackground(customColor);
        addEvents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // Convertir Graphics a Graphics2D

        // Establecer el grosor de la línea
        g2.setStroke(new BasicStroke(lineThickness));

        // Dibujar cada grupo de puntos
        for (ArrayList<Point> group : pointGroups) {
            for (int i = 1; i < group.size(); i++) {
                Point p1 = group.get(i - 1);
                Point p2 = group.get(i);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    private void createGroupPoints(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Crear un nuevo grupo de puntos al presionar el mouse
                currentGroup = new ArrayList<>();
                currentGroup.add(e.getPoint());
                pointGroups.add(currentGroup);
                repaint();
            }
        });
    }

    private void paintEvent(){
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Agregar puntos al grupo actual mientras se arrastra el mouse
                currentGroup.add(e.getPoint());
                repaint();
            }
        });
    }

    private void clearMouseListeners() {
        for (MouseListener ml : getMouseListeners()) {
            removeMouseListener(ml);
        }
        for (MouseMotionListener mml : getMouseMotionListeners()) {
            removeMouseMotionListener(mml);
        }
    }

    public void setActualTool(String actualTool) {
        this.actualTool = actualTool;
        clearMouseListeners();
        addEvents();
    }

    private void addEvents(){
        if (actualTool.equals("Draw")) {
            System.out.println("Se crearon los eventos");
            createGroupPoints();
            paintEvent();
            repaint();

        } else if (actualTool.equals("Eraser")) {
           
        }
    }

    public void cleanDrawBoard(){
        pointGroups = new ArrayList<>();
        currentGroup = new ArrayList<>();

        revalidate();
        repaint();
    }




}

