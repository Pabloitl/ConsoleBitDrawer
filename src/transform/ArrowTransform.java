package transform;

import drawer.GUIDrawer;

public class ArrowTransform implements Transformable {
    public final int STEP = 20;
    private int stepx = 0, stepy = 0;
    
    public ArrowTransform(String movement) {
        switch (movement) {
            case "up": stepy = -STEP;  break;
            case "down": stepy = STEP; break;
            case "left": stepx = -STEP; break;
            case "right": stepx = STEP;
        }
    }

    @Override
    public int[] apply(int[] coordinates) {
        return new int[] {coordinates[0] + stepx, coordinates[1] + stepy};
    }
    
    public boolean isInBounds(int[] coordinates) {
        
        return (0 <= coordinates[0] && coordinates[0] < GUIDrawer.WIDTH - Integer.SIZE)
                &&
               (0 <= coordinates[1] && coordinates[1] < GUIDrawer.HEIGHT - GUIDrawer.imageHeight);
    }
}
