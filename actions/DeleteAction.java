package actions;

import logic.Drawing;
import logic.Selection;
import shapes.Shape;

/**
 * DeleteAction implements a single undoable action where all Shapes in a given
 * Selection are added to a Drawing.
 */
public class DeleteAction implements DrawAction {

	Drawing d;
	Selection selection;

	int position;

	/**
	 * Creates an DeleteAction that removes all shapes in the given Selection
	 * from the given Drawing.
	 * 
	 * @param drawing
	 *            the drawing into which the shape should be added.
	 * @param selection
	 *            the shape to be added.
	 */
	public DeleteAction(Drawing drawing, Selection selection) {
		// The selection need to be hard-copied because the selection behind the
		// reference will change while editing the drawing.
		this.selection = selection.clone();
		this.d = drawing;
	}

	public void execute() {
		for (Shape s : selection) {
			d.removeShape(s);
		}
	}

	public String getDescription() {
		return null;
	}

	public void redo() {
		execute();
	}

	public void undo() {
		for (Shape s : selection) {
			d.insertShape(s);
		}
	}

}
