//imports
package mainPackage;
import entity.Entity;
import entity.Player;

public class CollisionChecker {
	
	
	 

	GamePanel gp;

	//Constructor
	
	public CollisionChecker(GamePanel gp) {
		this.gp= gp;
	}
	
	//blank constructor
	public CollisionChecker() {
		
	}
	// check every entity in the game
	

	public void checkTile(Entity entity) {
		
		// the body is in Player in a specific block because i can't use this in a static statement thus i can't call this method, so i had to write everything there
		
		}
	
	// check if the entity is a player or not
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for (int i = 0; i < gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				 
				// get entity's solid area position 
				
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				//get the object's solid area position
				
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // this checks if the 2 rectangles intersect
						System.out.println("up collision");
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // this checks if the 2 rectangles intersect
						System.out.println("down collision");
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // this checks if the 2 rectangles intersect
						System.out.println("left collision");
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // this checks if the 2 rectangles intersect
						System.out.println("right collision");
					}
					break;
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				
			}
		}
		
		
		return index;
	}
		
		
}

