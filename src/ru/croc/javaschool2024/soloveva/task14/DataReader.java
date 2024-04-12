package ru.croc.javaschool2024.soloveva.task14;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataReader {
    public static Map<String, Long> readFromFile(String filename) throws IOException {
        Map<String, Long> dataMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String item = parts[0].trim();
                long value = Long.parseLong(parts[1].trim());
                dataMap.put(item, value);
            }
        }

        return dataMap;
    }
}
