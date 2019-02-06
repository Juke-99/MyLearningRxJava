package rx.campletable;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.internal.observers.BlockingMultiObserver;
import io.reactivex.observers.DisposableCompletableObserver;

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
	
	@Test
	public void complete() {
		Completable comp = Completable.complete();
		comp.blockingAwait();
	}
	
	@Test
	public void blockingAwait() {
		BlockingMultiObserver<Void> observer = new BlockingMultiObserver<>();
		observer.blockingGet();
	}
	
	@Test
	public void testCompleteSubscribe() {
		Completable.complete().subscribe(new DisposableCompletableObserver() {
			@Override
			public void onComplete() {
				System.out.println("Complete!");
			}
			
			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void testCompletableSingle() {
		Throwable e = new RuntimeException();
		
		ac.completableSingle().get("firstCompletable")
			.andThen(ac.completableSingle().get("secondCompletable"))
			.test().assertComplete();
		
		ac.completableSingle().get("firstCompletable")
			.andThen(ac.completableSingle().get("secondCompletable"))
			.andThen(ac.completableSingle().get("error")).test().assertError(e);
		
		ac.completableSingle().get("firstCompletable")
		.andThen(Completable.never())
		.test().assertNotComplete();
	}
}
