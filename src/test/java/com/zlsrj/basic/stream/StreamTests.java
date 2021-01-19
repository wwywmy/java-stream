package com.zlsrj.basic.stream;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zlsrj.basic.stream.entity.Goods;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class StreamTests {

	@Test
	public void starter() {
		Goods g1 = new Goods(1, "BMW", 100, 200, Color.BLACK);
		Goods g2 = new Goods(1, "Audi", 200, 300, Color.BLUE);
		Goods g3 = new Goods(1, "BenZ", 180, 300, Color.BLACK);

		List<Goods> list = new ArrayList<Goods>();
		list.add(g1);
		list.add(g2);
		list.add(g3);

		int weights = list.parallelStream()// 为集合创建并行流
				.filter(b -> b.getColor() == Color.BLACK)//
				.sorted((x, y) -> x.getWeight() - y.getWeight())//
				.mapToInt(Goods::getWeight)//
				.sum()//
		;

		log.info("weights = {}", weights);

		weights = list.stream()// 为集合创建串行流
				.filter(b -> b.getColor() == Color.BLACK)//
				.sorted((x, y) -> x.getWeight() - y.getWeight())//
				.mapToInt(Goods::getWeight)//
				.sum()//
		;

		log.info("weights = {}", weights);

	}

	@Test
	public void streamTest() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		List<String> filtered = strings.stream()//
				.filter(string -> !string.isEmpty())//
				.collect(Collectors.toList());

		filtered.forEach(log::info);
	}

	@Test
	public void forEachTest() {
		Random random = new Random();
		IntStream stream = random.ints().limit(10);
		stream.forEach(System.out::println); // 迭代流中的每个数据
	}

	@Test
	public void mapTest() {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			numbers.add(RandomUtil.randomInt(1, 10));
		}

		numbers.stream().forEach(System.out::println);

		List<Integer> squaresList = numbers.stream()//
				.map(i -> i * i)// map 方法用于映射每个元素到对应的结果
				.distinct()//
				.collect(Collectors.toList())//
		;
		squaresList.stream().forEach(System.out::println);
	}

	@Test
	public void filterTest() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		long count = strings.stream()//
				.filter(string -> string.isEmpty())// filter 方法用于通过设置的条件过滤出元素
				.count()//
		;
		log.info("count = {}", count);
	}

	@Test
	public void limitTest() {
		Random random = new Random();
		random.ints()//
				.limit(10)// limit 方法用于获取指定数量的流
				.forEach(System.out::println)//
		;
	}

	@Test
	public void sortedTest() {
		Random random = new Random();
		random.ints()//
				.limit(10)//
				.sorted()// sorted 方法用于对流进行排序
				.forEach(System.out::println)//
		;
	}

	@Test
	public void parallelStreamTest() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		// 获取空字符串的数量
		long count = strings.parallelStream()// parallelStream 是流并行处理程序的代替方法
				.filter(string -> string.isEmpty())//
				.count()//
		;
		log.info("count = {}", count);
	}

	@Test
	public void collectorsTest() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		List<String> filtered = strings.stream()//
				.filter(string -> !string.isEmpty())//
				.collect(Collectors.toList())// 将流转换成集合
		;

		System.out.println("筛选列表: " + filtered);
		String mergedString = strings.stream()//
				.filter(string -> !string.isEmpty())//
				.collect(Collectors.joining(", "))// 将流转换聚合元素
		;
		System.out.println("合并字符串: " + mergedString);
	}

	@Test
	public void statisticsTest() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

		IntSummaryStatistics stats = numbers.stream()//
				.mapToInt((x) -> x)//
				.summaryStatistics()//产生统计结果的收集器
		;

		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("所有数之和 : " + stats.getSum());
		System.out.println("平均数 : " + stats.getAverage());
	}

}
