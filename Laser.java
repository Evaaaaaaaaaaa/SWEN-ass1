import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


// a subclass of 'Sprite', which get each created laser shooting upward to the enemy
public class Laser extends Sprite{
	// declare variables for laser
	private Image laser;
    private float laserX;
    private float laserY;
    
    // set the speed of the laser
    private final int LASERSPEED = 3;
    
   
    
    // constructor
    public Laser(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
		laser = new Image(imageSrc);
     	laserX = x;
    	laserY = y;
		// TODO Auto-generated constructor stub
	}

    // update 'laserY' with given delta, since the laser only move vertically
    public void update(float delta) {
        laserY -= delta * LASERSPEED;
    }
    
    // draw the laser at given(current) coordinate;
    public void render() {
    	laser.drawCentered(laserX, laserY);
    }

    // getter method for each laser variable, which are used outside this class
	public float getLaserX() {
		// TODO Auto-generated method stub
		return laserX;
	}

	public float getLaserY() {
		// TODO Auto-generated method stub
		return laserY;
	}
	
	public Image getLaser() {
		// TODO Auto-generated method stub
		return laser;
	}

}    
   