import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Printer {

    public static void print(Map<String, String> solution) throws Exception {
        try{
            PrintWriter writer = new PrintWriter("Solution.txt", "UTF-8");

            for(Map.Entry<String, String> entry : solution.entrySet()) {
                writer.println(entry.getKey() + " -> " + entry.getValue());

                PrintWriter personalWriter = new PrintWriter("solutions/" + entry.getKey() + ".txt", "UTF-8");
                personalWriter.println("You have to buy a present for: " + entry.getValue());
                personalWriter.close();
            }

            writer.close();
        } catch (Exception e) {
            throw new Exception("Couldn't write all solution");
        }
    }
}
