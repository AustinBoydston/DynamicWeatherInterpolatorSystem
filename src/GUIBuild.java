	import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
	/**
	 * Dynamic Weather Interpolator System Interface
	 * @author  Austin Boydston
	 * @created February 25, 2021
	 */
	
public class GUIBuild extends JFrame {
	
	static GUIBuild theGUIBuild;

	JPanel pnPanel0;
	ButtonGroup rbgPanel0;
	JButton btBut0;
	JTextField tfText0;
	JTextField tfText1;
	JTextField tfText2;
	JRadioButton rbRdBut0;
	JLabel lbLabel0;
	JLabel lbLabel1;
	JLabel lbLabel2;
	JLabel lbLabel3;
	JLabel lbLabel6;
	/**
	 */
	public static void main( String args[] ) 
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
	   theGUIBuild = new GUIBuild();
	} 

	/**
	 */
	public GUIBuild() 
	{
	   super( "TITLE" );

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
	   setVisible( true );
	} 
	

	//Action event handlers
	
	
	//Interpolator section of handlers
	
	//The action event handler for the radio button labeled nearest neighbor (bRdBut0) 
	//Calls the nearest neighbor class to process the input data.
	private void NearestNeighborRadioButton(ActionEvent nn)
	{
		
	}
	
}
