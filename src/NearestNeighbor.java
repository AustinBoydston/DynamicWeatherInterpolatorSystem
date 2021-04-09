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
	Station st = new Station();

	// station sets
	private String[][] stations = new String[121][5];

	private double Longitude;
	private double Lattitude;

	private String nearest = "";

	// constructor
	NearestNeighbor(double lat, double lon) {
		Longitude = lat;
		Lattitude = lon;
	}

	// get location data of mesonet statoins and populate the array with it.
	public void populate() throws FileNotFoundException {

		Scanner sc = new Scanner(new File(
				"C:\\Users\\ATB\\eclipse-workspace-2021\\DynamicWeatherInterpolatorSystem\\DemoData\\locations.csv"));
		sc.useDelimiter(",");

		int i = 0;

		while (sc.hasNext() && i < 121) {
			for (int j = 0; j < 5; j++) {
				if (sc.hasNext() && i < 121)
					stations[i][j] = sc.next();
			}
			i++;
		}

		/*
		 * //see if it populated correctly for(int h = 0; h < 121; h++) { for(int j = 0;
		 * j < 5; j++) { System.out.print(stations[h][j]); } System.out.println(); }
		 */
		sc.close();
	}

	// set nearest to the closest station to the given coordinates
	public void findClosest() {
		// Station st = new Station();

		int getdex = -1;
		double shortestDistance = 1000000000;

		double temp = 0;
		for (int i = 1; i < 121; i++) {
			temp = distance(Longitude, Lattitude, Double.valueOf(stations[i][3]), Double.valueOf(stations[i][4]));
			if (temp < shortestDistance) {
				shortestDistance = temp;
				getdex = i;
			}
		}

		// set the value of the nearest station and print it
		nearest = stations[getdex][0];
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
		double result = Math.pow(Math.sin(dlat / 2), 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlong / 2), 2);

		result = 2 * Math.asin(Math.sqrt(result));

		// Radius of Earth in kilometers, r = 6371
		// Use R = 3956 for miles
		double r = 6371;

		// Calculate the result
		result = result * r;

		return result;
	}

	//convert degrees into radians for calculations
	private double toRadians(double degree) {
		// conver the given value to radians
		double oneDegree = (Math.PI) / 180;
		return (oneDegree * degree);
	}

	//get the value in nearest
	public String getNearest()
	{
		return nearest;
	}
	
}
