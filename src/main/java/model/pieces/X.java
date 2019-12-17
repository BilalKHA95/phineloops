package model.pieces;

import model.enumtype.Orientation;
import model.enumtype.Side;
import view.pieces.PieceDrawing;
import view.pieces.XPieceDrawing;

import java.io.FileNotFoundException;

public class X extends Piece {
	/**
	 * @param orientation   The orientation of X
	 * @param line_number   The line number of X
	 * @param column_number The column number of X
	 * 
	 *                      initializes the attributes of X
	 * 
	 */
	public X(int orientation, int line_number, int column_number) {
		super(orientation, line_number, column_number);
		this.id = 4;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void translation(Side side) {
		// TODO Auto-generated method stub

	}

	@Override
	public PieceDrawing createDrawing() throws FileNotFoundException {
		return new XPieceDrawing(this);
	}

	@Override
	public int numberOfConnection() {
		int r = 0;
		if (this.getOneNeighbor(Orientation.NORTH) != null)
			if (this.getOneNeighbor(Orientation.NORTH).isConnectedTo(Orientation.SOUTH))
				r++;
		if (this.getOneNeighbor(Orientation.SOUTH) != null)
			if (this.getOneNeighbor(Orientation.SOUTH).isConnectedTo(Orientation.NORTH))
				r++;
		if (this.getOneNeighbor(Orientation.EAST) != null)
			if (this.getOneNeighbor(Orientation.EAST).isConnectedTo(Orientation.WEST))
				r++;
		if (this.getOneNeighbor(Orientation.WEST) != null)
			if (this.getOneNeighbor(Orientation.WEST).isConnectedTo(Orientation.EAST))
				r++;
		return r;
	}

	@Override
	public boolean isConnectedTo(Orientation orientation) {
		return true;
	}

	@Override
	public boolean connectedAll() {
		// TODO Auto-generated method stub
		return this.numberOfConnection() == 4;
	}

	public String toString() {
		return "\u253C";
	}
}