
public class Move {
	private int x;
	private int y;
	private CellValue cellVal;
	
	public Move(int x, int y, CellValue cellVal) {
		this.x = x;
		this.y = y;
		this.cellVal = cellVal;
	}
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public CellValue getCellVal() {
		return cellVal;
	}

	public void setCellVal(CellValue cellVal) {
		this.cellVal = cellVal;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Move)) {
			return false;
		} else {
			Move m = (Move) o;
			return m.getX() == x && m.getY() == y && m.getCellVal() == cellVal;
		}
	}
	
	@Override
	public String toString() {
		return "Move(" + "X:" + x + " Y:" + y + " Val:" + cellVal + ")";
	}
}
