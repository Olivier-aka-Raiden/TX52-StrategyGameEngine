package tx52.environment;

import java.util.UUID;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class StaticObject extends EnvironmentObject {
	
	//Do we need an UUID for static objects ?

	public StaticObject(float x, float y,UUID id){
		super(x,y,id);
	}

	public StaticObject(float x, float y,float width,float height,UUID id,World w,EnvMap map){
		super(x,y,width,height,id);
		BodyDef bd = new BodyDef();
		bd.position.set(x,y);  
		bd.type = BodyType.STATIC;
		
		//test with circle shape
		CircleShape cs = new CircleShape();
		cs.m_radius = 0.5f;
		
		//fixture test
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 0.5f;
		fd.friction = 0.3f;        
		fd.restitution = 0.5f;
		
		body =  w.createBody(bd);
		body.createFixture(fd);
	}

}
