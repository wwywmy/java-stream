package com.zlsrj.basic.stream.fun;

import java.util.function.Function;

public class FunTest {
	public int compute(int a, Function<Integer, Integer> function) {
		return function.apply(a);
	}

	public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
		return function1.compose(function2).apply(a);
	}

	public int computeAndThen(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
		return function1.andThen(function2).apply(a);
	}
}
