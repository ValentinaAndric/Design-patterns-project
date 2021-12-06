package mvc;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model); // povezani su ti frame i view
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
