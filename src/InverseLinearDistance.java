import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
 * Class to calculate the inverse linear distance interpolation of the given location
 * 
 */

public class InverseLinearDistance {

	Station st;

	// p is the parameter for the power function
	private int p;
	// n is the amount of stations within a certain radius of the point
	private int n;
	// the maximum distance at which a station will be considered for interpolation:
	// constant
	private final double RANGE = 70;
	private final int NUM_STATIONS = 121;
	private final int GPS_INDEX = 3;

	private double lon;
	private double lat;

	// stores the row index of the stations close enough to the point to consider
	// for the interpolation
	private ArrayList<Integer> nearStationsIndex;
	// Stores the distance from the point to the station of the stations that are
	// close enough to consider for the interpolation
	private ArrayList<Double> nearStationsDistance;

	// constructor: initializes the data fields
	InverseLinearDistance(double longitude, double lattitude) throws FileNotFoundException {
		st = new Station();
		// st.populate();
		lon = longitude;
		lat = lattitude;
		p = 2;
		n = 0;
		nearStationsIndex = new ArrayList<Integer>();
		nearStationsDistance = new ArrayList<Double>();
	}

	public void InverseLinearInterpolation() {
		// call the near stations method to initialize the sister arrays of near
		// stations
		calculateNearStations();
		// loop through the column of the csv files data
		for (int i = 0; i < 22; i++) {
			// if the column is the station id set the value to the custom ID INVL for
			// Inverse Linear
			if (i == 0) {
				System.out.print("INVL,");
			}
			// if the column is the name then set the value to the name given by the user.
			else if (i == 1) {
				System.out.print("Location,");
			}
			// if the column is one of the time data columns print the time data
			else if (i < 10) {
				System.out.print(st.getDataAtIndexes(2, i) + ",");
			}
			// if the column is the wind direction column then print the wind direction of a
			// close station
			else if (i == 15) {
				System.out.print(st.getDataAtIndexes(nearStationsIndex.get(0), i) + ",");
			}
			// if any other index then interpolate the data and print it
			else {
				System.out.print(inverseLinearIth(i) + ",");
			}
		}
	}

	/*
	 * get the Inverse Linear value with the given stations
	 * 
	 * formula for inverse linear calculations = { [ (value of ith station)^2 /
	 * (distance of ith station)^2 ] + ... + [vn^2/ n^2] } / [ (1/distance of ith
	 * station)^2 + (1/distance of ith + 1 station)^2 + ... +(1/n)^2 ]
	 * 
	 * columnIndex is the index of the column of the specific data we are
	 * interpolating. e.g. temperature, atmospheric pressue
	 */

	public double inverseLinearIth(int columnIndex) {
		
		double weight = 0.0;
		double distance = 0.0;
		double returnValNumerator = 0.0;
		double returnValDenominator = 0.0;
		// loop through the array(s) of near stations
		for (int i = 0; i < nearStationsDistance.size(); i++) {
			if (!st.getDataAtIndexes(nearStationsIndex.get(i), columnIndex).trim().contentEquals("")) {
				// get the summation of the numerator
				if(nearStationsDistance.get(i) != 0)
				{
				//calculate the weight and get the distance for the Inverse Weight Distance calculation	
				weight = 1 / Math.pow(nearStationsDistance.get(i), p);
				distance = Double.parseDouble(st.getDataAtIndexes(nearStationsIndex.get(i), columnIndex));
					
				//Sum the new values to the previous ones
				returnValNumerator = returnValNumerator +  (weight * distance);
				// get the summation of the denominator
				returnValDenominator = returnValDenominator + weight;
				}
				//System.out.println(returnValNumerator);
			}
		}
		//if the stations didn't give data (i.e. the values in the csv are null) then return 0
		if(returnValDenominator == 0)
		{
			return 0;
		}
		return (returnValNumerator / returnValDenominator);
	}

	// store the nearest stations distance into the array as well as the index they
	// were found in the file at
	private void calculateNearStations() {
		// temporary variable to store the distance in order to prevent calling the
		// function more than once
		double temp = 0;

		for (int i = 1; i < NUM_STATIONS; i++) {
			// calculate the distance between the given point (first two parameters) and the
			// gps location of the ith station (second two parameters)
			temp = distance(lon, lat, Double.parseDouble(st.getDataAtIndexes(i, GPS_INDEX)),
					Double.parseDouble(st.getDataAtIndexes(i, GPS_INDEX + 1)));
			
			// if the stations are within the range store the index and the distance in two
			// sister arrays
			if (temp <= RANGE) {
				nearStationsIndex.add(i);
				nearStationsDistance.add(temp);
			}
		}
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

	// convert degrees into radians for calculations
	private double toRadians(double degree) {
		// conver the given value to radians
		double oneDegree = (Math.PI) / 180;
		return (oneDegree * degree);
	}

	// loop through the different stations and find all within a fixed distance of
	// the given location

	// of these calculate

}
