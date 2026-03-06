package homework;

/**
 * Clasa finala derivata din clasa de baza Location.
 */
public final class Airport extends Location{
    private int numberOfTerminals;

    /**
     * Constructor pt clasa Airport.
     * @param name numele aeroportului
     * @param x coordonata X pe harta
     * @param y coordonata Y pe harta
     * @param numberOfTerminals numaryl de terminale ale aeroportului
     */
    public Airport(String name, float x, float y, int numberOfTerminals) {
        super(name, x, y);
        this.numberOfTerminals = numberOfTerminals;
    }
}
