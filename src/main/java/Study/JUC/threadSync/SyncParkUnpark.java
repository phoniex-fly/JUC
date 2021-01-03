package Study.JUC.threadSync;

import java.util.concurrent.locks.LockSupport;

/**
 * 控制先打印1 再打印2
 * 
 * @author HelloWorld
 *
 */
public class SyncParkUnpark {

	public static void main(String[] args) {
		Thread t2 = new Thread(() -> {
			LockSupport.park();
			System.out.println("2");
		}, "打印2");

		Thread t1 = new Thread(() -> {
			System.out.println("1");
			LockSupport.unpark(t2);

		}, "打印1");

		t1.start();
		t2.start();
	}

}
