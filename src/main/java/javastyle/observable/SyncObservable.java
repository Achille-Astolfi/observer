package javastyle.observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class SyncObservable {
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	private String firstName;
	private String lastName;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newValue) {
		String oldValue = this.firstName;
		if (!Objects.equals(oldValue, newValue)) {
			this.firstName = newValue;
			propertyChangeSupport.firePropertyChange("firstName", oldValue, newValue);
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newValue) {
		String oldValue = this.lastName;
		if (!Objects.equals(oldValue, newValue)) {
			this.lastName = newValue;
			propertyChangeSupport.firePropertyChange("lastName", oldValue, newValue);
		}
	}
}
