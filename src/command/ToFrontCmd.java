package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private int index;

	public ToFrontCmd(Shape shape, DrawingModel model) {

		this.shape = shape;
		this.model = model;
		this.index = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		if(index < model.getShapes().size()) {
		model.getShapes().remove(index);
		model.addOnIndex(index + 1, shape);
		}
	}

	@Override
	public void unexecute() {
		
		
		model.getShapes().remove(index + 1);
		model.addOnIndex(index, shape);

	}

}
