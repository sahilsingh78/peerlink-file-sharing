package peer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerServer {

    public static void main(String[] args) {

        int port = 10000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Peer server running on port " + port);

            while (true) {

                Socket socket = serverSocket.accept();

                new Thread(() -> sendFile(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(Socket socket) {

        try (

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                OutputStream output = socket.getOutputStream()

        ) {

            String fileName = reader.readLine();

            File file = new File("shared/" + fileName);

            if (!file.exists()) {
                output.write("FILE_NOT_FOUND".getBytes());
                return;
            }

            FileInputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[4096];
            int bytes;

            while ((bytes = fis.read(buffer)) != -1) {
                output.write(buffer, 0, bytes);
            }

            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}