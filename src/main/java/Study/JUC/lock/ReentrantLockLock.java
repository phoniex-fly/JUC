package Study.JUC.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockLock {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	/**
	 * 常规使用 lock.lock(); lock.unlock();
	 * @param args
	 */
	public static void emp1() {
		lock.lock();
		try {
			
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 可打断锁 lock.lockInterruptibly();
	 * @throws InterruptedException 
	 */
	private static void emp2() throws InterruptedException{

		Thread t1 = new Thread(()->{
			try {
				System.out.println("尝试获得锁");
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				System.out.println("锁被打断");
				e.printStackTrace();
			}
			try {
				System.out.println("获得锁");
			} finally {
				System.out.println("释放锁");
				lock.unlock();
				
			}
		},"t1");
		
		t1.start();
		
		lock.lock();
		Thread.sleep(3000);
		System.out.println("打断t1");
		t1.interrupt(); //打断锁
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		emp2();
	}

}
