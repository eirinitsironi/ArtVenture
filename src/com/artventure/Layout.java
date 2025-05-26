import java.util.EnumMap;

public class Layout {
    private String name;
    private EnumMap<Wall.WallType, Wall> walls;
    private boolean finalized;

    public Layout(String name, int widthSlots, int heightSlots) {
        this.name = name;
        this.walls = new EnumMap<>(Wall.WallType.class);
        initWalls(widthSlots, heightSlots);
        this.finalized = false;
    }

    private void initWalls(int widthSlots, int heightSlots) {
        for (Wall.WallType type : Wall.WallType.values()) {
            walls.put(type, new Wall(type, widthSlots, heightSlots));
        }
    }

    public Wall getWall(Wall.WallType type) {
        return walls.get(type);
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void finalizeLayout() {
        this.finalized = true;
        lockAllObjects();
    }

    private void lockAllObjects() {
        for (Wall wall : walls.values()) {
            for (GridSlot[] row : wall.getGrid()) {
                for (GridSlot slot : row) {
                    if (slot.getObject() != null) {
                        slot.getObject().setLocked(true);
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public Layout deepCopy() {
        Layout copy = new Layout(this.name, 0, 0);
        copy.finalized = this.finalized;

        Wall anyWall = this.walls.values().iterator().next();
        int width = anyWall.getGrid().length;
        int height = anyWall.getGrid()[0].length;

        copy.walls = new EnumMap<>(Wall.WallType.class);
        for (Wall.WallType type : Wall.WallType.values()) {
            Wall originalWall = this.walls.get(type);
            copy.walls.put(type, originalWall.deepCopy());
        }
        return copy;
    }
}

