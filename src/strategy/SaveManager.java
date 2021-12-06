package strategy;

public class SaveManager implements Save {

	private Save save;

	public SaveManager(Save save) {
		this.save = save;
	}

	@Override
	public void save(String path) {

		save.save(path);
	}

}
