package read;

public class HexTransformer {
    public static int toInteger(String hexString) {
        int result = 0;

        for (int i = 0; i < hexString.length(); i++)
            result |= toInteger(hexString.charAt(i))
                    << ((hexString.length() - i - 1) * Byte.SIZE / 2);
        
        return result;
    }

    public static int toInteger(char hexChar) {
        return Integer.parseInt(String.valueOf(hexChar), 16);
    }
    
    public static String toBinaryString(String hexString) {
        return Integer.toBinaryString(toInteger(hexString));
    }
    
    public static String toBox(String hexString) {
        int bin = HexTransformer.toInteger(hexString);
        String result = "";
        
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            if ((bin & 1 << i) != 0)
                result += "█";
        }
        return result;
    }
}
