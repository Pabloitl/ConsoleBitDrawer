package transform;

public class DiagonalTransform implements Transformable {
    @Override
    public int[] apply(int[] coordinates) {
        int step = 1;
        
        return new int[]{coordinates[0] + step * 2, coordinates[1] + step};
    }
}
