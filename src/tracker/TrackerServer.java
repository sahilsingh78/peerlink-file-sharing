package tracker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class TrackerServer {

    private static Set<String> peers = new HashSet<>();

    public static void main(String[] args) {

        int port = 9000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Tracker running on port " + port);

            while (true) {

                Socket socket = serverSocket.accept();
                new Thread(() -> handleClient(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {

        try (
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));

                PrintWriter writer =
                        new PrintWriter(socket.getOutputStream(), true)
        ) {

            String command = reader.readLine();

            if (command.startsWith("REGISTER")) {

                String peer = command.split(" ")[1];
                peers.add(peer);

                writer.println("REGISTERED");

            } else if (command.equals("GET_PEERS")) {

                for (String peer : peers) {
                    writer.println(peer);
                }

                writer.println("END");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}