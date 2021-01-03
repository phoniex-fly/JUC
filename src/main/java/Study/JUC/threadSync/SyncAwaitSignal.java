package Study.JUC.threadSync;

public class SyncAwaitSignal {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {

		},"打印1");
		t1.start();

		Thread t2 = new Thread(() -> {

		},"打印2");
		t2.start();
	}

}
