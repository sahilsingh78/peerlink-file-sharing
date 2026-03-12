package peer;

import java.io.*;
import java.net.Socket;

public class ChunkDownloader implements Runnable {

    private String host;
    private int port;
    private int chunkIndex;
    private String fileName;

    private byte[] chunkData;

    public ChunkDownloader(String host, int port, String fileName, int chunkIndex) {

        this.host = host;
        this.port = port;
        this.fileName = fileName;
        this.chunkIndex = chunkIndex;
    }

    public byte[] getChunkData() {
        return chunkData;
    }

    @Override
    public void run() {

        try (
                Socket socket = new Socket(host, port);

                PrintWriter writer =
                        new PrintWriter(socket.getOutputStream(), true);

                InputStream input =
                        socket.getInputStream()
        ) {

            writer.println(fileName + " " + chunkIndex);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            byte[] data = new byte[1024];
            int bytes;

            while ((bytes = input.read(data)) != -1) {

                buffer.write(data, 0, bytes);
            }

            chunkData = buffer.toByteArray();

            System.out.println("Downloaded chunk " + chunkIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}