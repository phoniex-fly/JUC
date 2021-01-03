package Study.JUC.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWaitLock {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	/**
	 * 尝试获取锁 lock.tryLock(); 
	 * 限时尝试获取锁 lock.tryLock(4, TimeUnit.SECONDS);
	 * @throws InterruptedException 
	 */
	public static void emp1() throws InterruptedException {
		Thread t1 = new Thread(()->{
			boolean tryLock = true;
			try {
//				tryLock = lock.tryLock();
				tryLock = lock.tryLock(1, TimeUnit.SECONDS);
//				tryLock = lock.tryLock(4, TimeUnit.SECONDS);
				if(!tryLock){
					System.out.println("未获取锁");
					return;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("获取锁");
			} finally {
				lock.unlock();
			}
		},"t1");
		t1.start();
		
		try {
			lock.lock();
			Thread.sleep(3000);
		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		emp1();
	}

}
