package core;

import java.util.HashSet;

public class Aspect {

	HashSet<Class<? extends Component>> requiredComponents = new HashSet<Class<? extends Component>>();
	
	public Aspect(Class<? extends Component>... types) {
		for(Class<? extends Component> type : types) {
			requiredComponents.add(type);
		}
	}
	
	public boolean contains(Entity entity) {
		boolean hasAll = true;
		for(Class<? extends Component> component : requiredComponents) {
			if(!entity.hasComponent(component)) {
				hasAll = false;
			}
		}
		return hasAll;
	}
}
