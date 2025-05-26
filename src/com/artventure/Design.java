import java.util.Scanner;
import java.util.Stack;

public class Design {
    private Layout currentLayout;
    private Stack<Layout> undoStack = new Stack<>();
    private Stack<Layout> redoStack = new Stack<>();
    private Layout savedLayout;
    private Wall.WallType currentView = Wall.WallType.FRONT;
    private Scanner scanner = new Scanner(System.in);

    public void createLayout(String name, int widthSlots, int heightSlots) {
        currentLayout = new Layout(name, widthSlots, heightSlots);
        undoStack.clear();
        redoStack.clear();
        savedLayout = null;
    }

    public void switchToWall(Wall.WallType type) {
        currentView = type;
    }

    public void chooseWallCLI() {
        System.out.println("Available options:");
        for (Wall.WallType type : Wall.WallType.values()) {
            System.out.println("- " + type);
        }
        System.out.print("Choose one: ");
        String input = scanner.nextLine().toUpperCase();

        try {
            switchToWall(Wall.WallType.valueOf(input));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option.");
        }
    }

    public void placeObjectCLI() {
        System.out.print("Object type (CHAIR/PAINTING): ");
        String typeStr = scanner.nextLine().toUpperCase();

        PlacedObject.ObjectType type;
        try {
            type = PlacedObject.ObjectType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type.");
            return;
        }

        System.out.print("Object's name: ");
        String name = scanner.nextLine();

        System.out.print("Width (metres): ");
        double width = Double.parseDouble(scanner.nextLine());

        System.out.print("Height (metres): ");
        double height = Double.parseDouble(scanner.nextLine());

        System.out.print("Depth (metres): ");
        double depth = Double.parseDouble(scanner.nextLine());

        System.out.print("Coordinates (x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();

        PlacedObject obj = new PlacedObject(type, name, new Dimensions(width, height, depth));
        boolean success = placeObject(obj, x, y);

        if (success) {
            System.out.println("Placed successfully.");
        }
    }

    public void removeObjectCLI() {
        System.out.print("Coordinates (x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();

        boolean removed = removeObject(x, y);
        if (removed) {
            System.out.println("Removed successfully.");
        } else {
            System.out.println("No object found at the given coordinates.");
        }
    }

    public void displayCurrentWallCLI() {
        Wall wall = currentLayout.getWall(currentView);
        GridSlot[][] grid = wall.getGrid();

        System.out.println("View: " + wall.getType());
        for (int y = grid[0].length - 1; y >= 0; y--) {
            for (int x = 0; x < grid.length; x++) {
                System.out.print(grid[x][y].isOccupied() ? "[X]" : "[ ]");
            }
            System.out.println();
        }
    }

    public boolean placeObject(PlacedObject obj, int x, int y) {
        if (currentLayout.isFinalized()) return false;

        if (obj.getType() == PlacedObject.ObjectType.CHAIR && currentView != Wall.WallType.FLOOR) {
            System.out.println("Chairs can be placed only on the floor.");
            return false;
        } else if (obj.getType() == PlacedObject.ObjectType.PAINTING && currentView == Wall.WallType.FLOOR) {
            System.out.println("Paintings can be placed only on the walls.");
            return false;
        }

        Wall wall = currentLayout.getWall(currentView);
        if (wall.canPlace(obj, x, y)) {
            saveState();
            wall.placeObject(obj, x, y);
            clearRedo();
            return true;
        }

        System.out.println("Invalid placement.");
        return false;
    }

    public boolean removeObject(int x, int y) {
        if (currentLayout.isFinalized()) return false;

        Wall wall = currentLayout.getWall(currentView);
        if (wall.getObjectAt(x, y) != null) {
            saveState();
            wall.removeObject(x, y);
            clearRedo();
            return true;
        }

        System.out.println("No object to remove at given coordinates.");
        return false;
    }

    private void saveState() {
        undoStack.push(currentLayout.deepCopy());
    }

    private void clearRedo() {
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentLayout.deepCopy());
            currentLayout = undoStack.pop();
            System.out.println("Undo performed.");
        } else {
            System.out.println("No previous state.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentLayout.deepCopy());
            currentLayout = redoStack.pop();
            System.out.println("Redo performed.");
        } else {
            System.out.println("No next state.");
        }
    }

    public void finalizeLayout() {
        currentLayout.finalizeLayout();
        System.out.println("Layout finalized. No more editing allowed.");
    }

    public void saveLayout() {
        this.savedLayout = currentLayout.deepCopy();
        System.out.println("Layout saved (without finalizing).");
    }

    public Layout getSavedLayout() {
        return savedLayout;
    }

    public Layout getCurrentLayout() {
        return currentLayout;
    }

    public Wall.WallType getCurrentView() {
        return currentView;
    }
}