package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import shapes.Shape;

public class Drawing extends JPanel implements Iterable<Shape> {

	private static final long serialVersionUID = 0;

	private ArrayList<Shape> shapes;

	public Drawing(Dimension size) {
		shapes = new ArrayList<Shape>(0);

		this.setPreferredSize(size);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);
	}

	public BufferedImage getImage() {

		BufferedImage bi = new BufferedImage(getPreferredSize().width,
				getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		this.print(g);
		return bi;
	}

	public Shape getShapeAt(Point p) {
		int index = shapes.size() - 1;
		while (index >= 0) {

			if (shapes.get(index).includes(p)) {
				return shapes.get(index);
			}
			index--;
		}
		return null;

	}

	public void insertShape(Shape s) {
		shapes.add(s);
	}

	@Override
	public Iterator<Shape> iterator() {
		return shapes.iterator();
	}

	public void listShapes() {
		System.out.println("---");
		for (Shape s : shapes) {
			System.out.println(s);
		}
		System.out.println("---");
	}

	public void lower(Shape s) {
		int index = shapes.indexOf(s);
		if (index < shapes.size() - 1) {
			shapes.remove(s);
			shapes.add(index, s);
		}
	}

	public int nShapes() {
		return shapes.size();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (Shape s : shapes) {
			s.draw(g);
		}
	}

	public void raise(Shape s) {
		int index = shapes.indexOf(s);
		if (index > 0) {
			shapes.remove(s);
			shapes.add(--index, s);
		}
	}

	public void removeShape(Shape s) {
		shapes.remove(s);
	}

}
