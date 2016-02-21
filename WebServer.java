import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;
import java.net.ServerSocket;


public class WebServer {
	/**
	 * Constructor for WebServer
	 */
	@SuppressWarnings("resource")
	protected void start() {
		ServerSocket s;

		try {
			// Create the ServerSocket
			s = new ServerSocket(12000);
			System.out.println("Webserver started on port 12000");
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return;
		}

		System.out.println("Patiently waiting for a connection... Any day now...");
		while (true) {
			try {
				// Accept the connection
				Socket remoteConnection = s.accept();
				
				// remoteConnection is now a connected socket
				System.out.println("Connection detected, sending data now.");
				BufferedReader readIn = new BufferedReader(new InputStreamReader(
						remoteConnection.getInputStream()));
				PrintWriter out = new PrintWriter(remoteConnection.getOutputStream());

				// Read data until we hit a blank line, which will signal the end of the client HTTP headers.
				String str = ".";
				while (!str.equals(""))
					str = readIn.readLine();

				// Send the response and headers out
				out.println("HTTP/1.0 200 OK");
				out.println("Content-Type: text/html");
				out.println("Server: Bot");
				
				// Signal the end of the headers with a blank line
				out.println("");
				
				// Send the HTML page
				out.println("<h1>Hello CS374!</h1>");
				out.flush();
				remoteConnection.close();
			} catch (Exception e) {
				System.out.println("Warning! an error has occured: " + e);
			}
		}
	}

	/**
	 * Start the application
	 */
	public static void main(String[] args) {
		WebServer planetAwesome = new WebServer();
		planetAwesome.start();
	}
}
