package rx.sample.helloworld;

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
}
