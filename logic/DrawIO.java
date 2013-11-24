package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import shapes.Circle;
import shapes.Line;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Text;

public class DrawIO {

	public void export(File f, DrawingController c) {
		try {
			c.getSelection().empty();
			BufferedImage bi = c.getDrawing().getImage(); // retrieve image
			ImageIO.write(bi, "png", f);
		}
		catch (IOException e) {
		}
	}

	public Point getPoint(String str) {
		String[] p = str.split(",");

		return new Point(Integer.parseInt(p[0].trim()), Integer.parseInt(p[1]
				.trim()));

	}

	public void open(File f, DrawingController c) {
		int lineNumber = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String str;

			Point p = getPoint(in.readLine());
			c.newDrawing(new Dimension(p.x, p.y));

			while ((str = in.readLine()) != null) {
				try {
					lineNumber++;
					if (str.length() == 0) {
						continue;
					}

					String[] parts = str.split(";");

					Point p1 = getPoint(parts[1]);
					Point p2 = getPoint(parts[2]);
					Shape sh = null;
					parts[0] = parts[0].trim();

					if (parts[0].equals("rect")) {
						boolean fill = Integer.parseInt(parts[4].trim()) == 0 ? false
								: true;
						sh = new Rectangle(p1.x, p1.y, fill);
					}
					else if (parts[0].equals("circ")) {
						boolean fill = Integer.parseInt(parts[4].trim()) == 0 ? false
								: true;
						sh = new Circle(p1.x, p1.y, fill);
					}
					else if (parts[0].equals("line")) {
						sh = new Line(p1.x, p1.y);
					}
					else if (parts[0].equals("text")) {
						int fontSize = Integer.parseInt(parts[4].trim());
						sh = new Text(p1.x, p1.y, fontSize, parts[5]);
					}
					else {
						throw new ArrayIndexOutOfBoundsException();
					}

					if (sh != null) {
						sh.setPoint2(p2);
						sh
								.setColor(new Color(Integer.parseInt(parts[3]
										.trim())));
						c.getDrawing().insertShape(sh);
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Could not read line " + lineNumber
							+ " in file \"" + f + "\"");
				}
				catch (NumberFormatException e) {
					System.out.println("Could not read line " + lineNumber
							+ " in file \"" + f + "\"");
				}

			}

			in.close();
		}
		catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}

	public void save(File f, DrawingController c) {
		Drawing d = c.getDrawing();

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(f));

			out.write(d.getPreferredSize().width + ","
					+ d.getPreferredSize().height + "\n");

			for (Shape s : c.getDrawing()) {
				out.write(s.toString() + "\n");
			}
			out.close();

		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not save the drawing.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
