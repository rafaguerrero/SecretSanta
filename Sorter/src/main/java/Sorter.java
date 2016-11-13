import java.util.*;
import java.util.stream.Collectors;

public class Sorter {
    private static Map<String, List<String>> RULES;
    private static List<String> PARTICIPANTS;

    public static Map<String, String> sort(List<String> participants, Map<String, List<String>> rules) throws Exception {
        RULES = rules;
        PARTICIPANTS = participants;

        List<String> unassigned = randomize(new ArrayList<>(participants));
        List<String> receivers = new ArrayList<>();
        Map<String, String> solution = new HashMap<>();

        List<String> randomCandidates = unassigned.stream()
                                                   .filter(p -> rules.get(p).get(0).equals("*"))
                                                   .collect(Collectors.toList());
        unassigned.removeAll(randomCandidates);
        unassigned.addAll(randomCandidates);

        boolean solutionFound = sort(unassigned, receivers, solution);

        if(!solutionFound) {
            throw new Exception("Couldn't find a proper solution");
        }

        return solution;
    }

    private static boolean sort(List<String> unassigned,
                                List<String> receivers,
                                Map<String, String> solution) {

        if(unassigned.isEmpty()) {
            return true;
        }

        String current = unassigned.get(0);
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
                solution.remove(current);
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

    private static List<String> randomize(List<String> participants) {
        long seed = System.nanoTime();

        Collections.shuffle(participants, new Random(seed));

        return participants;
    }
}
