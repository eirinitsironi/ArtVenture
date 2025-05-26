public class GridSlot {
    private int x;
    private int y;
    private Object object;

    public GridSlot(int x, int y) {
        this.x = x;
        this.y = y;
        this.object = null;
    }

    public boolean isOccupied() {
        return object != null;
    }

    public void placeObject(Object obj) {
        this.object = obj;
    }

    public void removeObject() {
        this.object = null;
    }

    public Object getObject() {
        return object;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}