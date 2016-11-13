import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final List<String> PARTICIPANTS = new ArrayList();
    private static final Map<String, List<String>> RULES = new HashMap();

    public static void main(String[] args) {
        try {
            readProperties();

            List<String> missingPeople = MissingPeople.find(PARTICIPANTS, RULES);

            if(missingPeople.size() == 0) {
                Map<String, String> solution = Sorter.sort(PARTICIPANTS, RULES);

                if(SolutionChecker.check(PARTICIPANTS, solution)) {
                    Printer.print(solution);

                } else {
                    System.out.println("The solution found is not a valid one");
                }

            } else {
                System.out.println("People missing in rules");
                missingPeople.forEach(p -> System.out.println(p));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readProperties() throws Exception {
        ResourcesPropertiesLoader propertiesLoader = new ResourcesPropertiesLoader();

        try {
            propertiesLoader.mapProperties("participants.properties", PARTICIPANTS);
            propertiesLoader.mapProperties("rules.properties", RULES);

        } catch (Exception e) {
            throw new Exception("COULDN'T MAP PROPERTIES");
        }
    }
}
