import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class NearestNeighborTest {

	@Test
	void test() throws FileNotFoundException {
		NearestNeighbor n1 = new NearestNeighbor(36.83, -99.64);
		
		n1.findClosest(); 
		
		assertEquals("nearest is the wrong value.", n1.getNearest(), "BUFF");
		//fail("Not yet implemented");
	}

}
