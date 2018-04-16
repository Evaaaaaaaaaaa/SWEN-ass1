import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

// make 'Player' be the subclass of the Sprite
public class Player extends Sprite{
	// declare variables for plane
	private static float planeX;
    private static float planeY;
    private static String planeImage;
    private static Image plane;
    // set the speed of the plane to be 0.5
    private static final float SPEED = 0.5f;
    
    // get the height and the width of the screen
    private static final int HEIGHT = App.SCREEN_HEIGHT; 
    private static final int WIDTH = App.SCREEN_WIDTH; 
    
    //this will be used to store the BoundingBox of the player
    private BoundingBox playerBB;
    
    // Constructor
	public Player(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
		// TODO Auto-generated constructor stub
		planeX = x;
	    planeY = y;
	    planeImage = "res/spaceship.png";
		        
	    plane = new Image(planeImage);
	}
	

    
		
	

    
    // update the 'Player' when direction keys or escape key is pressed
	// prevent the Player moving out of the frame
    public void update(Input input, int delta) {
	
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			planeY -= delta * SPEED;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			planeY += delta * SPEED;
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			planeX -= delta * SPEED;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			planeX += delta * SPEED;
		}

		if (planeY < 0) {
			planeY = 0;
		}
		if (planeX < 0) {
			planeX = 0;
		}
		if (planeY > HEIGHT) {
			planeY = HEIGHT;
		}
		if (planeX > WIDTH) {
			planeX = WIDTH;
		}
		
		// create a new BoundingBox for the player of the current position
		playerBB = new BoundingBox(plane, planeX, planeY);
		

	}
    
    // render, draw Plane at provided position in the argument
    public void render() {
    	
        plane.drawCentered(planeX, planeY);
    }

    

    // getters
	public float getX() {
		// TODO Auto-generated method stub
		return planeX;
	}
	public float getY() {
		// TODO Auto-generated method stub
		return planeY;
	}



	public BoundingBox getBB() {
		// TODO Auto-generated method stub
		return playerBB;
	}
    
}