package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import adapter.HexagonAdapter;
import command.Command;
import command.RemoveShapeCmd;
import command.UpdateCircleCmd;
import command.UpdatePointCmd;
import command.AddShapeCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import observer.*;
import strategy.Save;
import strategy.SaveCommand;
import strategy.SaveDrawing;
import strategy.SaveManager;

//s
public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Color innerColor;
	private Color outerColor;
	private Stack<Command> activities = new Stack<Command>();
	private Stack<Command> undoActivities = new Stack<Command>();
	private SelectedShapesListObserver observer;

	private Shape selectedShape;
	private Point startPoint;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		observer = new SelectedShapesListObserver(frame);
		model.addPropertyChangeListener(observer);
	}

	public void mouseClicked(MouseEvent me) {

		Shape newShape = null;
		Point click = new Point(me.getX(), me.getY());

		if (frame.getTglbtnSelect().isSelected()) {
			selectedShape = null;
			Iterator<Shape> iterator = model.getShapes().iterator();

			while (iterator.hasNext()) {
				Shape shape = iterator.next();
				if (shape.contains(click.getX(), click.getY()))
					selectedShape = shape;

			}

			if (selectedShape != null) {

				if (!selectedShape.isSelected()) {

					selectedShape.setSelected(true);
					model.addSelected(selectedShape);
					frame.getTextArea().append("Select: " + selectedShape.toString() + "\n");

				} else {

					selectedShape.setSelected(false);
					model.removeSelected(selectedShape);
					frame.getTextArea().append("Deselect: " + selectedShape.toString() + "\n");

				}
			}

		} else if (frame.getTglbtnPoint().isSelected()) {

			newShape = new Point(click.getX(), click.getY(), false, Color.black);

		} else if (frame.getTglbtnLine().isSelected()) {

			if (startPoint == null)
				startPoint = click;
			else {
				newShape = new Line(startPoint, new Point(me.getX(), me.getY(), false, Color.black));
				startPoint = null;
			}

		} else if (frame.getTglbtnCircle().isSelected()) {

			DlgCircle dialog = new DlgCircle();

			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOutlineColor().setBackground(outerColor);
			dialog.setVisible(true);

			if (dialog.isConfirm()) {
				newShape = dialog.getCircle();
			}

		} else if (frame.getTglbtnHexagon().isSelected()) {

			DlgHexagon dialog = new DlgHexagon();

			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);
			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOutlineColor().setBackground(outerColor);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				newShape = dialog.getHexagon();
			}

		} else if (frame.getTglbtnDonut().isSelected()) {
			DlgDonut dialog = new DlgDonut();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);
			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOutlineColor().setBackground(outerColor);
			dialog.setVisible(true);

			if (dialog.isConfirm()) {

				newShape = dialog.getDonut();
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {

			DlgRectangle dialog = new DlgRectangle();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(me.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(me.getY()));
			dialog.getTxtY().setEditable(false);
			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOutlineColor().setBackground(outerColor);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				// return;

				try {
					newShape = dialog.getRect();
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (newShape != null) {

			frame.getTextArea().append("Add: " + (newShape.toString() + "\n"));

			Command cmd = new AddShapeCmd((Shape) newShape, model);
			cmd.execute();
			activities.push(cmd);

		}

		if (!getActivities().isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}

		clearRedo();
		frame.getView().repaint();

	}

	public void delete() {

		if (model.getSelectedShapes() != null) {

			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {

				frame.getTextArea().append("Delete: shape(s): " + model.getSelectedShapes().toString() + "\n");

				Command cmd = new RemoveShapeCmd(model.getSelectedShapes(), model);
				cmd.execute();
				activities.push(cmd);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}

		this.setSelectedShape(null);
		clearRedo();
		frame.getView().repaint();
		frame.getTglbtnSelect().setSelected(false);
	}

	public void modify() {

		if (model.getSelectedShapes().get(0) != null) {
			modifyShape();
		} else {
			JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error",
					JOptionPane.ERROR_MESSAGE);
			frame.getTglbtnSelect().setSelected(true);
		}

		frame.getTglbtnSelect().setSelected(true);
	}

	private void modifyShape() {

		//Shape selectedShape = this.getSelectedShape();
		Shape selectedShape = model.getSelectedShapes().get(0);
		Command cmd;

		if (selectedShape != null) {

			if (selectedShape instanceof Point) {

				Point p = (Point) selectedShape;
				DlgPoint dialog = new DlgPoint();

				dialog.getTxtX().setText("" + Integer.toString(p.getX()));
				dialog.getTxtY().setText("" + Integer.toString(p.getY()));
				dialog.getBtnColor().setBackground(p.getColor());
				dialog.setVisible(true);

				if (dialog.isConfirm()) {

					frame.getTextArea().append("Modify: " + ((Point) dialog.getP()).toString() + "\n");
					cmd = new UpdatePointCmd((Point) selectedShape, dialog.getP());
					cmd.execute();
					activities.push(cmd);

				}

			} else if (selectedShape instanceof HexagonAdapter) {

				HexagonAdapter hexagon = (HexagonAdapter) selectedShape;
				DlgHexagon dialog = new DlgHexagon();

				dialog.getTxtX().setText("" + Integer.toString(hexagon.getHexagon().getX()));
				dialog.getTxtY().setText("" + Integer.toString(hexagon.getHexagon().getY()));
				dialog.getTxtR().setText("" + Integer.toString(hexagon.getHexagon().getR()));
				dialog.getBtnInnerColor().setBackground(hexagon.getHexagon().getAreaColor());
				dialog.getBtnOutlineColor().setBackground(hexagon.getHexagon().getBorderColor());
				dialog.setModal(true);
				dialog.setVisible(true);

				if (dialog.isConfirm()) {
					frame.getTextArea().append("Modify: " + ((HexagonAdapter) dialog.getHexagon()).toString() + "\n");

					cmd = new command.UpdateHexagonCmd((HexagonAdapter) selectedShape, dialog.getHexagon());
					cmd.execute();
					activities.push(cmd);
				}
			} else if (selectedShape instanceof Donut) {

				Donut donut = (Donut) selectedShape;
				DlgDonut dialogd = new DlgDonut();

				dialogd.getTxtX().setText("" + Integer.toString(donut.getCenter().getX()));
				dialogd.getTxtY().setText("" + Integer.toString(donut.getCenter().getY()));
				dialogd.getTxtR().setText("" + Integer.toString(donut.getRadius()));
				dialogd.getTxtInnerR().setText("" + Integer.toString(donut.getInnerRadius()));
				dialogd.getBtnInnerColor().setBackground(donut.getInnerColor());
				dialogd.getBtnOutlineColor().setBackground(donut.getColor());
				dialogd.setModal(true);
				dialogd.setVisible(true);

				if (dialogd.isConfirm()) {

					frame.getTextArea().append("Modify: " + ((Donut) dialogd.getDonut()).toString() + "\n");
					cmd = new command.UpdateDonutCmd((Donut) selectedShape, dialogd.getDonut());
					cmd.execute();
					activities.push(cmd);
				}
			} else if (selectedShape instanceof Circle && (selectedShape instanceof Donut) == false) {

				Circle circle = (Circle) selectedShape;
				DlgCircle dialog = new DlgCircle();

				dialog.getTxtX().setText("" + Integer.toString(circle.getCenter().getX()));
				dialog.getTxtY().setText("" + Integer.toString(circle.getCenter().getY()));
				dialog.getTxtR().setText("" + Integer.toString(circle.getRadius()));
				dialog.getBtnInnerColor().setBackground(circle.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(circle.getColor());

				dialog.setVisible(true);
				dialog.setModal(true);

				if (dialog.isConfirm()) {

					frame.getTextArea().append("Modify: " + ((Circle) dialog.getCircle()).toString() + "\n");

					cmd = new UpdateCircleCmd((Circle) selectedShape, dialog.getCircle());
					cmd.execute();
					activities.push(cmd);
				}

			} else if (selectedShape instanceof Line) {

				Line line = (Line) selectedShape;
				DlgLine dialog = new DlgLine();

				dialog.getTxtSPX().setText("" + Integer.toString(line.getStartPoint().getX()));
				dialog.getTxtSPY().setText("" + Integer.toString(line.getStartPoint().getY()));
				dialog.getTxtEPX().setText("" + Integer.toString(line.getEndPoint().getX()));
				dialog.getTxtEPY().setText("" + Integer.toString(line.getEndPoint().getY()));
				dialog.getBtnOutlineColor().setBackground(line.getColor());
				dialog.setModal(true);
				dialog.setVisible(true);

				if (dialog.isConfirm()) {

					frame.getTextArea().append("Modify: " + ((Line) dialog.getLine()).toString() + "\n");
					cmd = new command.UpdateLineCmd((Line) selectedShape, dialog.getLine());
					cmd.execute();
					activities.push(cmd);
				}

			} else if (selectedShape instanceof Rectangle) {

				Rectangle rect = (Rectangle) selectedShape;
				DlgRectangle dialog = new DlgRectangle();

				dialog.getTxtX().setText("" + Integer.toString(rect.getUpperLeftPoint().getX()));
				dialog.getTxtY().setText("" + Integer.toString(rect.getUpperLeftPoint().getY()));
				dialog.getTxtHeight().setText("" + Integer.toString(rect.getHeight()));
				dialog.getTxtWidth().setText("" + Integer.toString(rect.getWidth()));
				dialog.getBtnInnerColor().setBackground(rect.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(rect.getColor());
				dialog.setModal(true);
				dialog.setVisible(true);

				if (dialog.isConfirm()) {

					frame.getTextArea().append("Modify: " + ((Rectangle) dialog.getRect()).toString() + "\n");
					cmd = new command.UpdateRectangleCmd((Rectangle) selectedShape, dialog.getRect());
					cmd.execute();
					activities.push(cmd);
				}
			}
		}
		clearRedo();
		model.getSelectedShapes().get(0).setSelected(true);
		frame.getView().repaint();
	}

	public void undo() {

		if (!activities.isEmpty()) {
			Command cmd = activities.pop();
			cmd.unexecute();
			undoActivities.push(cmd);
			if (!undoActivities.isEmpty()) {
				frame.getBtnRedo().setEnabled(true);
			}

			if (activities.isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
			}
			frame.getTextArea().append("UNDO \n");
		}

		frame.getView().repaint();
	}

	public void redo() {

		if (!undoActivities.isEmpty()) {
			Command cmd = undoActivities.pop();
			cmd.execute();
			activities.push(cmd);
			frame.getBtnUndo().setEnabled(true);

			if (undoActivities.isEmpty()) {
				frame.getBtnRedo().setEnabled(false);
			}
			frame.getTextArea().append("REDO \n");
		}
		frame.getView().repaint();
	}

	public void clearRedo() {
		getUndoActivities().clear();
		frame.getBtnRedo().setEnabled(false);
	}

	public void toBack() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new command.ToBackCmd(s, model);
		cmd.execute();
		activities.add(cmd);
		clearRedo();
		frame.getTextArea().append("ToBack: " + s.toString() + "\n");
		frame.getView().repaint();
	}

	public void toFront() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new command.ToFrontCmd(s, model);
		cmd.execute();
		activities.add(cmd);
		clearRedo();
		frame.getTextArea().append("ToFront: " + s.toString() + "\n");
		frame.getView().repaint();

	}

	public void bringToFront() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new command.BringToFrontCmd(s, model);
		cmd.execute();
		activities.add(cmd);
		clearRedo();
		frame.getTextArea().append("BringToFront: " + s.toString() + "\n");
		frame.getView().repaint();
	}

	public void bringToBack() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new command.BringToBackCmd(s, model);
		cmd.execute();
		activities.add(cmd);
		clearRedo();
		frame.getTextArea().append("BringToBack: " + s.toString() + "\n");
		frame.getView().repaint();
	}

	public void activeInnerColor() {

		innerColor = JColorChooser.showDialog(null, "Choose inner color", frame.getTglbtnInnerColor().getBackground());
		if (innerColor != null)
			frame.getTglbtnInnerColor().setBackground(innerColor);

	}

	public void activeOuterColor() {
		outerColor = JColorChooser.showDialog(null, "Choose outer color", frame.getTglbtnNOuterColor().getBackground());
		if (outerColor != null)
			frame.getTglbtnNOuterColor().setBackground(outerColor);
	}

	public void saveDrawing() {

		SaveManager sm = new SaveManager(new SaveDrawing(model));

		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dlg = jFileChooser.showSaveDialog(frame);

		if (dlg == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getPath();
			sm.save(path);
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void saveCommands() {

		SaveManager sm = new SaveManager(new SaveCommand(frame));

		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dlg = jFileChooser.showSaveDialog(frame);

		if (dlg == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getPath();
			sm.save(path);
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void loadDrawing() {

		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dlg = jFileChooser.showOpenDialog(frame);
		File file = jFileChooser.getSelectedFile();

		if (dlg == JFileChooser.APPROVE_OPTION) {

			clearAll();
			frame.getTextArea().setText(" ");

			try {

				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				ArrayList<Shape> tempList = new ArrayList<Shape>();

				tempList = (ArrayList<Shape>) ois.readObject();
				model.getShapes().addAll(tempList);

				fis.close();
				ois.close();

			} catch (ClassNotFoundException e) {

				JOptionPane.showMessageDialog(null, "File not found!", "Message", JOptionPane.INFORMATION_MESSAGE);

			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "something went wrong!", "Message",
						JOptionPane.INFORMATION_MESSAGE);

			}
			frame.getBtnUndo().setEnabled(false);
			frame.getView().repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void loadCommands() {

		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dlg = jFileChooser.showOpenDialog(frame);
		File file = jFileChooser.getSelectedFile();

		if (dlg == JFileChooser.APPROVE_OPTION) {

			clearAll();
			frame.getTextArea().setText(" ");

			try {
				
				Scanner scanFile = new Scanner(file); // skeniranje fajla red po red
				frame.getTextArea().append("Start loading commands - click next \n");
				frame.getBtnNext().setEnabled(true);

				// omogucujes da preko next-a ucitavas liniju po liniju

				frame.getBtnNext().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Scanner temp = scanFile;
						try {

							if (temp.hasNextLine()) {

								String ourLine = temp.nextLine();
								//System.out.println(ourLine + "\n");
								readNextLine(ourLine);

							} else {

								frame.getBtnNext().setEnabled(false);
								JOptionPane.showMessageDialog(null, "There is no more shapes to load.", "Message",
										JOptionPane.INFORMATION_MESSAGE);
								scanFile.close();

							}

						} catch (Exception exc) {

							exc.printStackTrace();
						}

					}

				});

			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "something went wrong!", "Message",
						JOptionPane.INFORMATION_MESSAGE);

			}

		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		frame.getView().repaint();
	}

	public void readNextLine(String nextLine) {

		String[] s = nextLine.split(" ");
		Command cmd;

		if (s[0].equals("Add:")) {

			Shape addShape = returnShape(nextLine);

			if (addShape != null) {
				frame.getTextArea().append("Add: " + addShape.toString() + "\n");
				cmd = new AddShapeCmd((Shape) addShape, model);
				cmd.execute();
				activities.push(cmd);
			}

			if (!getActivities().isEmpty()) {
				frame.getBtnUndo().setEnabled(true);
			}

			clearRedo();
			frame.getView().repaint();

			/* 
			 * public void delete() {

		if (model.getSelectedShapes() != null) {

			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {

				frame.getTextArea().append("Delete shape(s): " + model.getSelectedShapes().toString() + "\n");

				Command cmd = new RemoveShapeCmd(model.getSelectedShapes(), model);
				cmd.execute();
				activities.push(cmd);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}

		this.setSelectedShape(null);
		clearRedo();
		frame.getView().repaint();
		frame.getTglbtnSelect().setSelected(false);
	}

	
*/
				
		} else if (s[0].equals("Delete:")) {

			frame.getTextArea().append("Delete: shape(s): " + model.getSelectedShapes().toString() + "\n");

			cmd = new RemoveShapeCmd(model.getSelectedShapes(), model);
			cmd.execute();
			activities.push(cmd);

			this.setSelectedShape(null);
			clearRedo();
			frame.getView().repaint();
			frame.getTglbtnSelect().setSelected(false);

		} else if (s[0].equals("Modify:")) {

			Shape shape = returnShape(nextLine);
			Shape oldShape = model.getSelectedShapes().get(0);

			if (s[1].equals("Point:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new UpdatePointCmd((Point) oldShape, (Point) shape);
				cmd.execute();
				activities.push(cmd);

			} else if (s[1].equals("Line:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateLineCmd((Line) oldShape, (Line) shape);
				cmd.execute();
				activities.push(cmd);

			} else if (s[1].equals("Circle:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateCircleCmd((Circle) oldShape, (Circle) shape);
				cmd.execute();
				activities.push(cmd);

			} else if (s[1].equals("Donut:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateDonutCmd((Donut) oldShape, (Donut) shape);
				cmd.execute();
				activities.push(cmd);

			} else if (s[1].equals("Rectangle:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateRectangleCmd((Rectangle) oldShape, (Rectangle) shape);
				cmd.execute();
				activities.push(cmd);

			} else if (s[1].equals("Hexagon:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateHexagonCmd((HexagonAdapter) oldShape, (HexagonAdapter) shape);
				cmd.execute();
				activities.push(cmd);

			}

			clearRedo();
			oldShape.setSelected(true);
			frame.getView().repaint();

		} else if (s[0].equals("Select:")) {

			Shape shape = returnShape(nextLine);

			for (int i = 0; i < model.getShapes().size(); i++) {

				if (model.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter h1 = (HexagonAdapter) model.getShapes().get(i);
					HexagonAdapter h2 = (HexagonAdapter) shape;

					if (h1.getHexagon().getX() == h2.getHexagon().getX()
							&& h1.getHexagon().getY() == h2.getHexagon().getY()
							&& h1.getHexagon().getR() == h2.getHexagon().getR()
							&& h1.getHexagon().getAreaColor().equals(h2.getHexagon().getAreaColor())
							&& h1.getHexagon().getBorderColor().equals(h2.getHexagon().getBorderColor())) {
						shape = model.getShapes().get(i);
					}
				}

				else if ((model.getShapes().get(i)).equals(shape)) {
					shape = model.getShapes().get(i);

				}
			}

			shape.setSelected(true);
			model.addSelected(shape);
			frame.getTextArea().append("Select: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();

		} else if (s[0].equals("Deselect:")) {

			Shape shape = returnShape(nextLine);

			for (int i = 0; i < model.getShapes().size(); i++) {

				if (model.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter h1 = (HexagonAdapter) model.getShapes().get(i);
					HexagonAdapter h2 = (HexagonAdapter) shape;

					if (h1.getHexagon().getX() == h2.getHexagon().getX()
							&& h1.getHexagon().getY() == h2.getHexagon().getY()
							&& h1.getHexagon().getR() == h2.getHexagon().getR()
							&& h1.getHexagon().getAreaColor().equals(h2.getHexagon().getAreaColor())
						&& h1.getHexagon().getBorderColor().equals(h2.getHexagon().getBorderColor()))
						{
						shape = model.getShapes().get(i);
					}

				}

				if (shape.equals(model.getShapes().get(i)))
					shape = model.getShapes().get(i);
			}

			shape.setSelected(false);
			model.removeSelected(shape);
			frame.getTextArea().append("Deselect: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();

		} else if (s[0].equals("UNDO")) {
			undo();
		} else if (s[0].equals("REDO")) {
			redo();
		} else if (s[0].equals("ToBack:")) {
			toBack();
		} else if (s[0].equals("ToFront:")) {
			toFront();
		} else if (s[0].equals("BringToBack:")) {
			bringToBack();
		} else if (s[0].equals("BringToFront:")) {
			bringToFront();
		}

	}

	public Shape returnShape(String nl) {

		Shape shape = null;

		String[] s = nl.split(" ");

		if (s[1].equals("Point:")) {
			Point p = new Point(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false, Color.decode(s[5]));
			shape = p;

		} else if (s[1].equals("Line:")) {

			Line l = new Line(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])),
					new Point(Integer.parseInt(s[6]), Integer.parseInt(s[7])), false, Color.decode(s[9]));
			shape = l;

		} else if (s[1].equals("Circle:")) {

			Circle c = new Circle(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])), Integer.parseInt(s[6]),
					false, Color.decode(s[8]), Color.decode(s[10]));
			shape = c;

		} else if (s[1].equals("Donut:")) {

			Donut dn = new Donut(new Point(Integer.parseInt(s[4]), Integer.parseInt(s[5])), Integer.parseInt(s[7]),
					Integer.parseInt(s[13]), false, Color.decode(s[9]), Color.decode(s[11]));
			shape = dn;

		} else if (s[1].equals("Rectangle:")) {

			Rectangle r = new Rectangle(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])),
					Integer.parseInt(s[6]), Integer.parseInt(s[8]), false, Color.decode(s[12]), Color.decode(s[10]));
			shape = r;

		} else if (s[1].equals("Hexagon:")) {

			HexagonAdapter ha = new HexagonAdapter(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])),
					Integer.parseInt(s[6]), false, Color.decode(s[10]), Color.decode(s[8]));
			shape = ha;
		}

		return shape;
	}

	public void clearAll() {

		model.getSelectedShapes().clear();
		model.getShapes().clear();
		activities.clear();
		undoActivities.clear();
		frame.getTextArea().setText(" ");
		// frame.getBtnUndo().setEnabled(false);

	}

	// ---------------------------GETTERS AND SETTERS---------------------------
	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Stack<Command> getActivities() {
		return activities;
	}

	public void setActivities(Stack<Command> activities) {
		this.activities = activities;
	}

	public Stack<Command> getUndoActivities() {
		return undoActivities;
	}

	public void setUndoActivities(Stack<Command> undoActivities) {
		this.undoActivities = undoActivities;
	}

}
