package app;

import java.net.*;
import java.io.*;

/**
 * @author Maya
 */
public class Server {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	/**
	 * Start the Server and wait for connections on the specified port
	 * 
	 * @param port Port to listen on
	 * @throws IOException Thrown in the networking classes if something bad happened
	 */
	public void start(int port) throws IOException{
		//Wait for a Client connection
		System.out.println("Waiting for a Client connection.......");
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		
		//a Client connected to this Server so create some input and output network buffers
		System.out.println("Received a Client connection on port " + clientSocket.getLocalPort());
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		//wait for Command (string that is terminated by a line feed character)
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			//if period command then shut the Server down
			if (".".equals(inputLine)) {
				System.out.println("Got a message to shut the Server down");
				out.println("QUIT");
				break;
			}
			else {
				//echo an acknowledgement back to the Client that Command was processed successfully
				System.out.println("Got a message of: " + inputLine);
				out.println("OK");
			}
		}
		
		//exit message that Server is shut down
		System.out.println("Server is shut down");
	}
	
	/**
	 * cleanup logic to close all the network connections
	 * 
	 * @throws IOException Thrown if anything bad happens from the networking classes
	 */
	public void cleanup() throws IOException{
		//close all input and output network buffers and sockets
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}

	/**
	 * entry method for the Server application (for testing only)
	 * 
	 * @param args Takes in any number of values
	 * @throws IOException Thrown if anything bad happens from the networking classes
	 */
	public static void main(String[] args) throws IOException {
		//create an instance of the Server
		//start the Server on port 6666 (which will not return until the Shutdown Command is received)
		//and then on exit clean everything up
		Server server = new Server();
		server.start(6666);
		server.cleanup();

	}

}
