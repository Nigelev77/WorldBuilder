package gui;

public interface CircularGUI {
	
	
	void setCenter(float x, float y);
	void render(long ccx);
	void setColor(int r, int g, int b, int a);
	void update();
}
