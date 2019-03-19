package utilities;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONParser {

    private ObjectMapper objectMapper;
    private File file;

    /**
     * The ObjectMapper() and File(path) instantiation happens here
     * @param dataPath The path to the JSON file. Can be found at the
     *                 resources folder
     */
    public JSONParser(String dataPath) {
        objectMapper = new ObjectMapper();
        file = new File(dataPath);
    }

    /**
     * Reads data from a JSON file and converts it to a Java object.
     * Note that method won't work for nested JSON objects.
     * @param module The class to which the JSON object will be mapped to
     * @param <T> Generic parameter for the ability to pass in different module types
     * @return Returns a Java object
     * @throws IOException
     */
    public <T> T serializeAsJavaObject(Class<T> module) throws IOException {
        return objectMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
                .readValue(file, module);
    }

    /**
     * Reads data from a JSON file and converts it to a Java object.
     * This method will only work for nested JSON objects.
     *
     * Note: This method does not yet work as of 12/17. Will need to revisit this
     * again at a later time.
     *
     * @param module The class to which the JSON object will be mapped to
     * @param <T> Generic parameter for the ability to pass in different module types
     * @return Returns a list of Java object
     * @throws IOException
     */
    public <T> List<T> serializeAsJavaObjectList(Class<T> module) throws IOException {
        return objectMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
                .readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, module));
    }
}
