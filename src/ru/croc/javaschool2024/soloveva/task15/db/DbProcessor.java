package ru.croc.javaschool2024.soloveva.task15.db;

import ru.croc.javaschool2024.soloveva.task15.models.Client;
import ru.croc.javaschool2024.soloveva.task15.models.Pet;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DbProcessor {
    private static final String INSERT_INTO_CLIENTS = "insert into clients (id, surname, name, number) values (?, ?, ?, ?)";
    private static final String INSERT_INTO_PETS = "insert into pets (id, name, age) values (?, ?, ?)";
    private static final String INSERT_INTO_CLIENTS_PETS = "insert into clients_pets (client_id, pet_id) values (?, ?)";

    private String url;
    private String user;
    private String password;

    public DbProcessor(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void createDb() throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stat = conn.createStatement()) {

            stat.execute("create table clients(id int generated by default as identity primary key, " +
                    "surname varchar(255), name varchar(255), number varchar(255))");
            stat.execute("create table pets(id int primary key, name varchar(255), age int)");
            stat.execute("create table clients_pets(client_id int, pet_id int)");
        }
    }

    public void deleteDb() throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stat = conn.createStatement()) {

            stat.execute("drop table if exists clients");
            stat.execute("drop table if exists pets");
            stat.execute("drop table if exists clients_pets");
        }
    }
    public void importData(Map<Client, Set<Pet>> clientsMap) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Set<Pet> allPets = clientsMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());

            for(Pet pet : allPets) {
                PreparedStatement petPS = conn.prepareStatement(INSERT_INTO_PETS);
                petPS.setInt(1, pet.getId());
                petPS.setString(2, pet.getName());
                petPS.setInt(3, pet.getAge());

                int affected_pets_rows = petPS.executeUpdate();
            }

            for (Map.Entry<Client, Set<Pet>> entry: clientsMap.entrySet()){
                PreparedStatement clientPS = conn.prepareStatement(INSERT_INTO_CLIENTS);
                clientPS.setInt(1, entry.getKey().getId());
                clientPS.setString(2, entry.getKey().getSurname());
                clientPS.setString(3, entry.getKey().getName());
                clientPS.setString(4, entry.getKey().getPhoneNumber());

                int affected_clients_rows = clientPS.executeUpdate();

                for(Pet pet : entry.getValue()) {
                    PreparedStatement clientPetPS = conn.prepareStatement(INSERT_INTO_CLIENTS_PETS);
                    clientPetPS.setInt(1, entry.getKey().getId());
                    clientPetPS.setInt(2, pet.getId());

                    int affected_clients_pets_rows = clientPetPS.executeUpdate();
                }
            }
        }
    }
}
