package tx52.environment;

import java.util.UUID;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import tx52.util.ConstantContainer;

public class StaticObject extends EnvironmentObject {
	
	

	public StaticObject(UUID id){
		super(id);
	}

	public StaticObject(float x, float y,float width,float height,UUID id,World w,EnvMap map){
		super(width,height,id);
		BodyDef bd = new BodyDef();
		bd.position.set(x,y);  
		bd.type = BodyType.STATIC;
		
		//test with circle shape
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(width,height);
		
		//fixture test
		FixtureDef fd = new FixtureDef();
		fd.shape = ps;
		fd.density = ConstantContainer.BASIC_DENSITY;
		fd.friction = ConstantContainer.BASIC_FRICTION;        
		fd.restitution = ConstantContainer.BASIC_RESTITUTION;
		
		body =  w.createBody(bd);
		body.createFixture(fd);
	}

}
