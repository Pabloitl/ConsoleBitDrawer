package drawer;

import java.io.File;

public class Drawer {
    public static final int BITS = 32;

    private File repr;

    public Drawer (String file) {
        repr = new File(file);
    }
}
