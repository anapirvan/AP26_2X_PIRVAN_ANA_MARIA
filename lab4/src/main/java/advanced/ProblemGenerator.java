package advanced;
import com.github.javafaker.Faker;
import homework.Intersection;
import homework.Street;

import java.util.Random;

public class ProblemGenerator {
    public static City generateRandomCity(String name, int nrIntersections, Faker faker) {
        City city = new City(name);
        Random random = new Random();

        double[] x = new double[nrIntersections];
        double[] y = new double[nrIntersections];

        Intersection[] intersections = new Intersection[nrIntersections];
        for (int i = 0; i < nrIntersections; i++) {
            x[i] = random.nextDouble() * 100;
            y[i] = random.nextDouble() * 100;
            intersections[i] = new Intersection(faker.address().streetName() + " " + i);
            city.addIntersection(intersections[i]);
        }

        for (int i = 0; i < nrIntersections; i++) {
            for (int j = i + 1; j < nrIntersections; j++) {
                double length = Math.sqrt(
                        Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)
                );
                city.addStreet(new Street(
                        faker.address().streetName(),
                        (float) length,
                        intersections[i],
                        intersections[j]
                ));
            }
        }

        return city;
    }
}
