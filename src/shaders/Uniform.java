package shaders;

import org.lwjgl.opengl.GL20;

public class Uniform<Type> {
	
	Type type;
	private int location;
	
	public Uniform(String name, Shader shader){
		location = shader.getUniformLocation(name);
	}
	
	public void loadValue(Type type, Shader shader) {
		this.type=type;
		TypeLoader.loadType(type, location, shader);
	}
	public Type getType() {
		return type;
	}
	
	public int getLocation() {
		return location;
	}
	
	
	
}
