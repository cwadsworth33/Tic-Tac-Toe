import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardCoordinatesTest {
	
	BoardCoordinates coords;
	
	@BeforeEach
	void setUp() {
		coords = BoardCoordinates.getInstance();
	}

	@Test
	void testCols() {
		List<Coords> col1 = new ArrayList<Coords>();
		List<Coords> col2 = new ArrayList<Coords>();
		List<Coords> col3 = new ArrayList<Coords>();
		col1.add(new Coords(0, 0));
		col1.add(new Coords(0, 1));
		col1.add(new Coords(0, 2));
		col2.add(new Coords(1, 0));
		col2.add(new Coords(1, 1));
		col2.add(new Coords(1, 2));
		col3.add(new Coords(2, 0));
		col3.add(new Coords(2, 1));
		col3.add(new Coords(2, 2));
		assertEquals(coords.getColOne(), col1);
		assertEquals(coords.getColTwo(), col2);
		assertEquals(coords.getColThree(), col3);
	}
	
	@Test
	void testRows() {
		List<Coords> row1 = new ArrayList<Coords>();
		List<Coords> row2 = new ArrayList<Coords>();
		List<Coords> row3 = new ArrayList<Coords>();
		row1.add(new Coords(0, 0));
		row1.add(new Coords(1, 0));
		row1.add(new Coords(2, 0));
		row2.add(new Coords(0, 1));
		row2.add(new Coords(1, 1));
		row2.add(new Coords(2, 1));
		row3.add(new Coords(0, 2));
		row3.add(new Coords(1, 2));
		row3.add(new Coords(2, 2));
		assertEquals(coords.getRowOne(), row1);
		assertEquals(coords.getRowTwo(), row2);
		assertEquals(coords.getRowThree(), row3);
	}
	
	@Test
	void testDiag() {
		List<Coords> diag1 = new ArrayList<Coords>();
		List<Coords> diag2 = new ArrayList<Coords>();
		diag1.add(new Coords(0, 0));
		diag1.add(new Coords(1, 1));
		diag1.add(new Coords(2, 2));
		diag2.add(new Coords(0, 2));
		diag2.add(new Coords(1, 1));
		diag2.add(new Coords(2, 0));
		assertEquals(coords.getDiagOne(), diag1);
		assertEquals(coords.getDiagTwo(), diag2);
	}

}
