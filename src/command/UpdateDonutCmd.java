package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command {

	private Donut oldState;
	private Donut newState;
	private Donut original;

	public UpdateDonutCmd(Donut oldState, Donut newState) {

		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();

		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());

		try {
			oldState.setRadius(newState.getRadius());
		} catch (Exception e) {

			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}

		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setSelected(newState.isSelected());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());

	}

	@Override
	public void unexecute() {
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());

		try {
			oldState.setRadius(original.getRadius());
		} catch (Exception e) {

			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}

		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setSelected(original.isSelected());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());

	}

}
