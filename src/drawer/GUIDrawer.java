package drawer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import read.DrawReader;
import read.HexTransformer;
import transform.DiagonalTransform;
import transform.Transformable;

public class GUIDrawer extends JPanel {
    private final int WIDTH = 400, HEIGHT = 200;
    
    protected DrawReader repr;
    protected String file;

    private final JFrame window;
    private final Container container;
    
    private int[] coordinates;

    public GUIDrawer(String file) throws FileNotFoundException {
        this.file = file;
        // Inicializar la ventana
        window = new JFrame("Dibujando icono");
        
        // Definir un tamanio
        window.setSize(WIDTH, HEIGHT);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        
        container = window.getContentPane();
        container.setSize(WIDTH, HEIGHT);
        
        // Agregar la ventana en el contenedor
        container.add(this, BorderLayout.CENTER);
    }
    
    public void setFile(String file) throws FileNotFoundException {
        repr = new DrawReader(file);
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        try {
            String line;
            int bin, lineCount = 0;
            
            setFile(file);
            while ((line = repr.readLine()) != null) {
                bin = HexTransformer.toInteger(line);
                
                for (int i = Integer.SIZE - 1; i >= 0; i--) {
                    if ((bin & (1 << i)) != 0)
                        graphics.drawLine(
                                coordinates[0] + Integer.SIZE - i,
                                coordinates[1] + lineCount,
                                coordinates[0] + Integer.SIZE - i,
                                coordinates[1] + lineCount);
                }
                
                lineCount++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void print() {
        coordinates = new int[]{0, 0};
        Transformable transform = new DiagonalTransform();
        
        while (true) {
            coordinates = transform.apply(coordinates);
            paint(getGraphics());
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
