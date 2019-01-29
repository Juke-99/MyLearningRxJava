package rx.sample.helloworld;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import io.reactivex.Observable;

public class HelloRxJavaTest {
	HelloRxJava hello = new HelloRxJava();
	
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
		Observable<Object> observer = hello.synchronousObserver("2", "3", "Wow!");
		observer.subscribe(System.out::println);
	}
}
