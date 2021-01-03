package Study.JUC.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {

	private static ReentrantLock lock = new ReentrantLock();
	private static boolean hasPizza = false;
	private static boolean hasWater = false;

	public static void emp1() throws InterruptedException {
		Condition waitPizza = lock.newCondition();
		Condition waitWater = lock.newCondition();

		Thread t1 = new Thread(() -> {
			lock.lock();
			try {
				while (!hasPizza) {
					System.out.println("等披萨");
					waitPizza.await();
				}
				System.out.println("吃披萨");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "eatPizza");
		t1.start();

		Thread t2 = new Thread(() -> {
			lock.lock();
			try {
				while (!hasWater) {
					System.out.println("等水");
					waitWater.await();
				}
				System.out.println("喝水");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "drinkWater");
		t2.start();

		lock.lock();
		try {
			Thread.sleep(2000);
			hasPizza = true;
			System.out.println("有pizza啦");
			waitPizza.signal();
			hasWater = true;
			System.out.println("有水啦");
			waitWater.signal();
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		emp1();
	}

}
