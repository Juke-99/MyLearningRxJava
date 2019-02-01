package rx.sample.helloworld;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.Test;

import io.reactivex.Observable;

public class HelloRxJavaTest {
	HelloRxJava hello = new HelloRxJava();
	Logger logger = Logger.getLogger("HelloRxJavaTest");
	
	@Test
	public void assertHello() {
		hello.hello("yah", "uhh", "raid");
	}
	
	@Test(expected = NullPointerException.class)
	public void testFromArrayNull() {
		String[] nullString = null;
		Observable<String> observer = Observable.fromArray(nullString);
	}
	
	@Test(expected = NullPointerException.class)
	public void testFromArrayCenterNull() {
		Observable<String> observer = Observable.fromArray("hello null", null, "see you null...");
	}
	
	@Test(expected = NullPointerException.class)
	public void testJustNull() {
		Observable<String> observer = Observable.just(null);
	}
	
	@Test
	public void testSynchronousObserver() {
		Observable<Object> observer = hello.synchronousObserver();
		observer.subscribe(System.out::println);
	}
	
	// This test isn't implemented onError. If this actual implement in development, this may become bug.
	@Test
	public void testNullSynchronousObserver() {
		Observable.create(observer -> {
			observer.onNext(1);
			observer.onNext(null);
			observer.onNext("3");
		}).subscribe(System.out::println);
	}
	
	@Test
	public void testChainObserver() {
		logger.info("skip: 4, take: 3");
		hello.chainObserver().skip(4).take(3)
		.map(t -> t.toString()).subscribe(System.out::println);
		
		logger.info("skip: 2, take: 0");
		hello.chainObserver().skip(2).take(0)
		.map(t -> t.toString()).subscribe(System.out::println);
		
		logger.info("skip: 0, take: 7");
		hello.chainObserver().skip(0).take(7)
		.map(t -> t.toString()).subscribe(System.out::println);
		
		logger.info("skip: 1, take: 5");
		hello.chainObserver().skip(1).take(5)
		.map(t -> t.toString()).subscribe(System.out::println);
	}
	
	@Test
	public void testMergeReduce() {
		logger.info("check merge");
		hello.mergeObserver().subscribe(System.out::println);
		
		logger.info("reduce");
		hello.mergeObserverInteger().reduce(new ArrayList<Integer>(), (x, y) -> {
			x.add(y);
			return x;
		}).subscribe(System.out::println);
	}
}
