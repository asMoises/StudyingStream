package app.ex02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import entities.ex02.Product;

public class SolvedCase {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		String path = "/home/moiss/Documentos/Fullstack_Java/StudyingStream/src/files/products.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Product> list = new ArrayList<Product>();
			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			System.out.println("Full path: " + path);

			// Calculating the average price using map and reduce
			Double avg = list.stream().map(x -> x.getPrice()).reduce(0.0, (x, y) -> x + y) / list.size();
			System.out.println("Average price: " + String.format("%.2f", avg));

			// sorting names
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> names = list.stream() // convert list into a stream
					.filter(x -> x.getPrice() < avg) // filter price < avg into a new stream
					.map(x -> x.getName()) // get only the names this new filtered stream
					.sorted(comp.reversed()) // sorting by reversed comparator
					.collect(Collectors.toList()); // convert into a list again

			names.forEach(System.out::println);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
