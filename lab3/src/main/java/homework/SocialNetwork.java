package homework;


import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@NoArgsConstructor

public class SocialNetwork {
    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

    public int computeImportance(Profile profile) {
        if (profile instanceof Person person) {
            return person.getRelationships().size();
        }
        if (profile instanceof Company company) {
            int count = 0;
            for (Profile p : profiles) {
                if (p instanceof Person person && person.getRelationships().containsKey(company)) {
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

    public void printNetwork() {
        profiles.sort(Comparator.comparingInt(p -> -computeImportance(p)));

        for (Profile profile : profiles) {
            System.out.println(profile.getName() + " [importance: " + computeImportance(profile) + "]");

            if (profile instanceof Person person) {
                for (Map.Entry<Profile, String> entry : person.getRelationships().entrySet()) {
                    System.out.println("  -> " + entry.getKey().getName() + " (" + entry.getValue() + ")");
                }
            }
        }
    }
}
