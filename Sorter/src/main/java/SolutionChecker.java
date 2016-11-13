import java.util.*;

public class SolutionChecker {

    public static boolean check(List<String> participants, Map<String, String> solution) {
        Set<String> keys = new HashSet<>();
        Set<String> values = new HashSet<>();

        solution.forEach((k, v) -> {
            keys.add(k);
            values.add(v);
        });

        return keys.size() == participants.size() && values.size() == participants.size();
    }
}
