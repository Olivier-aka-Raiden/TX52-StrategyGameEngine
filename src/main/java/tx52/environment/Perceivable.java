package tx52.environment;

public final class Perceivable {

	EnvironmentObject obj; //need modifs
	
	public Perceivable(EnvironmentObject o) {
		this.obj = o;
	}
	
	@Override	
	public String toString(){
		
		return obj.toString();
		
	} 
}
