package peer;

import utils.ChunkUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class PeerServer {

    private static final int PORT = 10000;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Peer server running on port " + PORT);

            while (true) {

                Socket socket = serverSocket.accept();

                new Thread(() -> handleClient(socket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {

        try (
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));

                OutputStream output =
                        socket.getOutputStream()
        ) {

            String request = reader.readLine();

            String[] parts = request.split(" ");

            String fileName = parts[0];
            int chunkIndex = Integer.parseInt(parts[1]);

            File file = new File("shared/" + fileName);

            List<byte[]> chunks = ChunkUtils.splitFile(file, 1024);

            if (chunkIndex < chunks.size()) {

                byte[] chunk = chunks.get(chunkIndex);

                output.write(chunk);
                output.flush();
            }

            System.out.println("Sent chunk " + chunkIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}