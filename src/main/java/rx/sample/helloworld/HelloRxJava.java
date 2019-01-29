package rx.sample.helloworld;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class HelloRxJava {
	public void hello(String... args) {
		Flowable.fromArray(args).subscribe(s -> System.out.println("Hey! " + s));
	}
	
	public void observableSample() {
		Observable<String> observerFrom = Observable.fromArray("a", "b", "c");
		Observable<String> observerJust = Observable.just("abc");
	}
	
	public Observable<Object> synchronousObserver(String... source) {
		return Observable.create(observable -> {
			observable.onNext(source);
		});
	}
	
	public void asynchronousObserver() {
		
	}
}
