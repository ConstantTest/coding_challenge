package cisco.java.challenge.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {
        try {
            if(args.length == 1) {
                Path filePath = Paths.get(args[0]);
                printResult(findUniqueWords(filePath));
            } else {
                System.out.println("The program is expecting one argument!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> findUniqueWords(Path filePath) throws IOException{
        Map<String, Integer> uniqueWords = new HashMap<>();

        //read file into stream
        try (Stream<String> lines = Files.lines(filePath)) {
            uniqueWords = lines
                    .flatMap( line -> Arrays.stream(line.split("(\\W\\s)|(\\W)|(\\d)")))
                    .filter(s -> !s.equalsIgnoreCase(""))
                    .map(String::toLowerCase)
                    .collect(Collectors.toMap(
                            s -> s,
                            s -> 1,
                            Integer::sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniqueWords;
    }

    private static void printResult(Map<String, Integer> words) {
        if (words.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            words.forEach((w, n) -> System.out.println(n + " " + w));;
        }
    }
}
