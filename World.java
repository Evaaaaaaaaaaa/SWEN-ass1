
import java.util.Arrays;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

//import utilities.BoundingBox;

// World class, back ground, and checking contact,call update and render for all sprite.
public class World {
	// declare variables for the image of background,
	// since we need minimum 3 vertical background image, declare the Y position for each of them
	// also set the background scrolling speed be a final float 0.2
	private Image background;
    private float backgroudY1;
    private float backgroudY2;
    private float backgroudY3;
    private static final float BACKGROUDSPEED = 0.2f;
    
    // declare variables for 'plane' (player)
    private float planeX;
    private float planeY;
    // This final variable is used to making scrolling background, background images move
    // up by 3*(its image height) when 3 imgaes are looping
    private static final int num_backgorunfImage = 3;
    private static final int IMA_HEIGHT = 512;
    
    // get the height and with of the game screen
    private static final int HEIGHT = App.SCREEN_HEIGHT;
    private static final int WIDTH = App.SCREEN_WIDTH;

    // declare a variable of type 'Player';
	private Player player;

	// initialise the initial number of laser to be 100
	// declare a lasers array with this size, to store created lasers. The size of 
	// lasers array will be increased if it reaches its current max size;
    private final static int init_laserNum = 100;
    private static Laser lasers[] = new Laser[init_laserNum];
    
    // initialise this variable to keep track of the number of lasers that 
    //have been created
    private static int arr_size = 0;
    
    // this boolean type array will return true if the laser has contacted 
    // any enemy, then this laser will stop updating and rendering
    private static boolean []laserUsed = new boolean[init_laserNum];
    
    // set the enemy number to be 8, this is all enemies we have in 
    // this game, then initialise a enemy array with this size
    private final static int enemyNum = 8;
    private static Sprite []enemy = new Sprite[enemyNum];
    // enemyKilled array (boolean type)will track if the enemy 
    // has contacted with any bullet
    private static boolean []enemyKilled = new boolean[enemyNum];
    // initialize vairables for enemy
    private static int enemyX = 64;
    private static final int enemyY = 32;
    private static final int enemyDistance = 128;
    
    // this will be used to store the BoundingBox for the current 
    // enemy and laser
    private static BoundingBox enemyBB;
    private static BoundingBox laserBB;
    
    
	public World() throws SlickException{
		// set the initial height for each background image
		// also store the single background image in 'background'
		backgroudY1 = IMA_HEIGHT;
		backgroudY2 = 0;
		backgroudY3 = -IMA_HEIGHT;
		background = new Image("res/space.png");
        
		// initialise the the x, y value for the plane
        // and create the player
		planeX = 480;
		planeY = 688;
		player = new Player("res/spaceship.png", planeX, planeY);
		
		// create enemies
		for (int i = 0; i < enemy.length; i++) {
	        enemy[i] = new Sprite("res/basic-enemy.png", enemyX+i*enemyDistance, enemyY);
	    }
		
		// set them all to be true first, so render and update of all 
		//sprites can be called when the game starts
	    for (int i = 0; i < laserUsed.length; i++) {
	        laserUsed[i] = true;	
	    }
	    for (int i = 0; i < enemyKilled.length; i++) {
	        enemyKilled[i] = true;	
	    }
	}
	


    
	// update method, which controls all update method of sprites
	public void update(Input input, int delta) throws SlickException {
        //set the moving downward speed of the scrolling background
	    backgroudY1 += delta * BACKGROUDSPEED;
		backgroudY2 += delta * BACKGROUDSPEED;
        backgroudY3 += delta * BACKGROUDSPEED;
        
        
        // call to update the current player position
		player.update(input, delta);
		
		// set this moves the off screen background images back to the top
		// so a cycle is created, and the background will scroll downworld 
		// continuously 
	    if (backgroudY1> HEIGHT) {
			backgroudY1 = HEIGHT-num_backgorunfImage*IMA_HEIGHT;
			
		}
		if (backgroudY2 > HEIGHT ){
			backgroudY2 = HEIGHT-num_backgorunfImage*IMA_HEIGHT;
			
		}
        if (backgroudY3 > HEIGHT) {
			backgroudY3 = HEIGHT-num_backgorunfImage*IMA_HEIGHT;
			
		}

        // create a laser when Space bar is pressed, and store is in an laser array
        if (input.isKeyPressed(Input.KEY_SPACE)) {
        	// if the array 'lasers' and 'laserUsed' is full, copy the current
        	// redeclare the array and increase its size of 1, and copy the 
        	//  original array to the new one, by doing this, it won't run out
        	// of lasers
            // as before, set the new boolean item in the 'laserUsed'to be true 
        	if(arr_size == lasers.length) {
        		lasers = Arrays.copyOf(lasers, lasers.length+1);
             	laserUsed = Arrays.copyOf(laserUsed, laserUsed.length+1);
             	laserUsed[arr_size] = true;
            }
            lasers[arr_size] = new Laser("res/shot.png", player.getX(), 
            		player.getY());
            arr_size++;
       
        }
        // check if the Player contact any enemy.
        for (int i = 0; i < enemy.length; i++) {
                // creating BoudingBox for enemy
                // and check if it intersects with the player,
        	    // call 'contactSprite()' if the intersect;
        	    enemy[i].setEnemyX(enemyX+enemyDistance*i);
        	    enemyBB = new BoundingBox(enemy[i].getEnemy(),
        	    		enemyX+enemyDistance*i, enemyY);    
        
        	    if(enemyBB.intersects(player.getBB())){
        	    	enemy[i].contactSprite(player);
        	    }
        	   
        }
       
        // check if each bullet has contacted any enemy, if yes, it will not
        // be updated anymore
        for (int i = 0; i<arr_size; i++) {
        	// only update the current laser if it has not made any contact to
        	// any enemies
        	if(laserUsed[i] == true) {
        		lasers[i].update(delta);
        	}
           
        	// creating BoundingBox for the current laser
           	laserBB = new BoundingBox(lasers[i].getLaser(), lasers[i].getLaserX(),
           			lasers[i].getLaserY());
	    	// check if each enemy makes contact with each laser, if they intersect,
           	// set its boolean tracking array to be false, it will stop updating
           	for (int j = 0; j < enemy.length; j++) {
	    		enemyBB = new BoundingBox(enemy[j].getEnemy(), 
	    				enemyX+enemyDistance*j, enemyY);    
	    		if (enemyBB.intersects(laserBB)) {
	    		    
	    			laserUsed[i] = false;
	    	        enemyKilled[j] = false;
	    	    }
	    	}
	    	
        }
    
        
        
	}   
	
	// draw background
	// looping each enemy and bullet, if they have not made contact with, call render.
	public void render()  {
		 //Draw all of the sprites in the game
	    background.draw(0,backgroudY1);
		background.draw(0,backgroudY2);
		background.draw(0,backgroudY3);
		background.draw(WIDTH/2,backgroudY1);
		background.draw(WIDTH/2,backgroudY2);
	    background.draw(WIDTH/2,backgroudY3);
	    
	    // call this method from Player class, to draw the plane.
	    player.render();
	    
	  
	    // loop through every enemy, draw enemy if it's not killed
	    for(int i=0; i<enemy.length; i++) {
	    	
        	if(enemy[i] != null && enemyKilled[i] == true) {
        		
	    		
	            enemy[i].render();
	    
	    	}
	 
	    }
	    // loop through every laser, if it has not made contact with any enemy,
	    // call its render method to draw it
	    for (int i = 0; i < arr_size; i++) {
	    	if (lasers[i] != null && laserUsed[i] == true) {
	            lasers[i].render();
        	}
	    	
	    }
	    
	}

}




