import java.util.*;
import java.util.stream.Collectors;

public class MissingPeople {

    public static List<String> find(List<String> participants, Map<String, List<String>> rules) {
        List<String> missing = new ArrayList<>();

        participants.stream()
            .filter(p -> !rules.containsKey(p))
            .forEach(missing::add);

        return missing;
    }
}
