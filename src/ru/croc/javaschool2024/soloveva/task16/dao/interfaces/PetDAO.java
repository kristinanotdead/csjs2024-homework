package ru.croc.javaschool2024.soloveva.task16.dao.interfaces;

import ru.croc.javaschool2024.soloveva.task16.models.Client;
import ru.croc.javaschool2024.soloveva.task16.models.Pet;

import java.sql.SQLException;
import java.util.List;

public interface PetDAO {
    Pet createPet(String name, Integer age, List<Client> clients) throws SQLException;
    Pet findPet(Integer id) throws SQLException;
    Pet updatePet(Pet pet) throws SQLException;
    void deletePet(Integer id) throws SQLException;
}
