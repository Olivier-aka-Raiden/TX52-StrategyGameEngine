package tx52.environment;

import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Vector2f;

/**
 * 
 * @author Dudul
 * @argument emitter, velocity, influenceType
 */
public final class EnvironmentChangeQuery {
	
	private final UUID emitter;
	private final Vector2f velocity; //new velocity to apply to the body
	private final String influenceType;
	private final UUID target;
	private final int attackSpeed;
	
	/**
	 * 
	 * @param emitter
	 * @param velocity
	 * @param influenceType
	 */
	public EnvironmentChangeQuery(UUID emitter, Vector2f velocity,String influenceType) {
		this.emitter = emitter;
		this.velocity = velocity;
		this.influenceType = influenceType;
		this.target = null;
		this.attackSpeed = 0;
	}
	public EnvironmentChangeQuery(UUID emitter, UUID target,int attackSpeed,String influenceType) {
		this.emitter = emitter;
		this.velocity = null;
		this.influenceType = influenceType;
		this.target = target;
		this.attackSpeed = attackSpeed;
	}

	public UUID getTarget() {
		return target;
	}
	public int getAttackSpeed() {
		return attackSpeed;
	}
	/** Replies the emitter.
	 */
	public UUID getEmitter() {
		return this.emitter;
	}
	
	/** Replies the change.
	 */
	public Vector2f getVelocity() {
		return this.velocity;
	}

	public String getInfluenceType() {
		return influenceType;
	}

}
