package tx52.environment;

public class EnvironmentEvent {
	String classNameOfUpdater;
	String updateAction;
	
	public EnvironmentEvent (String className,String action){
		this.classNameOfUpdater = className;
		this.updateAction = action;
	}

	public String getUpdateAction() {
		return updateAction;
	}
}
