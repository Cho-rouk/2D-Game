//imports
package tile;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import mainPackage.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	//constructor
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile= new Tile[10]; //create 10 tiles (grass, wall and water...)
		mapTileNum= new int[gp.maxWorldCol][gp.maxWorldRow]; // we're gonna store all the numbers from the text file here
		getTileImage();
		loadMap("/maps/world01.txt");
		
	}
	
	//get the tile image
	public void getTileImage() { //load the tile images into this method
		
		try{
			tile[0]= new Tile();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1]= new Tile();
			tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision=true;
			
			tile[2]= new Tile();
			tile[2].image=ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
			tile[2].collision=true;
			
			tile[3]= new Tile();
			tile[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4]= new Tile();
			tile[4].image=ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision=true;
			
			tile[5]= new Tile();
			tile[5].image=ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// load the tiles numbers into the mapTileNum
	public void loadMap(String filePath) {
		
		try {
			
			InputStream is= getClass().getResourceAsStream(filePath);
			BufferedReader br= new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row=0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line= br.readLine();
				while(col < gp.maxWorldCol) {
					String numbers[]= line.split(" ");
					int num= Integer.parseInt(numbers[col]);
					// we store the extracted numbers in mapTileNum
					mapTileNum[col][row]= num;
					col++;
				}
				if (col== gp.maxWorldCol) {
					col=0;
					row++;
				}
			}
			
			br.close();
			
		} catch (Exception e) {
			
		}
	}
	
	
	
	//draw the tiles 
	public void draw(Graphics2D g2) {
		
		// this is beautiful but too much typing ....
		
//		g2.drawImage(tile[1].image,0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image,48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image,96, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image,144, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image,192, 0, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image,0, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,48, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,96, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,144, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image,192, 48, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image,0, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,48, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,96, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,144, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,192, 96, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image,0, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,48, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,96, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image,144, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image,192, 144, gp.tileSize, gp.tileSize, null);
//		
//		g2.drawImage(tile[1].image,0, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image,48, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image,96, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image,144, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image,192, 192, gp.tileSize, gp.tileSize, null);
		
		// we need to automate the tile drawing 
		
		// creating variables
		
		int worldCol= 0;
		int worldRow=0;
		
		
		// we create a while loop 
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum= mapTileNum[worldCol][worldRow];
			
			//implementing a camera 
			
			int worldX= worldCol * gp.tileSize; // the position on the map
			int worldY= worldRow * gp.tileSize;
			int screenX= worldX - gp.player.worldX + gp.player.screenX; //where on the screen we draw it
			int screenY= worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
			}
			
			worldCol++;
			
			if(worldCol== gp.maxWorldCol) {
				worldCol=0;
				worldRow++;
				
				
			}
		
		}
		
		// this loop did the job but it uses only the grass tile, we need to create a map and save the map data to a text file
		
		
		
		
		
		
		
		
	}
	
	

}
