package homework;

/**
 * Clasa finala derivata din clasa de baza Location.
 */
public final class GasStation extends Location {
    private float gasPrice;

    /**
     * Constructor pentru clasa GasStation.
     * @param name numele benzinariei
     * @param x coordonata X pe harta
     * @param y coordonata Y pe harta
     * @param gasPrice pretul combustibilului
     */
    public GasStation(String name, float x, float y, float gasPrice) {
        super(name, x, y);
        this.gasPrice = gasPrice;
    }
}
