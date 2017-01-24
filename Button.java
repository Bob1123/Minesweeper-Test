import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Button extends GameObject {

	// ------------------------------------------------------------------------------------- Properties
	
	private boolean activated;
	private String name;
	
	// ------------------------------------------------------------------------------------- Constructor
	
	public Button(int x, int y, int width, int height, boolean activated, String name) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setActivated(activated);
		this.name = name;
	}

	public Button(int x, int y, int width, int height, String name) {
		this(x, y, width, height, false, name);
	}
	
	// ------------------------------------------------------------------------------------- Methods
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawString(name, getX(), getY()+getHeight()/3);
		g.setColor(Color.RED);
		if(activated) {
			g.setColor(Color.GREEN);
		}
		g.fillOval(getX()+getWidth()*2/3, getY()+getHeight()*2/3, getWidth()/3, getHeight()/3);
	}
	
	public void trigger(Point p) {
		if(contains(p)) {
			setActivated(!isActivated());
		}
	}
	
	// ------------------------------------------------------------------------------------- Getters and Setters
	
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
}
