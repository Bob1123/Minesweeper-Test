import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tester extends JPanel {
	
	GameBoard mineField = new GameBoard(100, 100, 800, 500);
	Button zero = new Button(1000, 100, 100, 100, "ZEROTH");
	Button one = new Button(1000, 200, 100, 100, "FIRST");
	Button two = new Button(1000, 300, 100, 100, "SECOND");
	Button three = new Button(1000, 400, 100, 100, "THIRD");
	Button flag = new Button(1000, 500, 100, 100, "Flag");

	public Tester() {
		JFrame window = new JFrame("Twist-Tac-Toe");
		window.setBounds(0, 0, 1200, 700);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.add(this);
		window.setVisible(true);		

		// Mouse Clicked
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			// All the work is going to be done in this method here
			@Override
			public void mouseClicked(MouseEvent e) {
				Point position = mineField.getPosition(getMousePosition());
				System.out.println(position);
				if(position.x != -1 && position.y != -1) {
					if(flag.isActivated()) {
						mineField.getBoard()[position.x][position.y].setStatus(StatusType.FLAGGED);
					} else {					
						if(mineField.getBoard()[position.x][position.y].getType() == BoxType.MINE) {
							JOptionPane.showMessageDialog(null, "YOU LOSE");
						}
						if(zero.isActivated()) {
							mineField.zero(position);
						}
						if(one.isActivated()) {
							mineField.one();
						}
						if(two.isActivated()) {
							mineField.two();
						}
						if(three.isActivated()) {
							mineField.three();
						}
						
	
						mineField.getBoard()[position.x][position.y].setStatus(StatusType.UNCOVERED);
					}
				}
				zero.trigger(getMousePosition());
				one.trigger(getMousePosition());
				two.trigger(getMousePosition());
				three.trigger(getMousePosition());
				flag.trigger(getMousePosition());
				
				repaint();
			}
		});

		// Mouse Moved
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {}
			@Override
			public void mouseDragged(MouseEvent e) {}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		mineField.draw(g);
		zero.draw(g);
		one.draw(g);
		two.draw(g);
		three.draw(g);
		flag.draw(g);
		g.drawString("Number of Flagged Mines: " + mineField.numFlagged(), 10, 50);
	}

	public static void main(String[] args) {
		System.out.println("Time to test!");
		new Tester();
	}

}