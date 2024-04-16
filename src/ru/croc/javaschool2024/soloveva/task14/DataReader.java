package ru.croc.javaschool2024.soloveva.task14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReader {
    public static Map<String, Long> readFromFile(String filename) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            return lines.map(line -> line.split(","))
                    .collect(Collectors.toMap(
                            parts -> parts[0].trim(),
                            parts -> Long.parseLong(parts[1].trim())
                    ));
        }
    }
}