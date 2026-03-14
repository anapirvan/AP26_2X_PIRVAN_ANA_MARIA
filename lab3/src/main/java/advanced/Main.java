package advanced;

import homework.Company;
import homework.Designer;
import homework.Profile;
import homework.Programmer;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Programmer programmer1 = new Programmer(1234, "Ana", "01.06.2005", "Backend");
        Programmer programmer2 = new Programmer(5673, "Maria", "13.04.2000", "DevOps");
        Designer designer1 = new Designer(6832, "Andrei", "05.11.1998", "UI/UX");
        Designer designer2 = new Designer(3291, "Matei", "10.04.2001", "Motion Graphics");

        Company company1 = new Company(3721, "Amazon");
        Company company2 = new Company(2906, "Emag");
        Company company3 = new Company(2452, "Facebook");

        programmer1.addRelationship(programmer2, "university classmate");
        programmer1.addRelationship(designer2, "best friend");
        programmer1.addRelationship(company3, "Senior developer");

        programmer2.addRelationship(programmer1, "univeristy classmate");
        programmer2.addRelationship(designer1, "former colleague");
        programmer2.addRelationship(company1, "DevOps Engineer");

        designer1.addRelationship(programmer2, "former colleague");
        designer1.addRelationship(designer2, "work colleague");
        designer1.addRelationship(company2, "Lead Designer");

        designer2.addRelationship(programmer1, "best friend");
        designer2.addRelationship(designer1, "work colleague");
        designer2.addRelationship(company2, "Motion Designer");

        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.addProfile(programmer1);
        socialNetwork.addProfile(programmer2);
        socialNetwork.addProfile(designer1);
        socialNetwork.addProfile(designer2);
        socialNetwork.addProfile(company1);
        socialNetwork.addProfile(company2);
        socialNetwork.addProfile(company3);


        List<Profile> cutPoints = socialNetwork.findCutpoints();
        for (Profile profile : cutPoints) {
            System.out.println(profile.getName());
        }

        List<Set<Profile>> components = socialNetwork.findBiconnectedComponents();

        for (int i = 0; i < components.size(); i++) {
            System.out.print("Componenta " + (i + 1) + ": ");
            for (Profile p : components.get(i)) {
                System.out.print(p.getName() + " ");
            }
            System.out.println();
        }
    }
}

