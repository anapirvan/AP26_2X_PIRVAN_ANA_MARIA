package homework;

import java.util.Objects;

/**
 * Reprezeinta o locatie geografica generala in cadrul problemei.
 * Aceasta reprezinta baza pentru tipuri specifice precum orase, aeroporturi si benzinarii.
 */
public abstract sealed class Location permits City,Airport,GasStation{
    protected String name;
    protected float x,y;

    /**
     * Constructor pt clasa Location.
     * @param name numele locatiei
     * @param x coordonata X pe harta
     * @param y coordonata Y pe harta
     */
    public Location(String name, float x, float y) {
        this.name = name;
        this.x=x;
        this.y=y;
    }

    /**
     * Returneaza numele locatiei.
     * @return String reprezentand numele.
     */
    public String getName() {
        return name;
    }

    /**
     * Returneaza coordonata X a locatiei.
     * @return float reprezentand coordonata X.
     */
    public float getX() {
        return x;
    }

    /**
     * Returneaza coordonata Y a locatiei.
     * @return float reprezentand coordonata Y.
     */
    public float getY() {
        return y;
    }

    /**
     * Modifica numele locatiei.
     * @param name Noul nume ce va fi atribuit locatiei.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifica coordonata X a locatiei.
     * @param xCoordinate Noua coordonata X ce va fi atribuita locatiei.
     */
    public void setX_coordinate(float xCoordinate) {
        this.x=x;
    }

    /**
     * Modifica coordonata Y a locatiei.
     * @param yCoordinate Noua coordonata Y ce va fi atribuita locatiei.
     */
    public void setY_coordinate(float yCoordinate) {
        this.y=y;
    }

    /**
     * Returneaza o reprezentare textuala a obiectului Location.
     * @return String ce contine detaliile complete ale locatiei.
     */
    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Compara aceasta locatie cu un alt obiect pentru egalitate.
     * Doua locatii sunt considerate egale daca au acelasi nume si aceleasi coordonate (x,y).
     * @param o Obiectul de referinta cu care se face comparatia.
     * @return True daca obiectele sunt egale d.p.d.v al continutului, false altfel.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(x, location.x) == 0 && Float.compare(y, location.y) == 0 && Objects.equals(name, location.name);
    }

    /**
     * Genereaza un cod hash pt aceasta locatie.
     * @return codul hash al obiectului
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, x, y);
    }
}

