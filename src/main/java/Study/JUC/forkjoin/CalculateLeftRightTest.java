package Study.JUC.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class CalculateLeftRight extends RecursiveTask<Integer> {

	private Integer start;
	private Integer end;

	public CalculateLeftRight(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if ((end - start) == 1) {
			System.out.println("start:"+start+",end:"+end);
			return end+start;
		}
		if(end == start) {
			System.out.println("start:"+start+",end:"+end);
			return end;
		}
		int mid = (end + start) / 2;
		CalculateLeftRight left = new CalculateLeftRight(start, mid);
		CalculateLeftRight right = new CalculateLeftRight(mid+1, end);
		left.fork();
		right.fork();
		return left.join()+right.join();
	}
}

public class CalculateLeftRightTest {
	public static void main(String[] args) {
		CalculateLeftRight calculate = new CalculateLeftRight(1,5);
		Integer invoke = ForkJoinPool.commonPool().invoke(calculate);
//		ForkJoinPool pool = new ForkJoinPool();
//		Integer invoke = pool.invoke(calculate);
		System.out.println(invoke);
	}
}
