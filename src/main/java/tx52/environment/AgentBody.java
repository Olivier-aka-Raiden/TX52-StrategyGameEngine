package tx52.environment;

import java.util.UUID;

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
