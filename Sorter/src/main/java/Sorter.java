import java.util.*;

public class Sorter {

    private static final List<String> PARTICIPANTS = new ArrayList();
    private static final Map<String, List<String>> RULES = new HashMap();
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
        List<String> unassigned = new ArrayList<>(PARTICIPANTS);
        Map<String, String> solution = new HashMap<>();

        sort(unassigned, solution);

        solution.forEach((k,v)->System.out.println(k + " gives present to : " + v));
    }

    private static boolean sort(List<String> unassigned, Map<String, String> solution) {
        if(unassigned.isEmpty()) {
            return true;
        }

        int random = (int) Math.floor(Math.random() * unassigned.size());

        String current = unassigned.get(random);
        unassigned.remove(current);

        List<String> candidates = RULES.get(current);

        for(String candidate : candidates) {
            solution.put(current, candidate);

            if(sort(unassigned, solution)) {
                return true;
            }
        }

        unassigned.add(current);
        solution.remove(current);

        return false;
    }
}
