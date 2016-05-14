package tx52.environment;

import java.util.EventObject;
import java.util.Map;
import java.util.UUID;

import org.arakhne.afc.math.continous.object2d.Point2f;

public class EnvironmentEvent extends EventObject {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int time;
	
	private final int width;
	
	private final int height;

	private final Map<Point2f, EnvironmentObject> objects;

	public EnvironmentEvent(UUID source, int time, int width, int height, Map<Point2f, EnvironmentObject> objects) {
		super(source);
		this.time = time;
		this.width = width;
		this.height = height;
		this.objects = objects;
	}
	
	public int getTime() {
		return this.time;
	}
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	public Map<Point2f, EnvironmentObject> getObjects() {
		return this.objects;
	}

}