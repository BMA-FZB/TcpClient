package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int SERVER_PORT = 2020;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is listening on port " + SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                String clientMessage = doReceive(clientSocket);
                System.out.println("Received: " + clientMessage);
                

                String capitalizedMessage = doCapitalize(clientMessage);
                doSend(clientSocket, capitalizedMessage);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String doReceive(Socket socket) throws IOException {
        try (DataInputStream inFromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) {
            return inFromClient.readLine();
        }
    }

    private static void doSend(Socket socket, String message) throws IOException {
        try (DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream())) {
            outToClient.writeBytes(message + "\n");
        }
    }

    private static String doCapitalize(String message) {
        return message.toUpperCase();
    }
}