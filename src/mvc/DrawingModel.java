
package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private PropertyChangeSupport pcs;

	public DrawingModel() {
		pcs = new PropertyChangeSupport(this);
	}

	// observer obrazac
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {

		pcs.removePropertyChangeListener(pcl);
	}

	public void add(Shape s) {
		shapes.add(s);
		pcs.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(s));

	}

	public void remove(Shape s) {
		pcs.firePropertyChange("Index", getShapes().size() - 1, getShapes().indexOf(s));
		shapes.remove(s);
	}

	public void addSelected(Shape s) {
		pcs.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size() + 1);
		selectedShapes.add(s);
		pcs.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(s));

	}

	public void removeSelected(Shape s) {
		pcs.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size() - 1);
		pcs.firePropertyChange("Index", getShapes().size() - 1, getShapes().indexOf(s));
		selectedShapes.remove(s);
	}

	public void addOnIndex(int index, Shape s) {

		shapes.add(index, s);
	//	System.out.println("sizeeee: " + getShapes().size());
		pcs.firePropertyChange("Index", getShapes().size(), index);
	}

	public Shape get(int index) {
		return shapes.get(index);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;

	}

}
