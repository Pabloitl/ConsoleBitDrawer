package read;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DrawReader implements DrawingReadable, Closeable {
    private BufferedReader reader;

    public DrawReader(String in) throws FileNotFoundException {
        this(new File(in));
    }
    
    public DrawReader(File in) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(in));
    }

    public String readLine() throws Exception {
        if (reader == null)
            throw new Exception("Reader is not instantiated");

        return reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
    }
}
