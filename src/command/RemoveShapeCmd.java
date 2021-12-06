package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	private ArrayList<Shape> shapes;
	private DrawingModel model;
	private ArrayList<Shape> temp = new ArrayList<Shape>();

	public RemoveShapeCmd(ArrayList<Shape> shapes, DrawingModel model) {
		this.shapes = new ArrayList<Shape>(shapes);
		this.model = model;
	}

	@Override
	public void execute() {
		temp.addAll(model.getShapes());
		for (Shape shape : shapes) {

			model.remove(shape);
			model.removeSelected(shape);
		}

	}

	@Override
	public void unexecute() {

		model.getShapes().clear();
		model.getShapes().addAll(temp);

		for (Shape shape : shapes) {

			shape.setSelected(true);
			model.addSelected(shape);

		}

	}

}
