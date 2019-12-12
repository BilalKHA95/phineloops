package model;

import view.BarPieceDrawing;
import view.PieceDrawing;

import java.io.FileNotFoundException;

public class Bar extends Piece {

	public Bar(int orientation, int line_number, int column_number) {
		super(orientation, line_number, column_number);
		this.id = 2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void translation(Side side) {
		// TODO Auto-generated method stub
		if (this.orientation == 1)
			this.orientation = 0;
		else
			this.orientation = 1;
	}

	@Override
	public PieceDrawing createDrawing() throws FileNotFoundException {
		return new BarPieceDrawing(this);
	}

	@Override
	public int numberOfConnection() {
		int r = 0;
		if (this.orientation == 0) {
			if (this.getOneNeighbor(Orientation.NORTH).isConnectedTo(Orientation.SOUTH))
				r++;
			if (this.getOneNeighbor(Orientation.SOUTH).isConnectedTo(Orientation.NORTH))
				r++;
		} else {
			if (this.getOneNeighbor(Orientation.EAST).isConnectedTo(Orientation.WEST))
				r++;
			if (this.getOneNeighbor(Orientation.WEST).isConnectedTo(Orientation.EAST))
				r++;
		}
		return r;
	}

	@Override
	public boolean isConnectedTo(Orientation orientation) {
		if (this.neighbor.containsKey(orientation)) {
			if (((orientation == Orientation.NORTH) || (orientation == Orientation.SOUTH)) && (this.id == 0))
				return true;
			if (((orientation == Orientation.EAST) || (orientation == Orientation.WEST)) && (this.id == 1))
				return true;
		}
		return false;
	}

	@Override
	public boolean connectedAll() {
		// TODO Auto-generated method stub
		return this.numberOfConnection() == 2;
	}

}
