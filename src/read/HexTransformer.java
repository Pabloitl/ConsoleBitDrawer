package read;

import java.util.BitSet;
import drawer.Drawer;

public class HexTransformer {
    public static BitSet toBitSet(String hexString) {
        BitSet result = new BitSet(Drawer.BITS);

        for (int i = 0; i < hexString.length(); i++) {
            int temp = Integer.parseInt(hexString.substring(i, i + 1), 16);
            String t = Integer.toBinaryString(temp);
        }
        return result;
    }
}
