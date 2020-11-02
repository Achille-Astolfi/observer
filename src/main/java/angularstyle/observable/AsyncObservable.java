package angularstyle.observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import angularstyle.observer.Observer;

public class AsyncObservable implements Observable<String> {
	private ExecutorService pool = Executors.newSingleThreadExecutor();
	private List<Observer<String>> observers = Collections.synchronizedList(new ArrayList<>());
	private int intValue;

	@Override
	public Subscription subscribe(Observer<String> observer) {
		synchronized (observers) {
			if (observers.isEmpty()) {
				pool.submit(this::notifyObservers);
			}
			observers.add(observer);
		}
		return () -> unsubscribe(observer);
	}

	private void unsubscribe(Observer<String> observer) {
		try {
			observer.complete();
		} finally {
			observers.remove(observer);
		}
	}

	private void notifyObservers() {
		List<Observer<String>> copy;
		synchronized (observers) {
			copy = new ArrayList<>(observers);
		}
		if (copy.isEmpty()) {
			return;
		}
		String value = String.valueOf(intValue++);
		try {
			for (var observer : copy) {
				observer.next(value);
			}
		} catch (Throwable exc) {
			for (var observer : copy) {
				try {
					observer.error(exc);
					observers.remove(observer);
				} catch (Throwable t) {
					// hide
					exc.addSuppressed(t);
				}
			}
		}
		try {
			Thread.sleep(1000);
			pool.submit(this::notifyObservers);
		} catch (InterruptedException e) {

		}
	}

}
