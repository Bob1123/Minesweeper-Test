import java.awt.Point;

public abstract class GameObject implements Drawable{
	
	// ------------------------------------------------------------------------------------- Properties
	
	private int x, y, width, height;
	
	// ------------------------------------------------------------------------------------- Methods
	
	public boolean contains(Point p) {
		return p.x >= x && p.x < x + width && p.y >= y && p.y < y + height;
	}
	
	// ------------------------------------------------------------------------------------- Getters and Setters
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if(width < 0) {
			width = 0;
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if(height < 0) {
			height = 0;
		}
		this.height = height;
	}
	
}
