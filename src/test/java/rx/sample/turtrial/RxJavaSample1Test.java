package rx.sample.turtrial;

import org.junit.Test;

public class RxJavaSample1Test {
	RxJavaSample1 observer = new RxJavaSample1();

	@Test
	public void testListObserver() {
		observer.listObserver()
			.filter(p -> p instanceof Integer)
			.map(m -> m * 3)
			.subscribe(System.out::println);
	}
}
