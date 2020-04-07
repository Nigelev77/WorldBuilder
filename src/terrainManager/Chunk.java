package terrainManager;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Chunk {
	
	protected static Matrix4f inverseTransform = new Matrix4f().identity();
	private static float ChunkSize = TerrainManager.SIZE/TerrainManager.VERTICES;
	private static final float intensity = 1/100f;
	
	private Vector3f[] vertices;
	public float x, z;
	
	public Chunk(float x, float z, Vector3f[] vertices) {
		this.vertices = vertices;
		this.x = x;
		this.z = z;
	}
	
	public void changeHeight(float x, float y, float z) {
		
		Vector2f pointPos = new Vector2f(x, z);
		for(Vector3f vertex:vertices) {
			Vector2f xzPosition = new Vector2f(vertex.x, vertex.z);
			float distance = xzPosition.distance(pointPos);
			float factor = 1f/(intensity*(distance)+1);
			vertex.y = y*factor;
			
		}
	}
	
	public void showCoords() {
		System.out.println("========== NEW CHUNK =============");
		for(Vector3f vertex:vertices) {
			System.out.println(vertex.toString());
		}
		System.out.println("========== END CHUNK =============");
	}
	
	public Vector3f[] getVertices() {
		return vertices;
	}
	
}
