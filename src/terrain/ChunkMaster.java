package terrain;

import java.util.LinkedList;
import org.joml.Vector3f;

public class ChunkMaster {
	
	private LinkedList<Chunk> chunks = new LinkedList<Chunk>();
	
	private final int SIZE = 1024; //Try to make it a power of 2 so that it can split nicely
	
	private final int CHUNK_SIZE = 16;
	
	private final int numberOfChunks = SIZE/CHUNK_SIZE;
	
	private Vector3f origin;
	
	public ChunkMaster(int x, int z) {
		origin = new Vector3f(x, 0, z);
		generateChunks();
	}
	
	private void generateChunks() {
		for(int z=0;z<numberOfChunks;z++) {
			for(int x=0;x<numberOfChunks;x++) {
				chunks.add(new Chunk(x, z));
			}
		}
	}
	
	private void loadChunkMesh() {
		boolean firstChunk = true;
		for(Chunk chunk: chunks) {
			float[] chunkVertices = chunk.getVertices();
			float[] chunkNormals = chunk.getNormals();
			float[] chunkTexture = chunk.getTextureCoords();
			int[] chunkIndices = chunk.getIndices();
			int posX = chunk.getxCoord();
			int poxZ = chunk.getzCoord();
			
		}
	}
	
	
}
