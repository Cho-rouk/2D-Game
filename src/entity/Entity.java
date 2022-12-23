//this is class is the parent class for the player, NPCs and all the characters
//imports
package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public static int worldX;
	public static int worldY;
	public static int speed;
	public static BufferedImage up1;
	public static BufferedImage up2;
	public static BufferedImage down1;
	public static BufferedImage down2;
	public static BufferedImage left1;
	public static BufferedImage left2;
	public static BufferedImage right1;
	public static BufferedImage right2;
	public static String direction;
	public static int spriteCounter=0;
	public static int spriteNum=1;
	public static Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY; // just to recall the original values because solidArea.x & olidArea.y are going to change
	public static Boolean collisionOn= false;

}
