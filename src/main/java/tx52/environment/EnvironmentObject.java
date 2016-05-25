package tx52.environment;

import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Point2f;
import org.arakhne.afc.math.continous.object2d.Rectangle2f;
import org.jbox2d.dynamics.Body;

public abstract class EnvironmentObject {
	
	protected float width;
	protected float height;
	protected UUID id;
	protected Body body;
	
	protected TreeNode node;
	//protected final enum type;
	
	/**
	 * creation of the object with his ID
	 * @param id
	 */
	public EnvironmentObject(UUID id) { 
		this.id = id;
		if(id==null)id=UUID.randomUUID();
	}
	
	/**
	 * creation of the object with his position and dimensions
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public EnvironmentObject(float width,float height,UUID id) {
		this.id = id;
		if(id==null)id=UUID.randomUUID();
		this.width=width;
		this.height=height;
	}
	
	/**
	 * generate the box from the position
	 * @return
	 */
	public Rectangle2f getBox() {
		Point2f position = this.getPosition();
		Rectangle2f box = new Rectangle2f(position.x(),position.y(),width,height);
		box.setFromCenter(position.x(),position.y(),position.x()+width/2,position.y()+height/2);
		return box;
	}

	/**
	 * get the position from jbox2d
	 * @return
	 */
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