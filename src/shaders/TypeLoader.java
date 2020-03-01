package shaders;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public enum TypeLoader {
	Integer, Float, Boolean, Vector2f, Vector3f, Matrix4f;
	
	private static List<String> types = new ArrayList<String>(values().length);
	
	public static void init() {
		for(int i=0;i<values().length;i++) {
			types.add(i, values()[i].toString());
		}
	}
	public static <Type> void loadType(Type type, int location, Shader shader) {
		int uniformType = types.indexOf(type.getClass().getSimpleName());
		switch(uniformType) {
		case 0:
			shader.loadInt(location, ((Integer) type).intValue()); 
			break;
		case 1:
			shader.loadFloat(location, ((Float) type).floatValue());
			break;
		case 2:
			shader.loadBoolean(location, ((Boolean) type).booleanValue());
			break;
		case 3:
			shader.loadVector2f(location, (Vector2f) type);
			break;
		case 4:
			shader.loadVector3f(location, (Vector3f) type);
			break;
		case 5:
			shader.loadMatrix4f(location, (Matrix4f) type);
			break;
		}
	}

}
