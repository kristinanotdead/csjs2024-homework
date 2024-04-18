package ru.croc.javaschool2024.soloveva.task16.dao.interfaces;

import ru.croc.javaschool2024.soloveva.task16.errors.IncorrectPhoneNumberException;
import ru.croc.javaschool2024.soloveva.task16.models.Client;
import ru.croc.javaschool2024.soloveva.task16.models.Pet;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    Client createClient(Client client) throws SQLException, IncorrectPhoneNumberException;
    Client findClient(Integer id) throws SQLException;
    Client updateClient(Client client) throws SQLException;
    void deleteClient(Integer id) throws SQLException;
    List<String> findClientPhoneNumbersBy(Pet pet) throws SQLException;
    List<Pet> getAllPetsOf(Client client) throws SQLException;
}
