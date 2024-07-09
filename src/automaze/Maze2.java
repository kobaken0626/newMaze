package automaze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Maze2 extends MyFrame implements KeyListener{
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("SPACEキーが押されました");
			Maze.enterPressed=true;
		}
	}
}
