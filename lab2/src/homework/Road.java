package homework;

import java.util.Objects;

/**
 * Reprezinta un drum intre 2 locatii.
 */
public class Road {
    private RoadType type;
    private float length;
    private int speedLimit;
    private Location from,to;

    /**
     * Constructor pt clasa Road.
     * @param type tipul drumului
     * @param length lungimea drumului
     * @param speedLimit limita de viteza a drumului
     * @param from Locatia de unde porneste drumul.
     * @param to Locatia unde ajunge drumul.
     */
    public Road(RoadType type, float length, int speedLimit, Location from, Location to) {
        this.type = type;
        this.length = length;
        this.speedLimit = speedLimit;
        this.from = from;
        this.to = to;
    }

    /**
     * Returneaza tipul drumului.
     * @return RoadType reprezentand tipul
     */
    public RoadType getType() {
        return type;
    }

    /**
     * Returneaza lungimea drumului.
     * @return float reprezentand lungimea
     */
    public float getLength() {
        return length;
    }

    /**
     * Returneaza limita de viteza a drumului.
     * @return int reprezentand limita de viteza
     */
    public int getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Returneaza locatia de unde incepe drumul.
     * @return Location reprezentand locatia de inceput
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Returneaza locatia unde ajunge drumul.
     * @return Location reprezentand locatia de sfarsit
     */
    public Location getTo() {
        return to;
    }

    /**
     * Modifica tipul drumului.
     * @param type Noul tip ce va fi atribuit drumului.
     */
    public void setType(RoadType type) {
        this.type = type;
    }

    /**
     * Modifica lungimea drumului.
     * @param length Noua lungime ce va fi atribuita drumului.
     */
    public void setLength(float length) {
        this.length = length;
    }

    /**
     * Modifica limita de viteza a drumului.
     * @param speedLimit Noua limita de viteza ce va fi atribuita drumului.
     */
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * Modifica locatia de unde porneste drumul.
     * @param from Noua locatie de start ce va fi atribuita drumului.
     */
    public void setFrom(Location from) {
        this.from = from;
    }

    /**
     * Modifica locatia in care se sfarseste drumul.
     * @param to Noua locatia de sfarsit ce va fi atribuita drumului.
     */
    public void setTo(Location to) {
        this.to = to;
    }

    /**
     * Returneaza o reprezentare textuala a obiectului Road.
     * @return String ce contine detaliile complete ale drumului.
     */
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

    /**
     * Compara acest drum cu un alt obiect pentru egalitate.
     * Doua drumuri sunt considerate egale daca au aceeasi lungime,aceeasi limita de viteza, acelasi tip si aceleasi locatii de inceput si final.
     * @param o Obiectul de referinta cu care se face comparatia.
     * @return True daca obiectele sunt egale d.p.d.v al continutului, false altfel.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Float.compare(length, road.length) == 0 && speedLimit == road.speedLimit && type == road.type && Objects.equals(from, road.from) && Objects.equals(to, road.to);
    }

    /**
     * Genereaza un cod hash pt acest drum.
     * @return codul hash al obiectului
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, length, speedLimit, from, to);
    }
}



