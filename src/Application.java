import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

/**
 * 
 * @author Austin Boydston
 * 
 * The main class that contains the main method and runs the application 
 */
public class Application {

	static final Gauge  inprogressRequests = Gauge.build().name("inprogress_requests").help("Inprogress requests.").register();
	static final Gauge AirTemperature = Gauge.build().name("air_temperature").help("Air Temperature").register();
	static final Gauge DewPointTemperature = Gauge.build().name("dewpoint_temperature").help("Dewpoint Temperature").register();	
	static final Gauge RelativeHumidity = Gauge.build().name("relative_humidity").help("Relative Humidity").register();
	static final Gauge WindChill = Gauge.build().name("wind_chill").help("Wind Chill").register();
	static final Gauge HeatIndex = Gauge.build().name("heat_index").help("Heat Index").register();
	static final Gauge WindDirection = Gauge.build().name("wind_direction").help("Wind Direction").register();
	static final Gauge WindSpeed = Gauge.build().name("wind_speed").help("Wind Speed").register();	
	static final Gauge MaxWindSpeed = Gauge.build().name("max_wind_speed").help("Max Wind Speed").register();
	static final Gauge AirPressure = Gauge.build().name("air_pressure").help("Air Pressure").register();
	static final Gauge MaxAirTemperature = Gauge.build().name("max_air_temperature").help("Max Air Temperature").register();
	static final Gauge MinAirTemperature = Gauge.build().name("min_air_temperature").help("Min Air Temperature").register();
	static final Gauge Precipitation = Gauge.build().name("precipitation").help("Precipitation").register();
	
	
	static HTTPServer metricsServer;
	static GUIBuild gui;
	
	static Station st;
	
	public static void main(String args[]) throws IOException
	{
		
		gui = new GUIBuild();
		
		metricsServer = new HTTPServer(8009);
		
		//run infinite loop of updating metrics until the program gui is exited
		while(gui.getExitBoolean())
		{
			
		
			//set all the metrics
			AirTemperature.set(st.getAirTemp());
			DewPointTemperature.set(st.getDewPointTemp());
			RelativeHumidity.set(st.getRelHumidity());
			WindChill.set(st.getWindChill());
			HeatIndex.set(st.getHeatIndex());
			WindDirection.set(st.getWindDir());
			WindSpeed.set(st.getWindSpeed());
			MaxWindSpeed.set(st.getMaxWindSpeed());
			AirPressure.set(st.getAirPressure());
			MaxAirTemperature.set(st.getMaxAirTemp());
			MinAirTemperature.set(st.getMinAirTemp());
			Precipitation.set(st.getPrecipitation());
			
			
			
		//wait 30 seconds
		try {
		    TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		}
		
	}
	 void processRequest() {
		    inprogressRequests.inc();
		   
		    
		    // Your code here.
		    inprogressRequests.dec();
		  }
}
