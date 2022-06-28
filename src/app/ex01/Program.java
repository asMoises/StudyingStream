package app.ex01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);

		Stream<Integer> st1 = list.stream().map(x -> x * 10);
		System.out.println("Numbers: " + Arrays.toString(st1.toArray()));

		Stream<String> st2 = Stream.of("Maria", "Alex", "Bob");
		System.out.println(Arrays.toString(st2.toArray()));

		// this way
		Stream<Integer> str3 = Stream.iterate(0, x -> x + 2);
		String t = Arrays.toString(str3.limit(22).toArray());
		System.out.println(t);

		// or... this way
		System.out.println(Arrays.toString(Stream.iterate(0, x -> x + 2).limit(22).toArray()));

		// fibo
		System.out.println(Arrays.toString(Stream.iterate(new Long[] { 0L, 1L }, p -> new Long[] { p[1], p[0] + p[1] })
				.map(p -> p[0]).limit(10).toArray()));

		// sum all elements from list
		int sum = list.stream().reduce(0, (x, y) -> x + y);
		System.out.println("Sum: " + sum);

		// print all from the list after predicate and map
		System.out.println(
				"as a Stream: " + list.stream().filter(x -> x % 2 == 0).map(x -> x * 10).collect(Collectors.toList()));
		System.out.println("as a list: " + Arrays.toString(
				list.stream().filter(x -> x % 2 == 0).map(x -> x * 10).collect(Collectors.toList()).toArray()));
	}

}
