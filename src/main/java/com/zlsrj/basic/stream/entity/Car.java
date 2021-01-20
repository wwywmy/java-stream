package com.zlsrj.basic.stream.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Car {
	@FunctionalInterface
	public interface Supplier<T> {
		T get();
	}

	public static Car create(final Supplier<Car> supplier) {
		return supplier.get();
	}

	public static void collide(final Car car) {
		log.info("Collided " + car.toString());
	}

	public void follow(final Car another) {
		log.info("Following the " + another.toString());
	}

	public void repair() {
		log.info("Repaired " + this.toString());
	}
}
