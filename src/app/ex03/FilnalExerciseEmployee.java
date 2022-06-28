package app.ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.ex03.Employee;

public class FilnalExerciseEmployee {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		String path = "/home/moiss/Documentos/Fullstack_Java/StudyingStream/src/files/employees.txt";

		try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
			String line = buffer.readLine();
			List<Employee> listEmp = new ArrayList<Employee>();
			while (line != null) {
				String[] fields = line.split(",");
				listEmp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = buffer.readLine();
			}
			for (Employee e : listEmp) {
				System.out.println(e);
			}
			System.out.print("\nEnter reference salary: ");
			Double salary = sc.nextDouble();
			System.out.println("e-Mail of people whose salary is more than: " + salary);

			// create a structure to compare
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> names = listEmp.stream() // convert list into a stream
					.filter(x -> x.getSalary() > salary) // filter price < avg into a new stream
					.map(x -> x.getEmail()) // get only the names this new filtered stream
					.sorted(comp) // sorting by reversed comparator
					.collect(Collectors.toList()); // convert into a list again

			names.forEach(System.out::println);

			Double sum = listEmp.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0,
					(x, y) -> x + y);
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

		} catch (IOException e) {

		}

		sc.close();
	}

}
