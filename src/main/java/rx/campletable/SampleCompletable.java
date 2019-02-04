package rx.campletable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import io.reactivex.Completable;

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
	
	public void sdoi() {
		Completable.fromAction(() -> {
			throw new IllegalArgumentException();
			});
	}
	
//	public Integer completableInteger(Integer subscribeNumber) {
//		Completable.fromAction(() -> {
//			subscribeNumber++;
//			
//			if(subscribeNumber-- != 0) {
//				throw new RuntimeException();
//			}
//			
//			throw new IllegalArgumentException();
//		}).retry(Integer.MAX_VALUE, throwable -> !(throwable instanceof IllegalArgumentException)).test();
//		
//		return subscribeNumber;
//	}
}
