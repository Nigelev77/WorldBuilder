package terrainManager;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import game.GameEngine;
import glRenderingObjects.ModelVao;
import utils.Maths;

public class TerrainManager {
	
	protected static final int VERTICES = 64;
	private static int BOXES = VERTICES-1;
	protected static int SIZE = 128;
	
	protected static Matrix4f transform;
	
	private int x;
	private int z;
	private Vector3f[] vertices = new Vector3f[VERTICES*VERTICES];
	private Vector2f[] textureCoords = new Vector2f[VERTICES*VERTICES];
	private Vector3f[] normals = new Vector3f[VERTICES*VERTICES];
	private int[] indices = new int[6*BOXES*BOXES];
	private ModelVao mesh;
	
	private Chunk[] chunks = new Chunk[BOXES*BOXES];
	
	private float[] vertexArray = new float[VERTICES*VERTICES*3];
	private float[] textureCoordsArray = new float[VERTICES*VERTICES*2];
	private float[] normalsArray = new float[VERTICES*VERTICES*3];
	
	
	
	public void generateMesh() {
		transform = Maths.createTransformMatrix(new Vector3f(1,0,0), 0, 0, 0, 1);
		Chunk.inverseTransform=new Matrix4f(transform).invert();
		int vertexPointer = 0;
		for(int i=0;i<VERTICES;i++) {
			for(int j = 0; j<VERTICES;j++) {
				vertices[vertexPointer] = createVertex(i, j);
				normals[vertexPointer] = new Vector3f(0,1,0);
				textureCoords[vertexPointer] = createTexCoord(i, j);
				vertexPointer++;
			}
		}
		int pointer = 0;
		int boxPointer = 0;
		for(int i=0;i<VERTICES-1;i++) {
			for(int j=0;j<VERTICES-1;j++) {
				int topLeft = (i*VERTICES)+j;
				int topRight = (i*VERTICES)+j+1;
				int bottomLeft = ((i+1)*VERTICES)+j;
				int bottomRight = ((i+1)*VERTICES)+j+1;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomRight;
				indices[pointer++] = bottomLeft;
				Vector3f[] vertexArr = {
						vertices[topLeft],
						vertices[topRight],
						vertices[bottomLeft],
						vertices[bottomRight]
				};
				chunks[boxPointer++] = new Chunk(j, i, vertexArr);
			}
		}
		changeChunkHeight();
		showChunks();
		this.mesh = new ModelVao();
		loadData();
		this.mesh.storeData(indices, new int[]{3, 2, 3}, vertexArray, textureCoordsArray, normalsArray);
		updateMesh();
	}
	
	private Vector3f createVertex(int i, int j) {
		float x = (float) j/(VERTICES-1f)*SIZE;
		float y = 0;
		float z = (float) -i/(VERTICES-1f)*SIZE;
		return new Vector3f(x, y, z);
	}
	
	private Vector2f createTexCoord(int i, int j) {
		float x = (float) j/(VERTICES-1f);
		float y = (float) i/(VERTICES-1f);
		return new Vector2f(x, y);
	}
	
	private void showChunks() {
//		for(Chunk chunk:chunks) {
//			chunk.showCoords();
//		}
		chunks[0].showCoords();
		chunks[chunks.length-1].showCoords();
	}
	
	public void changeChunkHeight() {
		Vector3f playerPosition = GameEngine.camera.getPosition();
		for(int i = 0;i<chunks.length;i++) {
			if(i%2==0) {
				continue;
			}else {
				chunks[i].changeHeight(playerPosition.x+10, playerPosition.y, playerPosition.z);
			}
		}
	}
	
	
	public void updateMesh() {
		float[] vertexArr = new float[VERTICES*VERTICES*3];
		for(int z = 0;z<BOXES;z++) {
			for(int x = 0;x<BOXES;x++) {
				Chunk currentChunk = chunks[(z*BOXES)+x];
				Vector3f[] chunkVertex = currentChunk.getVertices();
				fillArray(vertexArr, 3*((z*VERTICES)+x), chunkVertex[0]);
				fillArray(vertexArr, 3*((z*VERTICES)+x+1), chunkVertex[1]);
				fillArray(vertexArr, 3*(((z+1)*VERTICES)+x), chunkVertex[2]);
				fillArray(vertexArr, 3*(((z+1)*VERTICES)+x+1), chunkVertex[3]);
			}
		}
		this.mesh.storeData(indices, new int[] {3, 2, 3}, vertexArr, textureCoordsArray, normalsArray);
	}
	
	private void fillArray(float[] array, int index, Vector3f data) {
		float[] dataArr = {data.x, data.y, data.z};
		for(int i = 0;i<3;i++) {
			array[index+i] = dataArr[i];

		}
	}
	
	private void loadData() {
		int vertexPointer = 0;
		for(int i = 0;i<vertices.length;i++) {
			vertexArray[vertexPointer*3] = vertices[i].x;
			vertexArray[vertexPointer*3+1] = vertices[i].y;
			vertexArray[vertexPointer*3+2] = vertices[i].z;
			textureCoordsArray[vertexPointer*2] = textureCoords[i].x;
			textureCoordsArray[vertexPointer*2+1] = textureCoords[i].y;
			normalsArray[vertexPointer*3] = normals[i].x;
			normalsArray[vertexPointer*3+1] = normals[i].y;
			normalsArray[vertexPointer*3+2] = normals[i].z;
			vertexPointer++;
		}
	}
	
	public int getVaoID() {
		return this.mesh.getVaoID();
	}
	
	public int getVertexCount() {
		return mesh.getVertexCount();
	}
	
	public Matrix4f getTransform() {
		return transform;
	}
	
}
