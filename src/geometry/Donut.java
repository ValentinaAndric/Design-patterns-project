package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle implements Cloneable {

	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	/*
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}
	*/
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		Ellipse2D outer = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2); //jer je poluprecnik pa *2
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius() , this.getCenter().getY() - this.getInnerRadius(), this.innerRadius * 2, this.innerRadius * 2);
		
		Area area = new Area(outer);
		Area innerArea = new Area(inner);
		area.subtract(innerArea);
		
		g2d.setColor(getInnerColor());
		g2d.fill(area);
		g2d.setColor(getColor());
		g2d.draw(area);
		
		if (isSelected()) {
			g2d.setColor(Color.BLUE);
			g2d.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g2d.drawRect(this.getCenter().getX() - this.getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g2d.drawRect(this.getCenter().getX() + this.getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g2d.drawRect(this.getCenter().getX()- 3, this.getCenter().getY() - this.getRadius()- 3, 6, 6);
			g2d.drawRect(this.getCenter().getX()- 3,this.getCenter().getY()+ this.getRadius()- 3, 6, 6);
		}
		
		
	}
	
	@Override 
	public Donut clone() {
		Donut d = new Donut(); 
		
		d.setCenter(this.getCenter().clone());
		
		try {
			d.setRadius(this.getRadius());
		} catch (Exception e) {
			
			//e.printStackTrace();
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		
		d.setInnerRadius(this.getInnerRadius());
		d.setSelected(this.isSelected());
		d.setColor(this.getColor());
		d.setInnerColor(this.getInnerColor());

		return d;
	}
	/*
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE); //unapredjenje ovog jer smo bojili samo kao pozadinu 
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}*/
	
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius() &&
					this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	public int getInnerRadius() {
		return this.innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return "Donut: " + super.toString() + " inner_radius: " + getInnerRadius();
	}
	
}
