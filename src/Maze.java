
public class Maze extends MyFrame{
	static boolean isSpace=false;
	public void run(){
		MainMaze maze=new MainMaze();
		addKeyListener(maze);
		while(true) {
			maze.run(this);
			isSpace=false;
			while(true) {
				if(isSpace) {
					clear();
					break;
				}
				sleep(0.1);
			}
		}
	}
}


