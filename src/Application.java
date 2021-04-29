import java.io.IOException;

import io.prometheus.client.exporter.HTTPServer;

/**
 * 
 * @author Austin Boydston
 * 
 * The main class that contains the main method and runs the application 
 */
public class Application {


	
	static HTTPServer metricsServer;
	static GUIBuild gui;
	
	static Station st;
	
	public static void main(String args[]) throws IOException
	{
		metricsServer = new HTTPServer(8009);
		gui = new GUIBuild();	
	}		
	
}
