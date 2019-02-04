package rx.campletable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import io.reactivex.Completable;
import io.reactivex.exceptions.TestException;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;

public class SampleCompletable {
	Logger logger = Logger.getLogger("SampleCompletable");
	AtomicInteger atomicInteger = new AtomicInteger(3);
	
	public AtomicInteger completableAtomicInteger(AtomicInteger subscribeNumber) {
		Completable.fromAction(() -> {
			logger.info("point1 AtomicInteger: " + subscribeNumber);
			
			subscribeNumber.incrementAndGet();
			
			logger.info("point2 AtomicInteger: " + subscribeNumber);
			
			if(atomicInteger.decrementAndGet() != 0) {
				throw new RuntimeException();
			}
			
			logger.info("point3 AtomicInteger: " + subscribeNumber);
			
			throw new IllegalArgumentException();
		}).retry(Integer.MAX_VALUE, throwable -> !(throwable instanceof IllegalArgumentException)).test();
		
		return subscribeNumber;
	}
	
	public AtomicInteger alwaysTrue(AtomicInteger subscribeNumber) {
		Completable.fromAction(() -> {
			logger.info("point1 AtomicInteger: " + subscribeNumber);
			
			subscribeNumber.incrementAndGet();
			
			logger.info("point2 AtomicInteger: " + subscribeNumber);
			
			if(atomicInteger.decrementAndGet() != 0) {
				throw new RuntimeException();
			}
			
			logger.info("point3 AtomicInteger: " + subscribeNumber);
			logger.info("Functions.alwaysTrue: " + Functions.alwaysTrue());
			
			throw new IllegalArgumentException();
		}).retry(2, Functions.alwaysTrue()).test();
		
		return subscribeNumber;
	}
	
	public void unsafeCreateComp() {
		Completable.unsafeCreate(observer -> {
			EmptyDisposable.complete(observer);  // subscribe and complete
		});
	}
	
	public void unsafeCreateErr() {
		Completable.unsafeCreate(observer -> {
			EmptyDisposable.error(new TestException(), observer);
		});
	}
}
