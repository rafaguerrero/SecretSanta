import java.util.*;

public class ResourcesPropertiesLoader {

    public void mapProperties(String propertyFile, Map<String, List<String>> mapper) throws Exception{
        Properties properties = new Properties();
        loadProperties(properties, propertyFile);
        properties.stringPropertyNames().forEach(key -> mapper.put(key, new ArrayList(Arrays.asList(properties.getProperty(key).split(",")))));
    }

    public void mapProperties(String propertyFile, List<String> mapper) throws Exception {
        Properties properties = new Properties();
        loadProperties(properties, propertyFile);
        properties.stringPropertyNames().forEach(key -> mapper.add(key));
    }

    private void loadProperties(Properties properties, String propertyFile) throws Exception {
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFile));
    }
}
