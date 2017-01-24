import java.awt.Color;
import java.awt.Graphics;

public class GameBox extends GameObject {
	
	// ------------------------------------------------------------------------------------- Properties
	
	private BoxType type;
	private StatusType status;
	
	// ------------------------------------------------------------------------------------- Constructors
	
	public GameBox(int x, int y, int width, int height, StatusType covered, BoxType type) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setStatus(covered);
		setType(type);
	}
	
	public GameBox(int x, int y, int width, int height, BoxType type) {
		this(x, y, width, height, StatusType.COVERED, type);
	}
	
	// ------------------------------------------------------------------------------------- Methods
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		if(getStatus() == StatusType.COVERED) {
			g.setColor(Color.GRAY);
			g.fillRect(getX()+1, getY()+1, getWidth()-2, getHeight()-2);
			g.setColor(Color.BLACK);
		} else if(getStatus() == StatusType.FLAGGED) {
			g.setColor(Color.BLUE);
			g.fillRect(getX()+1, getY()+1, getWidth()-2, getHeight()-2);
			g.setColor(Color.BLACK);
		} else {
			switch(type) {
			case MINE: 	g.drawLine(getX(), 					getY(), 				getX()+getWidth(), 	getY()+getHeight()); break;
			case EIGHT: g.drawOval(getX()+getWidth()*2/3, 	getY()+getHeight()*2/3, getWidth()/3, 		getHeight()/3);
			case SEVEN: g.drawOval(getX()+getWidth()/3, 	getY()+getHeight()*2/3, getWidth()/3, 		getHeight()/3);
			case SIX: 	g.drawOval(getX(), 					getY()+getHeight()*2/3, getWidth()/3, 		getHeight()/3);
			case FIVE: 	g.drawOval(getX()+getWidth()*2/3, 	getY()+getHeight()/3,	getWidth()/3, 		getHeight()/3);
			case FOUR: 	g.drawOval(getX(), 					getY()+getHeight()/3, 	getWidth()/3, 		getHeight()/3);
			case THREE: g.drawOval(getX()+getWidth()*2/3,	getY(), 				getWidth()/3, 		getHeight()/3);
			case TWO:	g.drawOval(getX()+getWidth()/3,		getY(), 				getWidth()/3, 		getHeight()/3);
			case ONE: 	g.drawOval(getX(), 					getY(), 				getWidth()/3, 		getHeight()/3); break;
			default: break;
			}
		}
	}
	
	// ------------------------------------------------------------------------------------- Getters and Setters
	
	public BoxType getType() {
		return type;
	}

	public void setType(BoxType type) {
		this.type = type;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}
	
}
