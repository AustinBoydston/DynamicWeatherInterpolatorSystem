import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Nearest Neighbor Interpolation Program
 * 
 * calculates the nearest mesonet station to the inputed location and returns
 * the station id for the script launchpad to use as input.
 * 
 * @author Austin Boydston
 *
 */

public class NearestNeighbor {

	// data fields

	//station object for calling station data
	Station st;

	//longitude and lattitude variables
	private double Longitude;
	private double Lattitude;

	//return variable for the nearest station
	private int nearestIndex = 0;
	private String nearest = "";
	private String[] outputMetrics = new String[22];
	
	
	// constructor
	NearestNeighbor(double lat, double lon, Station station) {
		//initialize the lattitude and longitude
		Longitude = lat;
		Lattitude = lon;
		
		//initialize the station
		st = station;
		
	}

	/**
	 * Run all the methods in the Nearest Neighbor class
	 */
	public void run()
	{
		findClosest();
		setOutputMetrics();
		setAllStationValues();
	}
	
	
	/**
	 * get the station as it's used here
	 * @return
	 */
	public Station getStation()
	{
		return st;
	}
	
	//set all the values of the outputMetrics array so that it can be passed on to the station object later
	public void setOutputMetrics()
	{
		for (int i = 0; i < 22; i++) {
			// if the column is the station id set the value to the custom ID INVL for
			// Inverse Linear
			if (i == 0) {
				outputMetrics[i] = st.getDataAtIndexes(nearestIndex, i);
				
			}
			// if the column is the name then set the value to the name given by the user.
			else if (i == 1) {
				outputMetrics[i] = st.getDataAtIndexes(nearestIndex, i);
				
			}
			// if the column is one of the time data columns print the time data
			else if (i < 10) {
				outputMetrics[i] = st.getDataAtIndexes(2,  i);
				
			}
			// if the column is the wind direction column then print the wind direction of a
			// close station
			else if (i == 15) {
				outputMetrics[i]=st.getDataAtIndexes(nearestIndex, i);
				
			}
			// if any other index then interpolate the data and print it
			else {
				outputMetrics[i] = st.getDataAtIndexes(nearestIndex, i);
				//System.out.print(inverseLinearIth(i) + ",");
			}
		}
	}
	
	/**
	 * return the output metrics array for testing
	 * @return
	 */
	public String[] getOutputMetricsArray()
	{
		return outputMetrics;
	}
	
		/*
		 * set all the local variables to the out put of the inverse linear calculation
		 */
		public void setAllStationValues()
		{
			st.setStationID(outputMetrics[0]);
			st.setStationName(outputMetrics[1]);
			st.setState(outputMetrics[2]);
			st.setLattitude(Double.parseDouble(outputMetrics[3]));
			st.setLongitude(Double.parseDouble(outputMetrics[4]));
			st.setYear(Integer.parseInt(outputMetrics[5]));
			st.setMonth(Integer.parseInt(outputMetrics[6]));
			st.setDay(Integer.parseInt(outputMetrics[7]));
			st.setHour(Integer.parseInt(outputMetrics[8]));
			st.setMinute(Integer.parseInt(outputMetrics[9]));
			st.setAirTemp(Double.parseDouble(outputMetrics[10]));
			st.setDewPointTemp(Double.parseDouble(outputMetrics[11]));
			st.setRelHumidity(Double.parseDouble(outputMetrics[12]));
			st.setWindChill(Double.parseDouble(outputMetrics[13]));
			st.setHeatIndex(Double.parseDouble(outputMetrics[14]));
			st.setWindDir(outputMetrics[15]);
			st.setWindSpeed(Double.parseDouble(outputMetrics[16]));
			st.setMaxWindSpeed(Double.parseDouble(outputMetrics[17]));
			st.setAirPressure(Double.parseDouble(outputMetrics[18]));
			st.setMaxAirTemp(Double.parseDouble(outputMetrics[19]));
			st.setMinAirTemp(Double.parseDouble(outputMetrics[20]));
			st.setDewPointTemp(Double.parseDouble(outputMetrics[21]));
			
		}
	
	
	// set nearest to the closest station to the given coordinates
	public void findClosest() {
		// Station st = new Station();

		int getdex = -1;
		double shortestDistance = 1000000000;

		double temp = 0;
		for (int i = 1; i < 121; i++) {
			temp = distance(Longitude, Lattitude, Double.valueOf(st.getDataAtIndexes(i, 3)), Double.valueOf(st.getDataAtIndexes(i, 4)));
			if (temp < shortestDistance) {
				shortestDistance = temp;
				getdex = i;
			}
		}

		// set the value of the nearest station and print it
		nearest = st.getDataAtIndexes(getdex, 0);
		nearestIndex = getdex;
		System.out.println(nearest);
		;

	}

	// given two gps coordinates return the distance between the two
	public double distance(double lon1, double lat1, double lon2, double lat2) {
		// Convert the latitudes and longitudes
		// from degree to radians.
		lat1 = toRadians(lat1);
		lon1 = toRadians(lon1);
		lat2 = toRadians(lat2);
		lon2 = toRadians(lon2);

		// Haversine Formula
		double dlong = lon2 - lon1;
		double dlat = lat2 - lat1;
		double result = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlong / 2), 2);

		result = 2 * Math.asin(Math.sqrt(result));

		// Radius of Earth in kilometers, r = 6371
		// Use R = 3956 for miles
		double r = 6371;

		// Calculate the result
		result = result * r;
		
		return result;
	}

	// convert degrees into radians for calculations
	private double toRadians(double degree) {
		// conver the given value to radians
		double oneDegree = (Math.PI) / 180;
		return (oneDegree * degree);
	}

	// get the value in nearest
	public String getNearest() {
		return nearest;
	}

}
