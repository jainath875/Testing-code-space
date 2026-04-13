import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FindTopThreeFrequentWords {

    public static void main(String[] args) {
        String str = "java is great, java is object oriented, java is fun!";

       Map<String, Long> map = Arrays.stream(
            str.toLowerCase()
                .replaceAll("[^a-z\\s]", "")
                .split(" ")            
        ).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(map);

        List<Map.Entry<String, Long>> sorted = map.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .toList();

        System.out.println(sorted);

        List<Long> topFrequency = sorted.stream()
            .map(Map.Entry::getValue)
            .distinct()
            .limit(3)
            .toList();

        System.out.println(topFrequency);

        List<Map.Entry<String, Long>> list = sorted.stream()
                .filter(e -> topFrequency.contains(e.getValue()))
                .toList();

        System.out.println(list);

    }
    
}