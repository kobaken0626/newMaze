import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainMaze implements KeyListener{
	static int path=0;
	static int wall=1;
	int height=15;
	int wigth=15;
	boolean start,gorl=false;
	public void run(MyFrame f) {
		int[][]maze=new int [height][wigth];
		for(int i=0;i<height;i++) {
			for(int j=0;j<wigth;j++) {
				if(i==0 ||i==height-1 ||j==0 ||j==wigth-1) {
					maze[i][j]=wall;
				}
				else {
					maze[i][j]=path;
				}
			}
		}
		
		Random random = new Random();
		for(int i=2;i<height-1;i+=2) {
			for(int j=2;j<wigth-1;j+=2) {
				maze[i][j]=wall;
				while(true) {
					int ran;
					if(i==2) {
						ran=random.nextInt(4);
					}else {
						ran=random.nextInt(3);
					}
					int wallx=i;
					int wally=j;
					switch(ran) {
					case 3:
						++wallx;//上
						break;
					case 2:
						++wally;
						break;
					case 1:
						--wallx;
						break;
					case 0:
						--wally;
						break;
					}
					if(maze[wallx][wally]!=wall) {
						maze[wallx][wally]=wall;
						
						break;
					}
				}
			}
		}
		for(int i=0;i<height;i++) {
			for(int j=0;j<wigth;j++) {
				if(maze[i][j]==wall)
				if(maze[i][j]==wall) {
					f.setColor(0,0,0);
					f.fillRect(100+i*15,100+j*15,15,15);
				}else if(i==1&&j==1) {
					f.setColor(0,0,255);
					f.fillRect(100+i*15,100+j*15,15,15);
				}
				else if(i==13&&j==13) {
					f.setColor(255,0,0);
					f.fillRect(100+i*15,100+j*15,15,15);
				}
				else {
					f.setColor(255,255,255);
					f.fillRect(100+i*15,100+j*15,15,15);
				}
			}
		}
	}
    public void keyTyped(KeyEvent e) {
    }
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {

			Maze.isSpace=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
}