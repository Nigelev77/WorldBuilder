package glRenderingObjects;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL30;

public class ObjectHandler {
	
	public static List<Integer> vaos = new ArrayList<Integer>();
	public static List<Integer> vbos = new ArrayList<Integer>();
	
	public static void addVao(Vao vao) {
		vaos.add(vao.getVaoID());
	}
	public static void addVbo(Vbo vbo) {
		vbos.add(vbo.getVboID());
	}
	
	public static void cleanUp() {
		int[] vaoArr = new int[vaos.size()];
		vaos.forEach(n->vaoArr[vaos.indexOf(n)] = n.intValue());
		int[] vboArr = new int[vaos.size()];
		vbos.forEach(n->vboArr[vaos.indexOf(n)] = n.intValue());
		GL30.glDeleteBuffers(vboArr);
		GL30.glDeleteVertexArrays(vaoArr);
	}
}
