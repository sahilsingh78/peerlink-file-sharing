package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChunkUtils {

    public static List<byte[]> splitFile(File file, int chunkSize) throws IOException {

        List<byte[]> chunks = new ArrayList<>();

        FileInputStream fis = new FileInputStream(file);

        byte[] buffer = new byte[chunkSize];
        int bytes;

        while ((bytes = fis.read(buffer)) != -1) {

            byte[] chunk = new byte[bytes];
            System.arraycopy(buffer, 0, chunk, 0, bytes);

            chunks.add(chunk);
        }

        fis.close();

        return chunks;
    }
}