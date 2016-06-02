package tx52.environment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;

import org.arakhne.afc.math.continous.object2d.Rectangle2f;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import tx52.util.ConstantContainer;

public class Environment {
	World jBoxWorld;
	EnvMap world;
	LinkedBlockingDeque<EnvironmentEvent> eventFire = new LinkedBlockingDeque<EnvironmentEvent>();

	
	/**
	 * creation of the environment. Setup the Jbox2DWorld and create the EnvMap
	 * @param objects
	 */
	public Environment (float width, float height) {
		Vec2 gravity = new Vec2(0.0f, 0.0f);
		this.jBoxWorld = new World(gravity);
		jBoxWorld.setAllowSleep(true);
		this.world = new EnvMap(width,height);
	}

	/**
	 * we assume that a body is always added to the Jbox2Dworld when it's created.
	 * @param objects
	 */
	public void addObjectToWorld(Collection<EnvironmentObject> objects) {
		if(this.world.getObjectList()!=null){
			this.world.addObjects(objects);
		}else{
			this.world.setObjectList(new RTree(objects,new Rectangle2f(0,0,ConstantContainer.MAP_WIDTH,ConstantContainer.MAP_HEIGHT)));
		}
	}
	public World getjBoxWorld() {
		return jBoxWorld;
	}

	public void setjBoxWorld(World jBoxWorld) {
		this.jBoxWorld = jBoxWorld;
	}

	public EnvMap getWorld() {
		return world;
	}

	public void setWorld(EnvMap world) {
		this.world = world;
	}
	
	/**
	 * this function print in console the world
	 */
	public void printMap(){
		
		System.out.printf("Map size : Width=%f Height=%f\n\n",world.getWidth(),world.getHeight());
		System.out.println("Status of the RTree :");
		System.out.println("--Root--");
		
		DepthFirstIterator it = new DepthFirstIterator(world.getObjectList());
		TreeNode node = null;
		if(it.hasNext()){
			node = it.next();
			for(EnvironmentObject object : node.getObjects()){
				System.out.println(object);
			}
		}
		
		while(it.hasNext()){
			System.out.println("--Next Node--");
			node = it.next();
			for(EnvironmentObject object : node.getObjects()){
				System.out.println(object);
			}
			
		}System.out.println("***END OF TREE***\n");
		
		
	}

	/**
	 * 1 iteration of simulation
	 */
	public void runJBox() {
		float timeStep = ConstantContainer.DELTA_T;
		int velocityIterations = ConstantContainer.VELOCITY_ITERATIONS;
		int positionIterations = ConstantContainer.POSITION_ITERATIONS;
		 
//		System.out.println("--Jbox2D Status--");
		jBoxWorld.step(timeStep, velocityIterations, positionIterations);
//	    Body templist = jBoxWorld.getBodyList();
//	    for (Body body = templist; body != null; body = body.m_next) {
//		    Vec2 position = body.getPosition();
//		    float angle = body.getAngle();
//		    System.out.printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);
//	    }System.out.println("***END OF JBOX WORLD***\n");
	}

	/**
	 * change the tree in fucntion of the movements of all objects
	 */
	public void updateTree(ArrayList<UUID> agentToKill) {
		DepthFirstIterator it = new DepthFirstIterator(world.getObjectList());
		TreeNode node = null;
		ArrayList<EnvironmentObject> objectToAdd = new ArrayList<EnvironmentObject>();
		
		while(it.hasNext()){
			node = it.next();
			ArrayList<EnvironmentObject> objectsToRemove = new ArrayList<EnvironmentObject>();
			for(EnvironmentObject object : node.getObjects()){
				if(object instanceof AgentBody){
					AgentBody objectA = (AgentBody) object;
					if(objectA.getLife()<=0){
						agentToKill.add(object.getId());
						objectsToRemove.add(object);
					}else if(!node.getBox().contains(object.getBox())){
						objectToAdd.add(object);
						objectsToRemove.add(object);
					}
				}else if(!node.getBox().contains(object.getBox())){
					objectToAdd.add(object);
					objectsToRemove.add(object);
				}
			}
			for(EnvironmentObject object : objectsToRemove){
				node.removeObject(object);
			}
		}
		addObjectToWorld(objectToAdd);
		
	}

	public void fireEnvironmentChanged(EnvironmentEvent event) {
		this.eventFire.addLast(event);
	}

	public LinkedBlockingDeque<EnvironmentEvent> getEventFire() {
		return eventFire;
	}

}
