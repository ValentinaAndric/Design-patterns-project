package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SelectedShapesList {
	
	private int size;
	private PropertyChangeSupport pcs; 
	
	
	public SelectedShapesList() {
		//kad kreiramo objekat, kreiramo i instancu pcs i prihvta klasu n koju se odnosi
		pcs = new PropertyChangeSupport(this);
	}
	
	//mora observer da se prijavi na nase pracenje
	//koristi se osluskivac 
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		
		pcs.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		
		pcs.removePropertyChangeListener(pcl);
	}
///---------getters and setters------
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		pcs.firePropertyChange("Size", this.size, size);
		this.size = size;
	}

	public PropertyChangeSupport getPcs() {
		return pcs;
	}

	public void setPcs(PropertyChangeSupport pcs) {
		this.pcs = pcs;
	}

	
	
}
