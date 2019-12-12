package model;

public class Level {
	protected Piece[][] grid;
	protected int height, width;

	public Level(int height, int width) {
		this.grid = new Piece[height][width];
		this.height = height;
		this.width = width;
	}

	public Piece[][] getGrid() {
		return this.grid;
	}

	public void init_neighbors() {

		this.grid[0][0].addNeighbor(this.grid[1][0], Orientation.SOUTH);
		this.grid[0][0].addNeighbor(this.grid[0][1], Orientation.EAST);

		this.grid[0][this.width - 1].addNeighbor(this.grid[1][this.width - 1], Orientation.SOUTH);
		this.grid[0][this.width - 1].addNeighbor(this.grid[0][this.width - 2], Orientation.WEST);

		this.grid[this.height - 1][0].addNeighbor(this.grid[this.height - 2][0], Orientation.NORTH);
		this.grid[this.height - 1][0].addNeighbor(this.grid[this.height - 1][1], Orientation.EAST);

		this.grid[this.height - 1][this.width - 1].addNeighbor(this.grid[this.height - 2][this.width - 1],
				Orientation.NORTH);
		this.grid[this.height - 1][this.width - 1].addNeighbor(this.grid[this.height - 1][this.width - 2],
				Orientation.WEST);

		for (int i = 1; i < this.width - 1; i++) {
			this.grid[0][i].addNeighbor(this.grid[1][i], Orientation.SOUTH);
			this.grid[0][i].addNeighbor(this.grid[0][i + 1], Orientation.EAST);
			this.grid[0][i].addNeighbor(this.grid[0][i - 1], Orientation.WEST);
			this.grid[this.height - 1][i].addNeighbor(this.grid[this.height - 2][i], Orientation.NORTH);
			this.grid[this.height - 1][i].addNeighbor(this.grid[this.height - 1][i + 1], Orientation.EAST);
			this.grid[this.height - 1][i].addNeighbor(this.grid[this.height - 1][i - 1], Orientation.WEST);
		}

		for (int i = 1; i < this.height - 1; i++) {
			this.grid[i][0].addNeighbor(this.grid[i][1], Orientation.EAST);
			this.grid[i][0].addNeighbor(this.grid[i - 1][0], Orientation.NORTH);
			this.grid[i][0].addNeighbor(this.grid[i + 1][0], Orientation.SOUTH);
			this.grid[i][this.width - 1].addNeighbor(this.grid[i][this.width - 2], Orientation.WEST);
			this.grid[i][this.width - 1].addNeighbor(this.grid[i - 1][this.width - 1], Orientation.NORTH);
			this.grid[i][this.width - 1].addNeighbor(this.grid[i + 1][this.width - 1], Orientation.SOUTH);

		}

		for (int i = 1; i < this.height - 1; i++) {
			for (int j = 1; j < this.width - 1; j++) {
				this.grid[i][j].addNeighbor(this.grid[i - 1][j], Orientation.NORTH);
				this.grid[i][j].addNeighbor(this.grid[i + 1][j], Orientation.SOUTH);
				this.grid[i][j].addNeighbor(this.grid[i][j - 1], Orientation.WEST);
				this.grid[i][j].addNeighbor(this.grid[i][j + 1], Orientation.EAST);
			}
		}
	}
	
	public boolean checkGrid() {
		Boolean r=true;
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < grid.length; j++) {
				if(!grid[i][j].connectedAll())
					r=false;
			}
		return r;
	}
}
