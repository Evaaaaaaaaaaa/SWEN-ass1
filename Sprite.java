import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

// Sprite class
public class Sprite {
	
	//declare  variables for plane and enemy
	private Image enemy;
	private float enemyY;
	private float enemyX;
	private Image plane;
	private float planeX;
	private float planeY;
	public boolean contact = true;


 
    // Sprite class
    public Sprite(String imageSrc, float x, float y) throws SlickException {
    	// initialise the variables for the enemy
		enemy = new Image(imageSrc);
	    enemyX = x;
	    enemyY = y;
      
	}
    
   
	// no sprite needs to be updated, everything is done in the Sprite's subclass
	public void update(Input input, int delta) throws SlickException {
		
		
	}
	// draw enemy
	public void render() {
	    // draw enemy at this the coordinate provided in the argument
		enemy.drawCentered(getEnemyX(), enemyY);
		
		
	}

	//this method is called if the player contacts with the any enemy
	
	public void contactSprite(Sprite other) {
		// end the game 
        System.exit(1);
	}
    
	
	// getters and setters
	public Image getPlane() {
		return plane;
	}

	public void setPlane(Image plane) {
		this.plane = plane;
	}

	public float getPlaneY() {
		return planeY;
	}

	public void setPlaneY(float planeY) {
		this.planeY = planeY;
	}

	public float getPlaneX() {
		return planeX;
	}

	public void setPlaneX(float planeX) {
		this.planeX = planeX;
	}

	public boolean getContact() {
		// TODO Auto-generated method stub
		return contact;
	}

	public Image getEnemy() {
		// TODO Auto-generated method stub
		return enemy;
	}



	public float getEnemyX() {
		return enemyX;
	}



	public void setEnemyX(float enemyX) {
		this.enemyX = enemyX;
	}

}
