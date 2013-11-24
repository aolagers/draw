package shapes;

import java.awt.Graphics;

public class Rectangle extends FillableShape {

	public Rectangle(int x, int y, boolean filled) {
		super(x, y);
		this.filled = filled;
	}

	public void drawFilled(Graphics g) {
		g.fillRect(getPosition().x, getPosition().y, getSize().x, getSize().y);
	}

	public void drawNonFilled(Graphics g) {
		g.drawRect(getPosition().x, getPosition().y, getSize().x, getSize().y);
	}

	public String toString() {
		return "rect;" + super.toString();
	}

}
