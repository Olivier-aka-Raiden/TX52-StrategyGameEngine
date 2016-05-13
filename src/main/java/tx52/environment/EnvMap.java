package tx52.environment;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Rectangle2f;

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
	
	//Parameters of the world
	static final float width=100; 
	static final float height=100;
	
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
	public EnvMap(Collection<EnvironmentObject> objects){
		objectList = new RTree(objects,new Rectangle2f(0,0,width,height));
	}

	/**
	 * Add an object
	 * @param objects
	 */
	public void addObjects(Collection<EnvironmentObject> objects) {
		for( EnvironmentObject o : objects){
			objectList.getRoot().add(o);
		}
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
	

}
