package shaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;



public abstract class Shader {
	
	private int programID;
	private int vertexID;
	private int fragmentID;
	
	public Shader(String vertexFile, String fragmentFile) {
		int programID = GL32.glCreateProgram();
		vertexID = compileShader(vertexFile, GL32.GL_VERTEX_SHADER);
		fragmentID = compileShader(fragmentFile, GL32.GL_FRAGMENT_SHADER);
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getAllUniformLocations();
		
	}
	
	protected abstract void getAllUniformLocations();
	protected abstract void bindAttributes();
	
	protected void bindAttribute(int index, String inVariable) {
		GL20.glBindAttribLocation(programID, index, inVariable);
	}
	
	protected abstract void connectTextureUnits();
	
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	private int compileShader(String shaderSource, int shaderType) {
		StringBuilder source = new StringBuilder();
		try {
			FileInputStream file = new FileInputStream(new File(shaderSource));
			InputStreamReader stream = new InputStreamReader(file);
			BufferedReader reader = new BufferedReader(stream);
			String line;
			while((line=reader.readLine())!=null) {
				source.append(line+"\n");
			}
			
			reader.close();
			stream.close();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int shader = GL20.glCreateShader(shaderType);
		GL20.glShaderSource(shader, source);
		GL20.glCompileShader(shader);
		if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS)==GL11.GL_FALSE) {
			System.err.println("Could not compile shader, invalid syntax?");
			System.err.println(GL20.glGetShaderInfoLog(shader, 500));
			System.exit(-1);
		}
		return shader;
	}
	
	protected void loadInt(int location, int value) {
		GL20.glUniform1i(location, value);
	}
	
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void loadBoolean(int location, boolean value) {
		if(value) {
			GL20.glUniform1f(location, 1f);
		}else {
			GL20.glUniform1f(location, 0f);
		}
	}
	
	protected void loadVector2f(int location, Vector2f vec) {
		GL20.glUniform2f(location, vec.x, vec.y);
	}
	
	protected void loadVector3f(int location, Vector3f vec) {
		GL20.glUniform3f(location, vec.x, vec.y, vec.z);
	}
	
	protected void loadMatrix4f(int location, Matrix4f mat) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		mat.get(0, buffer);
		buffer.flip();
		GL20.glUniformMatrix4fv(location, false, buffer);
	}
}
