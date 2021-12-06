package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original;

	public UpdateRectangleCmd(Rectangle oldState, Rectangle newState) {

		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();

		oldState.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setSelected(newState.isSelected());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());

	}

	@Override
	public void unexecute() {
		oldState.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setSelected(original.isSelected());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());

	}

}
