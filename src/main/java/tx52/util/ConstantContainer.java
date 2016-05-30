package tx52.util;

//This class contains all constants of the engine
public class ConstantContainer {
	
	//Agent or Body Related
	public static final int NB_AGENTS = 25;
	
	public static final float BASIC_PERCEPTION_DISTANCE=10f;
	public static final float BASIC_MAX_LINEAR_SPEED=10f;
	public static final float BASIC_MAX_ANGULAR_SPEED=10f;
	public static final float BASIC_RADIUS=2;
	
	//JBOX2D related
	public static final float BASIC_DENSITY = 0.5f;
	public static final float BASIC_FRICTION = 0.3f;
	public static final float BASIC_RESTITUTION = 0.5f;
	
	public static final int VELOCITY_ITERATIONS = 6;
	public static final int POSITION_ITERATIONS = 2;
	
	//Time Related
	public static final float DELTA_T = 1.0f / 4.0f;
	
	//MAP Related
	public static final float MAP_WIDTH = 200;
	public static final float MAP_HEIGHT = 200;
	
	public static final float MAP_LIMIT_MIN_X = 20;
	public static final float MAP_LIMIT_MIN_Y = 20;
	public static final float MAP_LIMIT_MAX_X = 50;
	public static final float MAP_LIMIT_MAX_Y = 50;
	
	public static final float RANDOM_SPAWN_X = 30;
	public static final float RANDOM_SPAWN_Y = 30;
	
	//TREE Related
	public static final int NB_MAX_OBJECT_IN_NODE = 10;
	public static final int NB_CHILDREN = 4;
	
	//GUI Related
	
	//ETC

}
