package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shape {

	public Line(int x, int y) {
		super(new Point(x, y));
	}

	public void drawShape(Graphics g) {

		((Graphics2D) g).setStroke(new BasicStroke((float) strokeWidth));

		g.drawLine(point1.x, point1.y, point2.x, point2.y);

	}

	@Override
	public String toString() {
		return "line;" + super.toString();
	}

}
