/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Florian
 */
public class JavaStreamsTester {
    
    public void run(){
    List<String> stringList = Arrays.asList("", "0", "01", "012", "0123", "01234", "", "0", "01", "012", "0123", "01234", "", "0", "01", "012", "0123", "01234");
        List<Integer> intList = Arrays.asList(0,1,2,3,4,5,6,7,8,9);

        System.out.print("Test getCountEmptyString: " + getCountEmptyString(stringList));
        System.out.println("\tExpected: " + "3");
        System.out.print("Test getCountLength3: " + getCountLength3(stringList));
        System.out.println("\tExpected: " + "3");
        System.out.print("Test deleteEmptyStrings: " + deleteEmptyStrings(stringList));
        System.out.println("\tExpected: " + "[0, 01, 012, 0123, 01234, 0, 01, 012, 0123, 01234, 0, 01, 012, 0123, 01234]");
        System.out.print("Test getMergedString: " + getMergedString(stringList, ":"));
        System.out.println("\tExpected: " +":0:01:012:0123:01234::0:01:012:0123:01234::0:01:012:0123:01234");
        System.out.print("Test getSquares: " + getSquares(intList));
        System.out.println("\tExpected: " +"[0, 1, 4, 9, 16, 25, 36, 49, 64, 81]");
        System.out.print("Test getMax: " + getMax(intList));
        System.out.println("\tExpected: " + "9");
        System.out.print("Test getMin: " + getMin(intList));
        System.out.println("\tExpected: " + "0");
        System.out.print("Test getSum: " + getSum(intList));
        System.out.println("\tExpected: " + "45");
        System.out.print("Test getAverage: " + getAverage(intList));
        System.out.println("\tExpected: " + "4");
    }
    
    
    private static int getCountEmptyString(List<String> strings) {
        return (int) strings.stream()
                .filter(x -> x.isEmpty())
                .count();
    }

    private static int getCountLength3(List<String> strings) {
        return (int) strings.stream()
                .filter(x -> x.length() == 3)
                .count();
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream()
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

    }

    private static String getMergedString(List<String> strings, String seperator) {
        return strings.stream()
                .collect(Collectors.joining(seperator));
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        Function<Integer, Integer> square = x -> x * x;
        return numbers.stream()
                .map(square)
                .collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(x -> x)
                .max().getAsInt();
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(x -> x)
                .min().getAsInt();
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(x -> x)
                .sum();
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) numbers.stream()
                .mapToInt(x -> x)
                .average().getAsDouble();
    }

}
