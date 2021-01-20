package com.zlsrj.basic.stream;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zlsrj.basic.stream.entity.Car;

@SpringBootTest
public class CarServiceTests {

	@Test
	public void mainTest() {
		// 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
		Car car = Car.create(Car::new);
		Car car1 = Car.create(Car::new);
		Car car2 = Car.create(Car::new);
		Car car3 = new Car();
		List<Car> cars = Arrays.asList(car, car1, car2, car3);

		// 静态方法引用：它的语法是Class::static_method，实例如下：
		cars.forEach(Car::collide);
		cars.forEach(Car::repair);
		final Car police = Car.create(Car::new);
		cars.forEach(police::follow);
	}
}
