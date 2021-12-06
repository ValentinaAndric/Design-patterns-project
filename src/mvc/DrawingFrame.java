package mvc;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.GridBagLayout;

public class DrawingFrame extends JFrame {

	// mi ovde smestamo konkretan view koji je nas panel i zato instanciramo vire
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JPanel contentPane = new JPanel();
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private final JPanel pnlWest = new JPanel();

	private final JButton btnModify = new JButton("Modify");
	private final JButton btnDelete = new JButton("Delete");

	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JButton btnToBack = new JButton("To Back ");
	private final JButton btnToFront = new JButton("To Front");
	private final JButton btnBringToFront = new JButton("Bring To Front");
	private final JButton btnBringToBack = new JButton("Bring To Back");
	private final JLabel lblNewLabel_10 = new JLabel("Commands:");
	private final JLabel lblNewLabel_11 = new JLabel("");
	private final JLabel lblColors = new JLabel("Colors: ");
	private final JToggleButton tglbtnInnerColor = new JToggleButton("Inner color ");
	private final JToggleButton tglbtnNOuterColor = new JToggleButton("Outer color");
	private final JTextArea textArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(textArea);
	private JButton btnNext;

	public DrawingFrame() {

		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.mouseClicked(e);

			}
		});

		setTitle("IT 14-2018 Kijanovic Sara");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1000, 700);
		setResizable(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(95, 158, 160));

		contentPane.add(pnlNorth, BorderLayout.NORTH);

		getContentPane().add(view, BorderLayout.CENTER);
		GroupLayout gl_view = new GroupLayout(view);
		gl_view.setHorizontalGroup(gl_view.createParallelGroup(Alignment.LEADING).addGap(0, 587, Short.MAX_VALUE));
		gl_view.setVerticalGroup(gl_view.createParallelGroup(Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		view.setLayout(gl_view);

		pnlNorth.add(lblNewLabel_11);

		JLabel lblNewLabel_9 = new JLabel("Shapes: ");
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 12));
		pnlNorth.add(lblNewLabel_9);

		pnlNorth.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(tglbtnHexagon);

		ButtonGroup btnGroup = new ButtonGroup();

		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnHexagon);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setBackground(new Color(95, 158, 160));
		pnlSouth.add(tglbtnSelect);
		btnDelete.setEnabled(false);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});

		pnlSouth.add(btnDelete);
		btnGroup.add(btnDelete);
		btnModify.setEnabled(false);

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.modify();

			}
		});
		pnlSouth.add(btnModify);
		btnGroup.add(btnModify);

		pnlSouth.add(lblColors);

		pnlSouth.add(tglbtnInnerColor);
		btnGroup.add(tglbtnInnerColor);

		pnlSouth.add(tglbtnNOuterColor);
		btnGroup.add(tglbtnNOuterColor);
		pnlWest.setBackground(new Color(95, 158, 160));
		tglbtnInnerColor.setEnabled(true);
		tglbtnInnerColor.setEnabled(true);
		contentPane.add(pnlWest, BorderLayout.WEST);
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[] { 99, 0 };
		gbl_pnlWest.rowHeights = new int[] { 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 33,
				0 };
		gbl_pnlWest.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlWest.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlWest.setLayout(gbl_pnlWest);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 0;
		pnlWest.add(lblNewLabel_10, gbc_lblNewLabel_10);
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUndo.setEnabled(false);
		btnGroup.add(btnUndo);
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.fill = GridBagConstraints.BOTH;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 0);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 2;
		pnlWest.add(btnUndo, gbc_btnUndo);

		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRedo.setEnabled(false);
		btnRedo.setForeground(new Color(0, 0, 0));
		btnGroup.add(btnRedo);
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.fill = GridBagConstraints.BOTH;
		gbc_btnRedo.insets = new Insets(0, 0, 5, 0);
		gbc_btnRedo.gridx = 0;
		gbc_btnRedo.gridy = 4;
		pnlWest.add(btnRedo, gbc_btnRedo);

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		btnToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnToFront.setEnabled(false);
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.fill = GridBagConstraints.BOTH;
		gbc_btnToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnToFront.gridx = 0;
		gbc_btnToFront.gridy = 6;
		pnlWest.add(btnToFront, gbc_btnToFront);
		btnGroup.add(btnToFront);

		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		btnGroup.add(btnToBack);
		btnToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnToBack.setEnabled(false);
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.fill = GridBagConstraints.BOTH;
		gbc_btnToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnToBack.gridx = 0;
		gbc_btnToBack.gridy = 8;
		pnlWest.add(btnToBack, gbc_btnToBack);

		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		btnGroup.add(btnBringToFront);
		btnBringToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBringToFront.setEnabled(false);
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.fill = GridBagConstraints.BOTH;
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 10;
		pnlWest.add(btnBringToFront, gbc_btnBringToFront);

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		btnGroup.add(btnBringToBack);
		btnBringToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBringToBack.setEnabled(false);

		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.fill = GridBagConstraints.BOTH;
		gbc_btnBringToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToBack.gridx = 0;
		gbc_btnBringToBack.gridy = 12;
		pnlWest.add(btnBringToBack, gbc_btnBringToBack);

		tglbtnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.activeInnerColor();
				getTglbtnInnerColor().setSelected(false);
			}
		});
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 1;
		gbc_btnInnerColor.gridy = 8;
		pnlSouth.add(tglbtnInnerColor, gbc_btnInnerColor);

		tglbtnNOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.activeOuterColor();
				getTglbtnNOuterColor().setSelected(false);

			}
		});
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOuterColor.gridx = 1;
		gbc_btnOuterColor.gridy = 8;
		pnlSouth.add(tglbtnNOuterColor, gbc_btnOuterColor);

		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(new Color(95, 158, 160));
		pnlEast.setForeground(SystemColor.inactiveCaption);
		contentPane.add(pnlEast, BorderLayout.EAST);
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[] { 246, 0 };
		gbl_pnlEast.rowHeights = new int[] { 150, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlEast.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlEast.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlEast.setLayout(gbl_pnlEast);
		// pnlEast.add(textArea);
		textArea.setTabSize(40);
		textArea.setColumns(30);
		textArea.setRows(8);
		textArea.setSize(60, 50);
		textArea.setEditable(false);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlEast.add(scrollPane, gbc_scrollPane);

		JButton btnSaveCommands = new JButton("Save Commands");
		btnSaveCommands.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSaveCommands.setEnabled(true);
		GridBagConstraints gbc_btnSaveCommands = new GridBagConstraints();
		gbc_btnSaveCommands.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveCommands.gridx = 0;
		gbc_btnSaveCommands.gridy = 2;
		pnlEast.add(btnSaveCommands, gbc_btnSaveCommands);

		btnSaveCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveCommands();
			}
		});

		JButton btnLoadCommands = new JButton("Load Commands");
		btnLoadCommands.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLoadCommands.setEnabled(true);
		GridBagConstraints gbc_btnLoadCommands = new GridBagConstraints();
		gbc_btnLoadCommands.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoadCommands.gridx = 0;
		gbc_btnLoadCommands.gridy = 4;
		pnlEast.add(btnLoadCommands, gbc_btnLoadCommands);

		btnLoadCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadCommands();
			}
		});

		btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		btnNext.setEnabled(false);
		gbc_btnNext.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext.gridx = 0;
		gbc_btnNext.gridy = 6;
		pnlEast.add(btnNext, gbc_btnNext);

		JButton btnSaveDrawing = new JButton("Save Drawing");
		btnSaveDrawing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSaveDrawing.setEnabled(true);
		GridBagConstraints gbc_btnSaveDrawing = new GridBagConstraints();
		gbc_btnSaveDrawing.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveDrawing.gridx = 0;
		gbc_btnSaveDrawing.gridy = 9;
		pnlEast.add(btnSaveDrawing, gbc_btnSaveDrawing);

		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
			}
		});

		JButton btnLoadDrawing = new JButton("Load Drawing");
		btnLoadDrawing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLoadDrawing.setEnabled(true);
		GridBagConstraints gbc_btnLoadDrawing = new GridBagConstraints();
		gbc_btnLoadDrawing.gridx = 0;
		gbc_btnLoadDrawing.gridy = 11;
		pnlEast.add(btnLoadDrawing, gbc_btnLoadDrawing);

		btnLoadDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadDrawing();
			}
		});

	}

	// ---------------------GETERI I SETERI-------------------------
	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JToggleButton getTglbtnInnerColor() {
		return tglbtnInnerColor;
	}

	public JToggleButton getTglbtnNOuterColor() {
		return tglbtnNOuterColor;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}
}
