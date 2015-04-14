package berlin.vs.u1clientserver.server;

import java.io.*;
import java.net.*;

class TCPServer {
	public static void main(String argv[]) {
		try{
			startServer();
		}catch(Exception ex){
			System.out.println("Fehler beim Empfang der Daten: "+ex.getMessage());
		}
		
	}

	private static void startServer() throws Exception {

		String clientSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			System.out.println("Received: " + clientSentence);
			try{
				int result = compute(Integer.parseInt(clientSentence));
				System.out.println("Berechnet: "+result);
				outToClient.writeBytes(String.valueOf(result) + '\n');
			}catch(Exception e){
				System.out.println("Empfangene Daten sind ungueltig");
			}
			
		}

	}

	static int compute(int i) {
		if (i <= 0) // fuer negative Zahl auch 0!
			return 0;
		else if (i == 1)
			return 1;
		else
			return compute(i - 2) + compute(i - 1);
	}
}
