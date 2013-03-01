package test;


import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

public class ArtemisTestSystem extends EntityProcessingSystem {

	@Mapper ComponentMapper<ArtemisTestComponent> componentMapper;
	
	public ArtemisTestSystem() {
		super(Aspect.getAspectForAll(ArtemisTestComponent.class));
	}
	
	@Override
	protected void process(Entity e) {

		ArtemisTestComponent testComponent = componentMapper.get(e);
		testComponent.asdf++;
	}

}
