package terrain;

import org.joml.Matrix4f;

import glRenderingObjects.Vao;
import utils.Maths;

public class Terrain {
	
	private static int VERTICES = 128;
	private static int BOXES = VERTICES-1;
	private static int SIZE = 800;
	
	private int x;
	private int z;
	private float[] vertices = new float[VERTICES*VERTICES*3];
	private float[] textureCoords = new float[VERTICES*VERTICES*2];
	private float[] normals = new float[VERTICES*VERTICES*3];
	private int[] indices = new int[6*BOXES*BOXES];
	private Vao mesh;
	private Matrix4f transformation;
	
	
	public Terrain(int x, int z) {
		this.x = x;
		this.z = z;
		calculateMesh();
		System.out.println("oh no");
		
	}
	
	private void calculateMesh() {
		int vertexPointer=0;
		for(int i =0; i<VERTICES;i++) {
			for(int j=0; j<VERTICES;j++) {
				vertices[vertexPointer*3] = (float)j/(VERTICES-1f)*SIZE;
				vertices[vertexPointer*3+1] = 0;
				vertices[vertexPointer*3+2] = (float)-i/(VERTICES-1f)*SIZE;
				textureCoords[vertexPointer*2] = (float)j/(VERTICES-1f);
				textureCoords[vertexPointer*2+1] = (float)i/(VERTICES-1f);
				normals[vertexPointer*3] = 0;
				normals[vertexPointer*3+1] = 1;
				normals[vertexPointer*3+2] = 0;
				vertexPointer++;
			}
		}
		int pointer=0;
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
				
			}
		}
		this.mesh = new Vao();
		mesh.storeData(indices, new int[] {3, 2, 3}, vertices, textureCoords, normals);
		
	}
	
	public int getVao() {
		return mesh.getVaoID();
	}
	
	public int getVertexCount() {
		return mesh.getVertexCount();
	}
	
}
