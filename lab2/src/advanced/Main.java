package advanced;
import homework.*;


public class Main {
    public static void main(String[] args) {
        Problem pb=new Problem();

        Location iasi = new City("Iasi", 47.1f, 27.5f, 300000);
        Location vaslui = new City("Vaslui", 46.6f, 27.7f, 55000);
        Location aeroportBacau = new Airport("Aeroport-Bacau", 46.5f, 26.9f, 1);
        Location petromRoman = new GasStation("Petrom-Roman", 46.9f, 26.9f, 7.20f);
        Location pascani = new City("Pascani", 47.2f, 26.7f, 33000);
        Location suceava = new City("Suceava", 47.6f, 26.2f, 90000);

        pb.addLocation(iasi);
        pb.addLocation(vaslui);
        pb.addLocation(aeroportBacau);
        pb.addLocation(petromRoman);
        pb.addLocation(pascani);
        pb.addLocation(suceava);

        pb.addRoad(new Road(RoadType.HIGHWAY, 70.0f, 130, iasi, petromRoman));
        pb.addRoad(new Road(RoadType.COUNTRY, 65.0f, 50, iasi, vaslui));
        pb.addRoad(new Road(RoadType.EXPRESS, 50.0f, 100, vaslui, aeroportBacau));
        pb.addRoad(new Road(RoadType.COUNTRY, 45.0f, 60, petromRoman, aeroportBacau));
        pb.addRoad(new Road(RoadType.EXPRESS, 40.0f, 100, petromRoman, pascani));
        pb.addRoad(new Road(RoadType.HIGHWAY, 55.0f, 120, pascani, suceava));
        pb.addRoad(new Road(RoadType.COUNTRY, 110.0f, 70, iasi, suceava));

        pb.setStart(iasi);
        pb.setEnd(suceava);

        Solution sol=new Solution(pb,false);
        sol.solve();
    }
}
