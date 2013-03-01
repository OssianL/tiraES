package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import utils.DynArray;

public class EntityWorld {
	
	private HashSet<EntitySystem> systems = new HashSet<EntitySystem>();
	//private HashMap<System, Aspect> systemAspects;
	private HashMap<Aspect, HashSet<Integer>> entitiesPerAspect = new HashMap<Aspect, HashSet<Integer>>();
	private DynArray<Entity> entities = new DynArray<Entity>();
	private DynArray<HashMap<Class<? extends Component>, Component>> entityComponents = new DynArray<HashMap<Class<? extends Component>, Component>>();
	
	private int entityCount = 0;
	private int currentEntityId = 0;

	public EntityWorld() {
		
	}
	
	public void process() {
		
//		System.out.println("entity count: " + entities.size());
//		System.out.println("system count: " + systems.size());
//		System.out.println("aspect Count: " + entitiesPerAspect.size());
		
		for(EntitySystem system : systems) {
			for(int entity : entitiesPerAspect.get(system.getAspect())) {
				system.process(entities.get(entity));
			}
		}
	}
	
	public void initialize() {
		//
	}
	
	public Entity addEntity(Entity entity) {
		if(needToDefragmentateEntityContainers()) {
			System.out.println("DEFRAGMENTATION!!! " + entityCount);
			defragmentateEntityContainers();
		}
		entity.setId(currentEntityId);
		entity.setWorld(this);
		currentEntityId++;
		entityCount++;
		
		entities.add(entity.getId(), entity);
		return entity;
	}
	
	public void removeEntity(Entity entity) {
		
	}
	
	public void initEntity(Entity entity) {
		handleAspects(entity);
	}
	
	public void addComponents(Entity entity, Component... components) {
		for(Component component : components) {
			if(entityComponents.get(entity.getId()) == null) {
				if(!entityComponents.add(entity.getId(), new HashMap<Class<? extends Component>, Component>())) {
					System.out.println("matafaka!!!");
				}
			}
			entityComponents.get(entity.getId()).put(component.getClass(), component);
		}
	}
	
	public Component getComponent(Entity entity, Class<? extends Component> type) {
		return entityComponents.get(entity.getId()).get(type);
	}
	
	public void removeComponent(Entity entity, Component component) {
		
	}
	
	public boolean hasComponent(Entity entity, Class<? extends Component> type) {
		if(entityComponents.get(entity.getId()) != null) {
			return entityComponents.get(entity.getId()).containsKey(type);
		}
		return false;
	}
	
	public void addSystem(EntitySystem system) {
		systems.add(system);
		entitiesPerAspect.put(system.getAspect(), new HashSet<Integer>());
	}
	
	public int getEntityCount() {
		return entities.size();
	}
	
	private void handleAspects(Entity entity) {
		for(EntitySystem system : systems) {
			if(system.getAspect().contains(entity)) {
				entitiesPerAspect.get(system.getAspect()).add(entity.getId());
			}
		}
	}
	
	private boolean needToDefragmentateEntityContainers() {
		return currentEntityId >= entities.size();
	}
	
	//semi vammanen
	private void defragmentateEntityContainers() {
		int newSize = entityCount * 2;
		Object[] oldEntitiesArray = entities.getArray();
		Object[] newEntitiesArray = new Object[newSize];
		Object[] oldComponentsArray = entityComponents.getArray();
		Object[] newComponentsArray = new Object[newSize];
		
		int counter = 0;
		for(int i = 0; i < entities.size(); i++) {
			if(oldEntitiesArray[i] != null) {
				newEntitiesArray[counter] = oldEntitiesArray[i];
				newComponentsArray[counter] = oldComponentsArray[i];
				counter++;
				entities.get(i).setId(counter);
			}
		}
		entities.setArray(newEntitiesArray);
		entityComponents.setArray(newComponentsArray);
		currentEntityId = entityCount + 1;
	}
	
	
}
