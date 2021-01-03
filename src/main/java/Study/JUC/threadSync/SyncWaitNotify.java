package Study.JUC.threadSync;

/**
 * 控制先打印1 再打印2
 * @author HelloWorld
 *
 */
public class SyncWaitNotify {
	private static Object lock = new Object();
	private static boolean printOne = false;

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			synchronized (lock) {
				System.out.println("1");
				printOne = true;
				lock.notify();
			}
		}, "打印1");

		Thread t2 = new Thread(() -> {
			synchronized (lock) {
				while (!printOne) {
					try {
						System.out.println("2等待");
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("2");
			}

		}, "打印2");

		t1.start();
		t2.start();

	}

}
