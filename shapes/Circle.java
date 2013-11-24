package shapes;

import java.awt.Graphics;

public class Circle extends FillableShape {

	public Circle(int x, int y, boolean filled) {
		super(x, y);
		this.filled = filled;
	}

	public void drawFilled(Graphics g) {
		g.fillOval(getPosition().x, getPosition().y, getSize().x, getSize().y);
	}

	public void drawNonFilled(Graphics g) {
		g.drawOval(getPosition().x, getPosition().y, getSize().x, getSize().y);
	}

	public String toString() {
		return "circ;" + super.toString();
	}
}
