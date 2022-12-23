//imports
package mainPackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	// screen settings
	public final int originalTileSize= 16; //16x16 tile
	public final int scale= 3;
	public final int tileSize= originalTileSize*scale; //48x48 tile
	public final int maxScreenCol= 16;
	public final int maxScreenRow= 12;
	public final int screenWidth= tileSize* maxScreenCol; //768 pixels
	public final int screenHeight= tileSize * maxScreenRow; // 576 pixels
	
	// world settings
	
	public final int maxWorldCol=50;
	public final int maxWorldRow=50;
	public final int worldWidth= tileSize * maxWorldCol;
	public final int worldHeight= tileSize * maxWorldRow;
	
	// keys input
	
	KeyHandler keyH= new KeyHandler();
	
	// sounds
	Sound music= new Sound();
	Sound soundEffect = new Sound();
	
	
	//collision 
	
	public CollisionChecker collisChecker= new CollisionChecker(this);
	
	
	//FPS
	int FPS=60;
	
	//time 
	
	Thread gameThread;
	
	//player 
	public Player player = new Player(this, keyH);
	
	// UI
	
	public UI ui=new UI(this);
	
	
	//tile manager
	public TileManager tileM= new TileManager(this);
	
	// Objects
	
	public SuperObject obj[]= new SuperObject[10]; // we prepare 10 objects for example
	
	public AssetSetter aSetter = new AssetSetter(this);
	
	
	
	
	
	// Constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight))
		;
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		playMusic(0);
	}
	
	
	// start the thread
		public void startGameThread() {
			gameThread= new Thread(this);
			gameThread.start();
			
		}
		
		
		 //the run method- 1st method: the sleep method
//	@Override
//	public void run() {
//		
//		//interval variables, this interval is necessary for the update and repaint loop methods to know when the program can display the player's graphics ( the thread runs the methods endlessly so after the first change the player disappears)  
//		double drawInterval= 1000000000/FPS; // 1 second in nano/FPS, for a 60 FPS, the screen changes every 0.0166666666666667 seconds
//		double nextDrawTime= System.nanoTime()
//+drawInterval; 		
//		
//		
//		while(gameThread!= null) {
//			
////long currentTime= System.nanoTime();this returns the current value of running JVM's high-resolution time source in nanoseconds (1,000,000,000 nanoseconds = 1 second)
////long currentTime2 = System.currentTimeMillis();this returns the current time in milliseconds (1,000 milliseconds= 1 second)
//// create a game loop: update and repaint everytime 
////1 update: update information such as character positions
//			update();
////2 draw : draw the screen with the updated informations
//			repaint(); //paintComponent() method 
//			
//			
//			double remainingTime= nextDrawTime-System.nanoTime(); // the remaining time after the execution of the two methods and the next start of the loop (the sleep phase)
//			try { // the sleep method accepts only time in milliseconds not in nanoseconds, so the cast and the try/catch block is necessary, or we can simply convert time for nano to millis
//				
//				remainingTime= remainingTime/1000000; //conversion from nano to millis
//				if(remainingTime<0) { 
//					remainingTime=0; //if the remaining time is lower than 0, we change it to 0 so that the loop doesn't have to sleep, it can start over right away
//				}
//				
//				Thread.sleep((long) remainingTime);
//				nextDrawTime+=drawInterval;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
		 //the run method - 2nd method: the delta method (tbh i don't understand this)
		public void run() {
			
			//variables
			double drawInterval= 1000000000/FPS;
			double delta= 0;
			long lastTime=System.nanoTime();
			long currentTime;
			long timer= 0;
			int drawCount=0;
			
			while(gameThread!=null) {
				
				currentTime= System.nanoTime();
				delta+= (currentTime-lastTime)/drawInterval;
				timer+=currentTime-lastTime;
				lastTime=currentTime;
				if(delta>= 1) {
				//game loop methods 
				update();
				repaint();
				delta--;
				drawCount++;
				}
				if(timer>=1000000000) {
					//System.out.println("FPS:"+drawCount);
					drawCount=0;
					timer=0;
				}
				
			}
		}
	// update the player's position according to the keys input
	public void update() {
		Player.update();
	}
	
	
	// display the position by painting the player's graphics
	public void paintComponent(Graphics g) { //painting method in java
		super.paintComponent(g);
		 //convert the graphics to 2D graphics class: extends Graphics class and provides more sophisticated geometry, coordinates transformations, color management and text layouts
		Graphics2D g2= (Graphics2D) g;
		//tile
		tileM.draw(g2); // we draw the tile before the player
		
		//object
		
		for (int i =0; i< obj.length; i++) {
			
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		//player
		Player.draw(g2);
		
		//ui
		ui.draw(g2);
		//draws a rectangle on the screen
		g2.dispose();
		
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	
	public void stopMusic() {
		music.stop();
		
	}
	
	public void playSE(int i) {
		
		soundEffect.setFile(i);;
		soundEffect.play();
	}
}
