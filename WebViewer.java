import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class WebViewer {

	public static void main(String[] args) {
		// Prompt user for URL and store result in a String
		String url;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a url: ");
		url = scan.nextLine();
		
		// Pass the url to the getSource method to obtain the source code
		try {
			String source = getSource(url);
			System.out.println(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Close the scanner
		scan.close();

	}

	private static String getSource(String url) throws IOException {
        URL randomUrl = new URL(url);
        // Establish a URL connection and read in the source code 
        URLConnection yourConnection = randomUrl.openConnection();
        BufferedReader readIn = new BufferedReader(new InputStreamReader(
                yourConnection.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder sb = new StringBuilder();
        // While there is more source code keep appending sb
        while ((inputLine = readIn.readLine()) != null)
            sb.append(inputLine);
        readIn.close();

        // Return the source code of the URL
        return sb.toString();
    }
}



