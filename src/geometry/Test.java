package geometry;

import java.util.Arrays;
import java.util.HashMap;

public class Test {

	public static void main(String[] args) {

		Point p = new Point();
		p.setX(10);
		p.setY(20);
		p.setSelected(true);
		
		System.out.println("X coordinate of point p is: " + p.getX() + '\n' +
										"Y coordinate of point p is: " + p.getY() + '\n' +
										"Point p is selected: " + p.isSelected());

		double rez = p.distance(40, 50);
		System.out.println(rez);
		
		Point p1 = new Point();
		p1.setX(15);
		p1.setY(27);
		p1.setSelected(true);
		
		// 1. Inicijalizovati x koordinatu tacke p
		// na vrednost y koordinate tacke p1
	
		p.setX(p1.getY());
		System.out.println("x of p = " + p.getX());
		
		// 2. Postaviti za pocetnu tacku linije l1 tacku p, a
		// za krajnju tacku linije l1 tacku p1
		
		Line l1 = new Line();
		l1.setStartPoint(p);
		l1.setEndPoint(p1);

		// 3. Postaviti y koordinatu krajnje tacke l1 na 23
		
		l1.getEndPoint().setY(23);

		// 4. Inicijalizovati x koordinatu pocetne tacke linije l1
		// na vrednost y koordinate krajnje tacke linije l1
		
		l1.getStartPoint().setX(l1.getEndPoint().getY());

		// 5. Postaviti x koordinatu krajnje tacke l1 na vrednost
		// duzine linije l1 umanjene za vrednost zbira x i y
		// koordinate pocetne tacke linije l1
		
		l1.getEndPoint().setX((int) (l1.length() - (l1.getStartPoint().getX() + l1.getStartPoint().getY())));

		// 6. Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15
		
		Rectangle r1 = new Rectangle();
		r1.setUpperLeftPoint(p);
		r1.getUpperLeftPoint().setX(10);
		r1.getUpperLeftPoint().setY(15);

		// 7. Postaviti centar kruga c1 na koordinate tacke
		// gore levo od r1
		
		Circle c1 = new Circle();
		c1.setCenter(r1.getUpperLeftPoint());

		// 8. Postaviti x koordinatu centra kruga c1 na vrednost razlike
		// povrsine pravougaonika r1 i y koordinate pocetne tacke linije l1
		
		r1.setHeight(30);
		r1.setWidth(20);
		c1.getCenter().setX((int) r1.area() - l1.getStartPoint().getY());
		System.out.println("x of center of c1 = " + c1.getCenter().getX());
		
		Point p2 = new Point(300, 500);
		Point p3 = new Point(300, 500);
		Line l2 = new Line(p, new Point(500, 600), false);
		
		System.out.println(p2 == p3);
		// p3 = p2;
		// System.out.println(p2 == p3); // true
		
		System.out.println(p2.equals(p3));
		System.out.println(p2.equals(r1)); // false
		System.out.println(p2.equals(p)); // false
		
		System.out.println(p2.toString());
		System.out.println(p2);
		System.out.println(l2.toString());
		// c1.setRadius(55);
		System.out.println(c1.toString());
		System.out.println(r1.toString());
		
		String s = "Hello";
		String s1 = "Hello";
		
		System.out.println(s == s1); // true
		
		String s2 = new String("Hello");
		String s3 = new String("Hello");
		
		System.out.println(s2 == s3); // false
		System.out.println(s2.equals(s3)); 
		
		Donut d = new Donut();
		Donut d1 = new Donut(new Point(100, 200), 50, 30);
		Donut d2 = new Donut(new Point(100, 200), 50, 30);
		
		System.out.println(d1.toString());
		System.out.println(d1 == d2);
		System.out.println(d1.equals(d2));
		System.out.println(d1.contains(700, 800));
		System.out.println(d1.contains(p3));
		System.out.println(d1.area());
		
		// dinamicko povezivanje
		Circle c2 = new Donut(new Point(10, 10), 50, 20);
		
		// Niz
		Point p7 = new Point(5, 5);
		Point p8 = new Point(10, 10);
		Point p9 = new Point(2, 2);
		Point p10 = new Point(15, 15);
		
		Point[] tacke = {p7, p8, p9, p10};
		
		System.out.println("Nesortiran niz tacaka");
		for (int i = 0; i<tacke.length; i++) {
			System.out.println(tacke[i]);
		}
		
		// Sortiranje
		Arrays.sort(tacke);
		
		System.out.println("Sortiran niz tacaka");
		for (int i = 0; i<tacke.length; i++) {
			System.out.println(tacke[i]);
		}
		
		// HashMap-e
		HashMap<String, Shape> map = new HashMap<String, Shape>();
		map.put("point", p2);
		map.put("rectangle", r1);
		map.put("Point", p8);
		
		System.out.println("point from map is: " + map.get("point"));
		System.out.println("point from map is: " + map.get("Point"));
		
		Point p11 = new Point(50, 50);
		
		map.put("point", p11);
		System.out.println("point from map is: " + map.get("point"));
		
		Circle c3 = new Circle(new Point(50, 50), 40);
		
		try {
			c3.setRadius(-154);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Uvek se izvrsava!");
		}
		
		
	}

}
