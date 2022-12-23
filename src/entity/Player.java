//imports
package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import mainPackage.GamePanel;
import mainPackage.KeyHandler;


public class Player extends Entity{
	
	public static GamePanel gp;
	public static KeyHandler keyH;
	public static int screenX;
	public static int screenY;
	public static int hasKey = 0; // how many keys the player collected
	
	//constructor
	public Player(GamePanel gp,KeyHandler keyH) {
		this.gp=gp;
		this.keyH=keyH;
		
		screenX= gp.screenWidth/2 - (gp.tileSize/2);
		screenY= gp.screenHeight/2 - (gp.tileSize/2);
		
		
		solidArea= new Rectangle(); // 4 parameters to the constructor
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	//set player's default values
	public void setDefaultValues() {
		
		worldX=gp.tileSize*23; //starting position of the player in the middle of the map 28 / 23
		worldY=gp.tileSize*21;
		speed=4;
		direction= "down";
	}
	
	//get the player's image
	public void getPlayerImage() { //load the player's images into this method
		try {
			//load the images
			up1= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	//update method
	public static void update() {
		 
		
		if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true) {
			if(keyH.upPressed==true) {
			direction= "up";
			
		}
		else if(keyH.downPressed==true) {
			direction="down";
			
		}
		else if(keyH.rightPressed==true) {
			direction= "right";
			
		}
		else if(keyH.leftPressed==true) {
			direction= "left";
			
		}
		
			collisionOn= false;
			
		//gp.collisChecker.checkTile(null);
		// -----------------------------------------------------------
		// tileChecker method because I fucked up 
			
			Entity entity= new Entity();
			solidArea= new Rectangle(); // 4 parameters to the constructor
			solidArea.x = 8;
			solidArea.y = 16;
			solidArea.width = 32;
			solidArea.height = 32;
			int entityLeftWorldX = entity.worldX + entity.solidArea.x;
			int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
			int entityTopWorldY= entity.worldY + entity.solidArea.y;
			int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
			
			int entityLeftCol = entityLeftWorldX / gp.tileSize;
			int entityRightCol = entityRightWorldX / gp.tileSize;
			int entityTopRow = entityTopWorldY / gp.tileSize;
			int entityBottomRow = entityBottomWorldY / gp.tileSize;
			
			int tileNum1, tileNum2; // for the test
			
			switch(entity.direction) {
				
			case "up": 
				entityTopRow = (entityTopWorldY - entity.speed)/ gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
				
			case "down": 
				entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				
				break;
				
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				
				break;
				
			case "right":
				entityRightCol = (entityRightWorldX + entity.speed)/ gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				
				break;
			}
			
			
		// -----------------------------------------------------------
			
			// check object collision
			
			//gp.collisChecker.checkObject(this, true);
		// -----------------------------------------------------------
			
			int index = 999;
			boolean player=true;
			
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
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}
						break;
					case "down":
						entity.solidArea.y += entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // this checks if the 2 rectangles intersect
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
							
						}
						break;
					case "left":
						entity.solidArea.x -= entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // this checks if the 2 rectangles intersect
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
							
						}
						break;
					case "right":
						entity.solidArea.x += entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // this checks if the 2 rectangles intersect
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
							
						}
						break;
					}
					
					entity.solidArea.x = entity.solidAreaDefaultX;
					entity.solidArea.y = entity.solidAreaDefaultY;
					gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
					gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
					
				}
			}
			
			//System.out.println(index);
			
		
			
		// -----------------------------------------------------------
			
			pickUpObject(index);
			
			
			
			
		
		// if collision is false, player can move	
			if(collisionOn == false) {
				 switch(direction) {
				 
				 case "up": 
					 worldY -= speed;
					 break;
				 case "down":
					 worldY += speed;
					 break;
				 case "left": 
					 worldX -= speed;
					 break;
				 case "right":
					 worldX += speed;
					 break;
					 
				 }
			}
			
		spriteCounter++;
		if(spriteCounter>12) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
	}
		
		
	}
	
	public static void pickUpObject(int i) {
		
		if(i != 999) {
			
			//gp.obj[i] = null; // delete the object we touch
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null; // to delete it (collect it)
				System.out.println("Key collected!");
				System.out.println("You have "+ hasKey+" key(s)!");
				System.out.println("You can open "+hasKey+" door(s)!");
				break;
				
			case "Door":
				
				if(hasKey > 0) {
					gp.playSE(3);
					gp.obj[i] = null;
					System.out.println("Door opened!");
					hasKey--;
				}
				else {
					System.out.println("You need a key to open the door! Explore the area and find it!");
				}
				break;
				
			case "Chest":
				//gp.playSE(4);
				break;
				
			case "Boots":
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				break;
				
			}
			
		}
	}
	
	//draw method
	public static void draw(Graphics2D g2) {
//		g2.setColor(Color.red);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); 
		
		BufferedImage image= null;
		
		switch(direction) {
		
		case "up":
			if (spriteNum==1) {
				image=up1;
			}
			if (spriteNum==2) {
				image= up2;
			}
			break;
			
		case "down":
			if (spriteNum==1) {
				image=down1;
			}
			if (spriteNum==2) {
				image= down2;
			}
			break;
			
		case "left":
			if (spriteNum==1) {
				image=left1;
			}
			if (spriteNum==2) {
				image= left2;
			}
			break;
			
		case "right":
			if (spriteNum==1) {
				image=right1;
			}
			if (spriteNum==2) {
				image= right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null); // draws an image on the screen
		
	}
	

}
