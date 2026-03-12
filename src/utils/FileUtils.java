package utils;

import java.io.File;

public class FileUtils {

    public static boolean fileExists(String path) {

        File file = new File(path);

        return file.exists() && file.isFile();
    }
}