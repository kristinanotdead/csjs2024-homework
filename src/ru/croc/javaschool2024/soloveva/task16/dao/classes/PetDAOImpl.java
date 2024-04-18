package ru.croc.javaschool2024.soloveva.task16.dao.classes;

import ru.croc.javaschool2024.soloveva.task16.dao.interfaces.PetDAO;
import ru.croc.javaschool2024.soloveva.task16.models.Client;
import ru.croc.javaschool2024.soloveva.task16.models.Pet;

import java.sql.*;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    private static final String INSERT_INTO_PETS = "insert into pets (id, name, age) values (?, ?, ?)";
    private static final String INSERT_INTO_CLIENTS_PETS = "insert into clients_pets (client_id, pet_id) values (?, ?)";
    private static final String GET_PET_BY_ID = "select * from pets where id = ?";
    private static final String GET_LAST_PET_ID = "select id from pets order by id desc limit 1";
    private static final String UPDATE_PET = "update pets set name = ?, age = ? where id = ?";
    private static final String DELETE_PET = "delete from pets where id = ?";
    private static final String GET_CLIENT_BY_ID = "select * from clients where id = ?";
    private static final String INSERT_INTO_CLIENTS = "insert into clients (id, surname, name, number) values (?, ?, ?, ?)";

    private String url;
    private String user;
    private String password;

    public PetDAOImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Pet createPet(String name, Integer age, List<Client> clients) throws SQLException {
        Pet pet = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            PreparedStatement getIdPS = conn.prepareStatement(GET_LAST_PET_ID);

            int id = 0;

            try (ResultSet rs = getIdPS.executeQuery()) {
                if(rs.first()){
                    id = rs.getInt(1) + 1;
                } else {
                    return null;
                }
            }

            PreparedStatement petPS = conn.prepareStatement(INSERT_INTO_PETS);
            petPS.setInt(1, id);
            petPS.setString(2, name);
            petPS.setInt(3, age);

            int affectedPetRows = petPS.executeUpdate();

            if (affectedPetRows == 1) {
                pet = new Pet(id, name, age);
            } else {
                return null;
            }

            for(Client client : clients){
                PreparedStatement getClientPS = conn.prepareStatement(GET_CLIENT_BY_ID);
                getClientPS.setInt(1, client.getId());

                try (ResultSet rs = getClientPS.executeQuery()) {
                    if (!rs.first()) {
                        PreparedStatement clientPS = conn.prepareStatement(INSERT_INTO_CLIENTS);
                        clientPS.setInt(1, client.getId());
                        clientPS.setString(2, client.getSurname());
                        clientPS.setString(3, client.getName());
                        clientPS.setString(4, client.getPhoneNumber());

                        int affectedRows = clientPS.executeUpdate();
                    }

                    PreparedStatement insertPS = conn.prepareStatement(INSERT_INTO_CLIENTS_PETS);
                    insertPS.setInt(1, client.getId());
                    insertPS.setInt(2, pet.getId());

                    int affectedRows = insertPS.executeUpdate();
                }
            }

            conn.commit();
        }

        return pet;
    }

    @Override
    public Pet findPet(Integer id) throws SQLException {
        Pet pet = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement getPetPS = conn.prepareStatement(GET_PET_BY_ID);
            getPetPS.setInt(1, id);

            try (ResultSet rs = getPetPS.executeQuery()) {
                if (rs.first()) {
                    pet = new Pet(rs.getInt(1), rs.getString(2),
                            rs.getInt(3));
                }
            }
        }

        return pet;
    }

    @Override
    public Pet updatePet(Pet pet) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement updatePetPS = conn.prepareStatement(UPDATE_PET);
            updatePetPS.setString(1, pet.getName());
            updatePetPS.setInt(2, pet.getAge());
            updatePetPS.setInt(3, pet.getId());

            if (updatePetPS.executeUpdate() == 0) {
                return null;
            }
        }
        return pet;
    }
    @Override
    public void deletePet(Integer id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement deletePetPS = conn.prepareStatement(DELETE_PET);
            deletePetPS.setInt(1, id);

            deletePetPS.executeUpdate();
        }
    }
}


