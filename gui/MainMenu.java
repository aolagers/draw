package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;

/**
 * 
 * Represents a main menu for a DrawGUI
 * 
 * @author Alex Lagerstedt
 * 
 */
public class MainMenu extends JMenuBar {

	/**
	 * A dialog that asks the user for the size for a new Drawing.
	 * 
	 * @author Alex Lagerstedt
	 * 
	 */
	public static class NewDrawingDialog extends JDialog implements
			ActionListener {

		private static final long serialVersionUID = 0;

		private JSpinner widthSpinner;
		private JSpinner heightSpinner;
		private JButton ok;
		private JButton cancel;

		private Dimension d;

		public NewDrawingDialog() {
			d = null;
			this.setLayout(new BoxLayout(this.getContentPane(),
					BoxLayout.Y_AXIS));

			this.setTitle("Drawing size");
			JPanel jp;

			jp = new JPanel();
			jp.add(new JLabel("Width"));
			widthSpinner = new JSpinner(
					new SpinnerNumberModel(600, 10, 4096, 1));
			jp.add(widthSpinner);
			this.getContentPane().add(jp);

			jp = new JPanel();
			jp.add(new JLabel("Height"));
			heightSpinner = new JSpinner(new SpinnerNumberModel(450, 10, 4096,
					1));
			jp.add(heightSpinner);
			this.getContentPane().add(jp);

			jp = new JPanel();
			ok = new JButton("OK");
			cancel = new JButton("Cancel");
			jp.add(ok);
			jp.add(cancel);
			this.getContentPane().add(jp);

			ok.addActionListener(this);
			cancel.addActionListener(this);

			this.setModal(true);

			this.pack();
			setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(ok)) {

				d = new Dimension((Integer) widthSpinner.getValue(),
						(Integer) heightSpinner.getValue());
			}

			setVisible(false);
		}

		/**
		 * Returns the size for a new Drawing.
		 * 
		 * @return the size chosen in the dialog
		 */
		public Dimension getNewSize() {
			return d;
		}
	}

	private static final long serialVersionUID = 0;
	private JMenuItem undo;

	private JMenuItem redo;

	public MainMenu(MenuListener listener) {

		JMenu fileMenu = new JMenu("File");
		JMenuItem newdrawing = new JMenuItem("New", new ImageIcon(
				"img/document-new.png"));
		JMenuItem open = new JMenuItem("Open", new ImageIcon(
				"img/document-open.png"));
		// JMenuItem save = new JMenuItem("Save", new ImageIcon(
		// "img/document-save.png"));
		JMenuItem saveas = new JMenuItem("Save as", new ImageIcon(
				"img/document-save-as.png"));
		JMenuItem export = new JMenuItem("Export PNG", new ImageIcon(
				"img/document-save-as.png"));
		JMenuItem quit = new JMenuItem("Quit", new ImageIcon(
				"img/system-log-out.png"));

		JMenu editMenu = new JMenu("Edit");
		undo = new JMenuItem("Undo", new ImageIcon("img/edit-undo.png"));
		redo = new JMenuItem("Redo", new ImageIcon("img/edit-redo.png"));

		JMenu selectionMenu = new JMenu("Selection");
		JMenuItem all = new JMenuItem("Select all", new ImageIcon(
				"img/edit-select-all.png"));
		JMenuItem clear = new JMenuItem("Clear selection", new ImageIcon(
				"img/edit-clear.png"));
		JMenuItem delete = new JMenuItem("Delete", new ImageIcon(
				"img/edit-delete.png"));
		delete.setActionCommand("Delete");

		final JMenu helpMenu = new JMenu("Help");
		final JMenuItem about = new JMenuItem("About", new ImageIcon(
				"img/dialog-information.png"));

		redo.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Y, java.awt.Event.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
		newdrawing.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_N, java.awt.Event.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Z, java.awt.Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		// saveas.setAccelerator(KeyStroke.getKeyStroke(
		// java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK
		// | java.awt.Event.SHIFT_MASK));
		// save.setAccelerator(KeyStroke.getKeyStroke(
		// java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
		export.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_E, java.awt.Event.CTRL_MASK));
		saveas.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
		clear.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_C, java.awt.Event.CTRL_MASK));
		all.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
				java.awt.Event.CTRL_MASK));
		delete.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_DELETE, 0));

		quit.addActionListener(listener);
		all.addActionListener(listener);
		undo.addActionListener(listener);
		redo.addActionListener(listener);
		about.addActionListener(listener);
		delete.addActionListener(listener);
		clear.addActionListener(listener);
		newdrawing.addActionListener(listener);
		open.addActionListener(listener);
		// save.addActionListener(listener);
		saveas.addActionListener(listener);
		export.addActionListener(listener);

		fileMenu.add(newdrawing);
		fileMenu.add(open);
		fileMenu.addSeparator();
		// fileMenu.add(save);
		fileMenu.add(saveas);
		fileMenu.add(export);
		fileMenu.addSeparator();
		fileMenu.add(quit);

		editMenu.add(undo);
		editMenu.add(redo);

		selectionMenu.add(all);
		selectionMenu.add(clear);
		selectionMenu.add(delete);

		helpMenu.add(about);

		this.add(fileMenu);
		this.add(editMenu);
		this.add(selectionMenu);
		this.add(helpMenu);
	}
}
