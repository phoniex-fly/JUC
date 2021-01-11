package Study.JUC.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class Calculate extends RecursiveTask<Integer>{
	
	private Integer end;
	
	public Calculate(Integer end) {
		this.end = end;
	}


	@Override
	protected Integer compute() {
		if(end == 1) {
			return 1;
		}
		Calculate calculate = new Calculate(end-1);
		calculate.fork();
		return end+calculate.join();
	}
}

public class CalculateTest{
	public static void main(String[] args) {
		Calculate calculate = new Calculate(5);
		Integer invoke = ForkJoinPool.commonPool().invoke(calculate);
		System.out.println(invoke);
	}
}
