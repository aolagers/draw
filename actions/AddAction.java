package actions;

import logic.Drawing;
import shapes.Shape;

/**
 * AddAction implements a single undoable action where a Shape is added to a
 * Drawing.
 */
public class AddAction implements DrawAction {

	Drawing d;
	Shape s;

	/**
	 * Creates an AddAction that adds the given Shape to the given Drawing.
	 * 
	 * @param dr
	 *            the drawing into which the shape should be added.
	 * @param sh
	 *            the shape to be added.
	 */
	public AddAction(Drawing dr, Shape sh) {
		this.d = dr;
		this.s = sh;
	}

	public void execute() {
		d.insertShape(s);
	}

	public String getDescription() {
		return null;
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		d.removeShape(s);
	}

}
