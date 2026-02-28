package compulsory;

public class Main {
    public static void main(String[] args) {
        Location location1=new Location("Suceava", "city", 10, 20);
        System.out.println(location1.toString());

        Location location2=new Location("Iasi","city",25,30);
        System.out.println(location2.toString());

        Road road=new Road(RoadType.EXPRESS,110,70,location1,location2);
        System.out.println(road.toString());
    }

}