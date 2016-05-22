package tx52.environment;

import java.util.EventListener;


public interface EnvironmentListener extends EventListener {

	public abstract void gameOver();

	public abstract void EnvironmentChanged(Object object); //Onchanged ?
	
}
