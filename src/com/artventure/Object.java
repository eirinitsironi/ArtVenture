public class Object {
    public enum ObjectType { CHAIR, PAINTING }

    private ObjectType type;
    private String name;
    private Dimensions dimensions;
    private boolean locked = false;

    public Object(ObjectType type, String name, Dimensions dimensions) {
        this.type = type;
        this.name = name;
        this.dimensions = dimensions;
    }

    public ObjectType getType() {
        return type;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Object deepCopy() {
        return new Object(type, name, new Dimensions(
            dimensions.getWidth(),
            dimensions.getHeight(),
            dimensions.getDepth()
        ));
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

}