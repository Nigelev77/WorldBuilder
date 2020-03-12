package terrain;

public class Chunk {
	
	private static final int SQUARE_COUNT = 4;
	
	private int xCoord;
	private int zCoord;
	
	private float[] vertices = new float[9*3];
	private float[] normals = new float[9*3];
	private float[] textureCoords = new float[9*2];
	
	
	private int[] indices = new int[3*8];


	public Chunk(int xCoord, int zCoord) {
		this.xCoord = xCoord;
		this.zCoord = zCoord;
		createMesh();
	}
	
	public void createMesh() {
		for(int vertex=0;vertex<9;vertex++) {
			vertices[vertex*3] = (float) ((vertex%3)/2f);
			vertices[vertex*3+1] = 0f;
			vertices[vertex*3+2] = (float) ((vertex/3)/2f);
			textureCoords[vertex*2] = 0;
			textureCoords[vertex*2+1] = 0;
			normals[vertex*3] = 0;
			normals[vertex*3+1] = 1;
			normals[vertex*3+2] = 0;
			
		}
		int pointer=0;
		for(int i=0;i<SQUARE_COUNT;i++) {
			int topLeft = i+(i/2);
			int topRight = topLeft+1;
			int bottomLeft = topLeft+3;
			int bottomRight = topRight+3;
			indices[pointer++] = topLeft;
			indices[pointer++] = bottomLeft;
			indices[pointer++] = topRight;
			indices[pointer++] = topRight;
			indices[pointer++] = bottomRight;
			indices[pointer++] = bottomLeft;
			
		}
		
		
	}
	

	public int getxCoord() {
		return xCoord;
	}

	public int getzCoord() {
		return zCoord;
	}

	public float[] getVertices() {
		return vertices;
	}

	public float[] getNormals() {
		return normals;
	}

	public float[] getTextureCoords() {
		return textureCoords;
	}

	public int[] getIndices() {
		return indices;
	}
	
	
}
