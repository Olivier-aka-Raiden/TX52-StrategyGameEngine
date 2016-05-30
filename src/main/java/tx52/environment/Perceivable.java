package tx52.environment;

import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Point2f;
import org.arakhne.afc.math.continous.object2d.Rectangle2f;
import org.arakhne.afc.math.continous.object2d.Vector2f;

import com.google.common.base.Objects;

//import tx52.environment.AgentBody;


public final class Perceivable {
	
	private final UUID bodyId;
	private final UUID objectId;
	private Rectangle2f box;
	private Point2f position;
	private final float angle;
	//private final enum type;
	private final float maxLinearSpeed;
	private Vector2f currentLinearMotion;
	private final float maxAngularSpeed;
	private final float currentAngularSpeed;
	//private final String name;
	
	
	/**
	 * @param perceivedObject is the perceived object.
	 */
	public Perceivable(EnvironmentObject perceivedObject) {
		
		this.objectId = perceivedObject.getId();
		//this.name = perceivedObject.getName();
		this.box = perceivedObject.getBox();
		this.position = perceivedObject.getPosition().clone();
		/*if (type == null) {
			type = perceivedObject.getType();
			if (type == null) {
				type = perceivedObject.getClass().getName();
			}
		}
		this.type = type;*/
		if (perceivedObject instanceof DynamicObject) {
			DynamicObject dyObj = (DynamicObject) perceivedObject;
			this.angle = dyObj.getAngle();
			this.maxAngularSpeed = dyObj.getMaxAngularSpeed();
			this.maxLinearSpeed = dyObj.getMaxLinearSpeed();
			this.currentAngularSpeed = dyObj.getCurrentAngularSpeed();
			this.currentLinearMotion = dyObj.getCurrentLinearMotion().clone();
		} else {
			this.angle = 0f;
			this.maxAngularSpeed = 0f;
			this.maxLinearSpeed = 0f;
			this.currentAngularSpeed = 0f;
			this.currentLinearMotion = null;
		}
		if (perceivedObject instanceof AgentBody) {
			this.bodyId = ((AgentBody) perceivedObject).getId();
		} else {
			this.bodyId = null;
		}
	}
	
	
	public Perceivable clone() {
		try {
			Perceivable clone = (Perceivable) super.clone();
			clone.currentLinearMotion = this.currentLinearMotion.clone();
			clone.position = this.position.clone();
			clone.box = this.box.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	
	
	public boolean equals(Object obj) {
		if (obj instanceof Perceivable) {
			Perceivable p = (Perceivable) obj;
			return this.bodyId.equals(p.bodyId)
				&& Objects.equal(this.objectId, p.objectId);
		}
		return false;
	}
	

	
	/**
	 * compare an envObject and his perceivable
	 * @param o
	 * @return
	 */
	public int compareTo(EnvironmentObject o) {
		if (o instanceof AgentBody) {
			int c = this.bodyId.compareTo(((AgentBody) o).getId());
			if (c != 0) {
				return c;
			}
			return this.objectId.compareTo(o.getId());
		}
		return Integer.MAX_VALUE;
	}

	/** Replies the id of the body.
	 *
	 * @return the id of the body.
	 */
	public UUID getBodyID() {
		return this.bodyId;
	}

	/** Replies the id of the body.
	 *
	 * @return the id of the body.
	 */
	public UUID getID() {
		return this.objectId;
	}

	
	public Rectangle2f getShape() {
		return this.box;
	}

	
	public float getX() {
		return this.position.getX();
	}

	
	public float getY() {
		return this.position.getY();
	}

	
	public Point2f getPosition() {
		return this.position;
	}

	
	public float getAngle() {
		return this.angle;
	}

	
	public Vector2f getDirection() {
		return Vector2f.toOrientationVector(this.angle);
	}

	
	public float getMaxLinearSpeed() {
		return this.maxLinearSpeed;
	}

	
	public float getMaxAngularSpeed() {
		return this.maxAngularSpeed;
	}
	
	public float getCurrentAngularSpeed() {
		return this.currentAngularSpeed;
	}

	
	public float getCurrentLinearSpeed() {
		if (this.currentLinearMotion == null) {
			return 0;
		}
		return this.currentLinearMotion.length();
	}

	
	public Vector2f getCurrentLinearMotion() {
		if (this.currentLinearMotion == null) {
			return new Vector2f();
		}
		return this.currentLinearMotion;
	}

	/*
	public Serializable getType() {
		return this.type;
	}*/

	/*
	public String getName() {
		return this.name;
	}*/
	
	
	@Override	
	public String toString(){
		
		return "UUID : " + this.bodyId + "   Position : (" + getPosition().getX() + "," + getPosition().getY() + ")";
		
	}
}
