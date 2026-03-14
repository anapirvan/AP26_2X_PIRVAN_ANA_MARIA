package advanced;

import homework.Company;
import homework.Designer;
import homework.Profile;
import homework.Programmer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SocialNetworkTest {
    private SocialNetwork socialNetwork;
    private Programmer programmer1, programmer2;
    private Designer designer1, designer2;
    private Company company1, company2, company3;

    @Before
    public void setUp() {
        programmer1 = new Programmer(1234, "Ana", "01.06.2005", "Backend");
        programmer2 = new Programmer(5673, "Maria", "13.04.2000", "DevOps");
        designer1 = new Designer(6832, "Andrei", "05.11.1998", "UI/UX");
        designer2 = new Designer(3291, "Matei", "10.04.2001", "Motion Graphics");

        company1 = new Company(3721, "Amazon");
        company2 = new Company(2906, "Emag");
        company3 = new Company(2452, "Facebook");

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

        socialNetwork = new SocialNetwork();
        socialNetwork.addProfile(programmer1);
        socialNetwork.addProfile(programmer2);
        socialNetwork.addProfile(designer1);
        socialNetwork.addProfile(designer2);
        socialNetwork.addProfile(company1);
        socialNetwork.addProfile(company2);
        socialNetwork.addProfile(company3);
    }

    @Test
    public void findCutPointsTest() {
        List<Profile> cutPoints = socialNetwork.findCutpoints();
        assertEquals(2, cutPoints.size());
        assertTrue(cutPoints.contains(programmer1));
        assertTrue(cutPoints.contains(programmer2));

    }

    @Test
    public void findBiconnectedComponentsTest() {
        List<Set<Profile>> components = socialNetwork.findBiconnectedComponents();

        assertEquals(3, components.size());

        Set<Profile> expectedComp1 = Set.of(company1, programmer2);
        Set<Profile> expectedComp2 = Set.of(designer2, programmer1, programmer2, company2, designer1);
        Set<Profile> expectedComp3 = Set.of(programmer1, company3);

        boolean foundComp1 = components.contains(expectedComp1);
        boolean foundComp2 = components.contains(expectedComp2);
        boolean foundComp3 = components.contains(expectedComp3);

        assertTrue(foundComp1);
        assertTrue(foundComp2);
        assertTrue(foundComp3);
    }
}
