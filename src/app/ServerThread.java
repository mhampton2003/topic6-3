package app;

import java.io.IOException;

public class ServerThread extends Thread {
	
	/**
	 * override the run() method so the thread will run
	 */
	public void run() {
		//create an instance of a Server
		//start the Server on Port 6666 (which will not return until the Shutdown Command is received)
		//and then on exit clean everything up which will exit this thread
		Server server = new Server();
		try {
			server.start(6666);
			server.cleanup();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
