package compulsory;

public class Road {
    private RoadType type;
    private float length;
    private int speedLimit;
    private Location from,to;

    public Road(RoadType type, float length, int speedLimit, Location from, Location to) {
        this.type = type;
        this.length = length;
        this.speedLimit = speedLimit;
        this.from = from;
        this.to = to;
    }

    public RoadType getType() {
        return type;
    }
    public float getLength() {
        return length;
    }
    public int getSpeedLimit() {
        return speedLimit;
    }
    public Location getFrom() {
        return from;
    }
    public Location getTo() {
        return to;
    }

    public void setType(RoadType type) {
        this.type = type;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }
    public void setFrom(Location from) {
        this.from = from;
    }
    public void setTo(Location to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Road{" +
                "type=" + type +
                ", length=" + length +
                ", speedLimit=" + speedLimit +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}

enum RoadType{
    EXPRESS,
    HIGHWAY,
    COUNTRY
}
