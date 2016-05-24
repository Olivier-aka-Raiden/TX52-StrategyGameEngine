package tx52.environment;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Rectangle2f;
import org.jbox2d.dynamics.World;

/**
 * the world map
 * 
 * @author rdulieu
 *
 */

public class EnvMap {
	
	//TODO create a list of RTree that represent the world -> Cohen Sutherland ?
	private RTree objectList;
	private Map<UUID,AgentBody> bodies = new TreeMap<>();
	private final Random random = new Random();
	
	//Parameters of the world
	private final float width; 
	private final float height;
	
	public RTree getObjectList() {
		return objectList;
	}

	public void setObjectList(RTree objectList) {
		this.objectList = objectList;
	}

	/**
	 * Initialisation
	 * @param objects
	 */

	public EnvMap(float width,float height){
		this.width = width;
		this.height = height;
		this.objectList = new RTree(null,new Rectangle2f(0,0,width,height)); 

	}

	/**
	 * Add a collection of object
	 * @param objects
	 */
	public void addObjects(Collection<EnvironmentObject> objects) {
		for( EnvironmentObject o : objects){
			objectList.getRoot().add(o);
		}
	}
	
	/**
	 * add a single object
	 * @param object
	 */
	public void addObjects(EnvironmentObject object) {
		
			objectList.getRoot().add(object);
		
	}
	
	/** Replies the number of bodies in the map
	 * 
	 * @return the number of bodies
	 */
	public int getBodyCount(){
		return this.bodies.size();
	}
	/** Replies the agent bodies
	 * 
	 * @return the agent bodies
	 */
	public Collection<AgentBody> getAgentBodies(){
		return Collections.unmodifiableMap(this.bodies).values();
	}
	
	/** Replies the agent body
	 * @param id
	 * @return the agent bodies
	 */
	public AgentBody getAgentBody (UUID id) {
		return this.bodies.get(id);
	}

	public EnvironmentObject getObjectAt(float i, float j, float perceptionDistance) {
	  throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	/** Create a body of the given type.
	 *
	 * @param bodyType the type of the body.
	 * @param agentId the identifier of the agent that will be linked to the body.
	 * @param perceptionDistance the distance of perception.
	 * @return the body.
	 * @throws Exception if it is impossible to retrieve the body constructor or to create the instance. 
	 */
	public <T extends AgentBody> T createBody(Class<T> bodyType, UUID agentId,World w, float perceptionDistance) throws Exception {
		int x = this.random.nextInt((int)50);
		int y = this.random.nextInt((int)50);
		while (!canMoveInside(x, y)) {
			x = this.random.nextInt((int)width);
			y = this.random.nextInt((int)height);
		}

		UUID id = agentId;
		if (id == null) {
			id = UUID.randomUUID();
		}

		Constructor<T> cons = bodyType.getDeclaredConstructor(float.class, float.class, float.class, UUID.class,World.class,EnvMap.class, float.class);
		T body = cons.newInstance(x, y, 2, id,w, this, perceptionDistance);
		this.bodies.put(id, body);
		addObjects(body);

		return body;
	}

	private boolean canMoveInside(int x, int y) {
		
		return (x>=10 && y>=10 && x<width-50 && y<height-50);
	}

	public float getHeight() {
		
		return this.height;
	}
	public float getWidth() {
		
		return this.width;
	}

	

}
