package com.zlsrj.basic.stream;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zlsrj.basic.stream.fi.MyHandler;
import com.zlsrj.basic.stream.fi.MyInterface;
import com.zlsrj.basic.stream.fun.FunTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class LambdaTests {

	@Test
	public void threadTest() {
		Thread t1 = new Thread(new Runnable() { // @FunctionalInterface //public abstract void run();

			@Override
			public void run() {
				log.info(Thread.currentThread().getName() + " Hello");

			}
		});
		t1.start();
	}

	@Test
	public void lambdaThreadTest() {
		// 函数式接口
		// 有且只有一个抽象方法的接口被成为函数式接口
		// 函数式接口可以显式的被@FunctionalInterface所表示
		Thread t1 = new Thread(() -> log.info(Thread.currentThread().getName() + " Hello"));
		t1.start();
	}

	@Test
	public void myInterfaceTest() {
		MyInterface myInterface = (a, b) -> System.out.println(a + b);
		myInterface.method(1, 2);
	}

	@Test
	public void myHandlerTest() {
		// 在传统的java面向对象编程中，我们在方法内只可以传递值或对象引用，无法传递方法。
		MyHandler myHandler = msg -> System.out.println(msg);
		myHandler.handleMessage("hello lambda");
	}

	@Test
	public void funTestTest() {
		FunTest funTest = new FunTest();
		int result = funTest.compute(1, v -> v + 10);// 方法以参数形式传入
		log.info("result={}", result);

		result = funTest.compute(2, v -> v * 3, v -> v + 8);// compose (2+8)*3=30
		log.info("result={}", result);

		result = funTest.computeAndThen(2, v -> v * 3, v -> v + 8);// andThen 2*3+8=14
		log.info("result={}", result);

	}

	@Test
	public void consumerTest() {
		Consumer<String> consumer = s -> System.out.println("hello " + s);
		consumer.accept("mike");// 接收指定参数类型，无返回值，重点在于内部消费
	}

	@Test
	public void consumerAndThenTest() {
		Consumer<String> consumer = s -> System.out.println("hello " + s);
		Consumer<String> consumer1 = s -> System.out.println("nice to meet you  " + s);
		consumer.andThen(consumer1).accept("mike");// hello mike //nice to meet you mike
	}

	@Test
	public void predicateTest() {
		Predicate<Integer> predicate = b -> b % 2 == 0; // Predicate中的test方法，传入指定类型参数，返回布尔类型的结果，用于判断，断言
		System.out.println(predicate.test(3));
		System.out.println(predicate.test(4));
	}

	@Test
	public void supplierTest() {
		Supplier<String> sup = () -> "hello world";//Supplier意为供应，只有一个方法get，不接收任何参数，只返回指定类型结果
		System.out.println(sup.get());//hello world
	}

}
