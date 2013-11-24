package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Shape {

	protected Point point1;
	protected Point point2;

	protected Color color;
	protected double strokeWidth;
	protected boolean selected;

	public Shape(Point p) {
		point1 = p;
		point2 = new Point(p.x + 25, p.y + 25);
		color = Color.BLACK;
		selected = false;
		strokeWidth = 2;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		drawShape(g);
		if (selected) {
			drawSelectionIndicator(g);
		}
	}

	public void drawSelectionIndicator(Graphics g) {

		((Graphics2D) g).setStroke(new BasicStroke((float) 1.0));
		g.setColor(new Color(255, 0, 255));

		int len = 10;
		int off = 5;

		Point p1 = getPosition();
		Point p2 = new Point(getPosition().x + getSize().x, getPosition().y
				+ getSize().y);

		g.drawPolyline(
				// left up
				new int[] { p1.x - off, p1.x - off, p1.x - off + len },
				new int[] { p1.y - off + len, p1.y - off, p1.y - off }, 3);

		g.drawPolyline(
				// right down
				new int[] { p2.x + off - len, p2.x + off, p2.x + off },
				new int[] { p2.y + off, p2.y + off, p2.y + off - len }, 3);

		g.drawPolyline(
				// right up
				new int[] { p2.x + off - len, p2.x + off, p2.x + off },
				new int[] { p1.y - off, p1.y - off, p1.y - off + len }, 3);

		g.drawPolyline(
				// left down
				new int[] { p1.x - off, p1.x - off, p1.x - off + len },
				new int[] { p2.y + off - len, p2.y + off, p2.y + off }, 3);

	}

	public abstract void drawShape(Graphics g);

	public Color getColor() {
		return color;
	}

	public Point getPosition() {
		return new Point(Math.min(point1.x, point2.x), Math.min(point1.y,
				point2.y));
	}

	public Point getSize() {
		return new Point(Math.abs(point2.x - point1.x), Math.abs(point2.y
				- point1.y));
	}

	/**
	 * Checks if the Shape contains the given point. Has a 2 pixel margin in all
	 * directions.
	 * 
	 * @param p
	 *            point to check
	 * @return true if shape includes given point
	 */
	public boolean includes(Point p) {
		if ((p.x - getPosition().x > -2)
				&& (p.x - getPosition().x < getSize().x + 2)
				&& (p.y - getPosition().y > -2)
				&& (p.y - getPosition().y < getSize().y + 2)) {
			return true;
		}
		return false;
	}

	public void move(int x, int y) {
		point1.x = point1.x + x;
		point1.y = point1.y + y;
		point2.x = point2.x + x;
		point2.y = point2.y + y;
	}

	public void setColor(Color c) {
		color = c;
	}

	public void setPoint1(Point p) {
		this.point1 = p;
	}

	public void setPoint2(Point p) {
		this.point2 = p;
	}

	public void setSelected(boolean b) {
		selected = b;
	}

	public String toString() {
		String str = "" + point1.x;
		str += "," + point1.y;
		str += ";" + point2.x;
		str += "," + point2.y;
		str += ";" + color.getRGB();

		return str;
	}

}
