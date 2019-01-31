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
	
	public Observable<Object> synchronousObserver() {
		return Observable.create(observable -> {
			observable.onNext("one item");
			observable.onNext("two item");
			observable.onNext("three item");
		});
	}
	
	public Observable<Object> chainObserver() {
		return Observable.create(observer -> {
			observer.onNext("one item");
			observer.onNext("two item");
			observer.onNext("three item");
			observer.onNext("fore item");
			observer.onNext("five item");
			observer.onNext("six item");
			observer.onNext("seven item");
			observer.onNext("eight item");
			observer.onNext("nine item");
		});
	}
	
	public Observable<Object> mergeObserver() {
		Observable<String> observable1 = Observable.create(observer -> {
			observer.onNext("ob1-1");
			observer.onNext("ob1-2");
			observer.onNext("ob1-3");
		});
		
		Observable<Integer> observable2 = Observable.create(observer -> {
			observer.onNext(21);
			observer.onNext(22);
			observer.onNext(23);
		});
		
		return Observable.merge(observable1, observable2);
	}
}
