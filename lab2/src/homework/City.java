package homework;

/**
 * Clasa finala derivata din clasa de baza Location.
 */
public final class City extends Location{
    private int population;

    /**
     * Constructor pt clasa City.
     * @param name numele orasului
     * @param x coordonata X pe harta
     * @param y coordonata Y pe harta
     * @param population populatia orasului
     */
    public City(String name, float x, float y, int population) {
        super(name, x, y);
        this.population = population;
    }
}
