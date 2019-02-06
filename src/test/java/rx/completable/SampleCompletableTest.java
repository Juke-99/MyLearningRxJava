package rx.completable;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.observers.BlockingMultiObserver;
import io.reactivex.observers.DisposableCompletableObserver;
import rx.completable.SampleCompletable;

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
	
	@Test
	public void testMerge() {
		Throwable e = new RuntimeException();
		Completable first = ac.completableSingle().get("firstCompletable");
		Completable second = ac.completableSingle().get("secondCompletable");
		
		Completable.mergeArray(first, second).test().assertComplete();
		Completable.mergeArray(first, second, ac.completableSingle().get("error")).test().assertError(e);
	}
	
	@Test
	public void testAllElements() {
		Completable allElements = Flowable.just("request", "response").flatMapCompletable(message -> 
			Completable.fromRunnable(() -> System.out.println(message))
		);
		
		allElements.test().assertComplete();
	}
	
	@Test
	public void testAmbArray() {
		Throwable e = new RuntimeException();
		Completable first = ac.completableSingle().get("firstCompletable");
		Completable second = ac.completableSingle().get("secondCompletable");
		
		Completable.ambArray(first, Completable.never(), second).test().assertComplete();
		Completable.ambArray(ac.completableSingle().get("error"), first, second).test().assertError(e);
	}
}
