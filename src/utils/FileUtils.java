package utils;

import java.io.File;

public class FileUtils {

    public static boolean fileExists(String path) {

        File file = new File(path);
        return file.exists() && file.isFile();
    }

    public static long getFileSize(String path) {

        File file = new File(path);

        if (file.exists()) {
            return file.length();
        }

        return 0;
    }
}