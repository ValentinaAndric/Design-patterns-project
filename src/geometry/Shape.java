package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Cloneable, Serializable {

	private boolean selected;
	private Color color = Color.black;
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	protected abstract Shape clone();

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
}
