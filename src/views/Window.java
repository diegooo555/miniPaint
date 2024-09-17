package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Window extends JFrame {

    private MenuOptions menuOptions;
    private VerticalMenu verticalMenu;
    private DrawingBoard drawingBoard;
    private Tools tools;
    private String actualTool;
    private BottomPanel bottomPanel = new BottomPanel();
    private GridBagConstraints gbc;
    private String stateMenuTools;
    private JMenuItem changeMenuItem;
    private JButton changeMenuButton;
    private JButton changeSideButton;
    private String menuSide;
    boolean showMenu;
    

    public Window() {
        actualTool = "Draw";
        menuSide = "Left";
        stateMenuTools = "Top";
        showMenu = true;
        drawingBoard = new DrawingBoard(actualTool);
        tools = new Tools(drawingBoard, this);
        addKey();
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.CYAN);
        changeMenuItem = new JMenuItem("Menu Vertical");
        changeMenuButton = new JButton("Menu Horizontal");
        changeSideButton = new JButton("Cambiar Menu de Lado");
        eventChangeMenu();
        menuOptions = new MenuOptions(changeMenuItem);
        gbc = new GridBagConstraints();
        setJMenuBar(menuOptions);
        setMenuTop();
        eventChangeVertical();
        eventChangeSide();
    }

    public void setMenuTop() {
        gbc.gridx = 0; // Primera columna
        gbc.gridy = 1; // Segunda fila
        gbc.gridwidth = 1; // Ocupa una columna
        gbc.gridheight = 1; // Ocupa una fila
        gbc.weightx = 1.0; // Ocupa todo el ancho
        gbc.weighty = 0.8; // Ocupa el 80% del alto
        gbc.fill = GridBagConstraints.BOTH; // Llena todo el espacio disponible
        this.add(drawingBoard, gbc); // Añadir al contenedor
        setTools();
    }

    private void setTools() {
        gbc.gridx = 0; // Primera columna
        gbc.gridy = 0; // Primera fila
        gbc.gridwidth = 1; // Ocupa una columna
        gbc.gridheight = 1; // Ocupa una fila
        gbc.weightx = 1.0; // Ocupa todo el ancho
        gbc.weighty = 0.1; // Ocupa el 10% del alto
        gbc.fill = GridBagConstraints.BOTH; // Llena todo el espacio disponible
        tools.setBackground(Color.cyan); // Color de fondo del panel        
        this.add(tools, gbc); // Añadir al contenedor
    }
    


    private void setMenuSide(){
        verticalMenu = new VerticalMenu();
        changeSideButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, changeSideButton.getMinimumSize().height));
        changeSideButton.setFont(new Font("Arial", Font.PLAIN, 14));
        
        changeMenuButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, changeMenuButton.getMinimumSize().height));
        changeMenuButton.setFont(new Font("Arial", Font.PLAIN, 14));
        verticalMenu.add(changeMenuButton);
        verticalMenu.add(changeSideButton);
        remove(tools);
        if (menuSide.equals("Right")) {
            positionLayoutRight();
        }else{
            positionLayoutLeft();
        }
        this.add(drawingBoard, gbc);
        setBottomPanelHorizontal();
    }

    private void positionLayoutLeft(){
        gbc.gridx = 0; // Columna 0 (izquierda)
        gbc.gridy = 0; // Fila 0
        gbc.gridwidth = 1; // Ocupa 1 columna
        gbc.gridheight = 1; // Ocupa 1 fila (todo el alto)
        gbc.weightx = 0.05; // Ocupa el 20% del ancho
        gbc.weighty = 0.9; // Ocupa el 100% del alto
        gbc.fill = GridBagConstraints.BOTH;
        this.add(verticalMenu, gbc);

        gbc.gridx = 1; // Columna 1 (a la derecha del menú)
        gbc.gridy = 0; // Fila 0 (misma fila que el menú)
        gbc.gridwidth = 1; // Ocupa 1 columna
        gbc.gridheight = 1; // Ocupa 1 fila (todo el alto)
        gbc.weightx = 0.95; // Ocupa el 80% del ancho
        gbc.weighty = 0.9; // Ocupa el 100% del alto
        gbc.fill = GridBagConstraints.BOTH;
        this.add(drawingBoard, gbc);
    }

    private void positionLayoutRight(){
        gbc.gridx = 1; // Columna 1 (derecha)
        gbc.gridy = 0; // Fila 0
        gbc.gridwidth = 1; // Ocupa 1 columna
        gbc.gridheight = 1; // Ocupa 1 fila (todo el alto)
        gbc.weightx = 0.05; // Ocupa el 20% del ancho
        gbc.weighty = 0.9; // Ocupa el 100% del alto
        gbc.fill = GridBagConstraints.BOTH; // Se expande tanto en ancho como en alto
        this.add(verticalMenu, gbc);

        gbc.gridx = 0; // Columna 1 (a la izquierda del menú)
        gbc.gridy = 0; // Fila 0 (misma fila que el menú)
        gbc.gridwidth = 1; // Ocupa 1 columna
        gbc.gridheight = 1; // Ocupa 1 fila (todo el alto)
        gbc.weightx = 0.95; // Ocupa el 80% del ancho
        gbc.weighty = 0.9; // Ocupa el 100% del alto
        gbc.fill = GridBagConstraints.BOTH;
        this.add(drawingBoard, gbc);
    }

    public void setBottomPanel() {
        gbc.gridx = 0; // Primera columna
        gbc.gridy = 2; // Tercera fila
        gbc.gridwidth = 1; // Ocupa una columna
        gbc.gridheight = 1; // Ocupa una fila
        gbc.weightx = 1.0; // Ocupa todo el ancho
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH; 
        this.add(bottomPanel, gbc); 
    }

    public void setBottomPanelHorizontal() {
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 2; 
        gbc.gridheight = 1; 
        gbc.weightx = 1.0; 
        gbc.weighty = 0; 
        gbc.fill = GridBagConstraints.BOTH;
        this.add(bottomPanel, gbc);
    }

    private void eventChangeMenu() {
        changeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventChangeMenuPosition();
            }
        });
    }

    private void eventChangeSide() {
        changeSideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuSide.equals("Left")) {
                    remove(verticalMenu);
                    menuSide = "Right"; 
                    setMenuSide();
                } else {
                    remove(verticalMenu);
                    menuSide = "Left"; 
                    setMenuSide();
                }
                revalidate();
                repaint();
            }
        });
    }

    private void eventChangeVertical() {
        changeMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventChangeMenuPosition();
            }
        });
    }

    private void eventChangeMenuPosition(){
        if (stateMenuTools.equals("Top")) {
                   
            setJMenuBar(null);
            setMenuSide();
            setBottomPanelHorizontal();
            stateMenuTools = "Left";
            
        }else{
            if (stateMenuTools.equals("Left") || stateMenuTools.equals("Right")) {
                setBottomPanel();
                setJMenuBar(menuOptions);
                stateMenuTools = "Top";
                setMenuTop();
                remove(verticalMenu);
            }
        }
        revalidate();
        repaint();
    }



    private void addKey(){

        InputMap inputMap = drawingBoard.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = drawingBoard.getActionMap();

        // Asignar el evento F11 al InputMap
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "F11_pressed");

        // Definir la acción a realizar cuando se presiona F11
        actionMap.put("F11_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("¡Tecla F11 presionada!");
                if (showMenu) {
                    remove(tools);
                    showMenu = false;
                }else{
                    setTools();
                    showMenu = true;
                }
                revalidate();
                repaint();  
            }
            });
        
    }

    public void setTool(String newTool){
        actualTool = newTool;
        drawingBoard.setActualTool(actualTool);

    }
}