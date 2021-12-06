package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private int index;

	public ToBackCmd(Shape shape, DrawingModel model) {

		this.shape = shape;
		this.model = model;
		index = model.getShapes().indexOf(shape);

	}

	@Override
	public void execute() {
		if(index>0) {
		model.getShapes().remove(index);
		model.addOnIndex(index - 1, shape);
		}
	}

	@Override
	public void unexecute() {

		model.getShapes().remove(index - 1);
		model.addOnIndex(index, shape);

	}

}
