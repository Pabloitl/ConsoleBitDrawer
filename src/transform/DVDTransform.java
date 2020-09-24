package transform;

import drawer.GUIDrawer;

public class DVDTransform implements Transformable {
    private int
            stepx = 5,
            stepy = 5;

    @Override
    public int[] apply(int[] coordinates) {
        if (coordinates[0] < 0 || coordinates[0] > GUIDrawer.WIDTH - Integer.SIZE)
            stepx = -stepx;
        if (coordinates[1] < 0 || coordinates[1] > GUIDrawer.HEIGHT - GUIDrawer.imageHeight - 20)
            stepy = -stepy;
        
        return new int[] {coordinates[0] + stepx, coordinates[1] + stepy};
    }
    
}
