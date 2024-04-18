package ru.croc.javaschool2024.soloveva.task16.dao.classes;

import org.h2.command.dml.Update;
import ru.croc.javaschool2024.soloveva.task16.dao.interfaces.ClientDAO;
import ru.croc.javaschool2024.soloveva.task16.errors.IncorrectPhoneNumberException;
import ru.croc.javaschool2024.soloveva.task16.models.Client;
import ru.croc.javaschool2024.soloveva.task16.models.Pet;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ClientDAOImpl implements ClientDAO {
    private static final String INSERT_INTO_CLIENTS = "insert into clients (id, surname, name, number) values (?, ?, ?, ?)";
    private static final String GET_CLIENT_BY_PHONE_NUMBER = "select * from clients where number = ?";
    private static final String GET_CLIENT_BY_ID = "select * from clients where id = ?";
    private static final String UPDATE_CLIENT = "update clients set surname = ?, name = ?, number = ? where id = ?";
    private static final String DELETE_CLIENT = "delete from clients where id = ?";
    private static final String GET_PHONE_NUMBERS_BY_PET_ID =
            "select number from clients inner join clients_pets on clients.id = clients_pets.client_id " +
                    "inner join pets on clients_pets.pet_id = pets.id where pets.id = ?";
    private static final String GET_PETS_BY_CLIENT_ID =
            "select * from pets inner join clients_pets on pets.id = clients_pets.pet_id " +
                    "inner join clients on clients_pets.client_id = clients.id where clients.id = ?";

    private String url;
    private String user;
    private String password;

    public ClientDAOImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Client createClient(Client client) throws SQLException, IncorrectPhoneNumberException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement getClientPS = conn.prepareStatement(GET_CLIENT_BY_PHONE_NUMBER);
            getClientPS.setString(1, client.getPhoneNumber());

            try (ResultSet rs = getClientPS.executeQuery()) {
                if (rs.first()) {
                    throw new IncorrectPhoneNumberException(client.getPhoneNumber());
                }
            }

            PreparedStatement clientPS = conn.prepareStatement(INSERT_INTO_CLIENTS);
            clientPS.setInt(1, client.getId());
            clientPS.setString(2, client.getSurname());
            clientPS.setString(3, client.getName());
            clientPS.setString(4, client.getPhoneNumber());

            int affectedRows = clientPS.executeUpdate();
        }

        return client;
    }

    @Override
    public Client findClient(Integer id) throws SQLException {
        Client client = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement getClientPS = conn.prepareStatement(GET_CLIENT_BY_ID);
            getClientPS.setInt(1, id);

            try (ResultSet rs = getClientPS.executeQuery()) {
                if (rs.first()) {
                    client = new Client(rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getString(4));
                }
            }
        }
        return client;
    }

    @Override
    public Client updateClient(Client client) throws SQLException {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement updateClientPS = conn.prepareStatement(UPDATE_CLIENT);
            updateClientPS.setString(1, client.getSurname());
            updateClientPS.setString(2, client.getName());
            updateClientPS.setString(3, client.getPhoneNumber());
            updateClientPS.setInt(4, client.getId());

            if (updateClientPS.executeUpdate() == 0) {
                return null;
            }
        }

        return client;
    }

    @Override
    public void deleteClient(Integer id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement deleteClientPS = conn.prepareStatement(DELETE_CLIENT);
            deleteClientPS.setInt(1, id);

            deleteClientPS.executeUpdate();
        }
    }

    @Override
    public List<String> findClientPhoneNumbersBy(Pet pet) throws SQLException {
        List<String> numbers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement getNumbersPS = conn.prepareStatement(GET_PHONE_NUMBERS_BY_PET_ID);
            getNumbersPS.setInt(1, pet.getId());

            try (ResultSet rs = getNumbersPS.executeQuery()) {
                while(rs.next()){
                    numbers.add(rs.getString(1));
                }
            }
        }

        return numbers.isEmpty() ? null : numbers;
    }

    @Override
    public List<Pet> getAllPetsOf(Client client) throws SQLException {
        List<Pet> pets = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement getPetsPS = conn.prepareStatement(GET_PETS_BY_CLIENT_ID);
            getPetsPS.setInt(1, client.getId());

            try (ResultSet rs = getPetsPS.executeQuery()) {
                while(rs.next()){
                    pets.add(new Pet(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
            }
        }

        return pets.isEmpty() ? null : pets;
    }
}


