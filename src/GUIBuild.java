	import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import io.prometheus.client.Gauge;
	/**
	 * Dynamic Weather Interpolator System Interface
	 * @author  Austin Boydston
	 * @created February 25, 2021
	 */
	
public class GUIBuild extends JFrame implements ActionListener {
	
	
	//fields for scanning data
	Scanner sc;  
	String[][] set = new String[22][121];
	int fileNum = 0;
	
	static private boolean exited = false;
	Station st;
	
	
	//metrics
	static final Gauge  inprogressRequests = Gauge.build().name("dwis_inprogress_requests").help("Inprogress requests.").register();
	static final Gauge AirTemperature = Gauge.build().name("dwis_air_temperature").help("Air Temperature").register();
	static final Gauge DewPointTemperature = Gauge.build().name("dwis_dewpoint_temperature").help("Dewpoint Temperature").register();	
	static final Gauge RelativeHumidity = Gauge.build().name("dwis_relative_humidity").help("Relative Humidity").register();
	static final Gauge WindChill = Gauge.build().name("dwis_wind_chill").help("Wind Chill").register();
	static final Gauge HeatIndex = Gauge.build().name("dwis_heat_index").help("Heat Index").register();
	//static final Gauge WindDirection = Gauge.build().name("dwis_wind_direction").help("Wind Direction").register();
	static final Gauge WindSpeed = Gauge.build().name("dwis_wind_speed").help("Wind Speed").register();	
	static final Gauge MaxWindSpeed = Gauge.build().name("dwis_max_wind_speed").help("Max Wind Speed").register();
	static final Gauge AirPressure = Gauge.build().name("dwis_air_pressure").help("Air Pressure").register();
	static final Gauge MaxAirTemperature = Gauge.build().name("dwis_max_air_temperature").help("Max Air Temperature").register();
	static final Gauge MinAirTemperature = Gauge.build().name("dwis_min_air_temperature").help("Min Air Temperature").register();
	static final Gauge Precipitation = Gauge.build().name("dwis_precipitation").help("Precipitation").register();
	
	
	//Class Object Declarations
	InverseLinearDistance inv1;
	NearestNeighbor n1;
	
	
	//fields for the gui components 
	static GUIBuild theGUIBuild;

	JPanel pnPanel0;
	ButtonGroup rbgPanel0;
	JButton btBut0;
	JTextField tfText0;
	JTextField tfText1;
	JTextField tfText2;
	JRadioButton rbRdBut0;
	JRadioButton inverseButton;
	JLabel lbLabel0;
	JLabel lbLabel1;
	JLabel lbLabel2;
	JLabel lbLabel3;
	JLabel lbLabel6;
	/**
	 * @throws FileNotFoundException 
	 */
	public static void main( String args[] ) throws FileNotFoundException 
	{
	   try 
	   {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	   }
	   catch ( ClassNotFoundException e ) 
	   {
	   }
	   catch ( InstantiationException e ) 
	   {
	   }
	   catch ( IllegalAccessException e ) 
	   {
	   }
	   catch ( UnsupportedLookAndFeelException e ) 
	   {
	   }
	 
	   
	  // theGUIBuild = new GUIBuild();
	   theGUIBuild.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	exited = true;
	            System.exit(0);
	        }
	    });
	   
	   	} 

	/**
	 * @throws FileNotFoundException 
	 */
	public GUIBuild() throws FileNotFoundException 
	{
		
		
	   super( "TITLE" );
	   
	   //initialize the new station
	   st = new Station();
	   
	   exited = false;
	   //Primary Panel
	   pnPanel0 = new JPanel();
	   rbgPanel0 = new ButtonGroup();
	   GridBagLayout gbPanel0 = new GridBagLayout();
	   GridBagConstraints gbcPanel0 = new GridBagConstraints();
	   pnPanel0.setLayout( gbPanel0 );

	   btBut0 = new JButton( "Generate Dashboard"  );
	   gbcPanel0.gridx = 14;
	   gbcPanel0.gridy = 18;
	   gbcPanel0.gridwidth = 6;
	   gbcPanel0.gridheight = 2;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 0;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( btBut0, gbcPanel0 );
	   pnPanel0.add( btBut0 );

	 
	   
	   tfText0 = new JTextField( );
	   gbcPanel0.gridx = 0;
	   gbcPanel0.gridy = 5;
	   gbcPanel0.gridwidth = 9;
	   gbcPanel0.gridheight = 2;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 0;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( tfText0, gbcPanel0 );
	   pnPanel0.add( tfText0 );

	   tfText1 = new JTextField( );
	   gbcPanel0.gridx = 0;
	   gbcPanel0.gridy = 9;
	   gbcPanel0.gridwidth = 9;
	   gbcPanel0.gridheight = 2;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 0;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( tfText1, gbcPanel0 );
	   pnPanel0.add( tfText1 );

	   tfText1 = new JTextField( );
	   gbcPanel0.gridx = 11;
	   gbcPanel0.gridy = 9;
	   gbcPanel0.gridwidth = 9;
	   gbcPanel0.gridheight = 2;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 0;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( tfText1, gbcPanel0 );
	   pnPanel0.add( tfText1 );

	   
	   rbRdBut0 = new JRadioButton( "Nearest Neighbor"  );
	   rbgPanel0.add( rbRdBut0 );
	   gbcPanel0.gridx = 0;
	   gbcPanel0.gridy = 12;
	   gbcPanel0.gridwidth = 1;
	   gbcPanel0.gridheight = 1;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 0;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( rbRdBut0, gbcPanel0 );
	   pnPanel0.add( rbRdBut0 );

	   inverseButton = new JRadioButton( "Inverse Linear Distance"  );
	   rbgPanel0.add( inverseButton );
	   gbcPanel0.gridx = 11;
	   gbcPanel0.gridy = 12;
	   gbcPanel0.gridwidth = 1;
	   gbcPanel0.gridheight = 1;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 0;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( inverseButton, gbcPanel0 );
	   pnPanel0.add( inverseButton );
	   
	   lbLabel0 = new JLabel( "Location Name"  );
	   gbcPanel0.gridx = 0;
	   gbcPanel0.gridy = 3;
	   gbcPanel0.gridwidth = 9;
	   gbcPanel0.gridheight = 2;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 1;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( lbLabel0, gbcPanel0 );
	   pnPanel0.add( lbLabel0 );

	   lbLabel1 = new JLabel( "GPS Latitude"  );
	   gbcPanel0.gridx = 0;
	   gbcPanel0.gridy = 7;
	   gbcPanel0.gridwidth = 9;
	   gbcPanel0.gridheight = 2;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 1;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( lbLabel1, gbcPanel0 );
	   pnPanel0.add( lbLabel1 );

	   lbLabel2 = new JLabel( "GPS Longitude"  );
	   gbcPanel0.gridx = 11;
	   gbcPanel0.gridy = 7;
	   gbcPanel0.gridwidth = 8;
	   gbcPanel0.gridheight = 2;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 1;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( lbLabel2, gbcPanel0 );
	   pnPanel0.add( lbLabel2 );

	  

	   lbLabel6 = new JLabel( "Dynamic Weather Interpolator System"  );
	   gbcPanel0.gridx = 1;
	   gbcPanel0.gridy = 0;
	   gbcPanel0.gridwidth = 18;
	   gbcPanel0.gridheight = 3;
	   gbcPanel0.fill = GridBagConstraints.BOTH;
	   gbcPanel0.weightx = 1;
	   gbcPanel0.weighty = 1;
	   gbcPanel0.anchor = GridBagConstraints.NORTH;
	   gbPanel0.setConstraints( lbLabel6, gbcPanel0 );
	   pnPanel0.add( lbLabel6 );

	   setDefaultCloseOperation( EXIT_ON_CLOSE );

	   setContentPane( pnPanel0 );
	   pack();
	   
	   
	   
	  // btBut0.addActionListener( new ActionListener() {
		   //add action listener for Generate Dashboard button
		
				
				
				
				//if the source of the event is the generate dashboard button, run the interpolator
				
						
//							//initialize scanner
//							try {
//							 sc = new Scanner(new File("C:\\Users\\ATB\\eclipse-workspace-2021\\DynamicWeatherInterpolatorSystem\\DemoData\\20210329000" + fileNum + ".csv"));
//							 sc.useDelimiter(",");
//							 int i = 0;
//							 //increment file counter (five minutes)
//							 fileNum += 5;
//							 while(sc.hasNext())
//							 {
//								 for(int j = 0; j < 121; j++)
//								 {
//									 if(sc.hasNext())
//								 set[i][j] = sc.next();
//								 }
//								// sc.nextLine();
//										 i++;
//							 }
//							 
//							 sc.close();
//							 
//							 for(int j = 0; j < i; j++)
//							 {
//								 System.out.println();
//								// for(int h = 0; h < 121; h++)
//								 {
//								//	 System.out.print(set[j][h]);
//								 }
//							 }
//							 
//							}
//							catch(FileNotFoundException e1)
//							{
//								//print error to console
//								//System.err.println(e1);
//							}
//							if(rbRdBut0.isEnabled())
//							{
//								
//							}
							
							
							
//						}
			
		   
		 //  });
		   
		   
		   
		   
	   
	   
	   
	   setVisible( true );
	}

	
	  
	   
	
	//return the bollean variable that detects if the window has been closed 
	public boolean getExitBoolean()
	{
		return exited;
	}
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.out.print(e.getSource());
					if(rbRdBut0.isSelected())
					{
						 n1 = new NearestNeighbor(Double.parseDouble(tfText1.getText()), Double.parseDouble(tfText2.getText()), st);
						n1.run();
						//update the local station object
						st = n1.getStation();
					}
					else if(inverseButton.isSelected())
					{
						try {
							 inv1 = new InverseLinearDistance(Double.parseDouble(tfText1.getText()), Double.parseDouble(tfText2.getText()), st);
						} catch (NumberFormatException e1) {
							
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						}
						
						inv1.run();
						//update station object
						st = inv1.getStation();
					}
					else {
						
					}
					//run infinite loop of updating metrics until the program gui is exited
					while(!getExitBoolean())
					{
						
						
					//set the metrics to the current time in order for the time series to properly report the time of the observations.	
					AirTemperature.setToCurrentTime();
					DewPointTemperature.setToCurrentTime();
					RelativeHumidity.setToCurrentTime();
					WindChill.setToCurrentTime();
					HeatIndex.setToCurrentTime();
					//WindDirection.setToCurrentTime();
					WindSpeed.setToCurrentTime();
					MaxWindSpeed.setToCurrentTime();
					AirPressure.setToCurrentTime();
					MaxAirTemperature.setToCurrentTime();
					MinAirTemperature.setToCurrentTime();
					Precipitation.setToCurrentTime();
						
						
					//set all the metrics
					AirTemperature.set(st.getAirTemp());
					DewPointTemperature.set(st.getDewPointTemp());
					RelativeHumidity.set(st.getRelHumidity());
					WindChill.set(st.getWindChill());
					HeatIndex.set(st.getHeatIndex());
				//	WindDirection.set(st.getWindDir());
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
					    System.err.println(ie);
					}
					
					
					
					}
					
				}
					
	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.equals(e.getSource() == (btBut0))) {
//			
//		}
//	} 
//	
  
	//Action event handlers
	
	
	//Interpolator section of handlers
	
	//The action event handler for the radio button labeled nearest neighbor (bRdBut0) 
	//Calls the nearest neighbor class to process the input data.

	
	
}
