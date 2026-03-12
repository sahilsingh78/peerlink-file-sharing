package peer;

import utils.MetadataUtils;

import java.io.FileOutputStream;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class PeerClient {

    public static void main(String[] args) {

        try {

            Map<String,String> meta =
                    MetadataUtils.readMetadata("metadata/example.peerlink");

            String fileName = meta.get("filename");
            int chunks = Integer.parseInt(meta.get("chunks"));

            List<String> peers = getPeers();

            String[] peerInfo = peers.get(0).split(":");

            String host = peerInfo[0];
            int port = Integer.parseInt(peerInfo[1]);

            ChunkDownloader[] workers = new ChunkDownloader[chunks];
            Thread[] threads = new Thread[chunks];

            for (int i = 0; i < chunks; i++) {

                workers[i] = new ChunkDownloader(host, port, fileName, i);

                threads[i] = new Thread(workers[i]);
                threads[i].start();
            }

            for (Thread t : threads) {
                t.join();
            }

            FileOutputStream fos =
                    new FileOutputStream("downloaded_" + fileName);

            for (ChunkDownloader worker : workers) {

                fos.write(worker.getChunkData());
            }

            fos.close();

            System.out.println("File reconstructed successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getPeers() {

        List<String> peers = new ArrayList<>();

        try (
                Socket socket = new Socket("localhost", 9000);

                PrintWriter writer =
                        new PrintWriter(socket.getOutputStream(), true);

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            writer.println("GET_PEERS");

            String line;

            while (!(line = reader.readLine()).equals("END")) {

                peers.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return peers;
    }
}