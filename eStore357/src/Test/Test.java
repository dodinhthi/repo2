package Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public Test() {
		System.out.println("constructor");
	}

	public static void check() {
		System.out.println("check");
	}

	public static void main(String[] args) {
		// String s4 = "2cn";
		// System.out.println("s4=" + s4);
		// Bắt đầu là 2
		// Tiếp theo x hoặc y hoặc z
		// Tiếp theo bất kỳ 1 hoặc nhiểu lần.
		// Kết hợp các quy tắc: [abc] , . , +
		// true
		// boolean match = s4.matches("2([a]|[b]).+");
		// System.out.println(match);

		// String regex= ".xx.";
		// // Tạo đối tượng Pattern thông qua method tĩnh.
		// Pattern pattern = Pattern.compile(regex);
		// // Lấy ra đối tượng Matcher
		// Matcher matcher = pattern.matcher("MxxY");
		//
		// boolean match = matcher.matches();
		//
		// System.out.println("Match "+ match);

		// final String TEXT = "This \t is a \t\t\t String";
		//
		// // Khoảng trắng xuất hiện 1 hoặc nhiều lần.
		// String regex = "\\s+";
		//
		// Pattern pattern = Pattern.compile(regex);
		//
		// Matcher matcher = pattern.matcher(TEXT);
		//
		// int i = 0;
		// while (matcher.find()) {
		// System.out.print("start" + i + " = " + matcher.start());
		// System.out.print(" end" + i + " = " + matcher.end());
		// System.out.println(" group" + i + " = " + matcher.group());
		// i++;
		// }

		String s2 = "m";
		System.out.println("s2=" + s2);
		// Kiểm tra toàn bộ s2
		// Bắt đầu bởi m
		// Quy tắc ^
		// true
		boolean match = s2.matches("m.+");
		System.out.println("-Match ^m " + match);
	}

}
