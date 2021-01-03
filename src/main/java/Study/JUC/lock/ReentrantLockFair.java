package Study.JUC.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockFair {
	
	private static ReentrantLock lockUnfair = new ReentrantLock(false);//默认非公平
	
	private static ReentrantLock lockFair = new ReentrantLock(true);//公平
	

	public static void emp1() throws InterruptedException {
		Thread t1 = new Thread(()->{
			lockUnfair.lock();
			lockFair.lock();
		},"t1");
		t1.start();
		
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		emp1();
	}

}
