package homework;

import com.github.javafaker.Faker;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Faker faker=new Faker();

        var intersections = IntStream.rangeClosed(0, 9)
                .mapToObj(i -> new Intersection(faker.address().streetName()) )
                .toArray(Intersection[]::new);

        var streets=IntStream.rangeClosed(0,8)
                .mapToObj(i->new Street(faker.address().streetName(),i*2,intersections[i],intersections[i+1]))
                .toArray(Street[]::new);

        City city=new City("Iasi");

        for(int i=0;i<=9;i++) {
            city.addIntersection(intersections[i]);
        }
        for(int i=0;i<9;i++){
            city.addStreet(streets[i]);
        }

        city.addStreet(new Street(faker.address().streetName(), 15, intersections[0], intersections[2]));
        city.addStreet(new Street(faker.address().streetName(), 20, intersections[1], intersections[4]));
        city.addStreet(new Street(faker.address().streetName(), 10, intersections[3], intersections[6]));
        //city.displayStreets(8);

        city.otherMSTs(5);
    }
}
