package tx52.util;

import java.awt.Dimension;

//This class contains all constants of the engine
public class ConstantContainer {
	
	//Agent or Body Related
	public static final int NB_AGENTS = 1;
	
	public static final float BASIC_PERCEPTION_DISTANCE=10f;
	public static final float BASIC_MAX_LINEAR_SPEED=1f;
	public static final float BASIC_MAX_ANGULAR_SPEED=0.3f;
	public static final float BASIC_RADIUS=2;
	public static final int BASIC_LIFE = 100;
	public static final int BASIC_DAMAGE= 10;
	
	//Influence related
	public static final String MOTION_TYPE="MOTION";
	public static final String SHOOT_TYPE="SHOOT";
	
	//JBOX2D related
	public static final float BASIC_DENSITY = 0.5f;
	public static final float BASIC_FRICTION = 0.3f;
	public static final float BASIC_RESTITUTION = 0.5f;
	
	public static final int VELOCITY_ITERATIONS = 6;
	public static final int POSITION_ITERATIONS = 2;
	
	//Time Related
	public static final float DELTA_T = 1.0f / 2.0f;
	
	//MAP Related
	public static final float MAP_WIDTH = 200;
	public static final float MAP_HEIGHT = 200;
	
	public static final float MAP_LIMIT_MIN_X = 20;
	public static final float MAP_LIMIT_MIN_Y = 20;
	public static final float MAP_LIMIT_MAX_X = 40;
	public static final float MAP_LIMIT_MAX_Y = 40;
	
	public static final int RANDOM_SPAWN_X = 30;
	public static final int RANDOM_SPAWN_Y = 30;
	
	//TREE Related
	public static final int NB_MAX_OBJECT_IN_NODE = 10;
	public static final int NB_CHILDREN = 4;
	public static final int MAX_FPS = 30;
	public static final int FRAME_PERIOD = 1000/MAX_FPS; //a changer !
	
	//GUI Related
	public static final Dimension BG_SIZE = new Dimension (1033,867);
	public static final Dimension VIEWPORT_SIZE = new Dimension (800,600);
	
	
	//ETC

}
