import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sorter {

    private static final List<String> PARTICIPANTS = new ArrayList();
    private static final Map<String, String> RULES = new HashMap();
    static {
        ResourcesPropertiesLoader propertiesLoader = new ResourcesPropertiesLoader();

        try {
            propertiesLoader.mapProperties("participants.properties", PARTICIPANTS);
            propertiesLoader.mapProperties("rules.properties", RULES);

        } catch (Exception e) {
            System.out.println("COULDN'T MAP PROPERTIES");
            System.out.println(e.getMessage());
        }
    }



    public static void main(String[] args) {
        System.out.println("hola que ase");
    }
}
