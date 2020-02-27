package shaders;

import org.lwjgl.opengl.GL20;

public class Uniform<Type> {
	
	Type type;
	private int location;
	
	public Uniform(String name, int programID){
		location = GL20.glGetUniformLocation(programID, name);
	}
	
	public void loadValue(Type type, Shader shader) {
		this.type=type;
		TypeLoader.loadType(type, location, shader);
	}
	public Type getType() {
		return type;
	}
	
	
	
}
