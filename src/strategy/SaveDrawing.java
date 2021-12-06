package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import mvc.DrawingModel;

public class SaveDrawing implements Save {

	DrawingModel model;

	public SaveDrawing(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void save(String path) {

		File file = new File(path);

		try {

			for (int i = 0; i < model.getShapes().size(); i++) {

				if (model.getShapes().get(i).isSelected() == true) {
					model.getShapes().get(i).setSelected(false);
				}
			}

			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(model.getShapes());
			oos.flush();
			oos.close();
			fos.close();

		} catch (FileNotFoundException e) {

			JOptionPane.showMessageDialog(null, "File not found!", "Message", JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "something went wrong!", "Message", JOptionPane.INFORMATION_MESSAGE);

		}

	}

}
