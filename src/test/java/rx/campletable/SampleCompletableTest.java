package rx.campletable;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class SampleCompletableTest {
	SampleCompletable ac = new SampleCompletable();

	@Test
	public void testCompletableAtomicInteger() {
		assertEquals(3, ac.completableAtomicInteger(new AtomicInteger(0)).get());
	}

	@Test
	public void testAlwaysTrue() {
		assertEquals(3, ac.alwaysTrue(new AtomicInteger(0)).get());
	}
}
