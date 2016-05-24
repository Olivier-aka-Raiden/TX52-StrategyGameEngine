package tx52.environment;

import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Vector2f;

public final class EnvironmentChangeQuery {
	
	private final UUID emitter; //is it enough ?
	
	private final Vector2f velocity; //new velocity to apply to the body
	
	public EnvironmentChangeQuery(UUID emitter, Vector2f velocity) {
		this.emitter = emitter;
		this.velocity = velocity;
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

}
