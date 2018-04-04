package spring.bean;

public class BeanInfo {
	
	private String Id;
	
	private String clazz;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "BeanInfo [Id=" + Id + ", clazz=" + clazz + "]";
	}
	
	

}
