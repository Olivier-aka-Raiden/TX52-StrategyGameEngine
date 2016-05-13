package tx52.environment;

import java.util.ArrayList;
import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Circle2f;
import org.jbox2d.dynamics.World;

// A mettre abstract une fois différentes unités créées et passer ces unités en constructeur de createBody()
public class AgentBody extends DynamicObject implements Comparable<AgentBody>{
	
	private final UUID agentId;
	private final float perceptionDistance;
	
	public AgentBody (float x, float y,float radius,UUID agentId,World w,EnvMap map, float perceptionDistance) {
		super (x,y,radius,agentId,w,map);
		this.perceptionDistance = perceptionDistance;
		this.agentId = agentId;
	}

		public UUID getAgentId() {
		return agentId;
	}

	public float getPerceptionDistance() {
		return perceptionDistance;
	}
	
	/**
	 * only for agent body
	 * should we return the body of the agent in perception ?
	 * @return 
	 */
	public ArrayList<Perceivable> computePerception(){
		
		TreeNode currentNode = this.node;
		Circle2f range = new Circle2f(getPosition(),perceptionDistance);
		ArrayList<Perceivable> percept = new ArrayList<Perceivable>();
		
		//current node
		for(EnvironmentObject o:currentNode.getObjects()){
			if(o.box.intersects(range)){
				percept.add(new Perceivable(o));
			}
		}
		//other node
		if(this.getPosition().distance(currentNode.getBox().getClosestPointTo(getPosition()))<=range.getRadius()){
			//how can we limit the search ?
		}

		
		
		return percept;
}
		

	@Override
	public int compareTo(AgentBody o) {
		if (o == this) {
			return 0; 
		}
		if (o == null) {
			return Integer.MAX_VALUE;
		}
		return getAgentId().compareTo(o.getAgentId());
	}
}
