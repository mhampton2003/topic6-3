package app;

public class ServerApp {
	/**
	 * Puts a "." and waits for the messages to be sent from the client
	 * 
	 * @param args Takes any number of values
	 * @throws InterruptedException Thrown if anything interrupts the thread while it is running
	 */
	public static void main(String args[]) throws InterruptedException {
		//creates and starts a ServerThread thread
		ServerThread st = new ServerThread();
		st.start();
		
		//print a "." then sleep for five seconds ten times
		while (st.isAlive()) {
			System.out.println(".");
			Thread.sleep(5000);
		}
		
		
	}

}
