package aek.demo.utils;

import aek.demo.domain.Result;
import aek.demo.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Class for json file writing.
 *
 * @author Atila Ekimci
 */
public class JsonWriter {

    private static final String fileName = "./products_data.json";

    /**
     * Gets @{@link Set<Product>} object and writes to a file through the static file path.
     *
     * @param data Data object that has the list of offers
     */
    public static void writeToJsonFile(Result data) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            mapper.registerModule(new Jdk8Module());
            mapper.writeValue(new File(fileName), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
