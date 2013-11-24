package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logic.Drawing;
import logic.DrawingController;

/**
 * Graphical user interface for the Drawing editor "Draw"
 * 
 * @author Alex Lagerstedt
 * 
 */

public class DrawGUI extends JFrame {

	/**
	 * A simple container that contains a Drawing instance and keeps it
	 * centered.
	 * 
	 * @author Alex Lagerstedt
	 * 
	 */
	private class DrawingContainer extends JPanel {

		private static final long serialVersionUID = 0;

		public DrawingContainer() {
			super(new GridBagLayout());
		}

		public void setDrawing(Drawing d) {
			this.removeAll();
			this.add(d);
			mouse = new MouseListener(controller, tools);
			d.addMouseListener(mouse);
			d.addMouseMotionListener(mouse);
			setPreferredSize(d.getPreferredSize());
			pack();
		}

	}

	public class StatusBar extends JLabel {

		private static final long serialVersionUID = 0;

		public StatusBar() {
			super();
			super.setPreferredSize(new Dimension(100, 16));
			setMessage("Ready");
		}

		public void setMessage(String message) {
			setText(" " + message);
		}
	}

	private DrawingController controller;
	private DrawingContainer drawingContainer;
	private MouseListener mouse;
	private ToolBox tools;
	private JScrollPane scrollpane;

	// private StatusBar statusBar;

	private static final long serialVersionUID = 0;

	public static void main(String[] args) {

		new DrawGUI();

	}

	/**
	 * Constructs a new graphical user interface for the program and shows it.
	 */
	public DrawGUI() {

		this.setTitle("Draw 0.2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawingContainer = new DrawingContainer();
		scrollpane = new JScrollPane(drawingContainer);

		controller = new DrawingController(this);
		tools = new ToolBox(controller);
		controller.newDrawing(new Dimension(500, 380));

		// statusBar = new StatusBar();

		getContentPane().add(tools, BorderLayout.WEST);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		// getContentPane().add(statusBar, BorderLayout.SOUTH);

		MenuListener mainMenuListener = new MenuListener(controller);
		JMenuBar mainMenu = new MainMenu(mainMenuListener);
		this.setJMenuBar(mainMenu);

		pack();
		setVisible(true);

	}

	/**
	 * Updates the GUI to show the Drawing instance that is currently controlled
	 * by the DrawingController.
	 */
	public void updateDrawing() {

		drawingContainer.setDrawing(controller.getDrawing());
		scrollpane.setPreferredSize(new Dimension(drawingContainer
				.getPreferredSize().width + 100, drawingContainer
				.getPreferredSize().height + 100));
		pack();
		repaint();
	}
}
