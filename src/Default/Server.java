package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT = 2020;

	public static void main(String[] args) throws Exception {
		

		String capitalMessage;
		ServerSocket serverSoket=new ServerSocket(SERVER_PORT);
		System.out.println("sever running in : "+SERVER_PORT);
		while(true) {
			Socket socket=serverSoket.accept();
			String clientMessage=doReceive(socket);
			
			
			
			System.out.println("received "+clientMessage);
			capitalMessage=cpitalizedMessage(clientMessage);
			
			doSend(socket,capitalMessage);
			
			
		}

	}

	private static void doSend(Socket socket, String capitalMessage) throws Exception {
		DataOutputStream outToClient=new DataOutputStream(socket.getOutputStream());
		outToClient.writeBytes(capitalMessage);
	}

	private static String cpitalizedMessage(String clientMessage) {
		
		return clientMessage.toUpperCase()+"\n";
	}

	private static String doReceive(Socket socket) throws IOException {
		DataInputStream inFromClient=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		return inFromClient.readLine();
	}

}