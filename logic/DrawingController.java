package logic;

import gui.DrawGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import shapes.Shape;
import actions.AddAction;
import actions.ColorAction;
import actions.DeleteAction;
import actions.DrawAction;
import actions.FillAction;
import actions.MoveAction;
import actions.UndoManager;

public class DrawingController {

	private Drawing drawing;
	private UndoManager undoManager;
	private Selection selection;
	private DrawGUI gui;
	private Tool tool;

	public DrawingController(DrawGUI g) {
		drawing = null;
		undoManager = new UndoManager();
		selection = new Selection();
		gui = g;
		tool = Tool.LINE;
	}

	public void addShape(Shape s) {
		DrawAction add = new AddAction(drawing, s);
		add.execute();
		undoManager.addAction(add);

	}

	public void colorSelectedShapes(Color c) {
		for (Shape s : selection) {
			DrawAction col = new ColorAction(s, c);
			col.execute();
			undoManager.addAction(col);
		}
	}

	public void deleteSelectedShapes() {
		DrawAction del = new DeleteAction(drawing, selection);
		del.execute();
		undoManager.addAction(del);
		drawing.repaint();
	}

	public Drawing getDrawing() {
		return drawing;
	}

	public Selection getSelection() {
		return selection;
	}

	public Tool getTool() {
		return tool;
	}

	public void moveSelectedShapes(Point movement) {
		if (!selection.isEmpty()) {
			DrawAction move = new MoveAction(selection, movement);
			undoManager.addAction(move);
			move.execute();
		}
	}

	public void newDrawing(Dimension size) {
		drawing = new Drawing(size);
		if (gui != null) {
			gui.updateDrawing();
		}
	}

	public void recordMovement(Point movement) {
		if (!selection.isEmpty()) {
			DrawAction move = new MoveAction(selection, movement);
			undoManager.addAction(move);
		}
	}

	public void redo() {
		if (this.undoManager.canRedo()) {
			this.undoManager.redo();
		}
		drawing.repaint();
	}

	public void selectAll() {
		selection.empty();
		for (Shape sh : drawing) {
			selection.add(sh);
		}
		drawing.repaint();

	}

	public void setTool(Tool t) {
		this.tool = t;
	}

	public void toggleFilled() {
		DrawAction toggle = new FillAction(selection);
		toggle.execute();
		undoManager.addAction(toggle);
	}

	public void undo() {
		if (this.undoManager.canUndo()) {
			this.undoManager.undo();
		}
		drawing.repaint();
	}
}
