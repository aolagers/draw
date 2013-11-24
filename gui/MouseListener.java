package gui;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.DrawingController;
import logic.Tool;
import shapes.Circle;
import shapes.FillableShape;
import shapes.Line;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Text;

/**
 * MouseListener listens to the mouse events in a drawing and modifies the
 * Drawing through a DrawingController
 * 
 * @author Alex Lagerstedt
 * 
 */

public class MouseListener extends MouseAdapter {

	private DrawingController c;
	private ToolBox tools;

	boolean isDrawing;
	boolean multiSelect;

	private Point startPos;
	private Point lastPos;

	private Point mouseDelta;

	private Shape newShape;

	/**
	 * Constructs a new MouseListener
	 * 
	 * @param c
	 *            the DrawingController through which the modifications will be
	 *            done
	 * @param t
	 *            the ToolBox
	 */
	public MouseListener(DrawingController c, ToolBox t) {
		this.tools = t;
		this.c = c;
		this.newShape = null;
		this.mouseDelta = new Point(0, 0);

	}

	public void mouseDragged(MouseEvent m) {

		mouseDelta.x = m.getPoint().x - lastPos.x;
		mouseDelta.y = m.getPoint().y - lastPos.y;

		if (isDrawing && (newShape != null)) {
			newShape.setPoint2(lastPos);
		}

		if (c.getTool() == Tool.SELECT) {
			for (Shape s : c.getSelection()) {
				s.move(mouseDelta.x, mouseDelta.y);
			}
		}

		c.getDrawing().repaint();

		lastPos = m.getPoint();

	}

	public void mouseMoved(MouseEvent m) {
		lastPos = m.getPoint();
	}

	public void mousePressed(MouseEvent m) {
		startPos = lastPos;

		Tool t = c.getTool();
		isDrawing = true;

		if (t == Tool.SELECT) {

			Shape tmp = c.getDrawing().getShapeAt(startPos);

			if (((m.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == 0)
					&& !c.getSelection().contains(tmp)) {
				c.getSelection().empty();
			}

			if ((tmp != null) && (!c.getSelection().contains(tmp))) {

				// empty the selection before selecting a new shape if shift is
				// not down

				tools.setColor(tmp.getColor());

				if ((c.getSelection().isEmpty())
						&& (tmp instanceof FillableShape)) {
					tools.setFill(((FillableShape) tmp).getFilled());
				}

				if (tmp instanceof Text) {
					tools.setFontSize(((Text) tmp).getFont().getSize());
				}

				c.getSelection().add(tmp);

			}

			c.getDrawing().repaint();

		}
		else if (t == Tool.RECTANGLE) {
			newShape = new Rectangle(startPos.x, startPos.y, tools.getFill());
		}
		else if (t == Tool.CIRCLE) {
			newShape = new Circle(startPos.x, startPos.y, tools.getFill());
		}
		else if (t == Tool.LINE) {
			newShape = new Line(startPos.x, startPos.y);
		}
		else if (t == Tool.TEXT) {
			try {
				newShape = new Text(startPos.x, startPos.y, tools.getFontSize());
			}
			catch (IllegalArgumentException e) {
			}
			c.getDrawing().repaint();
		}

		if (newShape != null) {
			newShape.setColor(tools.getColor());
			c.addShape(newShape);
		}

	}

	public void mouseReleased(MouseEvent m) {
		isDrawing = false;
		newShape = null;

		if (c.getTool() == Tool.SELECT) {

			Point total = new Point(m.getPoint().x - startPos.x, m.getPoint().y
					- startPos.y);

			if ((total.x != 0) || (total.y != 0)) {
				c.recordMovement(total);
			}

		}
	}

}
