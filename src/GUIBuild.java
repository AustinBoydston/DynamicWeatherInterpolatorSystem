import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import io.prometheus.client.Gauge;

/**
 * Dynamic Weather Interpolator System Interface
 * 
 * @author Austin Boydston
 * @created February 25, 2021
 */

public class GUIBuild extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8538656869183871245L;
	// fields for scanning data
	Scanner sc;
	String[][] set = new String[22][121];
	int fileNum = 0;
	Thread thread = new Thread(new ThreadRunner());
	static private boolean exited = false;
	Station st;

	JSlider rangeSlide;
	JLabel range;
	
	// metrics
	static final Gauge inprogressRequests = Gauge.build().name("dwis_inprogress_requests").help("Inprogress requests.").register();
	static final Gauge AirTemperature = Gauge.build().name("dwis_air_temperature").help("Air Temperature").register();
	static final Gauge DewPointTemperature = Gauge.build().name("dwis_dewpoint_temperature").help("Dewpoint Temperature").register();
	static final Gauge RelativeHumidity = Gauge.build().name("dwis_relative_humidity").help("Relative Humidity").register();
	static final Gauge WindChill = Gauge.build().name("dwis_wind_chill").help("Wind Chill").register();
	static final Gauge HeatIndex = Gauge.build().name("dwis_heat_index").help("Heat Index").register();
	// static final Gauge WindDirection =
	// Gauge.build().name("dwis_wind_direction").help("Wind Direction").register();
	static final Gauge WindSpeed = Gauge.build().name("dwis_wind_speed").help("Wind Speed").register();
	static final Gauge MaxWindSpeed = Gauge.build().name("dwis_max_wind_speed").help("Max Wind Speed").register();
	static final Gauge AirPressure = Gauge.build().name("dwis_air_pressure").help("Air Pressure").register();
	static final Gauge MaxAirTemperature = Gauge.build().name("dwis_max_air_temperature").help("Max Air Temperature").register();
	static final Gauge MinAirTemperature = Gauge.build().name("dwis_min_air_temperature").help("Min Air Temperature").register();
	static final Gauge Precipitation = Gauge.build().name("dwis_precipitation").help("Precipitation").register();

	// Class Object Declarations
	InverseLinearDistance inv1;
	NearestNeighbor n1;

	// fields for the gui components
	static GUIBuild theGUIBuild;

	// ActionListener generateDashboard;

	JPanel pnPanel0;
	ButtonGroup rbgPanel0;
	JButton GenDashBut;
	JTextField tfText0;
	JTextField LonText;
	JTextField LatText;
	JRadioButton rbRdBut0;
	JRadioButton inverseButton;
	JLabel lbLabel0;
	JLabel lbLabel1;
	JLabel LonLabel;
	JLabel lbLabel3;
	JLabel lbLabel6;

	/**
	 * @throws FileNotFoundException
	 */
	public static void main(String args[]) throws FileNotFoundException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
//	 theGUIBuild = new GUIBuild(); 

		// theGUIBuild = new GUIBuild();

	}

	/**
	 * @throws FileNotFoundException
	 */
	public GUIBuild() throws FileNotFoundException {

		super("TITLE");

		// initialize the new station
		st = new Station();

//	  NearestNeighbor test = new NearestNeighbor(36.83, -99.64, st);
//	   test.run();
//	   System.out.println(test.getNearest());
//	   
		System.out.println("GUI Constructor Executed");
		exited = false;
		// Primary Panel
		pnPanel0 = new JPanel();
		rbgPanel0 = new ButtonGroup();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		GenDashBut = new JButton("Generate Metrics");
		gbcPanel0.gridx = 14;
		gbcPanel0.gridy = 18;
		gbcPanel0.gridwidth = 6;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(GenDashBut, gbcPanel0);
		pnPanel0.add(GenDashBut);
		GenDashBut.addActionListener(this);

		tfText0 = new JTextField();
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 5;
		gbcPanel0.gridwidth = 9;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(tfText0, gbcPanel0);
		pnPanel0.add(tfText0);

		LonText = new JTextField();
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 9;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(LonText, gbcPanel0);
		pnPanel0.add(LonText);

		LatText = new JTextField();
		gbcPanel0.gridx = 11;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 9;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(LatText, gbcPanel0);
		pnPanel0.add(LatText);

		rbRdBut0 = new JRadioButton("Nearest Neighbor");
		rbgPanel0.add(rbRdBut0);
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 12;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(rbRdBut0, gbcPanel0);
		pnPanel0.add(rbRdBut0);

		inverseButton = new JRadioButton("Inverse Linear Distance");
		rbgPanel0.add(inverseButton);
		gbcPanel0.gridx = 11;
		gbcPanel0.gridy = 12;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(inverseButton, gbcPanel0);
		pnPanel0.add(inverseButton);

		lbLabel0 = new JLabel("Location Name");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 3;
		gbcPanel0.gridwidth = 9;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel0, gbcPanel0);
		pnPanel0.add(lbLabel0);

		lbLabel1 = new JLabel("GPS Latitude");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 7;
		gbcPanel0.gridwidth = 9;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel1, gbcPanel0);
		pnPanel0.add(lbLabel1);

		LonLabel = new JLabel("GPS Longitude");
		gbcPanel0.gridx = 11;
		gbcPanel0.gridy = 7;
		gbcPanel0.gridwidth = 8;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(LonLabel, gbcPanel0);
		pnPanel0.add(LonLabel);

		lbLabel6 = new JLabel("Dynamic Weather Interpolator System");
		gbcPanel0.gridx = 1;
		gbcPanel0.gridy = 0;
		gbcPanel0.gridwidth = 18;
		gbcPanel0.gridheight = 3;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel6, gbcPanel0);
		pnPanel0.add(lbLabel6);

		
		
		rangeSlide = new JSlider(0, 200, 70);
		rangeSlide.setMajorTickSpacing(50);
		rangeSlide.setMinorTickSpacing(10);
		rangeSlide.setPaintTicks(true);
		rangeSlide.setPaintLabels(true);
		gbcPanel0.gridx = 1;
		gbcPanel0.gridy = 17;
		gbcPanel0.gridwidth = 18;
		gbcPanel0.gridheight = 16;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(rangeSlide, gbcPanel0);
		pnPanel0.add(rangeSlide);

		range = new JLabel("Max Range (ILD)");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 17;
		gbcPanel0.gridwidth = 18;
		gbcPanel0.gridheight = 16;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(range, gbcPanel0);
		pnPanel0.add(range);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(pnPanel0);
		pack();

		setVisible(true);
		
	}

	// return the boolean variable that detects if the window has been closed
	public synchronized boolean getExitBoolean() {
		return exited;
	}

	public synchronized void setExitBooleanTrue()
	{
		exited = true;
	}
	
//	 addWindowListener(new WindowAdapter() {
//	        @Override
//	        public void windowClosing(WindowEvent e) {
//	        	exited = true;
//	        	pnPanel0.
//	            System.exit(0);
//	        }
//	    });

	// Called by the thread that is created when the generate dashboard button is
	// clicked.
	protected void generateDashboard() {
		while (!getExitBoolean()) {
		// System.out.print(e.getSource());
			try {
				st.populate();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
					System.err.println(ie);
				}
			}
		if (rbRdBut0.isSelected()) {
			n1 = new NearestNeighbor(Double.parseDouble(LonText.getText()), Double.parseDouble(LatText.getText()), st);
			n1.run();
			// update the local station object
			st = n1.getStation();
		} else if (inverseButton.isSelected()) {
			try {
				inv1 = new InverseLinearDistance(rangeSlide.getValue(), Double.parseDouble(LonText.getText()),
						Double.parseDouble(LatText.getText()), st);
			} catch (NumberFormatException e1) {

				e1.printStackTrace();
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}

			inv1.run();
			// update station object
			st = inv1.getStation();
		} else {
			System.out.println("else executed");
		}
		// run infinite loop of updating metrics until the program gui is exited
		

			// set the metrics to the current time in order for the time series to properly
			// report the time of the observations.
			AirTemperature.setToCurrentTime();
			DewPointTemperature.setToCurrentTime();
			RelativeHumidity.setToCurrentTime();
			WindChill.setToCurrentTime();
			HeatIndex.setToCurrentTime();
			// WindDirection.setToCurrentTime();
			WindSpeed.setToCurrentTime();
			MaxWindSpeed.setToCurrentTime();
			AirPressure.setToCurrentTime();
			MaxAirTemperature.setToCurrentTime();
			MinAirTemperature.setToCurrentTime();
			Precipitation.setToCurrentTime();

			// set all the metrics
			System.out.println(st.getAirTemp());
			AirTemperature.set(st.getAirTemp());
			DewPointTemperature.set(st.getDewPointTemp());
			RelativeHumidity.set(st.getRelHumidity());
			WindChill.set(st.getWindChill());
			HeatIndex.set(st.getHeatIndex());
			// WindDirection.set(st.getWindDir());
			WindSpeed.set(st.getWindSpeed());
			MaxWindSpeed.set(st.getMaxWindSpeed());
			AirPressure.set(st.getAirPressure());
			MaxAirTemperature.set(st.getMaxAirTemp());
			MinAirTemperature.set(st.getMinAirTemp());
			Precipitation.set(st.getPrecipitation());

			// wait 1 minute seconds
			try {
				TimeUnit.SECONDS.sleep(60);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
				System.err.println(ie);
			}

		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		if (theGUIBuild.isDisplayable()) {

			setExitBooleanTrue();
			theGUIBuild.dispose();
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (theGUIBuild.isDisplayable()) {

			setExitBooleanTrue();
			theGUIBuild.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Start the thread to update the metrics if the thread has not already started
		if (!thread.isAlive()) {
			thread.start();
		}
	}

	// define inner runnable class
	public class ThreadRunner implements Runnable {

		@Override
		public void run() {

			generateDashboard();
			return;
		}

	}

}
