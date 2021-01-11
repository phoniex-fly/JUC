package Study.JUC.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
	public static void main(String[] args) {

		normalReference();
		softReference();
		weakReference();
		phantomReference();
	}

	/**
	 * 虚引用：虚引用对象回收时，会将对象放入ReferenceQueue中
	 * 
	 * 适用场景：管理堆外内存
	 */
	private static void phantomReference() {
		ReferenceQueue<Integer> queue = new ReferenceQueue<>();
		PhantomReference<Integer> phantom = new PhantomReference<Integer>(100, queue);
		System.out.println(phantom.get());// 永远获取不到这个值

	}

	/**
	 * 弱引用只要gc就会被回收
	 * 
	 * 适用场景：解决内存溢出
	 */
	private static void weakReference() {
		WeakReference<Integer> weak = new WeakReference<Integer>(100);
		System.out.println(weak.get());// 可以输出值
		System.gc();
		System.out.println(weak.get());// 不以输出值
	}

	/**
	 * 软引用对象默认不会被gc,空间不够用时，则会回收软引用
	 * 
	 * 适用场景：缓存
	 */
	private static void softReference() {
		SoftReference<Integer> soft = new SoftReference<>(100);
		System.out.println(soft.get());// 可以输出值
		System.gc();
		System.out.println(soft.get());// 可以输出值
		// 新创建足够多的对象导致内存不够用
		System.out.println(soft.get());// 不可以输出值
	}

	/**
	 * 强引用对象默认不会被gc,空间不够用时，也不会被回收，会导致内存溢出
	 */
	private static void normalReference() {
		Object o = new Object();
		o = null;// 设置为空可使得回收
	}

}
