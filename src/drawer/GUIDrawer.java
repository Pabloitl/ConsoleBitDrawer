package drawer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import read.DrawReader;
import read.HexTransformer;
import transform.*;

public class GUIDrawer extends JPanel {
    public final static int WIDTH = 200, HEIGHT = 200;
    public static int imageHeight;
    
    private int collisionCounter;
    
    protected DrawReader repr;
    protected String[] files;
    protected String file;

    private final JFrame window;
    private final Container container;
    private final JLabel counter;
    
    private int[] coordinates;
    
    public GUIDrawer(String[] files) throws FileNotFoundException {
        this.files = files;
        file = chooseFile();
        setFile(file);
        imageHeight = repr.getNumberLines();
        // Inicializar la ventana
        window = new JFrame("Dibujando icono");
        
        counter = new JLabel("Colisiones: 0");
        this.add(counter);
        
        // Definir un tamanio
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(WIDTH, HEIGHT);
        
        container = window.getContentPane();
        container.setSize(WIDTH, HEIGHT);
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        setBackground(Color.GRAY);
        
        // Agregar la ventana en el contenedor
        container.add(this, BorderLayout.CENTER);
    }
    
    private void setCollisions(int n) {
        counter.setText("Colisiones: " + n);
    }
    
    public void setFile(String file) throws FileNotFoundException {
        repr = new DrawReader(file);
    }
    
    private String chooseFile() {
        return files[(int) (Math.random() * files.length)];
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
        int iterations = 10;
        ArrowTransform transform;
        
        for (int i = 0; i < iterations; i++) {
            coordinates = new int[]{
                (int) (Math.random() * (WIDTH - Integer.SIZE)),
                (int) (Math.random() * (HEIGHT - imageHeight))};
            file = chooseFile();
            transform = new ArrowTransform(file.toLowerCase().substring(file.lastIndexOf("/") + 1, file.lastIndexOf(".")));
            
            while (transform.isInBounds(coordinates)) {
                paint(getGraphics());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                coordinates = transform.apply(coordinates);
            }
            setCollisions(++collisionCounter);
        }
    }
}
