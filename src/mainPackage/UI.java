//imports
package mainPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Key;

public class UI {
	
	GamePanel gp;
	Font font;
	BufferedImage keyImage;
	
	
	//constructor
	
	public UI(GamePanel gp) {
		
		this.gp= gp;
		font= new Font("Monospaced", Font.BOLD,25);
		OBJ_Key key= new OBJ_Key();
	}

	public void draw(Graphics2D g2) {
		
		g2.setFont(font);
		g2.setColor(Color.white);
		g2.drawString("Key= "+ gp.player.hasKey,25,50);
		
		 
	}
}
