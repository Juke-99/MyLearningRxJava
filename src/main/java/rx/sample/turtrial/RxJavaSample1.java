package rx.sample.turtrial;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class RxJavaSample1 {
	List<Integer> intList = Arrays.asList(1, 10, 3, 4, 55);
	
	public Observable<Integer> listObserver() {
		return Observable.fromIterable(intList);
	}
}
