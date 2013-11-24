package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import logic.DrawingController;
import logic.Selection;

import org.junit.Before;
import org.junit.Test;

import shapes.Circle;
import shapes.Line;
import shapes.Rectangle;
import shapes.Shape;

public class DrawTest {

	private DrawingController dc;
	private Selection sel;

	@Before
	public void setUp() {
		this.dc = new DrawingController(null);
		dc.newDrawing(new Dimension(1000, 1000));
	}

	@Test
	public void testAddShape() {
		Shape s = new Line(100, 100);
		s.setPoint2(new Point(200, 200));

		dc.addShape(s);

		assertTrue("Drawing should have one shape",
				dc.getDrawing().nShapes() == 1);
		assertEquals("Point (150,150) should be inside the shape", dc
				.getDrawing().getShapeAt(new Point(150, 150)), s);
		assertEquals("Point (250,150) should be outside the shape", dc
				.getDrawing().getShapeAt(new Point(250, 150)), null);
		dc.undo();
		assertTrue("Drawing should have no shapes after undoing the addition",
				dc.getDrawing().nShapes() == 0);

	}

	@Test
	public void testColorAction() {
		Shape s = new Line(100, 100);
		dc.addShape(s);

		assertEquals("Default color should be black", s.getColor(), Color.BLACK);

		dc.selectAll();
		dc.colorSelectedShapes(Color.CYAN);

		assertEquals("Modified color should be cyan", Color.CYAN, s.getColor());

		dc.undo();
		assertEquals("Color should be back to black after undoing",
				Color.BLACK, s.getColor());

	}

	@Test
	public void testMoveAction() {
		Shape s1 = new Rectangle(100, 100, true);
		s1.setPoint2(new Point(200, 200));
		dc.addShape(s1);

		Shape s2 = new Circle(100, 100, false);
		s2.setPoint2(new Point(200, 200));
		dc.addShape(s2);

		dc.selectAll();

		dc.moveSelectedShapes(new Point(57, 57));

		assertEquals("Position after movement", new Point(157, 157), s1
				.getPosition());

	}

	@Test
	public void testSelection() {
		sel = new Selection();
		Shape s = new Line(100, 100);
		sel.add(s);
		sel.add(s);
		assertEquals("A object should be selected only once", 1, sel.nShapes());

		Selection sel2 = sel.clone();

		assertEquals("Cloned selection", sel.contains(s), sel2.contains(s));
		assertEquals("Cloned selection", 1, sel2.nShapes());
		sel.empty();
		assertTrue("Should be empty", sel.isEmpty());
		assertFalse("Shouldn't be empty", sel2.isEmpty());

	}

}
