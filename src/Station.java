import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The station class represents a single mesonet station and its associated data like location, temperature, humidity etc
 * 
 * @author Austin Boydston 
 *
 */


public class Station {

	private String[][] stations = new String[121][22];
	
	
	//name and ID
	private String StationID;
	private String StationName;
	
	//location variables
	private double longitude;
	private double lattitude;
	private String state;
	
	//time variables
	private int year;
	private int month;
	private int day;
	private int hour;
	private int	minute;
	
	//Weather Data
	private double airTemp;
	private double dewPointTemp;
	private double relHumidity;
	private double windChill;
	private double heatIndex;
	private String windDir;
	private double windSpeed;
	private double maxWindSpeed;
	private double airPressure;
	private double maxAirTemp;
	private double minAirTemp;
	private double precipitation;
	
	Station() throws FileNotFoundException
	{
		populate();
	}
	
	//constructor
	public void setAllValues(String STID, String NAME, String ST, double LON, double LAT, int YEAR, int MO, int DA, int HR, int MI, double TAIR, double TDEW, double RELH, double CHIL, double HEAT, String WDIR, double WSPD, double WMAX, double PRES, double TMAX, double TMIN, double RAIN)
	{
		
		//initialize name variables
			StationID    = STID;
			StationName  = NAME;
		
		
		//initialize location variables
			state     = ST;	
			longitude = LON;
			lattitude = LAT;
			
		//initialize date variables
			year   = YEAR;
			month  =  MO;
			day    =  DA;
			hour   =  HR;
			minute =  MI;
		
		
		//initialize weather data variables
			airTemp       =  TAIR;
			dewPointTemp  =  TDEW;		
			relHumidity   =  RELH;		
			windChill     =  CHIL;		
			heatIndex     =  HEAT;		
			windDir       =  WDIR;		
			windSpeed     =  WSPD;		
			maxWindSpeed  =  WMAX;		
			airPressure   =  PRES;		
			maxAirTemp    =  TMAX;		
			minAirTemp    =  TMIN;		
			precipitation =  RAIN;		
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// get location data of mesonet statoins and populate the array with it.
		public void populate() throws FileNotFoundException {

			Scanner sc = new Scanner(new File(
					"C:\\Users\\ATB\\eclipse-workspace-2021\\DynamicWeatherInterpolatorSystem\\DemoData\\current.csv"));
			sc.useDelimiter(",");

			
			for(int i = 0; i < 121; i++)
			{
				String[] temp = sc.nextLine().split(",");
				for(int j = 0; j < 22; j++)
				{
				stations[i][j] = temp[j];
				}
			}
			sc.close();
		}

	
	//get the values from stations at the given index
	public String getDataAtIndexes(int i, int j)
	{
		
		if(i > 0 && i < 121 && j >= 0 && j < 22)
		{
		return stations[i][j];
		}
		else {
			System.err.println("getDataAtIndexes out of bounds");
			return "";
		}
	}	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters
	public String getStationID() {
		return StationID;
	}




	public String getStationName() {
		return StationName;
	}




	public double getLongitude() {
		return longitude;
	}




	public double getLattitude() {
		return lattitude;
	}




	public String getState() {
		return state;
	}




	public int getYear() {
		return year;
	}




	public int getMonth() {
		return month;
	}




	public int getDay() {
		return day;
	}




	public int getHour() {
		return hour;
	}




	public int getMinute() {
		return minute;
	}




	public double getAirTemp() {
		return airTemp;
	}




	public double getDewPointTemp() {
		return dewPointTemp;
	}




	public double getRelHumidity() {
		return relHumidity;
	}




	public double getWindChill() {
		return windChill;
	}




	public double getHeatIndex() {
		return heatIndex;
	}




	public String getWindDir() {
		return windDir;
	}




	public double getWindSpeed() {
		return windSpeed;
	}




	public double getMaxWindSpeed() {
		return maxWindSpeed;
	}




	public double getAirPressure() {
		return airPressure;
	}




	public double getMaxAirTemp() {
		return maxAirTemp;
	}




	public double getMinAirTemp() {
		return minAirTemp;
	}




	public double getPrecipitation() {
		return precipitation;
	}



	
	
	
	//Setters
	public void setStationID(String stationID) {
		StationID = stationID;
	}




	public void setStationName(String stationName) {
		StationName = stationName;
	}




	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}




	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}




	public void setState(String state) {
		this.state = state;
	}




	public void setYear(int year) {
		this.year = year;
	}




	public void setMonth(int month) {
		this.month = month;
	}




	public void setDay(int day) {
		this.day = day;
	}




	public void setHour(int hour) {
		this.hour = hour;
	}




	public void setMinute(int minute) {
		this.minute = minute;
	}




	public void setAirTemp(double airTemp) {
		this.airTemp = airTemp;
	}




	public void setDewPointTemp(double dewPointTemp) {
		this.dewPointTemp = dewPointTemp;
	}




	public void setRelHumidity(double relHumidity) {
		this.relHumidity = relHumidity;
	}




	public void setWindChill(double windChill) {
		this.windChill = windChill;
	}




	public void setHeatIndex(double heatIndex) {
		this.heatIndex = heatIndex;
	}




	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}




	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}




	public void setMaxWindSpeed(double maxWindSpeed) {
		this.maxWindSpeed = maxWindSpeed;
	}




	public void setAirPressure(double airPressure) {
		this.airPressure = airPressure;
	}




	public void setMaxAirTemp(double maxAirTemp) {
		this.maxAirTemp = maxAirTemp;
	}




	public void setMinAirTemp(double minAirTemp) {
		this.minAirTemp = minAirTemp;
	}




	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}
	
	
	
	
}
