import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DrawReader implements DrawingReader, Closeable {
    private BufferedReader reader;

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
