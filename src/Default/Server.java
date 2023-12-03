package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		String clientMessage;
		String capitalMessage;
		ServerSocket severSoket=new ServerSocket(2020);
		while(true) {
			Socket socket=severSoket.accept();
			DataInputStream inFromClient=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			DataOutputStream outToClient=new DataOutputStream(socket.getOutputStream());
			clientMessage=inFromClient.readLine();
			System.out.println("received "+clientMessage);
			capitalMessage=clientMessage.toUpperCase()+"\n";
			outToClient.writeBytes(capitalMessage);
		}

	}

}
