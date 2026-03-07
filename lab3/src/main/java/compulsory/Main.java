package compulsory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Profile> list=new ArrayList<>();

        list.add(new Person(123,"Ana"));
        list.add(new Person(345,"Maria"));
        list.add(new Company(687,"Amazon"));
        list.add(new Company(947,"Emag"));

        list.sort(Comparator.comparing(Profile::getName));
        for(Profile profile:list){
            System.out.println(profile.getName()+" "+profile.getId());
        }
    }
}
