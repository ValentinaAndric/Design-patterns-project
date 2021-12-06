package command;

import geometry.Line;

public class UpdateLineCmd implements Command {

	private Line oldState;
	private Line newState;
	private Line original;

	public UpdateLineCmd(Line oldState, Line newState) {

		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();

		oldState.getStartPoint().setX(newState.getStartPoint().getX());
		oldState.getStartPoint().setY(newState.getStartPoint().getY());
		oldState.getEndPoint().setX(newState.getEndPoint().getX());
		oldState.getEndPoint().setY(newState.getEndPoint().getY());
		oldState.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {

		oldState.getStartPoint().setX(original.getStartPoint().getX());
		oldState.getStartPoint().setY(original.getStartPoint().getY());
		oldState.getEndPoint().setX(original.getEndPoint().getX());
		oldState.getEndPoint().setY(original.getEndPoint().getY());
		oldState.setColor(original.getColor());
	}

}
