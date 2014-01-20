import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;


public class Enemy extends CubeletEntity{
//	Inherits:
//
//	public int totalHeight;
//	public int totalWidth;
//	public int x, y;
//	
//	protected abstract void render();
//	protected abstract void update(); 
//	
//	public int cubeAmntWidth;  //Amount of cube in the player width  wise
//	public int cubeAmntHeight; //Amount of cube in the player height wise
//	public int cubeWidth;      //Width  of individual cube
//	public int cubeHeight;     //Height of individual cube
//	public int speed = 5;
//	
//
//	//Keyboard variables, for easy keyboard switch
//	public static int KEY_MOVE_WEST = Keyboard.KEY_A;
//	public static int KEY_MOVE_EAST = Keyboard.KEY_D;
//	public static int KEY_MOVE_NORTH = Keyboard.KEY_W;
//	public static int KEY_MOVE_SOUTH = Keyboard.KEY_S;
//	
	
	//Variables that let the entity move
	//Overriding superclass (change from bool to int to let you "queue" up movement)
	public int isMoveWest = 0;
	public int isMoveEast = 0;
	public int isMoveNorth = 0;
	public int isMoveSouth = 0;
	
	private Color enemyColor = Color.red;
	private final int maxMove = 200; //Maximum amount of movement distance if randomly chosen to move
	//CONSTRUCTOR
	Enemy(int x, int y, int cubeAmntWidth, int cubeAmntHeight, int cubeWidth, int cubeHeight){
		this.x = x;
		this.y = y;
		this.cubeAmntWidth = cubeAmntWidth;
		this.cubeAmntHeight = cubeAmntHeight;
		this.cubeWidth = cubeWidth;
		this.cubeHeight = cubeHeight;
		
		totalWidth  = cubeAmntWidth * cubeWidth;
		totalHeight = cubeAmntHeight * cubeHeight;
	}
	
	public void render(){
		GL11.glColor3f(enemyColor.r, enemyColor.g, enemyColor.b);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2i(x, y);
			GL11.glVertex2i(x + totalWidth, y);
			GL11.glVertex2i(x + totalWidth, y + totalHeight);
			GL11.glVertex2i(x, y + totalHeight);
		GL11.glEnd();
	}
	
	
	public void update(){
		System.out.println(isMoveWest);
		System.out.println(isMoveEast);
		System.out.println(isMoveNorth);
		System.out.println(isMoveSouth);
		chooseAction();
		move();
	}
	
	public void chooseAction(){
		int r = (int) (Math.random() * 50000); //Get a number between 0 and 100
		int action = r % 3;//Get a random number between 0 and given number (non-inclusive)
		switch(action){ //Choosing an action, weighted toward do nothing
		case 0:
			moveRandom();
			break;
		default:
			//Do nothing
		}
	} 
	
	public void moveRandom(){
		int r = (int) (Math.random() * 50000); //Get a number between 0 and 100
		int dir = r % 4; //Choosing a random direction
		
		int i = r % maxMove; //Choosing a random amount of distance to move 
		for(; i != 0; i--){
			switch(dir){
			case 0:
				moveWest();
				break;
			case 1:
				moveEast();
				break;
			case 2:
				moveNorth();
				break;
			case 3:
				moveSouth();
				break;
			}
		}
	}
	
	
	@Override
	public void move(){ //Call move() in update() in order to move
		if(isMoveWest > 0){
			x -= speed;
			isMoveWest--;
		}
		if(isMoveEast > 0){
			x += speed;
			isMoveEast--;
		}
		if(isMoveNorth > 0){
			y -= speed;
			isMoveNorth--;
		}
		if(isMoveSouth > 0){
			y += speed;
			isMoveSouth--;
		}
	}
	
	//Setter functions for movement variables
	@Override
	public void moveWest(){
		isMoveWest++;
	}
	public void moveEast(){
		isMoveEast++;
	}
	public void moveNorth(){
		isMoveNorth++;
	}
	public void moveSouth(){
		isMoveSouth++;
	}
}

