package test;

import com.artemis.ArtemisWorld;

import core.Entity;
import core.EntityWorld;

public class TestEntityFactory {

	public static void createTestEntity(EntityWorld world) {
		Entity entity = new Entity();
		world.addEntity(entity);
		world.addComponents(entity, new TestComponent());
		world.initEntity(entity);
	}
	
	public static void createArtemisTestEntity(ArtemisWorld world) {
		com.artemis.Entity e = world.createEntity();
		e.addComponent(new ArtemisTestComponent());
		e.addToWorld();
	}
	
}
