package tx52.environment;

import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Point2f;
import org.arakhne.afc.math.continous.object2d.Rectangle2f;
import org.jbox2d.dynamics.Body;

public class EnvironmentObject {
	
	protected Rectangle2f box;
	protected UUID id;
	protected Body body;
	
	protected TreeNode node;
	//protected final enum type;
	
	/**
	 * creation of the object with his position (x,y)
	 * @param x
	 * @param y
	 * @param id
	 */
	public EnvironmentObject(float x, float y, UUID id) { 
		this.id = id;
		if(id==null)id=UUID.randomUUID();
		Point2f position = new Point2f(x, y);
		this.box = new Rectangle2f(position,position); //a modifier
	}
	
	/**
	 * creation of the object with his position and dimensions
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public EnvironmentObject(float x, float y,float width,float height,UUID id) {
		this.id = id;
		if(id==null)id=UUID.randomUUID();
		box = new Rectangle2f(x,y,width,height);
		box.setFromCenter(x,y,x+width/2,y+height/2);
	}
	
	public Rectangle2f getBox() {
		return box;
	}
	public void setBox(Rectangle2f box) {
		this.box = box;
	}
	public Point2f getPosition() {
		Point2f position = new Point2f(body.m_xf.p.x,body.m_xf.p.y);
		return position;
	}
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Body getBody() {
		return body;
	}
	
	@Override	
	public String toString(){
		
		return "UUID : " + id + "   Position : (" + getPosition().getX() + "," + getPosition().getY() + ")";
		
	}
	
}