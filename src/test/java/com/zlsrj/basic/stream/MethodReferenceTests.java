package com.zlsrj.basic.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MethodReferenceTests {

	List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
	List<Integer> nums = Arrays.asList(1, 2, 4, 10, 20, 30, 40);

	@Test
	public void acceptTest() {
		strings.forEach(new Consumer<String>() {
			public void accept(String t) {
				log.info(t);
			};
		});
	}

	@Test
	public void acceptLambdaTest() {
		strings.forEach(t -> log.info(t));
	}

	@Test
	public void acceptLambdaMethodReferenceTest() {
		strings.forEach(log::info);// objectName::instanceMethod
	}

	@Test
	public void lambdaTest() {
		List<BigDecimal> decimals = nums.stream().map(i -> new BigDecimal(i)).collect(Collectors.toList());
		
		decimals.stream().map(String::valueOf).forEach(log::info);
	}
	
	@Test
	public void acceptLambdaConstructReferenceTest() {
		List<BigDecimal> decimals = nums.stream()//
				.map(BigDecimal::new)//
				.collect(Collectors.toList())//
				;
		
		decimals.stream().map(String::valueOf).forEach(log::info);
	}
}
