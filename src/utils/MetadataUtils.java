package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MetadataUtils {

    public static Map<String,String> readMetadata(String path) throws IOException {

        Map<String,String> meta = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;

        while((line = reader.readLine()) != null){

            String[] parts = line.split("=");

            meta.put(parts[0], parts[1]);
        }

        reader.close();

        return meta;
    }
}