package peer;

import java.io.*;
import java.net.Socket;

public class PeerClient {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 10000;

        try (

                Socket socket = new Socket(host, port);

                PrintWriter writer = new PrintWriter(
                        socket.getOutputStream(), true);

                InputStream input = socket.getInputStream()

        ) {

            writer.println("example.txt");

            FileOutputStream fos = new FileOutputStream("downloaded_example.txt");

            byte[] buffer = new byte[4096];
            int bytes;

            while ((bytes = input.read(buffer)) != -1) {
                fos.write(buffer, 0, bytes);
            }

            fos.close();

            System.out.println("File downloaded");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}