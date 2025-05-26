public class Wall {
    public enum WallType { FRONT, BACK, LEFT, RIGHT, FLOOR }

    private WallType type;
    private GridSlot[][] grid;

    public Wall(WallType type, int widthSlots, int heightSlots) {
        this.type = type;
        grid = new GridSlot[widthSlots][heightSlots];
        for (int i = 0; i < widthSlots; i++) {
            for (int j = 0; j < heightSlots; j++) {
                grid[i][j] = new GridSlot(i, j);
            }
        }
    }

    public boolean canPlace(Object obj, int x, int y) {
        return isValidPosition(x, y) && !grid[x][y].isOccupied();
    }

    public boolean placeObject(Object obj, int x, int y) {
        if (canPlace(obj, x, y)) {
            grid[x][y].placeObject(obj);
            return true;
        }
        return false;
    }

    public boolean removeObject(int x, int y) {
        if (isValidPosition(x, y) && grid[x][y].isOccupied()) {
            grid[x][y].removeObject();
            return true;
        }
        return false;
    }

    public Object getObjectAt(int x, int y) {
        return isValidPosition(x, y) ? grid[x][y].getObject() : null;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    public GridSlot[][] getGrid() {
        return grid;
    }

    public WallType getType() {
        return type;
    }

    public Wall deepCopy() {
        Wall copy = new Wall(this.type, grid.length, grid[0].length);
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                Object obj = grid[x][y].getObject();
                if (obj != null) {
                    copy.grid[x][y].placeObject(obj.deepCopy());
                }
            }
        }
        return copy;
    }
}
