package angularstyle.observable;

import angularstyle.observer.Observer;

public interface Observable<T> {
	Subscription subscribe(Observer<T> observer);
}
