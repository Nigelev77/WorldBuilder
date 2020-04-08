package loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import glRenderingObjects.ModelVao;

public class SingleFaceWaveFrontLoader {
	
	
	public static ModelVao loadEntity(String fileName) {
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> textureCoords = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		float[] verticesArr;
		float[] textureCoordsArr = null;
		float[] normalsArr = null;
		int[] indicesArr;
		
		try {
			FileReader file = new FileReader(new File(fileName));
			BufferedReader reader = new BufferedReader(file);
			String faces = null;
			for(String line = reader.readLine();line!=null;line = reader.readLine()) {
				if(line.startsWith("v ")) {
					vertices.add(parseLine3f(line.split(" ")));
				}else if(line.startsWith("vt ")) {
					textureCoords.add(parseLine2f(line.split(" ")));
				}else if(line.startsWith("vn ")) {
					normals.add(parseLine3f(line.split(" ")));
				}else if(line.startsWith("f ")) {
					faces = line;
					break;
				}else {
					faces=line;
					continue;
				}
			}
			textureCoordsArr = new float[vertices.size()*2];
			normalsArr = new float[vertices.size()*3];
			
			for(String line = faces;line!=null;line = reader.readLine()) {
				if(!line.startsWith("f ")) {
					continue;
				}
				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				allocateVertexData(vertex1, indices, normals, textureCoords, textureCoordsArr, normalsArr);
				allocateVertexData(vertex2, indices, normals, textureCoords, textureCoordsArr, normalsArr);
				allocateVertexData(vertex3, indices, normals, textureCoords, textureCoordsArr, normalsArr);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		verticesArr = new float[vertices.size()*3];
		indicesArr = new int[indices.size()];
		int vertexPtr = 0;
		for(Vector3f vertex:vertices) {
			verticesArr[vertexPtr++] = vertex.x;
			verticesArr[vertexPtr++] = vertex.y;
			verticesArr[vertexPtr++] = vertex.z;
		}
		for(int i = 0; i<indices.size();i++) {
			indicesArr[i] = indices.get(i);
		}
		
		int[] dimensions = {3,2,3};
		ModelVao modelVao = new ModelVao();
		modelVao.storeData(indicesArr, dimensions, verticesArr, textureCoordsArr, normalsArr);
		return modelVao;
		
	}
	
	private static Vector3f parseLine3f(String[] line) {
		float x = Float.parseFloat(line[1]);
		float y = Float.parseFloat(line[2]);
		float z = Float.parseFloat(line[3]);
		return new Vector3f(x, y, z);
	}
	
	private static Vector2f parseLine2f(String[] line) {
		float x = Float.parseFloat(line[1]);
		float y = Float.parseFloat(line[2]);
		return new Vector2f(x, y);
	}
	
	private static void allocateVertexData(String[] vertex, List<Integer> indices, List<Vector3f> normals,
			List<Vector2f> textureCoords, float[] texture, float[] normal) {
		int vertexPointer = Integer.parseInt(vertex[0])-1;
		indices.add(vertexPointer);
		Vector2f textures = textureCoords.get(Integer.parseInt(vertex[1])-1);
		texture[vertexPointer*2] = textures.x;
		texture[vertexPointer*2+1] = textures.y;
		Vector3f norm = normals.get(Integer.parseInt(vertex[2])-1);
		normal[vertexPointer*3] = norm.x;
		normal[vertexPointer*3+1] = norm.y;
		normal[vertexPointer*3+2] = norm.z;
	}
}
