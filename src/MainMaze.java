
public class MainMaze extends MyFrame {
		 
		Maze maze;
		static float CELL_SIZE = 20;
	 
		public void run() {
			//new MyFrame();
			maze = new Maze(15, 15);
			addKeyListener(maze);
	 
			drawMaze();
			while (true) {
				if (MainMaze.spacePressed) {
					drawMaze();
					MainMaze.spacePressed = false;
				}
	 
				sleep(0.1);
			}
		}
	 
		public void drawMaze() {
			byte[][] map = maze.getMaze();
			clear();
			for (int y = 0; y < map[0].length; y++) {
				for (int x = 0; x < map.length; x++) {
					if (map[x][y] == 1) {
						setColor(0, 0, 0); // Wall color
					} else if (x == 1 && y == 1) {
						setColor(0, 0, 255); // Start point color
					} else if (x == map.length - 2 && y == map[0].length - 2) {
						setColor(255, 0, 0); // Goal point color
					} else {
						setColor(255, 255, 255); // Path color
					}
					fillRect(x * CELL_SIZE, 25 + y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
	 
		static boolean spacePressed;
	 
	}