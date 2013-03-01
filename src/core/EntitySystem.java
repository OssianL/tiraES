package core;

public class EntitySystem {

	private Aspect aspect;
	
	public EntitySystem(Aspect aspect) {
		this.aspect = aspect;
	}
	
	public void process(Entity entity) {
		
	}
	
	public Aspect getAspect() {
		return aspect;
	}
	
}
