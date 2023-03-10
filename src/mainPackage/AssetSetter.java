//imports
package mainPackage;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
	
	GamePanel gp;
	
	//constructor
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	//instantiate objects and place them o the map
	public void setObject() {
		
		//Key 1:
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		// Key 2: 
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		// Key 3:
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 38 * gp.tileSize;
		gp.obj[2].worldY = 8 * gp.tileSize;
		
		// Door 1:
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 10 * gp.tileSize;
		gp.obj[3].worldY = 11 * gp.tileSize;
		
		// Door 2: 
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 28 * gp.tileSize;
		
		// Door 3: 
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 12 * gp.tileSize;
		gp.obj[5].worldY = 22 * gp.tileSize;
		
		// Chest 1: 
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 10 * gp.tileSize;
		gp.obj[6].worldY = 7 * gp.tileSize;
		
		// Boots1:
		
		gp.obj[7] = new OBJ_Boots();
		gp.obj[7].worldX = 37 * gp.tileSize;
		gp.obj[7].worldY = 41 * gp.tileSize;
		
		
		
		
		
		
		
		
		
		
		
	}

}
