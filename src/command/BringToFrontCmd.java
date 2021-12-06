package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	// na poslednju poz
	private Shape shape;
	private DrawingModel model;
	private int index;

	public BringToFrontCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
		this.index = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		model.remove(shape);
		model.add(shape);

	}

	@Override
	public void unexecute() {

		if(model.getShapes().size() < 1) {
		model.getShapes().remove(model.getShapes().size() - 1);
		model.addOnIndex(index, shape);
		}
	}

}
