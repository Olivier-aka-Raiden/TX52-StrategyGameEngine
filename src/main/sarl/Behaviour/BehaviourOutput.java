/* 
 * $Id$
 * 
 * Copyright (C) 2007-2011 Stephane Galland <stephane.galland@utbm.fr>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Behaviour;

import java.io.Serializable;

import org.arakhne.afc.math.continous.object2d.Vector2f;


/**
 * Output of a behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class BehaviourOutput implements Serializable {

	private static final long serialVersionUID = 1243172129345360316L;

	//private final DynamicType type; always Kinematic in our case
	private final Vector2f linear = new Vector2f();
	private float angular = 0;
	
	/**
	 * @param outputToCopy is the output tp copy.
	 */
	public BehaviourOutput(BehaviourOutput outputToCopy) {
		assert(outputToCopy!=null);
		this.linear.set(outputToCopy.getLinear());
		this.angular = outputToCopy.getAngular();
	}
	
	/** Replies the linear output.
	 * 
	 * @return the linear output.
	 */
	public Vector2f getLinear() {
		return this.linear;
	}
	
	/** Replies the angular output.
	 * 
	 * @return the angular output.
	 */
	public float getAngular() {
		return this.angular;
	}
	
	/** Set the linear output.
	 * 
	 * @param linear
	 */
	public void setLinear(Vector2f linear) {
		assert(linear!=null);
		this.linear.set(linear);
	}
	
	/** Set the linear output.
	 * 
	 * @param dx
	 * @param dy
	 */
	public void setLinear(float dx, float dy) {
		this.linear.set(dx, dy);
	}

	/** Set the angular output.
	 * 
	 * @param angular
	 */
	public void setAngular(float angular) {
		this.angular = angular;
	}

	/** Set the linear output.
	 * 
	 * @param outputToCopy
	 */
	public void setLinear(BehaviourOutput outputToCopy) {
		if (outputToCopy!=null) {
			this.linear.set(outputToCopy.getLinear());
		}
	}

	/** Set the angular output.
	 * 
	 * @param outputToCopy
	 */
	public void setAngular(BehaviourOutput outputToCopy) {
		if (outputToCopy!=null) {
			this.angular = outputToCopy.getAngular();
		}
	}

	/** Set the linear and angular output.
	 * 
	 * @param outputToCopy
	 */
	public void set(BehaviourOutput outputToCopy) {
		if (outputToCopy!=null) {
			this.linear.set(outputToCopy.getLinear());
			this.angular = outputToCopy.getAngular();
		}
	}
	
	@Override
	public String toString() {
		return "l=" + this.linear.toString() + "; a=" + this.angular;
	}

	BehaviourOutput() {
	  this.linear.set(0,0);
	  this.angular = 0.0f;
	}

}