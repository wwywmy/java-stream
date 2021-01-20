package com.zlsrj.basic.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zlsrj.basic.stream.fi.GreetingService;
import com.zlsrj.basic.stream.fi.MathOperation;
import com.zlsrj.basic.stream.fi.MyConverter;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class LambdaExplTests {

	// Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）
	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}

	@Test
	public void mathOperationTest() {
		// lambda
		// (parameters) -> expression
		// (parameters) ->{statements; }

		// 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
		MathOperation addition = (int a, int b) -> a + b; // 类型声明
		// 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
		MathOperation subtraction = (a, b) -> a - b;// 不用类型声明
		// 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};// 大括号中的返回语句
			// 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
			// 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
		MathOperation division = (int a, int b) -> a / b;// 没有大括号及返回语句

		log.info("10 + 5 = {}", operate(10, 5, addition));
		log.info("10 - 5 = {}", operate(10, 5, subtraction));
		log.info("10 * 5 = {}", operate(10, 5, multiplication));
		log.info("10 / 5 = {}", operate(10, 5, division));
	}

	@Test
	public void greetingServiceTest() {
		// 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
		GreetingService greetService1 = message -> log.info("Hello " + message);// 不用括号
		GreetingService greetService2 = (message) -> log.info("Hello " + message);// 用括号

		greetService1.sayMessage("Baidu");
		greetService2.sayMessage("Google");
	}

	// lambda 表达式的局部变量可以不用声明为 final，
	// 但是必须不可被后面的代码修改（即隐性的具有final 的语义）
	String salutation = "Hello! ";

	@Test
	public void greetingServiceVarTest() {

		GreetingService greetService1 = message -> log.info(salutation + message);// 不用括号
		greetService1.sayMessage("Baidu");
	}

	@Test
	public void greetingServiceVarInnerTest() {
		GreetingService greetServiceInner = new GreetingService() {

			@Override
			public void sayMessage(String message) {
				log.info(salutation + message);
			}

		};

		greetServiceInner.sayMessage("Baidu");
	}

	@Test
	public void greetingServiceVarOuterTest() {
		int num = 1;
		MyConverter<Integer, String> s = (param) -> log.info(String.valueOf(param + num));
		s.convert(2);
//		num = 5;//Local variable num defined in an enclosing scope must be final or effectively final
	}
}
