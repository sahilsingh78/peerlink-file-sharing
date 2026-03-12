package peer;

import java.io.FileOutputStream;

public class PeerClient {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 10000;

        String fileName = "example.txt";

        int totalChunks = 4;

        try {

            ChunkDownloader[] workers = new ChunkDownloader[totalChunks];
            Thread[] threads = new Thread[totalChunks];

            for (int i = 0; i < totalChunks; i++) {

                workers[i] = new ChunkDownloader(host, port, fileName, i);

                threads[i] = new Thread(workers[i]);
                threads[i].start();
            }

            for (Thread t : threads) {
                t.join();
            }

            FileOutputStream fos =
                    new FileOutputStream("downloaded_example.txt");

            for (ChunkDownloader worker : workers) {

                fos.write(worker.getChunkData());
            }

            fos.close();

            System.out.println("File reconstructed successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}