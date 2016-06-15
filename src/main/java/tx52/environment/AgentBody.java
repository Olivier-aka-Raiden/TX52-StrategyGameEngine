package tx52.environment;

import java.util.ArrayList;
import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Circle2f;
import org.arakhne.afc.math.continous.object2d.Rectangle2f;
import org.jbox2d.dynamics.World;

import tx52.util.ConstantContainer;
import tx52.util.Semantic;

// A mettre abstract une fois différentes unités créées et passer ces unités en constructeur de createBody()
public class AgentBody extends DynamicObject implements Comparable<AgentBody>{
	
	private float perceptionDistance; 
	private int life;
	private int team;
	private int damagePerStep;
	


	public AgentBody (float x, float y,float radius,int team,UUID agentId,World w,EnvMap map, float perceptionDistance,Semantic type) {
		super (x,y,radius,agentId,w,map);
		this.perceptionDistance = perceptionDistance;
		this.team = team;
		this.life = ConstantContainer.BASIC_LIFE;
		this.damagePerStep = ConstantContainer.BASIC_DAMAGE;
		this.semantic = type;
	}


	public int getLife() {
		return life;
	}


	public int getTeam() {
		return team;
	}


	public float getPerceptionDistance() {
		return perceptionDistance;
	}
	
	/**
	 * return all the percepted objects
	 * 
	 * @return ArrayList<Perceivable>
	 */
	public ArrayList<Perceivable> computePerception(){
		
		TreeNode topNode = this.node;
		Circle2f range = new Circle2f(getPosition(),perceptionDistance);
		Rectangle2f r = new Rectangle2f();
		r.setFromCorners(range.getX()-range.getRadius(),range.getY()-range.getRadius(),range.getX()+range.getRadius(),range.getY()+range.getRadius());
		
		//determine the biggest node that is in range
		while(topNode.getParent()!=null && !topNode.getBox().contains(r)){
			topNode = topNode.getParent();
		}
		
		ArrayList<Perceivable> percept = new ArrayList<Perceivable>();
		
		DepthFirstIterator it = new DepthFirstIterator(topNode);
		while(it.hasNext()){
			TreeNode currentNode = it.next();
			if(currentNode.getBox().intersects(range)){
				for(EnvironmentObject o:currentNode.getObjects()){
					if(o.getBox().intersects(range) && o!=this){
						percept.add(new Perceivable(o));
					}
				}
			}
			
		}
		
		//ArrayList<Perceivable> percept = computePerception1N(topNode,range); //Recursive method
				
		return percept;
	}
	
	/**
	 * usable for recursivity  -> we may use the depthFirstIterator
	 * @return ArrayList<Perceivable>
	 */
	@Deprecated
	public ArrayList<Perceivable> computePerception1N(TreeNode topNode,Circle2f range){
		
		ArrayList<Perceivable> perception = new ArrayList<Perceivable>();
		
		for(EnvironmentObject o:topNode.getObjects()){
			if(o.getBox().intersects(range)){
				perception.add(new Perceivable(o));
			}
		}
		if(topNode.getChildren()!=null){
			for(TreeNode node:topNode.getChildren()){
				if(node.getBox().intersects(range))
				perception.addAll(computePerception1N(node, range));
			}
		}
		
		return perception;
		
	}
		

	@Override
	public int compareTo(AgentBody o) {
		if (o == this) {
			return 0; 
		}
		if (o == null) {
			return Integer.MAX_VALUE;
		}
		return getId().compareTo(o.getId());
	}


	public void takeDamage() {
		this.life-=damagePerStep;
	}
}
