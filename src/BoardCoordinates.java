import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardCoordinates {
	private List<Coords> ticTacToeBoardCoordinates = new ArrayList<Coords>();
	
	private static BoardCoordinates boardCoordinatesInstance;
	
	private BoardCoordinates() {
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				ticTacToeBoardCoordinates.add(new Coords(i, j));
			}
		}
	}
	
	public List<Coords> getTicTacToeBoardCoordinates() {
		return ticTacToeBoardCoordinates;
	}
	
	public List<Coords> getColOne() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getX() == 0)
				.collect(Collectors.toList());
	}
	
	public List<Coords> getColTwo() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getX() == 1)
				.collect(Collectors.toList());
	}
	
	public List<Coords> getColThree() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getX() == 2)
				.collect(Collectors.toList());
	}
	
	public List<Coords> getRowOne() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getY() == 0)
				.collect(Collectors.toList());
	}
	
	public List<Coords> getRowTwo() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getY() == 1)
				.collect(Collectors.toList());
	}
	
	public List<Coords> getRowThree() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getY() == 2)
				.collect(Collectors.toList());
	}
	
	public List<Coords> getDiagOne() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getY() == c.getX())
				.collect(Collectors.toList());
	}
	
	public List<Coords> getDiagTwo() {
		return ticTacToeBoardCoordinates.stream()
				.filter(c -> c.getX() + c.getY() == 2)
				.collect(Collectors.toList());
	}
	
	public static BoardCoordinates getInstance() {
		if (boardCoordinatesInstance == null) {
			boardCoordinatesInstance = new BoardCoordinates();
		}
		return boardCoordinatesInstance;
	}
}
