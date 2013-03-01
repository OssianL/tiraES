package test;

import core.Aspect;
import core.Entity;
import core.EntitySystem;

public class TestSystem extends EntitySystem{
	
	public TestSystem(Aspect aspect) {
		super(aspect);
	}
	
	@Override
	public void process(Entity entity) {
		TestComponent testComponent = (TestComponent) entity.getComponent(TestComponent.class);
		
		testComponent.kakkaa++;
	}
}
