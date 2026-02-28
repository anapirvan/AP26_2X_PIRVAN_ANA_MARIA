package compulsory;

import java.util.Objects;

public class Location {
    private String name;
    private String type;
    private float x,y;

    public Location(String name, String type, float x, float y) {
        this.name = name;
        this.type = type;
        this.x=x;
        this.y=y;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setX_coordinate(float xCoordinate) {
        this.x=x;
    }
    public void setY_coordinate(float yCoordinate) {
        this.y=y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

