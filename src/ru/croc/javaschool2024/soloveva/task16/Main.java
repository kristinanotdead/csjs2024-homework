package ru.croc.javaschool2024.soloveva.task16;

import ru.croc.javaschool2024.soloveva.task16.db.DbProcessor;
import ru.croc.javaschool2024.soloveva.task16.errors.IncorrectPhoneNumberException;
import ru.croc.javaschool2024.soloveva.task16.readers.DataReader;
import ru.croc.javaschool2024.soloveva.task16.dao.classes.ClientDAOImpl;
import ru.croc.javaschool2024.soloveva.task16.dao.classes.PetDAOImpl;
import ru.croc.javaschool2024.soloveva.task16.dao.interfaces.ClientDAO;
import ru.croc.javaschool2024.soloveva.task16.dao.interfaces.PetDAO;
import ru.croc.javaschool2024.soloveva.task16.models.Client;
import ru.croc.javaschool2024.soloveva.task16.models.Pet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
        public static void main(String[] args) {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e){
                System.out.println("Не установлен драйвер H2!");
                return;
            }

            DbProcessor processor = new DbProcessor("jdbc:h2:tcp://localhost/~/test", "sa", "");
            Map<Client, Set<Pet>> clientsMap;

            try {
                clientsMap = DataReader.readFromFile("src/ru/croc/javaschool2024/soloveva/task16/resources/data.csv");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            } catch (NumberFormatException e) {
                System.out.println("Непрвильный формат данных!");
                return;
            }

            try {
                processor.deleteDb();
                processor.createDb();
                processor.importData(clientsMap);

                System.out.println("Данные импортированы успешно!");
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }

            ClientDAO clientDAO = new ClientDAOImpl("jdbc:h2:tcp://localhost/~/test", "sa", "");
            PetDAO petDAO = new PetDAOImpl("jdbc:h2:tcp://localhost/~/test", "sa", "");

            Client client1 = new Client(13, "Мульцын", "Пашка", "+79634152351");
            Client client2 = new Client(14, "Печкин", "Почтальон", "+79876543210");

            try {
                clientDAO.createClient(client1);
            } catch (IncorrectPhoneNumberException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }

            try {
                clientDAO.createClient(client2);
            } catch (IncorrectPhoneNumberException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }

            try {
                int id = 13;
                Client foundClient = clientDAO.findClient(id);

                if (foundClient != null) {
                    System.out.println("Найден клиент: " + foundClient.getSurname() + " " + foundClient.getName() +
                            " " + foundClient.getPhoneNumber());
                } else {
                    System.out.printf("Клиент c id %d не найден\n", id);
                }

                id = 14;
                foundClient = clientDAO.findClient(id);

                if (foundClient != null) {
                    System.out.println("Найден клиент: " + foundClient.getSurname() + " " + foundClient.getName() +
                            " " + foundClient.getPhoneNumber());
                } else {
                    System.out.printf("Клиент c id %d не найден\n", id);
                }
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }

            Client newClient1 = new Client(15, "Бикович", "Милош", "+74561238989");
            Client newClient2 = new Client(14, "Печкин", "Велосипедист", "112");

            try {
                Client updateClient1 = clientDAO.updateClient(newClient1);

                if (updateClient1 != null) {
                    System.out.println("Данные клиента обновлены: " + updateClient1.getSurname() +
                            " " + updateClient1.getName() + " " + updateClient1.getPhoneNumber());
                } else {
                    System.out.printf("Клиент c id %d не найден\n", newClient1.getId());
                }

                Client updateClient2 = clientDAO.updateClient(newClient2);

                if (updateClient2 != null) {
                    System.out.println("Данные клиента обновлены: " + updateClient2.getSurname() +
                            " " + updateClient2.getName() + " " + updateClient2.getPhoneNumber());
                } else {
                    System.out.printf("Клиент c id %d не найден\n", newClient2.getId());
                }
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }

            try {
                int id = 14;
                clientDAO.deleteClient(id);
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }

            Pet pet1 = new Pet(1, "Котик", 2);
            Pet pet2 = new Pet(15, "Джексон", 16);

            try {
                List<String> phoneNumbers1 = clientDAO.findClientPhoneNumbersBy(pet1);

                if (phoneNumbers1 != null) {
                    System.out.printf("Номера телефонов хозяев питомца с id %d:\n", pet1.getId());
                    phoneNumbers1.forEach(System.out::println);
                } else {
                    System.out.printf("Хозяева питомца c id %d не найдены\n", pet1.getId());
                }

                List<String> phoneNumbers2 = clientDAO.findClientPhoneNumbersBy(pet2);

                if (phoneNumbers2 != null) {
                    System.out.printf("Номера телефонов хозяев питомца с id %d:\n", pet2.getId());
                    phoneNumbers2.forEach(System.out::println);
                } else {
                    System.out.printf("Хозяева питомца c id %d не найдены\n", pet2.getId());
                }
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }

            Client client3 = new Client(4, "Цветова",	"Анастасия",	"+79315412063");
            Client client4 = new Client(15, "Иванов", "Иван", "+79996249023");

            try {
                List<Pet> pets1 = clientDAO.getAllPetsOf(client3);

                if (pets1 != null) {
                    System.out.printf("У клента с id %d есть питомцы:\n", client3.getId());
                    pets1.forEach(pet -> System.out.println(pet.getName()));
                } else {
                    System.out.printf("Для клиента c id %d не найдено питомцев\n", client3.getId());
                }

                List<Pet> pets2 = clientDAO.getAllPetsOf(client4);

                if (pets2 != null) {
                    System.out.printf("У клента с id %d есть питомцы:\n", client4.getId());
                    pets2.forEach(pet -> System.out.println(pet.getName()));
                } else {
                    System.out.printf("Для клиента c id %d не найдено питомцев\n", client4.getId());
                }
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }

            try{
                List<Client> clients = List.of(client2, client3);

                Pet pet = petDAO.createPet("Лопух", 16, clients);

                if (pet != null) {
                    System.out.printf("Питомец %s успешно добавлен\n", pet.getName());
                } else {
                    System.out.println("Ошибка при добавлении питомца\n");
                }
            } catch (SQLException e){
                System.out.println("Ошибка БД: " + e.getMessage());
            }
        }
    }


