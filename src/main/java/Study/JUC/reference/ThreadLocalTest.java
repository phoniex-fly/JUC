package Study.JUC.reference;

public class ThreadLocalTest {

	private static Person person = new Person();

	private static ThreadLocal<Person> local = new ThreadLocal<>();

	public static void main(String[] args) {
		testNoThreadLocal();
		testThreadLocal();
	}

	/**
	 * ThreadLocal存储线程本地变量
	 */
	private static void testThreadLocal() {
		new Thread(() -> {
			SleepUtil.sleep(2000);
			System.out.println(local.get().name);
		}, "t1").start();

		new Thread(() -> {
			SleepUtil.sleep(1000);
			local.set(new Person());
		}, "t2").start();
	}

	/**
	 * 共享变量被其他线程修改
	 */
	private static void testNoThreadLocal() {
		new Thread(() -> {
			SleepUtil.sleep(2000);
			System.out.println(person.name);
		}, "t1").start();

		new Thread(() -> {
			SleepUtil.sleep(1000);
			person.name = "lisi";
		}, "t2").start();
	}

}

class Person {
	String name = "zhangsan";
}

class SleepUtil {
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
