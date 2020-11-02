package angularstyle.observer;

public class AsyncObserver implements Observer<String> {

	@Override
	public void next(String value) {
		System.out.println("Ricevuto " + value);
	}

}
