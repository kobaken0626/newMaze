package automaze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * グラフィックスを簡単に表示するウィンドウクラス
 * @author Cho Shinya
 *
 */
public class MyFrame extends JFrame implements Runnable {
	 private static final int CELL_SIZE = 20;
	    private static final int MIN_WIDTH = 15;
	    private static final int MIN_HEIGHT = 15;

	    private BufferedImage im;
	    private Color col = Color.BLACK;
	    private Thread t;
	    private Color bgColor = new Color(255, 255, 255);

	    private Maze maze;
	    private int width;
	    private int height;
	   
	public MyFrame() {
		super();
		  width = MIN_WIDTH * CELL_SIZE;
	        height = MIN_HEIGHT * CELL_SIZE;
	        setSize(width, height);
	        im = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                setVisible(false);
	                System.exit(1);
	            }
		});
	        this.maze = new Maze(MIN_WIDTH, MIN_HEIGHT);
	        this.setFocusable(true);
	        this.addKeyListener(this);
	}
	private void addKeyListener(MyFrame myFrame) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	public synchronized void saveImage(File dst) throws IOException {
		ImageIO.write(im, "png", dst);
	}
	/*public void autoSave() {
		final Object t=this;
		Runnable r = new Runnable() {
			public void run() {
				try {
					for (int i=1 ; i<=5; i++) {
						Thread.sleep(1000);
						String pathname = "screenshots"+File.separator+t.getClass().getSimpleName()+"_"+new TDate().toString("yyMMdd_hhmmss")+"_"+i+".png";
						saveImage(new File(pathname));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r).run();
	}*/
	/**
	 * 最初にpaintが呼ばれたときに、スレッドを動かしてアニメーションを制御する
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(im, 0 ,0 , null);
		if (t==null) {
			t=new Thread(this);
			t.start();
		}
	}
	/**
	 * 四角形を描画する。色はsetColor で指定。
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
        public synchronized void fillRect(double x, double y, double w, double h) {
               fillRect((int) x, (int) y, (int) w, (int) h);
        }
	public synchronized void fillRect(int x,int y,int w, int h) {
		Graphics g=getImageGraphics();
		if (g!=null) {
			g.setColor(col);
			g.fillRect(x, y, w, h);
		}
		g=getGraphics();
		if (g!=null) {
			g.setColor(col);
			g.fillRect(x, y, w, h);
		}
	}
	public synchronized void clear() {
		Color s=col;
		col=bgColor;
		fillRect(0,0,getWidth(),getHeight());
		col=s;
	}
	public synchronized void fillOval(int x,int y,int w, int h) {
		Graphics g=getImageGraphics();
		if (g!=null) {
			g.setColor(col);
			g.fillOval(x, y, w, h);
		}
		g=getGraphics();
		if (g!=null) {
			g.setColor(col);
			g.fillOval(x, y, w, h);
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
	                fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	            }
	        }
	    }
	private Graphics getImageGraphics() {
		return im.getGraphics();
	}
	/**
	 * 描画色を指定する。
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void setColor(int red, int green, int blue) {
		if (red<=0) red=0;
		if (red>255) red=255;
		if (green<=0) green=0;
		if (green>255) green=255;
		if (blue<=0) blue=0;
		if (blue>255) blue=255;
		col=new Color(red,green,blue);
	}
	/**
	 * 一定時間待つ
	 * @param time
	 */
	public void sleep(double time) {
		try {
			fillRect(0,0,0,0);// ダミー：これがないとXPで最後の四角形がちらつく
			Thread.sleep((int)(time*1000));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * アニメーションを行う時は、runメソッドをオーバライドする
	 */
	public void run() {
		drawMaze();
	}
	public synchronized void fillOval(double x, double y, double w, double h) {
		fillOval((int)x,(int)y,(int)w,(int)h);

	}
	public synchronized void drawString(String str, int x,int y, int size) {
		Graphics g=getImageGraphics();
		if (g!=null) {
			g.setColor(col);
			g.setFont(new Font("Monospaced",0,size));
			g.drawString(str, x, y);
		}
		//if (locked) return;
		g=getGraphics();
		if (g!=null) {
			g.setColor(col);
			g.setFont(new Font("Monospaced",0,size));
			g.drawString(str, x, y);
		}
	}
	
	 public void keyPressed(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	            Maze maze = new Maze(MIN_WIDTH, MIN_HEIGHT);
	            drawMaze();
	        }
	    }

	    public void keyReleased(KeyEvent e) {}

	    public void keyTyped(KeyEvent e) {}

	}
