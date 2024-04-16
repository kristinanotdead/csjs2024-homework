package ru.croc.javaschool2024.soloveva.task15.readers;

import ru.croc.javaschool2024.soloveva.task15.models.Client;
import ru.croc.javaschool2024.soloveva.task15.models.Pet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataReader {
    public static Map<Client, Set<Pet>> readFromFile(String filename) throws IOException, NumberFormatException {
        Map<Client, Set<Pet>> clientsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Client client = new Client(Integer.parseInt(parts[0]),parts[1],parts[2],parts[3]);
                Pet pet = new Pet(Integer.parseInt(parts[4]),parts[5],Integer.parseInt(parts[6]));

                Set<Pet> pets = clientsMap.getOrDefault(client, new HashSet<>());
                pets.add(pet);
                clientsMap.put(client, pets);
            }
        }
        return clientsMap;
    }
}
