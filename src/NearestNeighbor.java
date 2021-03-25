/**
 * Nearest Neighbor Interpolation Program
 * 
 * calculates the nearest mesonet station to the inputed location and returns the station id for the script launchpad to use as input. 
 * 
 * @author Austin Boydston
 *
 */

public class NearestNeighbor {

	//data fields
	Station st
	
	//constructor
	NearestNeighbor(Double lat, Double lon)
	{
		this.lat = lat;
		this.lon = lon;
	}
	
	
	public Station findClosest()
	{
		Station st = new Station();
		return st;
	}
	
}
