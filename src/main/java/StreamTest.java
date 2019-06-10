
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> streamOfArrayPart =
                Arrays.stream(arr, 1, 3); // 1~2 요소 [b, c]

        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream1 = list.stream();
        Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림

        Stream<String> buildStream = Stream.<String>builder().add("Eric").add("Elena").add("Java").build();
        Stream<Integer> integerStream = Stream.<Integer>builder().add(1).add(2).add(3).build();

        Stream<String> limit = Stream.generate(() -> "gen").limit(5);

        limit.forEach(entity->System.out.println(entity.toUpperCase()));

        Stream<Integer> limit1 = Stream.iterate(30, n -> n + 2).limit(5);
        limit1.forEach(entity->System.out.println(entity));

        IntStream intStream = IntStream.range(1, 5);
//        intStream.forEach(entity->System.out.println(entity));

//        Stream<Integer> boxedIntStream =
        IntStream.range(1, 5).boxed().forEach(System.out::println);


        DoubleStream doubleStream = new Random().doubles(3);


        List<String> names = Arrays.asList("Eric", "Elena", "Java");

        Stream<String> streamQ =
                names.stream().filter(name -> name.contains("a"));

        names.stream().map(String::toUpperCase);

        List<Product> products = Arrays.asList(Product.builder().name("hi").amount(10).build(), Product.builder().name("de").amount(20).build());

        Stream<Integer> integerStream1 = products.stream().map(Product::getAmount);

        List<List<String>> listArray =
                Arrays.asList(Arrays.asList("a"),
                        Arrays.asList("b"));
        List<String> collect = listArray.stream().flatMap(Collection::stream).collect(Collectors.toList());

        List<Student> students = Arrays.asList(
                Student.builder().name("a").kor(10).eng(20).math(30).build()
                ,Student.builder().name("b").kor(11).eng(24).math(34).build()
                ,Student.builder().name("c").kor(12).eng(26).math(37).build()
                ,Student.builder().name("d").kor(13).eng(27).math(39).build()
        );

        students.stream().flatMapToInt(student -> IntStream.of(student.getEng(),student.getKor(),student.getMath())).average().ifPresent(avg ->
                System.out.println(Math.round(avg * 10)/10.0));


        List<String> lang =
                Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

        lang.stream()
                .sorted()
                .collect(Collectors.toList());
// [Go, Groovy, Java, Python, Scala, Swift]

        lang.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
// [Swift, Scala, Python, Java, Groovy, Go]

        lang.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
// [Go, Java, Scala, Swift, Groovy, Python]

        lang.stream()
                .sorted((s1, s2) -> s2.length() - s1.length())
//                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
// [Groovy, Python, Scala, Swift, Java, Go]

        int sum = IntStream.of(1, 3, 5, 7, 9)
                .peek(System.out::println)
                .sum();
    }


    public Stream<String> streamOf(List<String> list) {
        return list == null || list.isEmpty()
                ? Stream.empty()
                : list.stream();
    }
}

