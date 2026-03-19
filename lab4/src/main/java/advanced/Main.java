package advanced;


import com.github.javafaker.Faker;
import homework.Intersection;
import homework.Street;

import java.util.stream.IntStream;

import static advanced.ProblemGenerator.generateRandomCity;

public class Main {
    public static void main(String[] args) {
        Faker faker=new Faker();
        City city = generateRandomCity("Iasi", 10, faker);
        city.maintenanceRoute();
    }
}

