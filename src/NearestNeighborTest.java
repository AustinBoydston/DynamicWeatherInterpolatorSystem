import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class NearestNeighborTest {

	@Test
	void test() throws FileNotFoundException {
		Station st = new Station();
		NearestNeighbor n1 = new NearestNeighbor(36.83, -99.64, st);
		
	//	n1.run(); 
		n1.findClosest();
		
		assertEquals(n1.getNearest(), "BUFF");
		
		n1.setOutputMetrics();
		
		assertEquals(n1.getOutputMetricsArray()[0], "BUFF");
		//fail("Not yet implemented");
	}

}
