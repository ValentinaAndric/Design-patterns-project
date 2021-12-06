package command;

import geometry.Point;

public class UpdatePointCmd implements Command {

	private Point oldState;
	private Point newState;
	private Point original;

	public UpdatePointCmd(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;

	}

	@Override
	public void execute() {

		original = oldState.clone();

		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());

	}

}
