package homework;
import java.util.Set;
import java.util.HashSet;

public class Main {
    /**
     * Se creeaza o instanta a clasei Problem si se populeaza cu diverse obiecte de tip Location si Road.
     */
    public static void main(String[] args) {
        Problem pb=new Problem();

        //Se adauga diferite tipuri de locatii in sistem
        Location location1=new City("Suceava", 10, 20,30000);
        Location location2=new Airport("Otopeni",25,30,3);
        Location location3= new GasStation("OMW",24,56,7.45f);
        Location location4= new City("Iasi",23,45,40000);
        pb.addLocation(location1);
        pb.addLocation(location2);
        pb.addLocation(location3);

        //Se adauga diferite tipuri de drumuri in sistem.
        Road road1=new Road(RoadType.EXPRESS,110,70,location1,location2);
        Road road2=new Road(RoadType.COUNTRY,90,70,location2,location3);
        pb.addRoad(road1);
        pb.addRoad(road2);

        //Se valideaza instanta problemei
        pb.isValid();

        //Se aplica metoda care determina daca exista drum de la o locatie la alta
        System.out.println(pb.canReach(location1,location3));
        System.out.println(pb.canReach(location3,location4));

    }

}