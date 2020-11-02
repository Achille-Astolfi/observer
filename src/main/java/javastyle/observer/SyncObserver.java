package javastyle.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SyncObserver implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropertyName() + " changed.");
	}

}
