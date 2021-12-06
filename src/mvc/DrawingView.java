package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Point;
import geometry.Shape;

public class DrawingView extends JPanel {

	private DrawingModel model = new DrawingModel();

	// view ce biti zaduzen za iscrtavanje oblika pa nam treba paint
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}

	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
