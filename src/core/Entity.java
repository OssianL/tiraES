package core;

public class Entity {

	private EntityWorld world;
	private Integer id;
	private Transform transform = new Transform();
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setWorld(EntityWorld world) {
		this.world = world;
	}
	
	public Component getComponent(Class<? extends Component> type) {
		return world.getComponent(this, type);
	}
	
	public boolean hasComponent(Class<? extends Component> type) {
		return world.hasComponent(this, type);
	}
}
