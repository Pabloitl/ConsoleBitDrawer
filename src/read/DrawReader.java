package read;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DrawReader implements DrawingReadable, Closeable {
    private String file;
    private BufferedReader reader;

    public DrawReader(String in) throws FileNotFoundException {
        file = in;
        reader = new BufferedReader(new FileReader(in));
    }
    
    public String readLine() throws Exception {
        if (reader == null)
            throw new Exception("Reader is not instantiated");

        return reader.readLine();
    }
    
    public int getNumberLines() {
        int result = -1
                ;
        try {
            reader.close();
            result = (int) Files.lines(Paths.get(file)).count();
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            return result;
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}
