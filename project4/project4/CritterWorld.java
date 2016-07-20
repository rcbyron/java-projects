package project4;

public class CritterWorld {

	private Critter[][] grid;
	
	public CritterWorld() {
		grid = new Critter[Params.world_height][Params.world_width];
	}
	
	public void show() {
		for (int i = -1; i <= grid.length; i++) {
			for (int j = -1; j <= grid[0].length; j++) {
				if (i == -1 || i == grid.length)
					System.out.print((j == -1 || j == grid[0].length) ? '+' : '-');
				else {
					if (j == -1 || j == grid[0].length)
						System.out.print('|');
					else if (grid[i][j] == null)
						System.out.print(' ');
					else
						System.out.print(grid[i][j]);
				}
			}
			System.out.println();
		}
	}
	
}
