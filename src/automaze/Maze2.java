package automaze;

public class Maze2 extends MyFrame{
	static boolean isSpace=false;
	public void run(){
		Maze maze=new Maze();
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