package gui;

import gui.MainMenu.NewDrawingDialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import logic.DrawIO;
import logic.DrawingController;

/**
 * Listens to actions from the buttons in a menu and modifies the Drawing
 * through a DrawingController
 * 
 * @author Alex Lagerstedt
 * 
 */
public class MenuListener implements ActionListener {

	DrawingController controller;
	JFileChooser fileDialog;

	public MenuListener(DrawingController c) {
		this.controller = c;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		DrawIO fio = new DrawIO();

		if (cmd.equals("Quit")) {
			System.exit(0);
		}

		else if (cmd.equals("Save")) {
			controller.getDrawing().listShapes();
		}

		else if (cmd.equals("Undo")) {
			controller.undo();
		}

		else if (cmd.equals("Redo")) {
			controller.redo();
		}

		else if (cmd.equals("Select all")) {
			controller.selectAll();

		}

		else if (cmd.equals("Clear selection")) {
			controller.getSelection().empty();
			controller.getDrawing().repaint();
		}

		else if (cmd.equals("Delete")) {
			controller.deleteSelectedShapes();
		}

		else if (cmd.equals("Open")) {
			fileDialog = new JFileChooser();
			fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileFilter filter = new FileNameExtensionFilter("Draw files",
					"draw");
			fileDialog.addChoosableFileFilter(filter);
			fileDialog.setFileFilter(filter);

			fileDialog.showOpenDialog(null);
			File f = fileDialog.getSelectedFile();
			if (f != null) {
				fio.open(f, controller);
			}

		}

		else if (cmd.equals("Save as")) {
			fileDialog = new JFileChooser();
			fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);

			fileDialog.setSelectedFile(new File("new.draw"));
			FileFilter filter = new FileNameExtensionFilter("Draw files",
					"draw");
			fileDialog.addChoosableFileFilter(filter);
			fileDialog.setFileFilter(filter);

			fileDialog.showSaveDialog(null);

			File f = fileDialog.getSelectedFile();
			if (f != null) {
				fio.save(f, controller);
			}
		}

		else if (cmd.equals("Export PNG")) {
			fileDialog = new JFileChooser();
			fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileDialog.setDialogType(JFileChooser.CUSTOM_DIALOG);
			FileFilter filter = new FileNameExtensionFilter(
					"Portable Network Graphics", "png");
			fileDialog.addChoosableFileFilter(filter);

			fileDialog.setSelectedFile(new File("out.png"));
			fileDialog.showSaveDialog(null);

			File f = fileDialog.getSelectedFile();
			if (f != null) {
				fio.export(f, controller);
			}

		}

		else if (cmd.equals("New")) {
			NewDrawingDialog diag = new NewDrawingDialog();
			Dimension size = diag.getNewSize();
			System.out.println(size);
			if (size != null) {
				controller.newDrawing(size);
			}
		}

		else {
			JOptionPane.showMessageDialog(null, "Not implemented.",
					"Not implemented", JOptionPane.ERROR_MESSAGE);
		}
	}
}
