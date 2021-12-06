package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class SelectedShapesListObserver implements PropertyChangeListener {

	private int size;
	private int sizeOfShapes;
	private int index;
	private DrawingFrame frame;
	// na pcs mogu da se prijave samo pcl sto znaci da nasa klasa treba da
	// implementira pcl

	public SelectedShapesListObserver(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Size")) {
			this.size = (int) evt.getNewValue();

		}
		if (evt.getPropertyName().equals("Index")) {
			this.index = (int) evt.getNewValue();
			this.sizeOfShapes = (int) evt.getOldValue();

		}

		enableButtons();

	}

	public void enableButtons() {

		
		if (size == 0) {
			frame.getBtnDelete().setEnabled(false);
			frame.getBtnModify().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);

		} else if (size == 1) {

			frame.getBtnDelete().setEnabled(true);
			frame.getBtnModify().setEnabled(true);

			if (index > 0) {
				frame.getBtnToBack().setEnabled(true);
				frame.getBtnBringToBack().setEnabled(true);
			} else {
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(false);
			}

			if (index < sizeOfShapes - 1) {
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnToFront().setEnabled(true);
			} else {
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
			}

		} else if (size > 1) {
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnModify().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);

		}

	}

}
