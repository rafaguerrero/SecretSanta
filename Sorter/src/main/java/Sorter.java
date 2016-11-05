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
        List<String> receivers = new ArrayList<>();
        Map<String, String> solution = new HashMap<>();

        boolean solutionFound = sort(unassigned, receivers, solution);

        if(solutionFound) {
            solution.forEach((k,v)->System.out.println(k + " gives present to : " + v));
        } else {
            System.out.println("Couldn't find a proper solution");
        }
    }

    private static boolean sort(List<String> unassigned, List<String> receivers, Map<String, String> solution) {
        if(unassigned.isEmpty()) {
            return true;
        }

        int random = (int) Math.floor(Math.random() * unassigned.size());

        String current = unassigned.get(random);
        unassigned.remove(current);

        List<String> candidates = getCandidates(current, receivers);

        for(String candidate : candidates) {
            if(!receivers.contains(candidate)) {
                solution.put(current, candidate);
                receivers.add(candidate);

                if(sort(unassigned, receivers, solution)) {
                    return true;
                }

                receivers.remove(candidate);
            }
        }

        unassigned.add(current);
        solution.remove(current);

        return false;
    }

    private static List<String> getCandidates(String current, List<String> receivers) {
        List<String> candidates = RULES.get(current);
        int lastIndex = candidates.size() - 1;

        if ("*".equals(candidates.get(lastIndex))) {
            List<String> missingCandidates = new ArrayList(PARTICIPANTS);
            missingCandidates.remove(current);
            missingCandidates.removeAll(receivers);

            candidates.remove(lastIndex);
            candidates.addAll(missingCandidates);
        }

        return candidates;
    }
}
