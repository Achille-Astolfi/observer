package angularstyle.observer;

public interface Observer<T> {
	void next(T value);

	default void error(Object msg) {
	}

	default void complete() {
	}
}
